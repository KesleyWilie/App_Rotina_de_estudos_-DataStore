package com.kw.rotinadeestudoscompose.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "rotina_datastore")

class AtividadeDataStore(private val context: Context) {
    private val atividadesKey = stringPreferencesKey("atividades")
    private val maxIdKey = intPreferencesKey("max_id")
    private val gson = Gson()
    private val typeToken = object : TypeToken<List<Atividade>>() {}.type

    // Metodo auxiliar para obter o máximo ID
    private fun getNextId(atividades: List<Atividade>): Int {
        return if (atividades.isEmpty()) 1
        else atividades.maxByOrNull { it.id }?.id?.plus(1) ?: 1
    }

    // Obter todas as atividades
    private suspend fun getAllAtividades(): List<Atividade> {
        return try {
            val jsonString = context.dataStore.data
                .map { preferences ->
                    preferences[atividadesKey] ?: "[]"
                }
                .first()

            gson.fromJson(jsonString, typeToken) ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    // Salvar todas as atividades
    private suspend fun saveAllAtividades(atividades: List<Atividade>) {
        try {
            val jsonString = gson.toJson(atividades)
            context.dataStore.edit { preferences ->
                preferences[atividadesKey] = jsonString
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Obter atividades por dia
    fun getAtividadesPorDia(dia: String): Flow<List<Atividade>> {
        return context.dataStore.data
            .map { preferences ->
                try {
                    val jsonString = preferences[atividadesKey] ?: "[]"
                    val atividades = gson.fromJson<List<Atividade>>(jsonString, typeToken) ?: emptyList()
                    atividades.filter { it.dia == dia }
                } catch (e: Exception) {
                    e.printStackTrace()
                    emptyList()
                }
            }
    }

    // Obter todas as atividades
    fun getTodasAtividades(): Flow<List<Atividade>> {
        return context.dataStore.data
            .map { preferences ->
                try {
                    val jsonString = preferences[atividadesKey] ?: "[]"
                    gson.fromJson(jsonString, typeToken) ?: emptyList()
                } catch (e: Exception) {
                    e.printStackTrace()
                    emptyList()
                }
            }
    }

    // Inserir nova atividade
    suspend fun inserirAtividade(dia: String, descricao: String) {
        try {
            val atividadesAtuais = getAllAtividades().toMutableList()
            val newId = getNextId(atividadesAtuais)
            val novaAtividade = Atividade(id = newId, dia = dia, descricao = descricao)
            atividadesAtuais.add(novaAtividade)
            saveAllAtividades(atividadesAtuais)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Atualizar atividade existente
    suspend fun atualizarAtividade(atividadeAtualizada: Atividade) {
        try {
            val atividadesAtuais = getAllAtividades().toMutableList()
            val index = atividadesAtuais.indexOfFirst { it.id == atividadeAtualizada.id }

            if (index != -1) {
                atividadesAtuais[index] = atividadeAtualizada
                saveAllAtividades(atividadesAtuais)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Deletar atividade
    suspend fun deletarAtividade(atividade: Atividade) {
        try {
            val atividadesAtuais = getAllAtividades().toMutableList()
            atividadesAtuais.removeIf { it.id == atividade.id }
            saveAllAtividades(atividadesAtuais)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}