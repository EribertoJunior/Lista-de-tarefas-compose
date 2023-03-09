package com.example.listacompose.domain.usercase

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

internal class ListUserCaseImplTest {

    private lateinit var listUserCase: ListUserCase
    private lateinit var listRepository: ListRepository

    @Before
    fun setUp() {
        listRepository = mockk()
        listUserCase = spyk(ListUserCaseImpl(listRepository))
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `quando o repository retornar um item, deve retornar um item`() {
        coEvery { listRepository.getList() } returns flow {
            emit(
                listOf(mockk())
            )
        }

        runBlocking {
            val listFlow = listUserCase.invoke()
            assertEquals(1, listFlow.first().count())
        }
    }

    @Test
    fun `quando o repository retornar tres itens, deve retornar tres itens`() {
        coEvery { listRepository.getList() } returns flow {
            emit(
                listOf(
                    mockk(),
                    mockk(),
                    mockk()
                )
            )
        }

        runBlocking {
            val listFlow = listUserCase.invoke()
            assertEquals(3, listFlow.first().count())
        }
    }

    @Test
    fun `quando o repository retonar uma lista vazia, deve retornar uma lista vazia`() {
        coEvery { listRepository.getList() } returns flow {
            emit(listOf())
        }

        runBlocking {
            val listFlow = listUserCase.invoke()
            assertEquals(0, listFlow.first().count())
        }
    }

    @Test
    fun `quando o repository retorar uma lista de itens, deve retornar a mesma lista d itens`() {
        val taskList = listOf(
            Task(0, "La", "li"),
            Task(1, "Le", "La"),
            Task(2, "li", "Le"),
        )

        coEvery { listRepository.getList() } returns flow {
            emit(taskList)
        }

        runBlocking {
            val listFlow = listUserCase.invoke()
            assertEquals(taskList, listFlow.first())
        }
    }
}