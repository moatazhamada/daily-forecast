package eg.com.dailyforecast.ui.main

import eg.com.dailyforecast.R

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import eg.com.dailyforecast.databinding.ItemForecastBinding
import eg.com.dailyforecast.db.dto.ForecastAtTime


class ForecastDataAdapter :RecyclerView.Adapter<ForecastDataAdapter.ForecastViewHolder>() {

    private var items: List<ForecastAtTime>? = null

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ForecastViewHolder {
        val itemsListBinding: ItemForecastBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_forecast, viewGroup, false
        )
        return ForecastViewHolder(itemsListBinding)
    }

    override fun onBindViewHolder(
        forecastViewHolder: ForecastViewHolder,
        i: Int
    ) {
        val currentData: ForecastAtTime = items!![i]
        forecastViewHolder.itemForecastBinding?.item = currentData
    }

    override fun getItemCount(): Int {
        return if (items != null) {
            items!!.size
        } else {
            0
        }
    }

    fun setForecasts(items: List<ForecastAtTime>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getForecast(position: Int): ForecastAtTime {
        return items!![position]
    }



    inner class ForecastViewHolder(itemForecastBinding: ItemForecastBinding) :
        RecyclerView.ViewHolder(itemForecastBinding.root) {
        var itemForecastBinding: ItemForecastBinding? = null

        init {
            this.itemForecastBinding = itemForecastBinding
        }

    }
}