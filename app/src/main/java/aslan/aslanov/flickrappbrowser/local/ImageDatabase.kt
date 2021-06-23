package aslan.aslanov.flickrappbrowser.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import aslan.aslanov.flickrappbrowser.model.entity.ImageLocal

@Database(entities = [ImageLocal::class], version = 1, exportSchema = false)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun getImageDao(): ImageDao

    companion object {

        @Volatile
        private var INSTANCE: ImageDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ImageDatabase::class.java,
            "image_local_flicker"
        ).build()
    }
}