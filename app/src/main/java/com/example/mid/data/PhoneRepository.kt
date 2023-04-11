package com.example.mid.data

import androidx.lifecycle.LiveData

class PhoneRepository(private val phoneDao:PhoneDao) {
    val read: LiveData<List<Phone>> = phoneDao.getAll()

    fun addPhone(phone:Phone){
        phoneDao.addPhone(phone)
    }

}