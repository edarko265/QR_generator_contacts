package com.example.contact_qrcode_generated.data

class ContactRepository(private val dao: ContactDao) {
    val contacts = dao.getAllContacts()
    suspend fun addContact(contact: Contact) = dao.insertContact(contact)
    suspend fun delete(contact: Contact) = dao.delete(contact)


}