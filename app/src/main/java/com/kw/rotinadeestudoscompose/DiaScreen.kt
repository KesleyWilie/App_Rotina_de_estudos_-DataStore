package com.kw.rotinadeestudoscompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kw.rotinadeestudoscompose.data.Atividade
import com.kw.rotinadeestudoscompose.viewmodel.RotinaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaScreen(
    dia: String,
    viewModel: RotinaViewModel,
    onResumoClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val lista by viewModel.atividadesPorDia(dia).collectAsState(initial = emptyList())

    var showAddDialog by remember { mutableStateOf(false) }
    var atividadeParaEdicao by remember { mutableStateOf<Atividade?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        dia,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = lista,
                    key = { it.id }
                ) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.descricao,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 16.dp, horizontal = 8.dp),
                                fontSize = 16.sp
                            )

                            // 1. BOTÃO DE EDITAR
                            IconButton(onClick = {
                                atividadeParaEdicao = item
                            }) {
                                Icon(
                                    Icons.Default.Edit,
                                    contentDescription = "Editar atividade",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }

                            // 2. BOTÃO DE DELETAR
                            IconButton(onClick = {
                                viewModel.deletar(item)
                            }) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Deletar atividade",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { showAddDialog = true },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Adicionar")
                }

                Button(
                    onClick = onResumoClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Resumo")
                }
            }
        }
    }

    if (showAddDialog) {
        AddMateriaDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { materia ->
                viewModel.addAtividade(dia, materia)
                showAddDialog = false
            }
        )
    }

    atividadeParaEdicao?.let { atividade ->
        EditMateriaDialog(
            atividade = atividade,
            onDismiss = { atividadeParaEdicao = null },
            onConfirm = { novaDescricao ->
                // Cria uma cópia da Atividade com a nova descrição e chama o editar no ViewModel
                viewModel.editar(atividade.copy(descricao = novaDescricao))
                atividadeParaEdicao = null
            }
        )
    }
}

@Composable
fun AddMateriaDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Adicionar matéria") },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Formato: Matéria - HH:MM") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (text.isNotBlank()) onConfirm(text.trim())
                }
            ) {
                Text("Adicionar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun EditMateriaDialog(
    atividade: Atividade,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var text by remember { mutableStateOf(atividade.descricao) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Atividade") },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Formato: Matéria - HH:MM") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (text.isNotBlank()) onConfirm(text.trim())
                }
            ) {
                Text("Salvar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}