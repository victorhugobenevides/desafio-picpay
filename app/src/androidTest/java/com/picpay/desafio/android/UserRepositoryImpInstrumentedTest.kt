package com.picpay.desafio.android

import com.google.gson.Gson
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.data.repository.UserRepositoryImp
import com.picpay.desafio.android.domain.entity.User
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.IOException
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(androidx.test.runner.AndroidJUnit4::class)
class UserRepositoryImpInstrumentedTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var userRepository: UserRepositoryImp
    private lateinit var picPayService: PicPayService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        // Configura o Retrofit com o MockWebServer
        picPayService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PicPayService::class.java)

        userRepository = UserRepositoryImp(picPayService)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getUsers_shouldReturnSuccess_whenServiceFetchesDataSuccessfully() = runBlocking {
        // Simula uma resposta de sucesso com uma lista de usuários
        val users = listOf(User(id = "1", name = "Test User", username = "testuser", img = "img_url"))
        val responseBody = Gson().toJson(users)
        mockWebServer.enqueue(MockResponse().setBody(responseBody).setResponseCode(200))

        // Chama o método do repositório
        val result = userRepository.getUsers()

        // Verifica que o resultado é sucesso e contém os dados esperados
        assertTrue(result.isSuccess)
        assertEquals(users, result.getOrNull())
    }

    @Test
    fun getUsers_shouldReturnFailure_whenServiceReturnsHttpError() = runBlocking {
        // Simula uma resposta de erro 500
        mockWebServer.enqueue(MockResponse().setResponseCode(500))

        // Chama o método do repositório
        val result = userRepository.getUsers()

        // Verifica que o resultado é falha e contém uma HttpException
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IOException)
        assertTrue(result.exceptionOrNull()?.message?.contains("Erro HTTP") == true)
    }

    @Test
    fun getUsers_shouldReturnFailure_whenNetworkFails() = runBlocking {
        // Encerra o MockWebServer para simular uma falha de rede
        mockWebServer.shutdown()

        // Chama o método do repositório
        val result = userRepository.getUsers()

        // Verifica que o resultado é falha e contém uma IOException
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IOException)
        assertTrue(result.exceptionOrNull()?.message?.contains("Erro de rede") == true)
    }
}
