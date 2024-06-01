package com.ethanpunter.charactersheets.stats

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.BaseObservable

abstract class Stat : BaseObservable() {

    abstract val position: Int

    abstract fun getView(inflater: LayoutInflater): View

}