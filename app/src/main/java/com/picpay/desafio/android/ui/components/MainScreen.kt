import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.picpay.desafio.android.ui.components.UserList
import com.picpay.desafio.android.ui.theme.colorPrimary
import com.picpay.desafio.android.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(navController: NavHostController?) {

    val viewModel: MainViewModel = hiltViewModel()
    val uiState = viewModel.uiState.observeAsState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorPrimary)
    ) {
        when (val state = uiState.value) {
            is MainViewModel.UiState.Loading -> LoadingScreen()
            MainViewModel.UiState.Idle -> LoadingScreen()
            is MainViewModel.UiState.Success -> UserList(users = state.users)
            is MainViewModel.UiState.Error -> ErrorScreen(message = state.message)
            null -> {}
        }
    }
}
