package aslan.aslanov.flickrappbrowser.ui.fragment.flickrList

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import aslan.aslanov.flickrappbrowser.R
import aslan.aslanov.flickrappbrowser.databinding.FragmentFlickerBinding
import aslan.aslanov.flickrappbrowser.local.sharedPreference.DatabaseStatus
import aslan.aslanov.flickrappbrowser.local.sharedPreference.SharedPreferenceManager
import aslan.aslanov.flickrappbrowser.network.StatusNetwork
import aslan.aslanov.flickrappbrowser.ui.activity.MainActivity
import aslan.aslanov.flickrappbrowser.ui.fragment.flickrList.adapter.FlickerAdapter
import aslan.aslanov.flickrappbrowser.viewModel.imageList.FlickrViewModel
import com.google.android.material.snackbar.Snackbar


class FlickerFragment : Fragment() {
    private val binding by lazy { FragmentFlickerBinding.inflate(layoutInflater) }
    private val viewModel by activityViewModels<FlickrViewModel>()
    private val adapterImage by lazy {
        FlickerAdapter {
            val action = FlickerFragmentDirections.actionFlickerFragmentToPreviewFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)
        bindUI()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        SharedPreferenceManager.isDatabase = DatabaseStatus.DATABASE
    }

    private fun bindUI(): Unit = with(binding) {
        lifecycleOwner = this@FlickerFragment
        flickerViewModel = viewModel

        imageButtonAllData.setOnClickListener {
            SharedPreferenceManager.isDatabase = DatabaseStatus.DATABASE
            getListFromDatabase()
        }



        mainContainer.setOnRefreshListener {
            isDatabaseActive()
            mainContainer.isRefreshing = false
        }
        recyclerViewImages.apply {
            adapter = adapterImage
        }

        searchView.setOnQueryTextListener(listener)
    }

    private val listener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            SharedPreferenceManager.isDatabase = DatabaseStatus.NETWORK
            query?.let { viewModel.getAllPhotosFromApi(query.toString()) }

            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {

            return false
        }
    }

    private fun isDatabaseActive() {
        if (SharedPreferenceManager.isDatabase == DatabaseStatus.NETWORK) {
            if (!SharedPreferenceManager.isLogin) {
                SharedPreferenceManager.isLogin=true
                getAllListFromApi()
            }else{
                Log.d(TAG, "isDatabaseActive: ${SharedPreferenceManager.isLogin}")
            }
        } else {
            getListFromDatabase()
        }
    }


    private fun observeData(): Unit = with(viewModel) {
        isDatabaseActive()
        imageFlicker.observe(viewLifecycleOwner, {
            it?.let {
                adapterImage.submitList(it)
            }
        })

        status.observe(viewLifecycleOwner, { status ->
            status?.let {
                when (status) {
                    StatusNetwork.ERROR -> {
                        binding.textViewError.text=getString(R.string.error)
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(
                            requireContext(),
                            requireView(),
                            "Please Check your Network connectivity",
                            Snackbar.LENGTH_LONG
                        )
                            .show()
                    }
                    StatusNetwork.LOADING -> {
                        binding.textViewError.visibility=View.VISIBLE
                        binding.progressBar.visibility = View.VISIBLE
                        binding.textViewError.text=getString(R.string.loading)
                    }
                    StatusNetwork.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        binding.textViewError.visibility=View.GONE
                    }
                }
            }

        })
    }

    private fun getListFromDatabase(): Unit = with(viewModel) {
        getAllSavedPhotos()
    }

    private fun getAllListFromApi(): Unit = with(viewModel) {
        getAllPhotosFromApi("all")
    }

    override fun onStart() {
        super.onStart()
        observeData()
        Log.d(TAG, "onStart: ")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }

    companion object {
        private const val TAG = "FlickerFragment"
    }
}