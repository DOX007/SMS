package com.simplemobiletools.smsmessenger.helpers

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.simplemobiletools.smsmessenger.R

fun showDoxInfoDialog(context: Context) {
    val prefs = SharedPrefsHelper(context)
    val inflater = LayoutInflater.from(context)
    val dialogView = inflater.inflate(R.layout.dialog_whitelist_editor, null)

    // Fält för rubrik/namn
    val headerInput = dialogView.findViewById<EditText>(R.id.header_input)
    headerInput.setText(prefs.getCustomLogHeader())

    // Vitlistnings-UI
    val whitelistInput = dialogView.findViewById<EditText>(R.id.whitelist_input)
    val whitelistAddBtn = dialogView.findViewById<Button>(R.id.whitelist_add_btn)
    val whitelistContainer = dialogView.findViewById<LinearLayout>(R.id.whitelist_container)

    // Ladda och visa redan sparade nummer
    val whitelist = prefs.getWhitelistedNumbers().toMutableList()
    fun refreshWhitelistView() {
        whitelistContainer.removeAllViews()
        whitelist.forEach { number ->
            val numberField = EditText(context)
            numberField.setText(number)
            numberField.isEnabled = false
            whitelistContainer.addView(numberField)
        }
    }
    refreshWhitelistView()

    whitelistAddBtn.setOnClickListener {
        val number = whitelistInput.text.toString().trim()
        if (number.isNotEmpty() && !whitelist.contains(number)) {
            whitelist.add(number)
            whitelistInput.setText("")
            refreshWhitelistView()
        }
    }

    AlertDialog.Builder(context)
        .setTitle("DOX info")
        .setView(dialogView)
        .setPositiveButton("OK") { _, _ ->
            // Spara rubrik/namn
            prefs.setCustomLogHeader(headerInput.text.toString())
            // Spara vitlistan
            prefs.setWhitelistedNumbers(whitelist)
        }
        .setNegativeButton("Avbryt", null)
        .show()
}
