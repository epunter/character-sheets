package com.ethanpunter.charactersheets.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DB_NAME = "character-sheet-db"
private const val DB_VERSION = 2

@Database(
    entities = [
        DatabaseCharacterSheet::class,
        DatabaseStat::class,
        DatabaseAbilityScore::class,
        DatabaseDecoratedStat::class,
        DatabaseTextBlock::class,
        DatabaseTextLine::class
    ],
    version = DB_VERSION,
    exportSchema = false
)
@androidx.room.TypeConverters(TypeConverters::class)
abstract class CharacterSheetDatabase : RoomDatabase() {

    abstract fun characterSheetDao(): CharacterSheetDao
    abstract fun statsDao(): StatsDao

    companion object {
        private var INSTANCE: CharacterSheetDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): CharacterSheetDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        CharacterSheetDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}