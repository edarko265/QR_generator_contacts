package com.example.contact_qrcode_generated.data
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ContactViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val repository: ContactRepository

    init {
        val dao = ContactDatabase.getDatabase(context).contactDao()
        repository = ContactRepository(dao)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactViewModel(repository) as T
    }
}