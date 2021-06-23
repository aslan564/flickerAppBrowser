package aslan.aslanov.flickrappbrowser.ui.fragment.flickrList.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import aslan.aslanov.flickrappbrowser.model.entity.ImageFlickerDiffer
import aslan.aslanov.flickrappbrowser.model.entity.ImageLocal


class FlickerAdapter(private val onClickFlicker:(ImageLocal)->Unit): ListAdapter<ImageLocal, FlickrViewHolder>(
    ImageFlickerDiffer()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrViewHolder {
        return FlickrViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FlickrViewHolder, position: Int) {
        holder.bind(getItem(position),onClickFlicker)
    }
}