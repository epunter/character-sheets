package com.ethanpunter.charactersheets.viewmodels

import com.ethanpunter.charactersheets.data.CharacterSheet
import com.ethanpunter.charactersheets.data.StatChangedListener
import com.ethanpunter.charactersheets.database.Repository
import com.ethanpunter.charactersheets.stats.Stat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CharacterSheetViewModel(private val repository: Repository) : StatChangedListener {

    var currentCharacter: CharacterSheet? = null
        set(value) {
            field?.getAllAttributes()?.forEach { it.clearListeners() }
            field = value
            field?.getAllAttributes()?.forEach { it.addListener(this) }
        }

    fun getCurrentCharacterAttributes() = currentCharacter?.getAllAttributes()

    private suspend fun updateStat(stat: Stat) {
        repository.updateStat(stat, currentCharacter?.id)
    }

    override fun onStatChanged(stat: Stat) {
        CoroutineScope(IO).launch {
            updateStat(stat)
            repository.updateCharacter(currentCharacter)
        }
    }
}