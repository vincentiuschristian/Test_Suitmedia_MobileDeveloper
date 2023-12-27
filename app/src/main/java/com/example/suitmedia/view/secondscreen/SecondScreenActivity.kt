package com.example.suitmedia.view.secondscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat.getParcelableExtra
import com.example.suitmedia.R
import com.example.suitmedia.data.response.DataItem
import com.example.suitmedia.databinding.ActivitySecondScreenBinding
import com.example.suitmedia.view.thirdscreen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topBar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.back_icon)
        }

        val user = getParcelableExtra(intent, KEY_DATA, DataItem::class.java)

        if (user!= null){
            binding.apply {
                tvFirstname.text = user.firstName
                tvLastname.text = user.lastName
            }
        }

        binding.btnChooseAUser.setOnClickListener{
            startActivity(Intent(this, ThirdScreenActivity::class.java))
        }

        val name = intent.getStringExtra(EXTRA_NAME)
        binding.tvName.text = name

    }

    companion object {
        const val KEY_DATA = "key_data"
        const val EXTRA_NAME = "extra_name"
    }

}