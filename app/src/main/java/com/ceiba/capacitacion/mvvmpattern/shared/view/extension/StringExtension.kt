package com.ceiba.capacitacion.mvvmpattern.shared.view.extension

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ceiba.capacitacion.mvvmpattern.R

fun String.showMessage(context: Context) {
    MaterialAlertDialogBuilder(context)
        .setTitle(context.resources.getString(R.string.info))
        .setMessage(this)
        .setPositiveButton(context.resources.getString(R.string.confirm), null)
        .show()
}