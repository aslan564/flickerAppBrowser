  package aslan.aslanov.flickrappbrowser.viewModel.imageList

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import aslan.aslanov.flickrappbrowser.local.ImageDatabase
import aslan.aslanov.flickrappbrowser.model.entity.ImageLocal
import aslan.aslanov.flickrappbrowser.network.StatusNetwork
import aslan.aslanov.flickrappbrowser.repository.FlickerRepository
import aslan.aslanov.flickrappbrowser.util.toImageLocalList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "FlickrViewModel"

class FlickrViewModel(application: Application) : AndroidViewModel(application) {
    private val database = ImageDatabase(application)
    private val repository = FlickerRepository(database)
    private var _imageFlicker = MutableLiveData<List<ImageLocal>>()
    val imageFlicker: LiveData<List<ImageLocal>>
        get() = _imageFlicker

    private var _imageFlickerStatus = MutableLiveData<StatusNetwork>()
    val status: LiveData<StatusNetwork>
        get() = _imageFlickerStatus


    fun getAllSavedPhotos() = viewModelScope.launch {
        _imageFlicker.value = repository.getImageFromDatabase()
        _imageFlickerStatus.postValue(StatusNetwork.SUCCESS)
    }

    fun getAllPhotosFromApi(query: String) = viewModelScope.launch {
        _imageFlickerStatus.postValue(StatusNetwork.LOADING)
        val images = repository.fetchPhotoList(query)

        when (images.status) {
            StatusNetwork.SUCCESS -> {
                Log.d(TAG, "getAllPhotos: ${images.data}")
                val convertList = withContext(Dispatchers.Default) {
                    _imageFlickerStatus.postValue(StatusNetwork.SUCCESS)
                    return@withContext images.data!!.items!!.toImageLocalList()
                }
                _imageFlicker.value = convertList

            }
            StatusNetwork.LOADING -> {
                Log.d(TAG, "getAllPhotos: ${images.status}")
                _imageFlickerStatus.postValue(StatusNetwork.LOADING)
            }
            StatusNetwork.ERROR -> {
                Log.d(TAG, "getAllPhotos: ${images.status}")
                _imageFlickerStatus.postValue(StatusNetwork.ERROR)
            }
        }
    }
}