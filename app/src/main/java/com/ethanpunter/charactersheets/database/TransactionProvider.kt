package com.ethanpunter.charactersheets.database

import androidx.room.withTransaction

class TransactionProvider(
    private val db: CharacterSheetDatabase
) {
    suspend fun <R> runAsTransaction(block: suspend () -> R): R {
        return db.withTransaction(block)
    }
}