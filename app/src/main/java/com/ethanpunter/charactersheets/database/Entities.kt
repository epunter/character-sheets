package com.ethanpunter.charactersheets.database

import android.graphics.Point
import androidx.room.*

@Entity(
    tableName = "character_sheet"
)
class DatabaseCharacterSheet(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var name: String,
    var profession: String,
    var level: String
)

data class DatabaseFullCharacterSheet(
    @Embedded
    val characterSheet: DatabaseCharacterSheet,

    @Relation(
        entity = DatabaseAbilityScore::class,
        parentColumn = "id",
        entityColumn = "characterSheetId"
    )
    private val abilityScores: List<DatabaseAbilityScore>,
    @Relation(
        entity = DatabaseDecoratedStat::class,
        parentColumn = "id",
        entityColumn = "characterSheetId"
    )
    private val decoratedStats: List<DatabaseDecoratedStat>,
    @Relation(
        entity = DatabaseTextBlock::class,
        parentColumn = "id",
        entityColumn = "characterSheetId"
    )
    private val textBlocks: List<DatabaseTextBlock>,
    @Relation(
        entity = DatabaseTextLine::class,
        parentColumn = "id",
        entityColumn = "characterSheetId"
    )
    private val textLines: List<DatabaseTextLine>
) {
    @Ignore
    val stats = abilityScores + decoratedStats + textBlocks + textLines
}

@Entity(
    tableName = "stat",
    foreignKeys = [
        ForeignKey(
            entity = DatabaseCharacterSheet::class,
            parentColumns = ["id"],
            childColumns = ["characterSheetId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
open class DatabaseStat(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var position: Point,
    var editable: Boolean,
    var characterSheetId: Long
)

@Entity(
    tableName = "ability_score"
)
class DatabaseAbilityScore(
    id: Long = 0L,
    position: Point,
    editable: Boolean,
    characterSheetId: Long,
    val statName: String,
    var statValue: String,
    var useModifier: Boolean
) : DatabaseStat(id, position, editable, characterSheetId)

@Entity(
    tableName = "decorated_stat"
)
class DatabaseDecoratedStat(
    id: Long = 0L,
    position: Point,
    editable: Boolean,
    characterSheetId: Long,
    val statName: String,
    var statValue: String,
    var decorationId: Int
) : DatabaseStat(id, position, editable, characterSheetId)

@Entity(
    tableName = "text_block"
)
class DatabaseTextBlock(
    id: Long = 0L,
    position: Point,
    editable: Boolean,
    characterSheetId: Long,
    val statName: String,
    var statValues: List<String>
) : DatabaseStat(id, position, editable, characterSheetId)

@Entity(
    tableName = "text_line"
)
class DatabaseTextLine(
    id: Long = 0L,
    position: Point,
    editable: Boolean,
    characterSheetId: Long,
    val statName: String,
    var statValue: String
) : DatabaseStat(id, position, editable, characterSheetId)

