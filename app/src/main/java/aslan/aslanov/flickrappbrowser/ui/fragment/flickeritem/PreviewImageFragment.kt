package aslan.aslanov.flickrappbrowser.ui.fragment.flickeritem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import aslan.aslanov.flickrappbrowser.R
import aslan.aslanov.flickrappbrowser.databinding.FragmentImagePreviewBinding
import aslan.aslanov.flickrappbrowser.viewModel.imageList.FlickrViewModel
import aslan.aslanov.flickrappbrowser.viewModel.preview.PreviewViewModel

class PreviewImageFragment : Fragment() {
    private val binding by lazy { FragmentImagePreviewBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<PreviewViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    override fun onStart() {
        super.onStart()
        observeData()
    }
    private fun bindUI(): Unit = with(binding) {
        lifecycleOwner = this@PreviewImageFragment
        arguments?.let {
            val imageLocal = PreviewImageFragmentArgs.fromBundle(it).imageFlicker
            image = imageLocal
            viewModel.getImage(imageLocal!!.authorId!!)
            if (saveImageButton.text.toString()==getString(R.string.save_image)) {
                saveImageButton.setOnClickListener {
                    viewModel.saveImageToDatabase(imageLocal)
                    saveImageButton.text=getString(R.string.followed)
                }
            }else{
                saveImageButton.setOnClickListener {
                    viewModel.deleteImage(imageLocal)
                    saveImageButton.text=getString(R.string.save_image)
                }
            }
        }
    }

    private fun observeData():Unit= with(viewModel) {
        imageLiveData.observe(viewLifecycleOwner,{
            it?.let {
                binding.saveImageButton.text=getString(R.string.followed)
            }
        })
    }
}