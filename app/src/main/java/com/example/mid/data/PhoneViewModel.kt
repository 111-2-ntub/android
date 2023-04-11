package com.example.mid.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.viewmodel
import androidx.lifecycle.LiveData


class PhoneViewModel(application: Application) : AndroidViewModel(application) {
     val getAll: LiveData<List<Phone>>
    private val repository: PhoneRepository

    init {
        val phoneDao: PhoneDao = PhoneDatabase.getDatabase(application).phoneDao()
        repository= PhoneRepository(phoneDao)
        getAll=repository.read
    }


     fun addPhone(phone:Phone){
       repository.addPhone(phone)
    }
}