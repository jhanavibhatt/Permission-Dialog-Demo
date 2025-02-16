package com.example.cursoredittextdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cursoredittextdemo.databinding.ActivityMain4Binding
import com.example.cursoredittextdemo.databinding.ActivityMainBinding
import com.example.cursoredittextdemo.databinding.DialpadBinding
import com.example.cursoredittextdemo.simplemobiletools.MyEditText

class MainActivity4 : AppCompatActivity() {
    lateinit var binding : ActivityMain4Binding
    private lateinit var dialPadBinding: DialpadBinding
    // Binding for included dialpad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dialPadBinding = DialpadBinding.bind(binding.dialpadLayout.root)
dialPadBinding.etNum.disableKeyboard()

        setupDialPadButtons(dialPadBinding.root, dialPadBinding.etNum)    }
    private fun setupDialPadButtons(layout: View, dialPadInput: MyEditText) {
        val dialPadIds = listOf(
            R.id.dialPad_0_holder to "0",
            R.id.dialPad_1_holder to "1",
            R.id.dialPad_2_holder to "2",
            R.id.dialPad_3_holder to "3",
            R.id.dialPad_4_holder to "4",
            R.id.dialPad_5_holder to "5",
            R.id.dialPad_6_holder to "6",
            R.id.dialPad_7_holder to "7",
            R.id.dialPad_8_holder to "8",
            R.id.dialPad_9_holder to "9",
            R.id.dialPad_asterisk_holder to "*",
            R.id.dialPad_hashtag_holder to "#"
        )

        for ((id, value) in dialPadIds) {
            layout.findViewById<View>(id)?.setOnClickListener {
                onDialPadButtonClick(dialPadInput, value)
            }
        }

        // Backspace button
        layout.findViewById<View>(R.id.ivBackPace)?.setOnClickListener {
            deleteLastDigit(dialPadInput)
        }
    }
    fun EditText.disableKeyboard() {
        showSoftInputOnFocus = false
    }
    private fun onDialPadButtonClick(dialPadInput: MyEditText, value: String) {
        dialPadInput.append(value)
        dialPadInput.text?.let { dialPadInput.setSelection(it.length) } // Move cursor to end
    }

    private fun deleteLastDigit(dialPadInput: MyEditText) {
        val currentText = dialPadInput.text.toString()
        if (currentText.isNotEmpty()) {
            dialPadInput.setText(currentText.substring(0, currentText.length - 1))
            dialPadInput.text?.let { dialPadInput.setSelection(it.length) }
        }
    }
}