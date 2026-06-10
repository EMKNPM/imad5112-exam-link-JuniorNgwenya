/*
 * FULL NAME: [Insert Your Full Name]
 * STUDENT NUMBER: [Insert Your Student Number]
 * MODULE: IMAD5112 - Introduction to Mobile Application Development
 */

package com.example.imadpracticum

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"

        // Parallel arrays to store gear items
        // These are public so DetailActivity can access them
        val itemNames = mutableListOf("Tent", "Marshmallows", "Flashlight")
        val categories = mutableListOf("Shelter", "Food", "First Aid")
        val quantities = mutableListOf("4-person waterproof", "Mega size for S'mores", "Check AA batteries")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to UI elements
        val totalCountText: TextView = findViewById(R.id.totalCountText)
        val addGearButton: Button = findViewById(R.id.addGearButton)
        val viewChecklistButton: Button = findViewById(R.id.viewChecklistButton)

        // Calculate total items packed by looping through the items array
        updateTotalCount(totalCountText)

        // Log app state
        Log.d(TAG, "App loaded with ${itemNames.size} gear items.")

        // Set up the Add Gear button click handler
        addGearButton.setOnClickListener {
            Log.d(TAG, "Add Gear button clicked.")
            showAddGearDialog(totalCountText)
        }

        // Set up the View Detailed Checklist button click handler
        viewChecklistButton.setOnClickListener {
            Log.d(TAG, "View Checklist button clicked.")
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }

    // Calculate total items using a FOR loop and update the display
    private fun updateTotalCount(totalCountText: TextView) {
        var count = 0
        // Loop through the itemNames array to count items
        for (i in itemNames.indices) {
            count++
        }
        totalCountText.text = count.toString()
    }

    // Show a dialog to add new gear to the arrays
    private fun showAddGearDialog(totalCountText: TextView) {
        // Inflate the dialog layout programmatically
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_gear, null)
        val nameInput: EditText = dialogView.findViewById(R.id.nameInput)
        val categoryInput: EditText = dialogView.findViewById(R.id.categoryInput)
        val quantityInput: EditText = dialogView.findViewById(R.id.quantityInput)

        // Build the dialog
        AlertDialog.Builder(this)
            .setTitle("Add New Gear")
            .setView(dialogView)
            .setPositiveButton("Add Item") { _, _ ->
                // Get user input and trim whitespace
                val name = nameInput.text.toString().trim()
                val category = categoryInput.text.toString().trim()
                val quantity = quantityInput.text.toString().trim()

                // Validate that all fields are filled
                if (name.isEmpty() || category.isEmpty() || quantity.isEmpty()) {
                    Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Empty field detected - showing toast warning.")
                } else {
                    // Append the new item to the parallel arrays
                    itemNames.add(name)
                    categories.add(category)
                    quantities.add(quantity)

                    Log.d(TAG, "New gear added: $name ($category - $quantity)")
                    Toast.makeText(this, "Item added successfully!", Toast.LENGTH_SHORT).show()

                    // Refresh the total count display
                    updateTotalCount(totalCountText)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
