package aslan.aslanov.flickrappbrowser.repository

import androidx.lifecycle.LiveData
import aslan.aslanov.flickrappbrowser.local.ImageDatabase
import aslan.aslanov.flickrappbrowser.model.FlickrResponse
import aslan.aslanov.flickrappbrowser.model.entity.ImageLocal
import aslan.aslanov.flickrappbrowser.network.NetworkResult
import aslan.aslanov.flickrappbrowser.network.RetrofitClient
import java.lang.Exception

class FlickerRepository(private val database: ImageDatabase) {

    private val service = RetrofitClient.flickerService

    suspend fun fetchPhotoList(tags: String): NetworkResult<FlickrResponse> {
        return try {
            val response = service.fetchFlickerPhoto("json", tags, 1)
            if (response.isSuccessful) {
                val data = response.body()
                data?.let {
                    return@let NetworkResult.success(it)
                } ?: NetworkResult.error(response.message() ?: "no data from response", null)
            } else {
                NetworkResult.error(response.message() ?: "no data from response", null)
            }
        } catch (ex: Exception) {
            NetworkResult.error(ex.localizedMessage ?: "no data from catch", null)
        }
    }

    suspend fun saveDatabase(imageLocal: ImageLocal) {
        database.getImageDao().upsertImage(imageLocal)
    }

    suspend fun getImage(id: String): ImageLocal {
        return database.getImageDao().getImage(id)
    }

    suspend fun delete(imageLocal: ImageLocal) {
        database.getImageDao().deleteImage(imageLocal)
    }

    suspend fun getImageFromDatabase(): List<ImageLocal> {
        return database.getImageDao().getImageList()
    }
}