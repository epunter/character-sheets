package com.ethanpunter.charactersheets.data

import android.view.LayoutInflater
import android.view.View
import com.ethanpunter.charactersheets.stats.AbilityScore
import com.ethanpunter.charactersheets.stats.BasicText

data class Character(
    val name: String,
    val type: String,
    val level: Int,
    val race: String,
    val background: String,
    val abilityScores: List<AbilityScore>
) {

    fun getAllAttributes(inflater: LayoutInflater): List<View> {
        return listOf(
            BasicText(0, "Character Name", name),
            BasicText(1, "Class", type),
            BasicText(2, "Level", level.toString()),
            BasicText(3, "Race", race),
            BasicText(4, "Background", background)
        ).map { it.getView(inflater) }
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
//
//    fun getArmourClass(): Int
//
//    fun getInitiative(): Int
//
//    fun getSpeed(): Int
//
//    fun getDeathSaves(): Pair<Int, Int>
}