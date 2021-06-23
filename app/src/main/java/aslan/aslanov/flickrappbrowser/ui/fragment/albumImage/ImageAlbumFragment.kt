package aslan.aslanov.flickrappbrowser.ui.fragment.albumImage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import aslan.aslanov.flickrappbrowser.databinding.FragmentImageAlbumBinding


class ImageAlbumFragment : Fragment() {

    private val binding by lazy { FragmentImageAlbumBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?=binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
        observeData()

    }

    private fun bindUI():Unit= with(binding) {
        lifecycleOwner=this@ImageAlbumFragment
        recyclerViewImagesAlbum.apply {

        }
    }

    private fun observeData() {

    }

    companion object {
        private const val TAG = "ImageAlbumFragment"
    }
}