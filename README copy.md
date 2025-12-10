# 📚 Rotina de Estudos da Semana

## 📋 Sobre o Projeto

Aplicativo Android desenvolvido em duas versões (XML e Jetpack Compose) para gerenciamento de rotina de estudos semanal. Permite organizar matérias e horários por dia da semana, adicionar novas tarefas e visualizar um resumo da carga de estudos.

---

## 🎯 Objetivos do Aplicativo

- **Organização**: Gerenciar a rotina de estudos de forma clara e visual
- **Flexibilidade**: Adicionar, visualizar e organizar matérias por dia da semana
- **Acompanhamento**: Visualizar resumo semanal com total de tarefas
- **Praticidade**: Interface simples e intuitiva para uso diário

---

## 🏗️ Arquitetura

### **Versão XML (Activities)**
```
MainActivity (Lista de dias)
    ↓
DiaActivity (Tarefas do dia)
    ↓
ResumoActivity (Resumo semanal)
```

### **Versão Compose (Navigation)**
```
MainActivity → Compose
    ↓
MainScreen → DiaScreen → ResumoScreen
    (Navigation Compose)
```

---

## 📱 Funcionalidades

### ✅ Tela Principal
- Lista dos 7 dias da semana
- Navegação para detalhes de cada dia
- Design clean com cards clicáveis

### ✅ Tela do Dia
- Visualização das matérias/tarefas do dia
- Botão "Adicionar" para novas tarefas
- Botão "Resumo" para visualizar estatísticas
- Formato: `Matéria - HH:MM`

### ✅ Adicionar Tarefa
- Dialog/AlertDialog para entrada de dados
- Formato sugerido: `Nome da Matéria - Horário`
- Validação de entrada

### ✅ Tela de Resumo
- Quantidade de tarefas por dia
- Total de tarefas na semana
- Visão geral da carga de estudos

---

## 🛠️ Tecnologias Utilizadas

### **Versão XML**
- **Kotlin** - Linguagem principal
- **Activities** - Gerenciamento de telas
- **XML Layouts** - Interface do usuário
- **ListView/ArrayAdapter** - Listas dinâmicas
- **AlertDialog** - Diálogos de entrada
- **Intent** - Navegação entre Activities

### **Versão Jetpack Compose**
- **Kotlin** - Linguagem principal
- **Jetpack Compose** - UI declarativa
- **Material 3** - Design system moderno
- **Navigation Compose** - Navegação declarativa
- **State Management** - `remember`, `mutableStateOf`
- **LazyColumn** - Listas otimizadas
- **Scaffold** - Estrutura de layout

---

## 📦 Estrutura do Projeto

```
app/
├── src/main/
│   └── java/com/example/rotinadeestudoscompose/
│       ├── MainActivity.kt (Activity principal)
│       ├── RotinaApp.kt (Navegação)
│       ├── MainScreen.kt (Tela principal)
│       ├── DiaScreen.kt (Tela do dia)
│       ├── ResumoScreen.kt (Tela resumo)
│       ├── Repository.kt (Dados)
│       └── ui/theme/
│           └── Theme.kt (Tema Material 3)
```

---

## 🎨 Design

### **Paleta de Cores (Compose)**
- **Primary**: `#6200EE` (Roxo)
- **Primary Container**: `#BB86FC` (Roxo claro)
- **Secondary**: `#03DAC6` (Turquesa)
- **Background**: `#FFFBFE` (Branco suave)

### **Componentes Visuais**
- **Cards** com elevação
- **TopAppBar** com título e navegação
- **Buttons** com espaçamento adequado
- **LazyColumn** para listas scrolláveis
- **AlertDialog/Dialog** para input de dados

---

## 🔧 Como Executar

### **Pré-requisitos**
- Android Studio (última versão)
- SDK Android 24+ (Nougat)
- Kotlin 1.9+
- Gradle 8.0+

### **Passos**
1. Clone o repositório
2. Abra o projeto no Android Studio
3. Sincronize o Gradle
4. Execute no emulador ou dispositivo físico

```bash

./gradlew :app:installDebug
```

---

## 📊 Comparação XML vs Compose

| Aspecto | XML | Jetpack Compose |
|---------|-----|-----------------|
| **Código** | Separado (XML + Kotlin) | Tudo em Kotlin |
| **Navegação** | Intents | Navigation Compose |
| **Estado** | Manual (findViewById) | Declarativo (State) |
| **Preview** | Layout Editor | @Preview em tempo real |
| **Manutenção** | Mais verboso | Mais conciso |
| **Performance** | Boa | Otimizada (recomposição) |
| **Curva de aprendizado** | Baixa | Média |

---

## 💡 Conceitos Aplicados

### **Programação Android**
✅ Ciclo de vida de Activities  
✅ Navegação entre telas  
✅ Gerenciamento de estado  
✅ Listas dinâmicas  
✅ Diálogos e inputs  

### **Jetpack Compose**
✅ Composables functions  
✅ State hoisting  
✅ Recomposição  
✅ Material Design 3  
✅ Navigation component  
✅ Theme customization  

### **Boas Práticas**
✅ Repository pattern  
✅ Separação de responsabilidades  
✅ Código limpo e legível  
✅ Comentários quando necessário  
✅ Nomenclatura clara  

---

## 🚀 Melhorias Futuras

- [ ] Persistência de dados (Room Database)
- [ ] Notificações para horários de estudo
- [ ] Edição e exclusão de tarefas
- [ ] Temas personalizáveis
- [ ] Estatísticas avançadas
- [ ] Export/Import de rotina
- [ ] Widget para tela inicial
- [ ] Modo Pomodoro integrado
---