package ng.sampleproject.sampleproject.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by USER on 13/08/2019.
 */
object NetworkUtils {

    fun checkInternetConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

}