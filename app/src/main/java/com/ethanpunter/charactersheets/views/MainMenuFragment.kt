package com.ethanpunter.charactersheets.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanpunter.charactersheets.databinding.MainMenuBinding
import com.ethanpunter.charactersheets.viewmodels.MainMenuViewModel
import com.ethanpunter.charactersheets.views.adapters.CharacterListAdapter
import kotlinx.coroutines.launch

class MainMenuFragment : Fragment() {

    lateinit var mainMenuViewModel: MainMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = context?.applicationContext
        val binding = MainMenuBinding.inflate(inflater)
        binding.vm = mainMenuViewModel
        binding.btnAddCharSheet.setOnClickListener { lifecycleScope.launch { mainMenuViewModel.addCharacter() } }
        binding.charSheetList.layoutManager = LinearLayoutManager(context)
        binding.charSheetList.adapter = context?.let { CharacterListAdapter(mainMenuViewModel, it) }
        return binding.root
    }
}