import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.data.repository.UserRepositoryImp
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserRepositoryImpTest {

    private lateinit var userRepository: UserRepository
    private val picPayService: PicPayService = mock()

    @Before
    fun setUp() {
        userRepository = UserRepositoryImp(picPayService)
    }

    @Test
    fun `getUsers returns success when service returns user list`(): Unit = runBlocking {
        // Arrange
        val mockUsers = listOf(User(id = "1", name = "John", username = "john_doe", img = "url"))
        whenever(picPayService.getUsers()).thenReturn(mockUsers)

        // Act
        val result = userRepository.getUsers()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(mockUsers, result.getOrNull())
        verify(picPayService).getUsers()
    }

    @Test
    fun `getUsers returns failure when service throws an exception`(): Unit = runBlocking {
        // Arrange
        val exception = RuntimeException("Network error") // Exceção não verificada
        whenever(picPayService.getUsers()).thenThrow(exception)

        // Act
        val result = userRepository.getUsers()

        // Assert
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verify(picPayService).getUsers()
    }
}
