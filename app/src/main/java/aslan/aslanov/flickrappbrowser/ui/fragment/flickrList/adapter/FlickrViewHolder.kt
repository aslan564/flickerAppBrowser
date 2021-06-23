package aslan.aslanov.flickrappbrowser.ui.fragment.flickrList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import aslan.aslanov.flickrappbrowser.databinding.LayoutFlickirItemBinding
import aslan.aslanov.flickrappbrowser.model.ImageFlicker
import aslan.aslanov.flickrappbrowser.model.entity.ImageLocal

class FlickrViewHolder private constructor(private val binding: LayoutFlickirItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ImageLocal, onClickListener: (ImageLocal) -> Unit): Unit = with(binding) {
        flickrImage = item
        root.setOnClickListener {
            onClickListener(item)
        }
        imageButton.setOnClickListener{
            if (!textViewTitle.isVisible) {
                textViewTitle.visibility=View.VISIBLE
            }else
                textViewTitle.visibility=View.INVISIBLE

        }
        executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): FlickrViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = LayoutFlickirItemBinding.inflate(inflater, parent, false)
            return FlickrViewHolder(view)
        }
    }
}
