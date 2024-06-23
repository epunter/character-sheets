package com.ethanpunter.charactersheets.stats

import android.content.Context
import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.BaseObservable
import com.ethanpunter.charactersheets.data.StatChangedListener

abstract class Stat : BaseObservable() {

    open val customHeight: Int? = null

    open val id: Long = 0L

    abstract val position: Point

    abstract val editable: Boolean

    private val listeners = mutableListOf<StatChangedListener>()

    abstract fun getView(inflater: LayoutInflater): View

    abstract fun edit(context: Context)

    fun addListener(listener: StatChangedListener) {
        this.listeners.add(listener)
    }

    fun clearListeners() {
        this.listeners.clear()
    }

    fun notifyListeners() {
        this.listeners.forEach { it.onStatChanged(this) }
    }

}