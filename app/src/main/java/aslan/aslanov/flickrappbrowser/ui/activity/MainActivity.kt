package aslan.aslanov.flickrappbrowser.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import aslan.aslanov.flickrappbrowser.databinding.ActivityMainBinding
import aslan.aslanov.flickrappbrowser.local.sharedPreference.SharedPreferenceManager
import aslan.aslanov.flickrappbrowser.ui.fragment.flickrList.FlickerFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")

        super.onDestroy()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}