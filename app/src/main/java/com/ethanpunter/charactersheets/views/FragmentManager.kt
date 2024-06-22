package com.ethanpunter.charactersheets.views

import androidx.fragment.app.Fragment
import com.ethanpunter.charactersheets.R
import com.ethanpunter.charactersheets.data.CharacterSheet
import com.ethanpunter.charactersheets.database.Repository
import com.ethanpunter.charactersheets.viewmodels.MainMenuViewModel

class FragmentManager(
    private val fragmentManager: androidx.fragment.app.FragmentManager,
    repository: Repository
) {

    private val backstack: ArrayDeque<Fragment> = ArrayDeque()

    private val characterSheetFragment = CharacterSheetFragment()

    private val mainMenuFragment = MainMenuFragment()

    private var currentFragment: Fragment = mainMenuFragment

    init {
        mainMenuFragment.mainMenuViewModel = MainMenuViewModel(this, repository)
    }

    fun goToCharacterList() {
        currentFragment.let { backstack.add(it) }

        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_host, mainMenuFragment)
            commit()
        }

        currentFragment = mainMenuFragment
    }

    fun goToCharacterSheet(characterSheet: CharacterSheet) {
        currentFragment.let { backstack.add(it) }

        characterSheetFragment.characterSheet = characterSheet
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