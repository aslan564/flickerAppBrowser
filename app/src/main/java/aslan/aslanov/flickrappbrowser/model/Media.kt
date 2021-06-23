package aslan.aslanov.flickrappbrowser.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Media(
    @Json(name = "m")
    val m: String?
):Parcelable