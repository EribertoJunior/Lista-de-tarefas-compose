package com.example.listacompose.ui.screens

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.listacompose.data.model.Task
import com.example.listacompose.ui.components.TaskItem
import com.example.listacompose.ui.theme.ListaComposeTheme
import com.example.listacompose.ui.uistate.ListUiState
import com.example.listacompose.ui.viewmodels.ListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ListScreen(modifier: Modifier, viewModel: ListViewModel = getViewModel()) {
    val uiState by viewModel.state.collectAsState()

    when (uiState) {
        ListUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is ListUiState.Success -> {
            //val list = remember { (uiState as ListUiState.Success).data }

            ListScreen(modifier = modifier, (uiState as ListUiState.Success).data)
        }
    }
}

@Composable
fun ListScreen(modifier: Modifier, listTask: List<Task>) {

    LazyColumn(
        modifier = modifier,
        state = rememberLazyListState(),
        verticalArrangement = spacedBy(8.dp),
    ) {
        item {
            Spacer(modifier = Modifier)
        }

        items(listTask, key = { taks -> taks.id }) { task ->
            TaskItem(
                modifier = Modifier.padding(horizontal = 8.dp),
                task = task
            )
        }

        item {
            Spacer(modifier = Modifier)
        }
    }

}

@Preview
@Composable
fun ListScreenPreview() {
    ListaComposeTheme {
        Surface {
            ListScreen(
                modifier = Modifier.fillMaxSize(),
                listTask = listOf(
                    Task(1, "Titulo", LoremIpsum(50).values.first()),
                    Task(2, "Titulo2", LoremIpsum(30).values.first()),
                )
            )
        }
    }
}
