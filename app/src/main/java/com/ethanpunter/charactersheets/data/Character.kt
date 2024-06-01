package com.ethanpunter.charactersheets.data

import android.view.LayoutInflater
import android.view.View
import com.ethanpunter.charactersheets.R
import com.ethanpunter.charactersheets.stats.AbilityScore
import com.ethanpunter.charactersheets.stats.BasicText
import com.ethanpunter.charactersheets.stats.DecoratedStat
import com.ethanpunter.charactersheets.stats.Stat

class Character(
    val name: BasicText,
    val profession: BasicText,
    val level: BasicText,
    vararg val stats: Stat
) {
    fun getAllAttributes(): List<Stat> {
        val baseStats = listOf(name, profession, level)
        return (baseStats + stats).sortedWith(compareBy({ it.position.y }, { it.position.x }))
    }

//    fun getSavingThrows()
//
//    fun getSkillProficiencies()
//
//    fun getInventory(): List<String>
//
//    fun getMaxHp(): Int
//
//    fun getCurrentHp(): Int
//
//    fun getInitiative(): Int
//
//    fun getSpeed(): Int
//
//    fun getDeathSaves(): Pair<Int, Int>
}