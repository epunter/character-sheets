package com.ethanpunter.charactersheets

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.activity.OnBackPressedCallback
import com.ethanpunter.charactersheets.database.CharacterSheetDatabase
import com.ethanpunter.charactersheets.database.Repository
import com.ethanpunter.charactersheets.database.TransactionProvider
import com.ethanpunter.charactersheets.databinding.ActivityMainBinding
import com.ethanpunter.charactersheets.views.FragmentManager

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager

    private lateinit var database: CharacterSheetDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = CharacterSheetDatabase.getInstance(applicationContext)
        val repository = Repository(
            database.characterSheetDao(),
            database.statsDao(),
            TransactionProvider(database)
        )

        fragmentManager =
            FragmentManager(supportFragmentManager, repository)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        val inflater: LayoutInflater =
            applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ActivityMainBinding.inflate(inflater)

        fragmentManager.goToCharacterList()

        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                fragmentManager.goBack()
            }
        })
    }
}