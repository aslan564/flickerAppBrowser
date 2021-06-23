package aslan.aslanov.flickrappbrowser.local

import androidx.lifecycle.LiveData
import androidx.room.*
import aslan.aslanov.flickrappbrowser.model.entity.ImageLocal

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertImage(imageLocal: ImageLocal)

    @Query("SELECT * FROM image_flicker")
    suspend fun getImageList(): List<ImageLocal>

    @Query("SELECT * FROM image_flicker WHERE imageLocalId =:imageLocalId")
    suspend fun getImage(imageLocalId: String): ImageLocal

    @Delete
    suspend fun deleteImage(imageLocal: ImageLocal)
}

