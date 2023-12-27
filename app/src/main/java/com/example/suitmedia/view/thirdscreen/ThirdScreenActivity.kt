package com.example.suitmedia.view.thirdscreen

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmedia.R
import com.example.suitmedia.data.ViewModelFactory
import com.example.suitmedia.databinding.ActivityThirdScreenBinding
import com.example.suitmedia.view.adapter.LoadingStateAdapter
import com.example.suitmedia.view.adapter.UserAdapter

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var adapter: UserAdapter
    private val viewModel: ThirdScreenViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topBar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.back_icon)
        }

        adapter = UserAdapter()
        binding.apply {
            rvUser.adapter = adapter
            rvUser.layoutManager = LinearLayoutManager(applicationContext)
            refresh.setOnRefreshListener {
                getUser()
                refresh.isRefreshing = false
            }
        }

        getUser()
    }

    private fun getUser() {
        showLoading(true)
        binding.apply {
            tvEmpty.visibility = View.VISIBLE
            rvUser.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    adapter.retry()
                }
            )
        }

        viewModel.user.observe(this) { result ->
            showLoading(false)
            if (result != null) {
                adapter.submitData(lifecycle, result)
                binding.tvEmpty.visibility = View.GONE
            } else {
                binding.apply {
                    refresh.visibility = View.GONE
                    tvEmpty.visibility = View.VISIBLE
                    rvUser.visibility = View.GONE
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}