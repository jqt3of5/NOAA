package com.example.jqt3of5.noaa

import android.content.Context
import java.io.InputStreamReader
import kotlin.collections.HashMap

class FipsDataLoader {
    fun loadFipsData(context : Context) : Map<String, List<CountyFipsData>>
    {
        val stream = context.resources.openRawResource(R.raw.state_county_fips)
        val lines = InputStreamReader(stream).readLines()

        var map = HashMap<String, MutableList<CountyFipsData>>()
        for (line in lines)
        {
            val split = line.split(",")
            val data = CountyFipsData(split[0], split[1].toInt(), split[2].toInt(), split[3], split[4])
            if (!map.containsKey(data.state))
            {
                map.put(data.state, mutableListOf())
            }
            val list = map.get(data.state)!!
            list.add(data)
        }
        return map
    }
}