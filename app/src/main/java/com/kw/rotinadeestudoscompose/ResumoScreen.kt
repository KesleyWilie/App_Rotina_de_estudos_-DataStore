package com.kw.rotinadeestudoscompose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kw.rotinadeestudoscompose.viewmodel.RotinaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumoScreen(
    viewModel: RotinaViewModel,
    onBackClick: () -> Unit
) {
    val resumo by viewModel.resumo.collectAsState(initial = emptyMap())

    val total = resumo.values.sum()

    val textoResumo = buildString {
        append("Resumo semanal:\n\n")
        resumo.forEach { (dia, qtd) ->
            append("$dia: $qtd atividade(s)\n")
        }
        append("\nTotal: $total atividade(s)")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Resumo Semanal",
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
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = textoResumo,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 18.sp,
                    lineHeight = 24.sp
                )
            }
        }
    }
}
