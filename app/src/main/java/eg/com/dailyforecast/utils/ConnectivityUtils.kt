package eg.com.dailyforecast.utils

import android.content.Context
import android.net.ConnectivityManager
import dagger.hilt.android.qualifiers.ApplicationContext
import eg.com.dailyforecast.ui.MyApplication
import kotlinx.coroutines.*
import java.io.IOException
import java.net.*
import javax.inject.Inject


class ConnectivityUtils  {

    companion object {
        // check for the internet using socket to ping google host
        private fun isOnline(): Boolean {
            return try {
                    runBlocking { withContext(Dispatchers.IO){
                        val timeoutMs = 3000
                        val sock = Socket()
                        sock.connect(InetSocketAddress(Constants.GOOGLE_HOST, Constants.GOOGLE_PORT), timeoutMs)
                        sock.close()
                        true
                    } }
            } catch (e: IOException) {
                false
            }
        }

        private fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            val mInternet= isOnline()
            return if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                mInternet
            } else
                false
        }

        fun isNetworkAvailable(): Boolean {
            return isNetworkAvailable(MyApplication.getInstance())
        }

    }
}
