package com.ethanpunter.charactersheets

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.ethanpunter.charactersheets.databinding.ActivityMainBinding
import com.ethanpunter.charactersheets.stats.PrimaryStat

class MainActivity : AppCompatActivity() {

    var res = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        val inflater: LayoutInflater =
            applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val binding = ActivityMainBinding.inflate(inflater)
        val stat = PrimaryStat("Strength", 21)
        binding.stat1 = stat
        binding.statBlockOne.root.setOnClickListener {
            binding.statBlockOne.root.layoutParams = ViewGroup.LayoutParams(100 * res, 100 * res)
            res += 1
            if (res > 10) {
                res = 1
            }
        }

        setContentView(binding.root)
    }
}