package aslan.aslanov.flickrappbrowser.util

import aslan.aslanov.flickrappbrowser.model.ImageFlicker
import aslan.aslanov.flickrappbrowser.model.entity.ImageLocal
import java.net.URI
import java.util.*

object NetworkConstant {
    // https://www.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1
    //https://www.flickr.com/photos/bees/albums/72157632104094494
    const val BASE_URL = "https://www.flickr.com/"
}

fun List<ImageFlicker>.toImageLocalList(): List<ImageLocal> = map {
    ImageLocal(
        author = it.author,
        imageLocalId =UUID.randomUUID().toString(),
        authorId = it.authorId,
        dateTaken = it.dateTaken,
        description = it.description,
        link = it.link,
        media = it.media,
        published = it.published,
        tags = it.tags,
        title = it.title
    )
}