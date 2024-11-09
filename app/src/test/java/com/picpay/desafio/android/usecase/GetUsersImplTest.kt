package com.picpay.desafio.android.usecase

import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.di.UserRepository
import com.picpay.desafio.android.domain.usecase.GetUsersImpl
import com.picpay.desafio.android.domain.usecase.di.GetUsers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.junit.runner.RunWith

@RunWith(MockitoJUnitRunner::class)
class GetUsersImplTest {

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var getUsers: GetUsers

    @Before
    fun setUp() {
        getUsers = GetUsersImpl(userRepository)
    }
    
    @Test
    fun `execute should return success with user list when repository returns data`() = runBlocking {
        // Mocking data
        val mockUsers = listOf(User(id = "1", name = "John", username = "john_doe", img = "url"))
        `when`(userRepository.getUsers()).thenReturn(Result.success(mockUsers))
        
        // Executing the use case
        val result = getUsers.execute()
        
        // Verifying the result
        assertTrue(result.isSuccess)
        assertEquals(mockUsers, result.getOrNull())
    }

    @Test
    fun `execute should return failure when repository throws an exception`() = runBlocking {
        // Mocking an exception
        `when`(userRepository.getUsers()).thenReturn(Result.failure(Exception("Network error")))

        // Executing the use case
        val result = getUsers.execute()

        // Verifying the result
        assertTrue(result.isFailure)
        assertEquals("Network error", result.exceptionOrNull()?.message)
    }

    @Test
    fun `execute should return success with empty list when repository returns empty data`() = runBlocking {
        // Mocking an empty response
        `when`(userRepository.getUsers()).thenReturn(Result.success(emptyList()))

        // Executing the use case
        val result = getUsers.execute()

        // Verifying the result
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull().isNullOrEmpty())
    }
}
