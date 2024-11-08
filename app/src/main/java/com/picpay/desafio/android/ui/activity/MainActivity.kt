package com.picpay.desafio.android.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.UserListAdapter
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter


    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(this) { uiState ->
            when (uiState) {
                is MainViewModel.UiState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is MainViewModel.UiState.Success -> {
                    progressBar.visibility = View.GONE
                    adapter.users = uiState.users
                }

                is MainViewModel.UiState.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, uiState.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
