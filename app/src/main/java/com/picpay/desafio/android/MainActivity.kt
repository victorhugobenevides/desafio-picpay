package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.repository.UserRemoteDataSource
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.usecase.GetUsers
import com.picpay.desafio.android.domain.usecase.GetUsersImpl
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import com.picpay.desafio.android.ui.viewmodel.factory.MainViewModelFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this, MainViewModelFactory(getUsersUseCase()))
            .get(MainViewModel::class.java)

        observeViewModel()
    }

    private fun getUsersUseCase(): GetUsers {
        val service = createPicPayService()
        val repository = UserRemoteDataSource(service)
        return GetUsersImpl(repository)
    }

    private fun observeViewModel() {
        viewModel.users.observe(this) { users ->
            adapter.users = users!!
        }
        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun createPicPayService(): PicPayService {
         val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
        val gson: Gson by lazy { GsonBuilder().create() }
        val okHttp: OkHttpClient by lazy { OkHttpClient.Builder().build()}
        val retrofit: Retrofit by lazy { Retrofit.Builder().baseUrl(url).client(okHttp).addConverterFactory(GsonConverterFactory.create(gson)).build() }
        val service: PicPayService by lazy { retrofit.create(PicPayService::class.java)}

        return service
    }
}
