package com.ethanpunter.charactersheets.database

import android.graphics.Point
import androidx.lifecycle.LiveData
import androidx.room.*
import com.ethanpunter.charactersheets.data.CharacterSheet
import com.ethanpunter.charactersheets.stats.Stat
import com.ethanpunter.charactersheets.stats.TextLine

@Dao
interface CharacterSheetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseCharacterSheet: DatabaseCharacterSheet): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(databaseCharacterSheet: DatabaseCharacterSheet)

    @Query("SELECT * FROM character_sheet WHERE id = :id")
    fun get(id: Long): DatabaseFullCharacterSheet

    @Query("SELECT * FROM character_sheet")
    fun getAll(): LiveData<List<DatabaseFullCharacterSheet>>

    @Query("DELETE FROM character_sheet WHERE id = :id")
    fun delete(id: Long)

    fun convert(character: CharacterSheet): DatabaseCharacterSheet =
        DatabaseCharacterSheet(
            character.id,
            character.name.textValue,
            character.profession.textValue,
            character.level.textValue
        )

    fun convert(character: DatabaseFullCharacterSheet, stats: List<Stat>): CharacterSheet =
        CharacterSheet(
            character.characterSheet.id,
            TextLine(0L, Point(0, 0), true, "Character Name", character.characterSheet.name),
            TextLine(0L, Point(0, 1), true, "Class", character.characterSheet.profession),
            TextLine(0L, Point(1, 1), true, "Level", character.characterSheet.level),
            *stats.toTypedArray()
        )
}