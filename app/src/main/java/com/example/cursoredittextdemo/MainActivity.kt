package com.example.cursoredittextdemo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cursoredittextdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        }
        override fun dispatchTouchEvent(event: MotionEvent): Boolean {
            currentFocus?.let { focusView ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    val imm = getSystemService(InputMethodManager::class.java)
                    imm?.hideSoftInputFromWindow(focusView.windowToken, 0)
                    focusView.clearFocus()
                }
            }
            return super.dispatchTouchEvent(event)
        }
//


    }
//this is autocompletetextvie in this when i type then default soft keypad open but in this when i
//select numeric pad and write one input then automatically alphabetic pad occor how to solve this

//binding.acToNumber.setOnEditorActionListener(object : TextView.OnEditorActionListener {
//    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
//        when (actionId) {
//            EditorInfo.IME_ACTION_DONE -> {
//                Logger.d(TAG, "onEditorAction DONE")
//
//                // Hide the keyboard
//                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                imm.hideSoftInputFromWindow(binding.acToNumber.windowToken, 0)
//
//                // Update views
//                binding.acToNumber.visibility = View.GONE
//                binding.clToNumberView.visibility = View.VISIBLE
//
//                // Get and format the destination number
//                destinationNumber = binding.acToNumber.text.toString()
//                binding.tvToNumber.text = getFormattedNumber(destinationNumber)
//
//                // Reset input type correctly
//                binding.acToNumber.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE
//
//                return true
//            }
//        }
//        return false
//    }
//})
