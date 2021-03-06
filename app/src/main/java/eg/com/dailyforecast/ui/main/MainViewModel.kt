package eg.com.dailyforecast.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eg.com.dailyforecast.api.ResultDto
import dagger.hilt.android.lifecycle.HiltViewModel
import eg.com.dailyforecast.api.ForecastRepository
import eg.com.dailyforecast.db.dto.ForecastResponse
import eg.com.dailyforecast.utils.ConnectivityUtils
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ForecastRepository
) : ViewModel() {
    val noInternet: MutableLiveData<Boolean> = MutableLiveData(!ConnectivityUtils.isNetworkAvailable())
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val errorMessage: MutableLiveData<String?> = MutableLiveData()

    suspend fun getCityForecast(query: String): LiveData<ResultDto<ForecastResponse?>> {
        return repository.getCityForecast(query.trim().toLowerCase(Locale.getDefault()))
    }

}