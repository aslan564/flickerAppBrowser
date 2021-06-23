package aslan.aslanov.flickrappbrowser.model.entity


import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.*
import aslan.aslanov.flickrappbrowser.model.Media
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "image_flicker")
data class ImageLocal(
    @PrimaryKey(autoGenerate = false)
    var imageLocalId: String,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "author_id") val authorId: String?,
    @ColumnInfo(name = "date_taken") val dateTaken: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "link") val link: String?,
    @Embedded(prefix = "media_") val media: Media?,
    @ColumnInfo(name = "published") val published: String?,
    @ColumnInfo(name = "tags") val tags: String?,
    @ColumnInfo(name = "title") val title: String?
) : Parcelable

class ImageFlickerDiffer : DiffUtil.ItemCallback<ImageLocal>() {
    override fun areItemsTheSame(oldItem: ImageLocal, newItem: ImageLocal): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ImageLocal, newItem: ImageLocal): Boolean {
        return oldItem.author == newItem.author
    }

}