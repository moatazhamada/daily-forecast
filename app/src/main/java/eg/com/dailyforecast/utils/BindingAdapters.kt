package eg.com.dailyforecast.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import eg.com.dailyforecast.R

@BindingAdapter("app:visibility")
fun setVisibility(view: View, isVisible: Any?) {
    when (isVisible) {
        is Boolean -> {
            if (isVisible) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }
        is String? -> {
            if (!isVisible.isNullOrEmpty()) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }
    }

}

@BindingAdapter("app:setErrorText")
fun setErrorText(view: TextView, text: String?) {
    if (!text.isNullOrEmpty())
        view.text = text
    else
        view.text = view.context.getString(R.string.unexpected_exception)
}