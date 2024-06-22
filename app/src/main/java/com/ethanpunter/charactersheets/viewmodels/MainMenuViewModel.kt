package com.ethanpunter.charactersheets.viewmodels

import android.graphics.Point
import android.os.Handler
import android.os.Looper
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ethanpunter.charactersheets.data.CharacterSheet
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.R
import com.ethanpunter.charactersheets.database.Repository
import com.ethanpunter.charactersheets.stats.AbilityScore
import com.ethanpunter.charactersheets.stats.TextLine
import com.ethanpunter.charactersheets.stats.DecoratedStat
import com.ethanpunter.charactersheets.stats.TextBlock
import com.ethanpunter.charactersheets.views.FragmentManager

class MainMenuViewModel(
    private val fragmentManager: FragmentManager,
    private val repository: Repository
) : BaseObservable(), Observer<List<CharacterSheet>> {

    @Bindable
    var characterSheets: List<CharacterSheet> = repository.liveCharacters.value ?: emptyList()

    init {
        repository.liveCharacters.observeForever(this)
    }

    fun openCharacter(characterSheet: CharacterSheet) {
        fragmentManager.goToCharacterSheet(characterSheet)
    }

    suspend fun addCharacter() {
        repository.insertOrUpdateCharacterSheet(createAnastasia())
    }

    suspend fun deleteCharacter(characterSheet: CharacterSheet) {
        repository.deleteCharacter(characterSheet)
    }

    private fun createAnastasia(): CharacterSheet =
        CharacterSheet(
            0L,
            TextLine(0L, Point(0, 0), true, "Character Name", "Anastasia"),
            TextLine(0L, Point(0, 1), true, "Class", "Cleric"),
            TextLine(0L, Point(1, 1), true, "Level", "18"),
            TextLine(0L, Point(0, 2), true, "Race", "Human"),
            TextLine(0L, Point(1, 2), true, "Background", "Acolyte"),
            DecoratedStat(0L, Point(0, 3), true, "Armour Class", "21", R.drawable.shield),
            DecoratedStat(0L, Point(0, 3), true, "Health", "126", R.drawable.heart),
            AbilityScore(0L, Point(0, 4), true, "Strength", "16", true),
            AbilityScore(0L, Point(1, 4), true, "Dexterity", "12", true),
            AbilityScore(0L, Point(2, 4), true, "Constitution", "12", true),
            AbilityScore(0L, Point(0, 5), true, "Intelligence", "11", true),
            AbilityScore(0L, Point(1, 5), true, "Wisdom", "19", true),
            AbilityScore(0L, Point(2, 5), true, "Charisma", "14", true),
            TextBlock(
                0L, Point(0, 6),
                true,
                "Saving Throws",
                "Strength\nDexterity\nConstitution\nIntelligence\nWisdom\nCharisma"
            ),
            TextBlock(
                0L, Point(1, 6),
                true,
                "Skills",
                "Acrobatics\nAnimal Handling\nArcana\nAthletics\nDeception\nHistory\nInsight\nIntimidation\nInvestigation\nMedicine\nNature\nPerception\nPerformance\nPersuasion\nReligion\nSleight of Hand\nStealth\nSurvival"
            ),
            TextBlock(0L, Point(0, 7), true, "Equipment", "")
        )

    override fun onChanged(characters: List<CharacterSheet>) {
        this.characterSheets = characters
        notifyPropertyChanged(BR.characterSheets)
    }
}