package aslan.aslanov.flickrappbrowser.network.services

import aslan.aslanov.flickrappbrowser.model.FlickrResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerService {
    // https://www.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1

    //https://www.flickr.com/photos/12037949754@N01/albums/

    //https://www.flickr.com/photos/bees/albums/72157632104094494

//tags=android&
    @GET("services/feeds/photos_public.gne")
    suspend fun fetchFlickerPhoto(
        @Query("format") format: String = "json",
        @Query("tags") tags: String = "all",
        @Query("nojsoncallback") callback: Int = 1
    ): Response<FlickrResponse>

}