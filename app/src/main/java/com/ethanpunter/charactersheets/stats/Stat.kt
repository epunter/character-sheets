package com.ethanpunter.charactersheets.stats

import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.BaseObservable

abstract class Stat : BaseObservable() {

    open val customHeight: Int? = null

    abstract val position: Point

    abstract fun getView(inflater: LayoutInflater): View

}