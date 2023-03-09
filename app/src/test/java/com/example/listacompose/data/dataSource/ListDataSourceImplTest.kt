package com.example.listacompose.data.dataSource

import com.example.listacompose.data.database.TaskDao
import com.example.listacompose.data.model.Task
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class ListDataSourceImplTest {

    private lateinit var taskDaoMock: TaskDao
    private lateinit var listDataSource: ListDataSource

    @Before
    fun setUp() {
        taskDaoMock = mockk()
        listDataSource = spyk(ListDataSourceImpl(taskDaoMock))
    }

    @After
    fun tearDown(){
        clearAllMocks()
    }

    @Test
    fun `quanto taskDao retornar um item, deve retornar um item`() {
        every { taskDaoMock.getTasks() } returns flow {
            emit(listOf(mockk()))
        }

        runBlocking {
            val tasks = listDataSource.getTasks()
            assertEquals(1, tasks.first().count())
        }
    }

    @Test
    fun `quando taskDao retornar cinco itens, deve retornar cinco itens`() {
        every { taskDaoMock.getTasks() } returns flow {
            emit(
                listOf(
                    mockk(),
                    mockk(),
                    mockk(),
                    mockk(),
                    mockk(),
                )
            )
        }

        runBlocking {
            val tasks = listDataSource.getTasks()
            assertEquals(5, tasks.first().count())
        }
    }

    @Test
    fun `quando receber uma lista de tres itens, deve retornar a mesma lista com tres itens`() {
        val taskList = listOf(
            Task(0, "La", "li"),
            Task(1, "Le", "La"),
            Task(2, "li", "Le"),
        )

        every { taskDaoMock.getTasks() } returns flow {
            emit(
                taskList
            )
        }

        runBlocking {
            val taskFlow = listDataSource.getTasks()
            assertEquals(taskList, taskFlow.first())
        }
    }

    @Test
    fun `quando nao houver itens salvos, deve retornar uma lista vazia`() {
        every { taskDaoMock.getTasks() } returns flow {
            emit(
                listOf()
            )
        }

        runBlocking {
            val listFlow = listDataSource.getTasks()

            assertEquals(0, listFlow.first().count())
        }
    }
}