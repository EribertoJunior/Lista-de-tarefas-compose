package com.example.listacompose.data.repository

import com.example.listacompose.data.dataSource.ListDataSource
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test


internal class ListRepositoryImplTest {

    private val listDataSourceMock: ListDataSource = mockk()
    private val listRepository = spyk(ListRepositoryImpl(listDataSource = listDataSourceMock))

    @Test
    fun `quando houver itens salvos deve retornar esses itens`() {
        coEvery { listDataSourceMock.getTasks() } returns flow {
            emit(listOf(mockk(relaxed = true)))
        }

        runBlocking {
            val result = listRepository.getList()
            assertEquals(1, result.count())
        }
    }
}