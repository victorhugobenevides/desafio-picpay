import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertIsDisplayed
import org.junit.Rule
import org.junit.Test

class LoadingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingScreen_displaysCircularProgressIndicator() {
        // Desativa as animações para o ambiente de teste
        composeTestRule.mainClock.autoAdvance = false
        // Configura o conteúdo para renderizar o LoadingScreen
        composeTestRule.setContent {
            LoadingScreen()
        }

        // Avança o relógio manualmente para garantir que o conteúdo tenha sido renderizado
        composeTestRule.mainClock.advanceTimeByFrame()

        // Verifica se o CircularProgressIndicator com a tag "Loading" existe e está visível
        composeTestRule.onNodeWithTag("Loading")
            .assertExists("O CircularProgressIndicator não foi encontrado.")
            .assertIsDisplayed()
    }
}
