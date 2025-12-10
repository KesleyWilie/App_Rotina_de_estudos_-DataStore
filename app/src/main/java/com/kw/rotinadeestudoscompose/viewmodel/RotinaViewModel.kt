package com.kw.rotinadeestudoscompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kw.rotinadeestudoscompose.data.RotinaRepository
import com.kw.rotinadeestudoscompose.data.Atividade
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RotinaViewModel(private val repository: RotinaRepository) : ViewModel() {

    fun atividadesPorDia(dia: String): Flow<List<Atividade>> =
        repository.getAtividadesPorDia(dia)

    fun addAtividade(dia: String, desc: String) {
        viewModelScope.launch {
            try {
                repository.addAtividade(dia, desc)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deletar(atividade: Atividade) {
        viewModelScope.launch {
            try {
                repository.deletarAtividade(atividade)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun editar(atividade: Atividade) {
        viewModelScope.launch {
            try {
                repository.editarAtividade(atividade)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    val resumo: Flow<Map<String, Int>> =
        repository.getResumo().map { lista ->
            lista.groupBy { it.dia }.mapValues { it.value.size }
        }
}