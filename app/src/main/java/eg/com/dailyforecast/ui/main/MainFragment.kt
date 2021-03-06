package eg.com.dailyforecast.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import eg.com.dailyforecast.R
import eg.com.dailyforecast.api.ResultDto.Status.*
import eg.com.dailyforecast.databinding.FragmentMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(), LifecycleObserver {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null

    private val viewModel: MainViewModel by viewModels()
    private val adapter = ForecastDataAdapter()
    private var job: Job? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreated() {
        activity?.lifecycle?.removeObserver(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.lifecycle?.addObserver(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.svMain.isSubmitButtonEnabled = true
        binding.svMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (!query.isNullOrEmpty()) {
                    viewModel.noInternet.postValue(false)
                    viewModel.errorMessage.postValue(null)
                    search(query)
                    true
                } else false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.noInternet.postValue(false)
                viewModel.errorMessage.postValue(null)
                return false
            }
        })
        setupRecyclerView()
    }

    private fun search(query: String) {
        // Make sure we cancel the previous job before creating a new one
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.getCityForecast(query).observe(viewLifecycleOwner, {
                when (it.status) {
                    SUCCESS -> {
                        viewModel.loading.postValue(false)
                        it.data?.let { it1 -> binding.setVariable(BR.item, it1) }
                        it.data?.list?.let { it1 -> adapter.setForecasts(it1) }
                    }
                    ERROR -> {
                        viewModel.loading.postValue(false)
                        viewModel.errorMessage.postValue(it.message)
                    }
                    LOADING -> {
                        viewModel.loading.postValue(true)
                    }
                    NO_INTERNET -> {
                        viewModel.noInternet.postValue(true)
                    }
                }
            })
        }
    }

    private fun setupRecyclerView() {
        // bind RecyclerView
        binding.rvForecasts.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rvForecasts.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.lifecycle?.removeObserver(this)
        _binding = null
        job?.cancel()
    }
}