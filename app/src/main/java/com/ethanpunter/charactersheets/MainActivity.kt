package com.ethanpunter.charactersheets

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.activity.OnBackPressedCallback
import com.ethanpunter.charactersheets.databinding.ActivityMainBinding
import com.ethanpunter.charactersheets.views.FragmentManager

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentManager =
            FragmentManager(supportFragmentManager)
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