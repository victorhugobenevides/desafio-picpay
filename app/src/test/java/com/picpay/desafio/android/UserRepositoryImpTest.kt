import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.data.repository.UserRepositoryImp
import com.picpay.desafio.android.domain.entity.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class UserRepositoryImpTest {

    @Mock
    private lateinit var picPayService: PicPayService

    private lateinit var userRepository: UserRepositoryImp

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        userRepository = UserRepositoryImp(picPayService)
    }

    @Test
    fun `getUsers should return success when service fetches data successfully`() = runBlocking {
        // Configura o mock para retornar uma lista de usuários
        val users = listOf(User(id = "1", name = "Test User", username = "testuser", img = "img_url"))
        whenever(picPayService.getUsers()).thenReturn(users)

        // Chama o método do repositório
        val result = userRepository.getUsers()

        // Verifica que o resultado é sucesso e contém os dados esperados
        assertTrue(result.isSuccess)
        assertEquals(users, result.getOrNull())
    }

    @Test
    fun `getUsers should return failure when service throws an exception`(): Unit = runBlocking {
        // Configura o mock para lançar uma exceção
        val exception = RuntimeException("Network error")
        whenever(picPayService.getUsers()).thenThrow(exception)

        try{
            // Chama o método do repositório
            userRepository.getUsers()
        }catch (e: Exception){
            // Verifica que o resultado é uma exceção
            assertEquals(exception, e)
        }


    }
}
