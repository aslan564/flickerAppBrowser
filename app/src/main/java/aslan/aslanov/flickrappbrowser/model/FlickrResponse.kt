package aslan.aslanov.flickrappbrowser.model


import com.squareup.moshi.Json

data class FlickrResponse(
    @Json(name = "description")
    val description: String?,
    @Json(name = "generator")
    val generator: String?,
    @Json(name = "items")
    val items: List<ImageFlicker>?,
    @Json(name = "link")
    val link: String?,
    @Json(name = "modified")
    val modified: String?,
    @Json(name = "title")
    val title: String?
)