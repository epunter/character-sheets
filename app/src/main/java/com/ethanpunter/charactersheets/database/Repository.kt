package com.ethanpunter.charactersheets.database

import androidx.lifecycle.Transformations
import com.ethanpunter.charactersheets.data.CharacterSheet
import com.ethanpunter.charactersheets.stats.Stat

class Repository(
    private val characterSheetDao: CharacterSheetDao,
    private val statsDao: StatsDao,
    private val transactionProvider: TransactionProvider
) {
    private val liveDatabaseCharacters = characterSheetDao.getAll()
    val liveCharacters = Transformations.map(liveDatabaseCharacters) { characters ->
        characters.map { character ->
            val stats = character.stats.map { statsDao.convert(it) }
            characterSheetDao.convert(character, stats)
        }
    }

    suspend fun insertOrUpdateCharacterSheet(characterSheet: CharacterSheet) {
        transactionProvider.runAsTransaction {
            if (characterSheet.id == 0L) {
                val id = characterSheetDao.insert(characterSheetDao.convert(characterSheet))
                characterSheet.stats.forEach {
                    statsDao.insert(statsDao.convert(it, id))
                }
            } else {
                characterSheetDao.update(characterSheetDao.convert(characterSheet))
                characterSheet.stats.forEach {
                    statsDao.update(statsDao.convert(it, characterSheet.id))
                }
            }
        }
    }

    suspend fun deleteCharacter(characterSheet: CharacterSheet) {
        transactionProvider.runAsTransaction {
            characterSheetDao.delete(characterSheet.id)
        }
    }

    suspend fun updateStat(stat: Stat, characterId: Long?) {
        characterId?.let {
            transactionProvider.runAsTransaction {
                statsDao.update(statsDao.convert(stat, characterId))
            }
        }
    }

    suspend fun updateCharacter(characterSheet: CharacterSheet?) {
        characterSheet?.let {
            transactionProvider.runAsTransaction {
                characterSheetDao.update(characterSheetDao.convert(characterSheet))
            }
        }
    }
}