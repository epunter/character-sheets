package com.ethanpunter.charactersheets.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ethanpunter.charactersheets.data.Character
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.stats.AbilityScore
import com.ethanpunter.charactersheets.stats.BasicStat
import com.ethanpunter.charactersheets.views.FragmentManager

class MainMenuViewModel(private val fragmentManager: FragmentManager) : BaseObservable() {

    @Bindable
    var characters = ArrayList<Character>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.characters)
        }

    init {
        addCharacter(
            Character(
                "Anastasia", "Cleric", 18,
                listOf(
                    AbilityScore(0, "Strength", 20, true),
                    AbilityScore(1, "Dexterity", 12, true),
                    AbilityScore(2, "Constitution", 10, true)
                )
            )
        )
        addCharacter(
            Character(
                "Jahangir",
                "Fighter",
                5,
                listOf(AbilityScore(0, "Dexterity", 23, true))
            )
        )
        addCharacter(
            Character(
                "Texilli",
                "Druid",
                100,
                listOf(AbilityScore(0, "Constitution", 3, true))
            )
        )
        addCharacter(
            Character(
                "Eagle",
                "Barbarian",
                20,
                listOf(AbilityScore(0, "Charisma", 20, false))
            )
        )
    }

    fun openCharacter(character: Character) {
        fragmentManager.goToCharacterSheet(character)
    }

    private fun addCharacter(character: Character) {
        characters.add(character)
        notifyPropertyChanged(BR.characters)
    }

}