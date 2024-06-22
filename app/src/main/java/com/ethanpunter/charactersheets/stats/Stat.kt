package com.ethanpunter.charactersheets.stats

import android.content.Context
import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.BaseObservable

abstract class Stat : BaseObservable() {

    open val customHeight: Int? = null

    open val id: Long = 0L

    abstract val position: Point

    abstract val editable: Boolean

    abstract fun getView(inflater: LayoutInflater): View

    abstract fun edit(context: Context)

}