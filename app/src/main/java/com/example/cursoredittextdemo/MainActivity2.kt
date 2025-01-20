package com.example.cursoredittextdemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity(), CustomPermissionFragment.PermissionCallback {
    private var isPermissionDialogShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        checkAndRequestContactPermission()

    }
    override fun onResume() {
        super.onResume()

        // Re-check if permission is granted on returning to the app
        if (CustomPermissionFragment.isPermissionGranted(this)) {
            isPermissionDialogShown = false // Reset flag since permission is now granted
        } else if (!isPermissionDialogShown) {
            checkAndRequestContactPermission() // Show dialog only if permission is not granted
        }
    }
        private fun checkAndRequestContactPermission() {
            if (!CustomPermissionFragment.isPermissionGranted(this)) {
                isPermissionDialogShown = true
                val fragment = CustomPermissionFragment()
                fragment.setPermissionCallback(this)

                supportFragmentManager.beginTransaction()
                    .add(fragment, "PermissionFragment")
                    .commit()
            }
        }

    override fun onPermissionGranted() {

    }

    override fun onPermissionDenied() {
       // TODO("Not yet implemented")
    }

}