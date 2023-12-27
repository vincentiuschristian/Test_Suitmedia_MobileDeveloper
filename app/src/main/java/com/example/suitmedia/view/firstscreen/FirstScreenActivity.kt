package com.example.suitmedia.view.firstscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmedia.R
import com.example.suitmedia.databinding.ActivityFirstScreenBinding
import com.example.suitmedia.view.secondscreen.SecondScreenActivity

class FirstScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            val text = binding.edtPalindrome.text.toString()
            if (isPalindrome(text)) {
                showToast(resources.getString(R.string.isPalindrome))
            } else {
                showToast(resources.getString(R.string.notPalindrome))
            }
        }

        binding.btnNext.setOnClickListener {
            val name = binding.edtName.text.toString()
            val intent = Intent(applicationContext, SecondScreenActivity::class.java)
            intent.putExtra(SecondScreenActivity.EXTRA_NAME, name)
            startActivity(intent)
        }

    }

    private fun isPalindrome(str: String): Boolean {
        val reversedStr = str.reversed()
        return str == reversedStr
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}