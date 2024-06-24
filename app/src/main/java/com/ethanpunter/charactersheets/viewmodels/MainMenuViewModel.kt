package com.ethanpunter.charactersheets.viewmodels

import android.graphics.Point
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
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
        repository.insertOrUpdateCharacterSheet(createNewCharacter())
    }

    suspend fun deleteCharacter(characterSheet: CharacterSheet) {
        repository.deleteCharacter(characterSheet)
    }

    private fun createNewCharacter(): CharacterSheet =
        CharacterSheet(
            0L,
            TextLine(0L, Point(0, 0), true, "Character Name", "New Character"),
            TextLine(0L, Point(0, 1), true, "Class", "Commoner"),
            TextLine(0L, Point(1, 1), true, "Level", "1"),
            TextLine(0L, Point(0, 2), true, "Race", "Human"),
            TextLine(0L, Point(1, 2), true, "Background", "Farmhand"),
            DecoratedStat(0L, Point(0, 3), true, "Armour Class", "10", R.drawable.shield),
            DecoratedStat(0L, Point(1, 3), true, "Health", "4", R.drawable.heart),
            AbilityScore(0L, Point(2, 3), true, "Speed", "30ft", false),
            AbilityScore(0L, Point(0, 4), true, "Strength", "10", true),
            AbilityScore(0L, Point(1, 4), true, "Dexterity", "10", true),
            AbilityScore(0L, Point(2, 4), true, "Constitution", "10", true),
            AbilityScore(0L, Point(0, 5), true, "Intelligence", "10", true),
            AbilityScore(0L, Point(1, 5), true, "Wisdom", "10", true),
            AbilityScore(0L, Point(2, 5), true, "Charisma", "10", true),
            TextBlock(
                0L, Point(0, 6),
                false,
                "Saving Throws",
                mutableListOf(
                    "Strength",
                    "Dexterity",
                    "Constitution",
                    "Intelligence",
                    "Wisdom",
                    "Charisma"
                )
            ),
            TextBlock(
                0L, Point(1, 6),
                false,
                "Skills",
                mutableListOf(
                    "Acrobatics",
                    "Animal Handling",
                    "Arcana",
                    "Athletics",
                    "Deception",
                    "History",
                    "Insight",
                    "Intimidation",
                    "Investigation",
                    "Medicine",
                    "Nature",
                    "Perception",
                    "Performance",
                    "Persuasion",
                    "Religion",
                    "Sleight of Hand",
                    "Stealth",
                    "Survival"
                )
            ),
            TextBlock(0L, Point(0, 7), true, "Equipment", mutableListOf())
        )

    override fun onChanged(characters: List<CharacterSheet>) {
        this.characterSheets = characters
        notifyPropertyChanged(BR.characterSheets)
    }
}