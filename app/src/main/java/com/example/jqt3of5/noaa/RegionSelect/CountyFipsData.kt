package com.example.jqt3of5.noaa.RegionSelect

class CountyFipsData(val stateName : String, val stateCode : String, val stateFips : String, val countFips : String, val countyName : String, val fipsClass : String) {

    fun getZoneCode() : String
    {
        return stateCode + "C" + countFips
    }

    fun getStateCountyName() : String
    {
        return countyName + ", " + stateName
    }

    override fun toString(): String {
        return getStateCountyName()
    }
}