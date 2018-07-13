package com.example.jqt3of5.noaa.Repository.Api.DataObjects

import kotlin.collections.HashMap

class AlertCountsByLocation {
    var areas : HashMap<String, Int>? = null
    var regions : HashMap<String, Int>? = null
    var zones : HashMap<String, Int>? = null
}