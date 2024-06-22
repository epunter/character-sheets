package com.ethanpunter.charactersheets.viewmodels

import android.graphics.Point
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ethanpunter.charactersheets.data.Character
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.R
import com.ethanpunter.charactersheets.stats.AbilityScore
import com.ethanpunter.charactersheets.stats.TextLine
import com.ethanpunter.charactersheets.stats.DecoratedStat
import com.ethanpunter.charactersheets.stats.TextBlock
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
                TextLine(Point(0, 0), true, "Character Name", "Anastasia"),
                TextLine(Point(0, 1), true, "Class", "Cleric"),
                TextLine(Point(1, 1), true, "Level", "18"),
                TextLine(Point(0, 2), true, "Race", "Human"),
                TextLine(Point(1, 2), true, "Background", "Acolyte"),
                DecoratedStat(Point(0, 3), true, "Armour Class", "21", R.drawable.shield),
                DecoratedStat(Point(0, 3), true, "Health", "126", R.drawable.heart),
                AbilityScore(Point(0, 4), true, "Strength", "16", true),
                AbilityScore(Point(1, 4), true, "Dexterity", "12", true),
                AbilityScore(Point(2, 4), true, "Constitution", "12", true),
                AbilityScore(Point(0, 5), true, "Intelligence", "11", true),
                AbilityScore(Point(1, 5), true, "Wisdom", "19", true),
                AbilityScore(Point(2, 5), true, "Charisma", "14", true),
                TextBlock(
                    Point(0, 6),
                    true,
                    "Saving Throws",
                    "Strength\nDexterity\nConstitution\nIntelligence\nWisdom\nCharisma"
                ),
                TextBlock(
                    Point(1, 6),
                    true,
                    "Skills",
                    "Acrobatics\nAnimal Handling\nArcana\nAthletics\nDeception\nHistory\nInsight\nIntimidation\nInvestigation\nMedicine\nNature\nPerception\nPerformance\nPersuasion\nReligion\nSleight of Hand\nStealth\nSurvival"
                ),
                TextBlock(Point(0, 7), true, "Equipment", "")
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