package com.example.jqt3of5.noaa.Api.DataObjects

import kotlin.collections.HashMap

class AlertCountsByLocation {
    var areas : HashMap<String, Int>? = null
    var regions : HashMap<String, Int>? = null
    var zones : HashMap<String, Int>? = null
}