package com.example.suitmedia.view.thirdscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmedia.data.UserRepository
import com.example.suitmedia.data.response.DataItem

class ThirdScreenViewModel(repository: UserRepository) : ViewModel() {
    val user: LiveData<PagingData<DataItem>> = repository.getUser().cachedIn(viewModelScope)
}