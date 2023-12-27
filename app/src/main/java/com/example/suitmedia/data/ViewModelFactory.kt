package com.example.suitmedia.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.suitmedia.di.Injection
import com.example.suitmedia.view.thirdscreen.ThirdScreenViewModel

class ViewModelFactory(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass) {

        ThirdScreenViewModel::class.java -> ThirdScreenViewModel(repository)

        else ->throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    } as T

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(): ViewModelFactory {
            if (INSTANCE == null){
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository())
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}