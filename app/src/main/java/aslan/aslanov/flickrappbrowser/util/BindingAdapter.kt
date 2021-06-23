package aslan.aslanov.flickrappbrowser.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import aslan.aslanov.flickrappbrowser.R
import com.squareup.picasso.Picasso

@BindingAdapter("app:downloadImage")
fun picassoImageLoader(imageView: ImageView, url: String?) {
    url?.let{
        Picasso.with(imageView.context).load(url).placeholder(R.drawable.ic_broken).into(imageView)
    }
}
