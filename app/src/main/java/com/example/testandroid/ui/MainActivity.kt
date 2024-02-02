package com.example.testandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testandroid.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val _viewModel: MainActivityViewModel by viewModels()
    private lateinit var _binding: ActivityMainBinding

    private val eventAdapter = EventAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel.registerEvent(Lifecycle.Event.ON_CREATE)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        _viewModel.setEventCollector()
        setContentView(_binding.root)
    }

    override fun onStart() {
        super.onStart()
        _viewModel.registerEvent(Lifecycle.Event.ON_START)
        initListeners()
        initObservers()
        initCollectors()
        setupRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        _viewModel.registerEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onRestart() {
        super.onRestart()
        _viewModel.registerEvent(Lifecycle.Event.ON_ANY)
    }

    override fun onPause() {
        super.onPause()
        _viewModel.registerEvent(Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        _viewModel.registerEvent(Lifecycle.Event.ON_STOP)
        destroyObserversAndCollectors()
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewModel.registerEvent(Lifecycle.Event.ON_DESTROY)
    }

    private fun initCollectors() {
        lifecycleScope.launch {
            _viewModel.state.collect { state ->
                when (state) {
                    is ViewStates.Idle -> setIdleUi()
                    is ViewStates.Loading -> setLoadingUi()
                    else -> {}
                }
            }
        }
    }

    private fun initObservers() {
        _viewModel.activity.observe(
            this
        ) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        }
        _viewModel.eventList.observe(this) {
            eventAdapter.data = it
            eventAdapter.notifyDataSetChanged()
        }
    }

    private fun destroyObserversAndCollectors() {
        _viewModel.activity.removeObservers(this)
        _viewModel.eventList.removeObservers(this)
    }

    private fun initListeners() {
        _binding.btnCalculate.setOnClickListener {
            _binding.tvResult.text =
                _viewModel.calculateFactorial(_binding.edtInputNumber.text.toString())
        }

        _binding.btnRequest.setOnClickListener {
            _viewModel.getActivity()
        }

    }

    private fun setLoadingUi() {
        _binding.widgetLayout.visibility = View.GONE
        _binding.progressBar.visibility = View.VISIBLE
    }

    private fun setIdleUi() {
        _binding.widgetLayout.visibility = View.VISIBLE
        _binding.progressBar.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        _binding.eventRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventAdapter
        }
    }

}