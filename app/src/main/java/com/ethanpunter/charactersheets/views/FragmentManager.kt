package com.ethanpunter.charactersheets.views

import android.content.Context
import androidx.fragment.app.Fragment
import com.ethanpunter.charactersheets.R
import com.ethanpunter.charactersheets.data.Sheet
import com.ethanpunter.charactersheets.viewmodels.MainMenuViewModel

class FragmentManager(
    private val fragmentManager: androidx.fragment.app.FragmentManager,
    private val context: Context
) {

    private val backstack: ArrayDeque<Fragment> = ArrayDeque()

    private var currentFragment: Fragment? = null

    fun goToCharacterList() {
        currentFragment?.let { backstack.add(it) }

        val fragment = MainMenuFragment.newInstance(context, MainMenuViewModel(this))
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_host, fragment)
            commit()
        }

        currentFragment = fragment
    }

    fun goToCharacterSheet(sheet: Sheet) {
        currentFragment?.let { backstack.add(it) }

        val fragment = CharacterSheetFragment.newInstance(sheet)
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_host, fragment)
            commit()
        }

        currentFragment = fragment
    }

    fun goBack() {
        val nextFragment = backstack.removeFirstOrNull()
        nextFragment?.let {
            fragmentManager.beginTransaction().apply {
                replace(R.id.fragment_host, it)
                commit()
            }
            currentFragment = it
        }
    }


}