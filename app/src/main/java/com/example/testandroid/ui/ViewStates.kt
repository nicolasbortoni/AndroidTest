package com.example.testandroid.ui

sealed class ViewStates {
    object Loading: ViewStates()
    object Idle: ViewStates()
}