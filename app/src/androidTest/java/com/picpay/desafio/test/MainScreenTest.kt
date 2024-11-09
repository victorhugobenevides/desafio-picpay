import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.ui.components.MainScreen
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import org.junit.Rule
import org.junit.Test

class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mainScreen_displaysLoading() {
        // Configurar o conteúdo do Composable
        composeTestRule.setContent {
            // Cria um MutableState para simular o estado de Loading
            MainScreen(uiState = MainViewModel.UiState.Loading, navController = null)
        }

        // Verifica se o LoadingScreen é exibido
        composeTestRule.onNodeWithTag("Loading")
            .assertExists("O CircularProgressIndicator não foi encontrado.")
            .assertIsDisplayed()
    }

    @Test
    fun mainScreen_displaysError() {
        composeTestRule.setContent {
            MainScreen(uiState = MainViewModel.UiState.Error("Erro de carregamento"), navController = null)
        }

        // Verifica se a tela de erro é exibida
        composeTestRule.onNodeWithTag("Error")
            .assertExists("A tela de erro não foi encontrada.")
            .assertIsDisplayed()
    }

    @Test
    fun mainScreen_displaysSuccess() {
        // Simula um estado de sucesso com uma lista de usuários
        val users = listOf(User(id = "1", name = "Usuário 1", username = "user1", img = "https://example.com/avatar1.png"))

        composeTestRule.setContent {
            MainScreen(uiState = MainViewModel.UiState.Success(users), navController = null)
        }

        // Verifica se a lista de usuários é exibida
        composeTestRule.onNodeWithText("Usuário 1")
            .assertExists("O usuário não foi encontrado na lista.")
            .assertIsDisplayed()
    }
}
