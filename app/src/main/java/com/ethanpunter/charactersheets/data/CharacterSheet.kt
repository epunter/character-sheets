package com.ethanpunter.charactersheets.data

import com.ethanpunter.charactersheets.stats.TextLine
import com.ethanpunter.charactersheets.stats.Stat

class CharacterSheet(
    val id: Long = 0L,
    val name: TextLine,
    val profession: TextLine,
    val level: TextLine,
    vararg val stats: Stat
) {
    fun getAllAttributes(): List<Stat> {
        val baseStats = listOf(name, profession, level)
        return (baseStats + stats).sortedWith(compareBy({ it.position.y }, { it.position.x }))
    }
}