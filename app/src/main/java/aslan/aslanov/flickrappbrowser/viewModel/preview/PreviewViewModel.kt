package aslan.aslanov.flickrappbrowser.viewModel.preview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import aslan.aslanov.flickrappbrowser.local.ImageDatabase
import aslan.aslanov.flickrappbrowser.model.entity.ImageLocal
import aslan.aslanov.flickrappbrowser.repository.FlickerRepository
import kotlinx.coroutines.launch

class PreviewViewModel(application: Application) : AndroidViewModel(application) {
    private val database = ImageDatabase(application)
    private val repository = FlickerRepository(database)

    private var _imageMutableLive = MutableLiveData<ImageLocal>()
    val imageLiveData: LiveData<ImageLocal>
        get() = _imageMutableLive

    fun saveImageToDatabase(imageLocal: ImageLocal) = viewModelScope.launch {
        repository.saveDatabase(imageLocal)
    }

    fun getImage(id: String) = viewModelScope.launch {
        _imageMutableLive.postValue(repository.getImage(id))
    }

    fun deleteImage(imageLocal: ImageLocal)=viewModelScope.launch {
        repository.delete(imageLocal)
    }

}