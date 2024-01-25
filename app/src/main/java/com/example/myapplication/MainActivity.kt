package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    private var messageToDecrypt: String? = null
    private var messageToEncrypt: String? = null
    val cryptoManager = CryptoManager()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        encryptMsg("Hello")
        decryptMsg()
    }

    private fun decryptMsg() {
        val file = File(filesDir, "secret.txt")
        messageToDecrypt = cryptoManager.decrypt(
            inputStream = FileInputStream(file)
        ).decodeToString()
    }

    private fun encryptMsg(messageToEncrypt: String = "Hello") {
        val bytes = messageToEncrypt.encodeToByteArray()
        val file = File(filesDir, "secret.txt")
        if (!file.exists()) {
            file.createNewFile()
        }

        val fos = FileOutputStream(file)

        this.messageToEncrypt = cryptoManager.encrypt(
            bytes = bytes,
            outputStream = fos
        ).decodeToString()
    }
}