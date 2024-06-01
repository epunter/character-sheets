package com.ethanpunter.charactersheets.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ethanpunter.charactersheets.data.Character
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.views.FragmentManager

class MainMenuViewModel(private val fragmentManager: FragmentManager) : BaseObservable() {

    @Bindable
    var characters = ArrayList<Character>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.characters)
        }

    init {
        addCharacter(Character("Anastasia", "Cleric", 18))
        addCharacter(Character("Jahangir", "Fighter", 5))
        addCharacter(Character("Texilli", "Druid", 100))
        addCharacter(Character("Eagle", "Barbarian", 20))
    }

    fun openCharacter(character: Character) {
        fragmentManager.goToCharacterSheet(character)
    }

    private fun addCharacter(character: Character) {
        characters.add(character)
        notifyPropertyChanged(BR.characters)
    }

}