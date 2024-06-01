package com.ethanpunter.charactersheets.views

import androidx.fragment.app.Fragment
import com.ethanpunter.charactersheets.R
import com.ethanpunter.charactersheets.data.Character
import com.ethanpunter.charactersheets.viewmodels.MainMenuViewModel

class FragmentManager(
    private val fragmentManager: androidx.fragment.app.FragmentManager
) {

    private val backstack: ArrayDeque<Fragment> = ArrayDeque()

    private val characterSheetFragment = CharacterSheetFragment()

    private val mainMenuFragment = MainMenuFragment()

    private var currentFragment: Fragment = mainMenuFragment

    init {
        mainMenuFragment.mainMenuViewModel = MainMenuViewModel(this)
    }

    fun goToCharacterList() {
        currentFragment.let { backstack.add(it) }

        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_host, mainMenuFragment)
            commit()
        }

        currentFragment = mainMenuFragment
    }

    fun goToCharacterSheet(character: Character) {
        currentFragment.let { backstack.add(it) }

        characterSheetFragment.character = character
        characterSheetFragment
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_host, characterSheetFragment)
            commit()
        }

        currentFragment = characterSheetFragment
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