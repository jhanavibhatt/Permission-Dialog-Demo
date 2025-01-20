package com.example.cursoredittextdemo

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


class CustomPermissionFragment : Fragment() {

    interface PermissionCallback {
        fun onPermissionGranted()
        fun onPermissionDenied()
    }

    private var callback: PermissionCallback? = null
    private var permissionDialog: AlertDialog? = null

    fun setPermissionCallback(callback: PermissionCallback) {
        this.callback = callback
    }

    override fun onResume() {
        super.onResume()

        // Re-check the permission status
        if (isPermissionGranted(requireContext())) {
            // Permission is granted, notify and dismiss the dialog
            callback?.onPermissionGranted()
            dismissDialog()
            removeSelf()
        } else {
            // Permission is not granted, show dialog if not already shown
            if (permissionDialog == null || !permissionDialog!!.isShowing) {
                showPermissionDialog()
            }
        }
    }

    private fun showPermissionDialog() {
        permissionDialog = AlertDialog.Builder(requireContext())
            .setTitle("Permission Needed")
            .setMessage("This app requires access to your contacts. Please enable it in Settings.")
            .setPositiveButton("Go to Settings") { _, _ ->
                openAppSettings()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
                callback?.onPermissionDenied()
                removeSelf()
            }
            .setCancelable(false)
            .create()
        permissionDialog?.show()
    }

    private fun dismissDialog() {
        permissionDialog?.dismiss()
        permissionDialog = null
    }

    private fun openAppSettings() {
        val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", requireContext().packageName, null)
        }
        startActivity(intent)
    }

    companion object {
        fun isPermissionGranted(context: Context): Boolean {
            return ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun removeSelf() {
        parentFragmentManager.beginTransaction().remove(this).commit()
    }
}