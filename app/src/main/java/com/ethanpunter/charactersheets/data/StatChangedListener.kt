package com.ethanpunter.charactersheets.data

import com.ethanpunter.charactersheets.stats.Stat

interface StatChangedListener {

    fun onStatChanged(stat: Stat)

}