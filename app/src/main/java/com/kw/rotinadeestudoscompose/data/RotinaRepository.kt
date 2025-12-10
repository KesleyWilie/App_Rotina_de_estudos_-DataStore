package com.kw.rotinadeestudoscompose.data

import kotlinx.coroutines.flow.Flow

class RotinaRepository(private val dataStore: AtividadeDataStore) {
    fun getAtividadesPorDia(dia: String): Flow<List<Atividade>> =
        dataStore.getAtividadesPorDia(dia)

    suspend fun addAtividade(dia: String, descricao: String) {
        dataStore.inserirAtividade(dia, descricao)
    }

    suspend fun deletarAtividade(atividade: Atividade) {
        dataStore.deletarAtividade(atividade)
    }

    suspend fun editarAtividade(atividade: Atividade) {
        dataStore.atualizarAtividade(atividade)
    }

    fun getResumo(): Flow<List<Atividade>> = dataStore.getTodasAtividades()
}