package com.ethanpunter.charactersheets.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ethanpunter.charactersheets.data.Sheet
import com.ethanpunter.charactersheets.BR

class MainMenuViewModel : BaseObservable() {

    @Bindable
    var characters = ArrayList<Sheet>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.characters)
        }

    init {
        addCharacter(object : Sheet {
            override fun getName(): String = "Anastasia"

            override fun getType(): String = "Cleric"

            override fun getLevel(): Int = 18
        })

        addCharacter(object : Sheet {
            override fun getName(): String = "Jahangir"

            override fun getType(): String = "Fighter"

            override fun getLevel(): Int = 5
        })

        addCharacter(object : Sheet {
            override fun getName(): String = "Texilli"

            override fun getType(): String = "Druid"

            override fun getLevel(): Int = 10
        })

        addCharacter(object : Sheet {
            override fun getName(): String = "Eagle"

            override fun getType(): String = "Barbarian"

            override fun getLevel(): Int = 20
        })
    }

    private fun addCharacter(sheet: Sheet) {
        characters.add(sheet)
        notifyPropertyChanged(BR.characters)
    }

}