package com.ethanpunter.charactersheets.viewmodels

import android.graphics.Point
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ethanpunter.charactersheets.data.Character
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.R
import com.ethanpunter.charactersheets.stats.AbilityScore
import com.ethanpunter.charactersheets.stats.BasicStat
import com.ethanpunter.charactersheets.stats.BasicText
import com.ethanpunter.charactersheets.stats.DecoratedStat
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
                BasicText(Point(0, 0), "Character Name", "Anastasia"),
                BasicText(Point(0, 1), "Class", "Cleric"),
                BasicText(Point(1, 1), "Level", "18"),
                BasicText(Point(0, 2), "Race", "Human"),
                BasicText(Point(1, 2), "Background", "Acolyte"),
                DecoratedStat(Point(0, 3), "Armour Class", "20", R.drawable.shield),
                DecoratedStat(Point(0, 3), "Health", "200", R.drawable.heart),
//                DecoratedStat(Point(0, 3), "Speed", "30ft", R.drawable.boot),
                AbilityScore(Point(0, 4), "Strength", "20", true),
                AbilityScore(Point(1, 4), "Dexterity", "12", true),
                AbilityScore(Point(2, 4), "Constitution", "10", true),
                AbilityScore(Point(0, 5), "Intelligence", "20", false),
                AbilityScore(Point(1, 5), "Wisdom", "12", true),
                AbilityScore(Point(2, 5), "Charisma", "10", true)
            )
        )
//        addCharacter(
//            Character(
//                "Jahangir",
//                "Fighter",
//                5,
//                listOf(AbilityScore(0, "Dexterity", 23, true))
//            )
//        )
//        addCharacter(
//            Character(
//                "Texilli",
//                "Druid",
//                100,
//                listOf(AbilityScore(0, "Constitution", 3, true))
//            )
//        )
//        addCharacter(
//            Character(
//                "Eagle",
//                "Barbarian",
//                20,
//                listOf(AbilityScore(0, "Charisma", 20, false))
//            )
//        )
    }

    fun openCharacter(character: Character) {
        fragmentManager.goToCharacterSheet(character)
    }

    private fun addCharacter(character: Character) {
        characters.add(character)
        notifyPropertyChanged(BR.characters)
    }

}