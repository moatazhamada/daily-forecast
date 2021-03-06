package eg.com.dailyforecast.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import eg.com.dailyforecast.MyApplication
import kotlinx.coroutines.*
import java.io.IOException
import java.net.*


class ConnectivityUtils {
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
