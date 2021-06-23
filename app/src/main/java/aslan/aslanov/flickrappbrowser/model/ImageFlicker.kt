package aslan.aslanov.flickrappbrowser.model


import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.Json

data class ImageFlicker(
    @Json(name = "author")
    val author: String?,
    @Json(name = "author_id")
    val authorId: String?,
    @Json(name = "date_taken")
    val dateTaken: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "link")
    val link: String?,
    @Json(name = "media")
    val media: Media?,
    @Json(name = "published")
    val published: String?,
    @Json(name = "tags")
    val tags: String?,
    @Json(name = "title")
    val title: String?
)
