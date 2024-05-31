package com.ethanpunter.charactersheets

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.ethanpunter.charactersheets.databinding.ActivityMainBinding
import com.ethanpunter.charactersheets.viewmodels.MainMenuViewModel
import com.ethanpunter.charactersheets.views.MainMenuFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        val inflater: LayoutInflater =
            applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ActivityMainBinding.inflate(inflater)

        val menuViewModel = MainMenuViewModel()
        val fragment = MainMenuFragment(menuViewModel, applicationContext)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_host, fragment)
            commit()
        }

        setContentView(binding.root)
    }
}