package com.example.contact_qrcode_generated

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contact_qrcode_generated.data.Contact
import com.example.contact_qrcode_generated.data.ContactViewModel
import com.example.contact_qrcode_generated.data.ContactViewModelFactory
import com.example.contact_qrcode_generated.ui.theme.Contact_QRCode_GeneratedTheme
import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val factory = ContactViewModelFactory(applicationContext)
            val viewModel: ContactViewModel = viewModel(factory = factory)

            Contact_QRCode_GeneratedTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ContactApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun ContactApp(viewModel: ContactViewModel) {
    val context = LocalContext.current
    val contacts by viewModel.contacts.collectAsState(initial = emptyList())

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var selectedContact by remember { mutableStateOf<Contact?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        ContactInputForm(
            firstName = firstName,
            lastName = lastName,
            phone = phone,
            email = email,
            onFirstNameChange = { firstName = it },
            onLastNameChange = { lastName = it },
            onPhoneChange = { phone = it },
            onEmailChange = { email = it },
            onSave = {
                if (firstName.isNotBlank() && phone.isNotBlank()) {
                    viewModel.addContact(Contact(firstName = firstName, lastName = lastName, phone = phone, email = email))
                    firstName = ""
                    lastName = ""
                    phone = ""
                    email = ""
                    Toast.makeText(context, "Contact Saved", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "First name & phone required", Toast.LENGTH_SHORT).show()
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            items(contacts) { contact ->
                ContactCard(
                    firstName = contact.firstName,
                    lastName = contact.lastName,
                    phone = contact.phone,
                    email = contact.email,
                    onClick = {
                        selectedContact = contact
                    },
                    onDelete = {
                        viewModel.deleteContact(contact)
                        Toast.makeText(context, "Deleted: ${contact.firstName}", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        // Show QR Code Dialog
        selectedContact?.let { contact ->
            AlertDialog(
                onDismissRequest = { selectedContact = null },
                confirmButton = {
                    TextButton(onClick = { selectedContact = null }) {
                        Text("Close")
                    }
                },
                title = { Text("QR Code for ${contact.firstName}") },
                text = {
                    QRCodeImage(
                        data1 = contact.firstName,
                        data2 = contact.lastName,
                        data3 = contact.phone,
                        data4 = contact.email,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
            )
        }
    }
}

@Composable
fun ContactInputForm(
    firstName: String,
    lastName: String,
    phone: String,
    email: String,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Add New Contact", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(value = firstName, onValueChange = onFirstNameChange, label = { Text("First Name") })
        OutlinedTextField(value = lastName, onValueChange = onLastNameChange, label = { Text("Last Name") })
        OutlinedTextField(value = phone, onValueChange = onPhoneChange, label = { Text("Phone") })
        OutlinedTextField(value = email, onValueChange = onEmailChange, label = { Text("Email") })
        Button(onClick = onSave, modifier = Modifier.align(Alignment.End)) {
            Text("Save")
        }
    }
}

@Composable
fun QRCodeImage(
    data1: String,
    data2: String,
    data3: String,
    data4: String,
    modifier: Modifier = Modifier
) {
    val qrBitmap: Bitmap? = generateQRCode(data1, data2, data3, data4)
    qrBitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = "QR Code",
            modifier = modifier,
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun ContactCard(
    firstName: String,
    lastName: String,
    phone: String,
    email: String,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("$firstName $lastName", fontWeight = FontWeight.Bold)
                    Text(phone)
                    Text(email, fontStyle = FontStyle.Italic)
                }
                Button(
                    onClick = onDelete,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Delete")
                }
            }
        }
    }
}

