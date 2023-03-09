package com.example.listacompose.data.repository

import com.example.listacompose.data.dataSource.ListDataSource
import com.example.listacompose.data.model.Task
import com.example.listacompose.domain.repository.ListRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


internal class ListRepositoryImplTest {

    private lateinit var listDataSourceMock: ListDataSource
    private lateinit var listRepository: ListRepository

    @Before
    fun setUp() {
        listDataSourceMock = mockk()
        listRepository = spyk(ListRepositoryImpl(listDataSource = listDataSourceMock))
    }

    @After
    fun tearDown(){
        clearAllMocks()
    }

    @Test
    fun `quando o data_source retornar um item, deve retornar um item`() {

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
    fun `quando o data_source retornar cinco itens, deve retornar cinco itens`() {
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