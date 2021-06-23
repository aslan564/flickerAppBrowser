package aslan.aslanov.flickrappbrowser.local.sharedPreference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object SharedPreferenceManager {
    private const val SHARED_PREFERENCES_FILE_KEY =
        "aslan.aslanov.flickrappbrowser.local.sharedPreference"

    private lateinit var instance: SharedPreferenceManager
    private lateinit var sharedPreferences: SharedPreferences
    private val IS_LOGIN = Pair<String, Boolean>("is_login", false)
    private val IS_DATABASE = Pair<String, String>("is_database", DatabaseStatus.NETWORK)

    fun instance(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE_KEY, MODE_PRIVATE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var isLogin: Boolean
        get() = sharedPreferences.getBoolean(IS_LOGIN.first, IS_LOGIN.second)
        set(value) = sharedPreferences.edit {
            it.putBoolean(IS_LOGIN.first, value)
        }

    var isDatabase: String?
        get() = sharedPreferences.getString(IS_DATABASE.first, IS_DATABASE.second)
        set(value) = sharedPreferences.edit {
            it.putString(IS_DATABASE.first, value)
        }
}
object DatabaseStatus{
  const val  DATABASE="database"
  const val  NETWORK="network"

}