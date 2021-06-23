package aslan.aslanov.flickrappbrowser

import android.app.Application
import aslan.aslanov.flickrappbrowser.local.sharedPreference.SharedPreferenceManager

class FlickerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferenceManager.instance(this)
    }
}