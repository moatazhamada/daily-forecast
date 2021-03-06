package eg.com.dailyforecast.api

import androidx.annotation.Keep

/**
 * A generic class that holds a value with its loading status.
 *
 * Result is usually created by the Repository classes where they return
 * `LiveData<Result<T>>` to pass back the latest data to the UI with its fetch status.
 */
@Keep
data class ResultDto<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        NO_INTERNET
    }

    companion object {
        fun <T> success(data: T): ResultDto<T> {
            return ResultDto(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): ResultDto<T> {
            return ResultDto(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): ResultDto<T> {
            return ResultDto(Status.LOADING, data, null)
        }

        fun <T> noInternet(data: T? = null): ResultDto<T> {
            return ResultDto(Status.NO_INTERNET, data, null)
        }
    }
}