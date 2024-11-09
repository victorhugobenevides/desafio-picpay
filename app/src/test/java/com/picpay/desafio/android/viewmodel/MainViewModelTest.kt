package com.picpay.desafio.android.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.usecase.di.GetUsers
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getUsers: GetUsers

    private lateinit var mainViewModel: MainViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `getUsers should emit Loading and then Error when fetching fails`() = runTest {
        // Configura o mock para retornar uma falha
        whenever(getUsers.execute()).thenReturn(Result.failure(Exception("Network error")))

        // Inicializa o ViewModel com o mock configurado
        mainViewModel = MainViewModel(getUsers)

        // Lista para capturar os estados emitidos
        val emittedStates = mutableListOf<MainViewModel.UiState>()

        // Observador que adiciona os estados na lista
        val observer = Observer<MainViewModel.UiState> { emittedStates.add(it) }
        mainViewModel.uiState.observeForever(observer)

        // Avança a execução das corrotinas até o final
        advanceUntilIdle()

        // Verifica a sequência de estados emitidos
        assertTrue(emittedStates.isNotEmpty())
        assertTrue(emittedStates[1] is MainViewModel.UiState.Loading)
        assertTrue(emittedStates[2] is MainViewModel.UiState.Error)
        assertEquals("Network error", (emittedStates[2] as MainViewModel.UiState.Error).message)

        // Remove o observador após o teste
        mainViewModel.uiState.removeObserver(observer)
    }


    @Test
    fun `getUsers should emit Loading and then Success when fetching succeeds`() = runTest {
        // Configura o caso de uso para retornar uma lista de usuários com sucesso
        val users = listOf(User(id = 1.toString(), name = "Test User", username = "testuser", img = "img_url"))
        whenever(getUsers.execute()).thenReturn(Result.success(users))

        mainViewModel = MainViewModel(getUsers)

        // Lista para capturar os estados emitidos
        val emittedStates = mutableListOf<MainViewModel.UiState>()

        // Observador que adiciona os estados na lista
        val observer = Observer<MainViewModel.UiState> { emittedStates.add(it) }
        mainViewModel.uiState.observeForever(observer)

        // Avança a execução até o final
        advanceUntilIdle()

        // Verifica a sequência de estados emitidos
        assertEquals(3, emittedStates.size)
        assertTrue(emittedStates[1] is MainViewModel.UiState.Loading)
        assertTrue(emittedStates[2] is MainViewModel.UiState.Success)
        assertEquals(users, (emittedStates[2] as MainViewModel.UiState.Success).users)

        // Remove o observador após o teste
        mainViewModel.uiState.removeObserver(observer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
