package com.example.testandroid.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testandroid.domain.models.EventModel
import com.example.testandroid.domain.models.ActivityModel
import com.example.testandroid.domain.usecases.GetActivityUseCase
import com.example.testandroid.domain.usecases.GetEventsUseCase
import com.example.testandroid.domain.usecases.RegisterEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getActivityUseCase: GetActivityUseCase,
    private val registerEventUseCase: RegisterEventUseCase,
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val _activity = MutableLiveData<String>()
    val activity: LiveData<String> = _activity

    private var _eventList = MutableLiveData<List<EventModel>>()
    val eventList: LiveData<List<EventModel>> = _eventList

    private var _state = MutableStateFlow<ViewStates>(ViewStates.Idle)
    val state: StateFlow<ViewStates> = _state

    fun calculateFactorial(number: String): String {
        if (number.isEmpty()) {
            return "NaN"
        }
        var result: Long = 1
        for (i in 1..number.toInt()) {
            result *= i
        }
        if (result > 700000000 || result == 0L) {
            return "Excedido"
        }
        return result.toString()
    }

    fun getActivity() {
        viewModelScope.launch {
            _state.value = ViewStates.Loading
            val result: ActivityModel? = getActivityUseCase.invoke()
            if (result == null) {
                _activity.value = "Error in request"
            } else {
                _activity.value = result.activity!!
            }
            _state.value = ViewStates.Idle
        }
    }

    fun registerEvent(event: Lifecycle.Event) {
        viewModelScope.launch {
            val currentDate = LocalDateTime.now()
            val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            registerEventUseCase.invoke(EventModel(event.name, currentDate.format(dateFormat)))
        }
    }

    fun setEventCollector() {
        viewModelScope.launch {
            getEventsUseCase.invoke()
                .map {
                    it.map { event ->
                        event.toDomain()
                    }
                }
                .collect {
                    _eventList.value = it
                }
        }
    }

}