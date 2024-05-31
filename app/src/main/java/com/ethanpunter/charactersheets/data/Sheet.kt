package com.ethanpunter.charactersheets.data

interface Sheet {
//
//    fun getPrimaryStats() : List<PrimaryStat>
//
//    fun getSavingThrows()
//
//    fun getSkillProficiencies()
//
//    fun getInventory(): List<String>
//
//    fun getMaxHp(): Int
//
//    fun getCurrentHp(): Int

    fun getName(): String

    fun getType(): String

    fun getLevel(): Int

    fun getTitle(): String
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