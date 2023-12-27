package com.example.suitmedia.view.firstscreen

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
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
                showAlert(
                    resources.getString(R.string.result),
                    resources.getString(R.string.isPalindrome)
                )
            } else {
                showAlert(
                    resources.getString(R.string.result),
                    resources.getString(R.string.notPalindrome)
                )
            }
        }

        binding.btnNext.setOnClickListener {
            val name = binding.edtName.text.toString()
            if (name.isNotEmpty()){
                val intent = Intent(applicationContext, SecondScreenActivity::class.java)
                intent.putExtra(SecondScreenActivity.EXTRA_NAME, name)
                startActivity(intent)
            } else {
                showAlert(
                    resources.getString(R.string.warning),
                    resources.getString(R.string.message_warning)
                )
            }
        }

    }

    private fun isPalindrome(str: String): Boolean {
        val reversedStr = str.reversed()
        return str == reversedStr
    }

    private fun showAlert(
        title: String,
        message: String,
        onPositiveClick: ((dialog: DialogInterface, which: Int) -> Unit)? = null
    ) {
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(resources.getString(R.string.ok), onPositiveClick)
            create()
            show()
        }
    }

}