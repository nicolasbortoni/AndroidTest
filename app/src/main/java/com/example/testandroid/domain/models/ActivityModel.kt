package com.example.testandroid.domain.models

data class ActivityModel(
    var activity: String? = null,
    var type: String? = null,
    var participants: Int? = null,
    var price: Double? = null,
    var link: String? = null,
    var key: String? = null,
    var accessibility: Double? = null
)
