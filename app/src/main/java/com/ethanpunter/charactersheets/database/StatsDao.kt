package com.ethanpunter.charactersheets.database

import android.content.Context
import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import androidx.room.*
import com.ethanpunter.charactersheets.stats.*

@Dao
interface StatsDao {

    @Query("SELECT * FROM ability_score")
    fun getAllAbilities(): List<DatabaseAbilityScore>

    @Query("SELECT * FROM stat")
    fun getAllStats(): List<DatabaseStat>

    fun convert(stat: DatabaseStat): Stat {
        return when (stat) {
            is DatabaseAbilityScore -> convert(stat)
            is DatabaseDecoratedStat -> convert(stat)
            is DatabaseTextBlock -> convert(stat)
            is DatabaseTextLine -> convert(stat)
            else -> createEmptyStat()
        }
    }

    fun convert(stat: Stat, sheetId: Long): DatabaseStat {
        return when (stat) {
            is AbilityScore -> convert(stat, sheetId)
            is DecoratedStat -> convert(stat, sheetId)
            is TextBlock -> convert(stat, sheetId)
            is TextLine -> convert(stat, sheetId)
            else -> createEmptyDatabaseStat()
        }
    }
    
    fun insert(stat: DatabaseStat) {
        when (stat) {
            is DatabaseAbilityScore -> insert(stat)
            is DatabaseDecoratedStat -> insert(stat)
            is DatabaseTextBlock -> insert(stat)
            is DatabaseTextLine -> insert(stat)
        }
    }

    fun update(stat: DatabaseStat) {
        when (stat) {
            is DatabaseAbilityScore -> update(stat)
            is DatabaseDecoratedStat -> update(stat)
            is DatabaseTextBlock -> update(stat)
            is DatabaseTextLine -> update(stat)
        }
    }

    // ABILITY SCORE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseAbilityScore: DatabaseAbilityScore): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(databaseAbilityScore: DatabaseAbilityScore)

    fun convert(stat: AbilityScore, characterSheetId: Long): DatabaseAbilityScore =
        DatabaseAbilityScore(
            stat.id,
            stat.position,
            stat.editable,
            characterSheetId,
            stat.statName,
            stat.statValue,
            stat.useModifier
        )

    fun convert(stat: DatabaseAbilityScore): AbilityScore =
        AbilityScore(
            stat.id,
            stat.position,
            stat.editable,
            stat.statName,
            stat.statValue,
            stat.useModifier
        )

    // DECORATED STAT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseDecoratedStat: DatabaseDecoratedStat): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(databaseDecoratedStat: DatabaseDecoratedStat)

    fun convert(stat: DecoratedStat, characterSheetId: Long): DatabaseDecoratedStat =
        DatabaseDecoratedStat(
            stat.id,
            stat.position,
            stat.editable,
            characterSheetId,
            stat.statName,
            stat.statValue,
            stat.decoration
        )

    fun convert(stat: DatabaseDecoratedStat): DecoratedStat =
        DecoratedStat(
            stat.id,
            stat.position,
            stat.editable,
            stat.statName,
            stat.statValue,
            stat.decorationId
        )

    // TEXT BLOCK
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseTextBlock: DatabaseTextBlock): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(databaseTextBlock: DatabaseTextBlock)

    fun convert(stat: TextBlock, characterSheetId: Long): DatabaseTextBlock =
        DatabaseTextBlock(
            stat.id,
            stat.position,
            stat.editable,
            characterSheetId,
            stat.textLabel,
            stat.textValues
        )

    fun convert(stat: DatabaseTextBlock): TextBlock =
        TextBlock(
            stat.id,
            stat.position,
            stat.editable,
            stat.statName,
            stat.statValues.toMutableList()
        )

    // TEXT LINE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseTextLine: DatabaseTextLine): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(databaseTextLine: DatabaseTextLine)

    fun convert(stat: TextLine, characterSheetId: Long): DatabaseTextLine =
        DatabaseTextLine(
            stat.id,
            stat.position,
            stat.editable,
            characterSheetId,
            stat.textLabel,
            stat.textValue
        )

    fun convert(stat: DatabaseTextLine): TextLine =
        TextLine(
            stat.id,
            stat.position,
            stat.editable,
            stat.statName,
            stat.statValue
        )


    private fun createEmptyStat(): Stat =
        object : Stat() {
            override val position: Point
                get() = TODO("Not yet implemented")
            override val editable: Boolean
                get() = TODO("Not yet implemented")

            override fun getView(inflater: LayoutInflater): View {
                TODO("Not yet implemented")
            }

            override fun edit(context: Context) {
                TODO("Not yet implemented")
            }

        }


    private fun createEmptyDatabaseStat(): DatabaseStat =
        DatabaseStat(
            0L,
            Point(0, 0),
            false,
            0L
        )
}

