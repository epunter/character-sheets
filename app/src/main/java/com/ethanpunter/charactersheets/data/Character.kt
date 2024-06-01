package com.ethanpunter.charactersheets.data

import com.ethanpunter.charactersheets.stats.BasicStat

data class Character(
    val name: String,
    val type: String,
    val level: Int,
    val basicStats: List<BasicStat>
) {
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
//    fun getBackground(): String
//
//    fun getRace(): String
//
//    fun getArmourClass(): Int
//
//    fun getInitiative(): Int
//
//    fun getSpeed(): Int
//
//    fun getDeathSaves(): Pair<Int, Int>
}