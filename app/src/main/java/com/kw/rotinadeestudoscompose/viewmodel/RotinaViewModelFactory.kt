package com.kw.rotinadeestudoscompose.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kw.rotinadeestudoscompose.data.AtividadeDataStore
import com.kw.rotinadeestudoscompose.data.RotinaRepository

class RotinaViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dataStore = AtividadeDataStore(context)
        val repository = RotinaRepository(dataStore)
        return RotinaViewModel(repository) as T
    }
}