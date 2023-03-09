package com.example.listacompose.data.repository

import com.example.listacompose.data.dataSource.ListDataSource
import com.example.listacompose.data.model.Task
import com.example.listacompose.domain.repository.ListRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


internal class ListRepositoryImplTest {

    private lateinit var listDataSourceMock: ListDataSource
    private lateinit var listRepository: ListRepository

    @Before
    fun before() {
        listDataSourceMock = mockk()
        listRepository = spyk(ListRepositoryImpl(listDataSource = listDataSourceMock))
    }

    @Test
    fun `quando houver um item salvo deve retornar um item`() {

        coEvery { listDataSourceMock.getTasks() } returns flow {
            emit(
                listOf(mockk(relaxed = true))
            )
        }

        runBlocking {
            val result = listRepository.getList()
            assertEquals(1, result.first().count())
        }
    }

    @Test
    fun `quando houver cinco itens salvos, deve retornar cinco itens`() {
        coEvery { listDataSourceMock.getTasks() } returns flow {
            emit(
                listOf(
                    mockk(relaxed = true),
                    mockk(relaxed = true),
                    mockk(relaxed = true),
                    mockk(relaxed = true),
                    mockk(relaxed = true)
                )
            )
        }

        runBlocking {
            val result = listRepository.getList()
            assertEquals(5, result.first().count())
        }
    }

    @Test
    fun `deve retornar o mesmo objeto que veio do 'data source'`(){
        val task = Task(0, "teste", "lala")

        coEvery { listDataSourceMock.getTasks() } returns flow {
            emit(
                listOf(task)
            )
        }

        runBlocking {
            val listFlow = listRepository.getList()
            assertEquals(task, listFlow.first()[0])
        }
    }
}