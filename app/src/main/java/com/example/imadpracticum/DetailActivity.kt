/*
 * FULL NAME: [Insert Your Full Name]
 * STUDENT NUMBER: [Insert Your Student Number]
 * MODULE: IMAD5112 - Introduction to Mobile Application Development
 */

package com.example.imadpracticum

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "DetailActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Get references to UI elements
        val checklistContainer: LinearLayout = findViewById(R.id.checklistContainer)
        val backButton: Button = findViewById(R.id.backButton)

        // Build the checklist display by looping through the parallel arrays
        displayChecklist(checklistContainer)

        Log.d(TAG, "Detail screen loaded with ${MainActivity.itemNames.size} items.")

        // Set up the Back to Base button
        backButton.setOnClickListener {
            Log.d(TAG, "Back button clicked. Returning to Main Screen.")
            finish() // Go back to the previous screen
        }
    }

    // Loop through the parallel arrays and display each item in the checklist
    private fun displayChecklist(container: LinearLayout) {
        val names = MainActivity.itemNames
        val cats = MainActivity.categories
        val quants = MainActivity.quantities

        // Use a FOR loop to build the list of gear items
        for (i in names.indices) {
            // Create a vertical container for each item
            val itemLayout = LinearLayout(this)
            itemLayout.orientation = LinearLayout.VERTICAL
            itemLayout.setPadding(16, 16, 16, 16)

            // Set a bottom border for each item
            val border = View(this)
            border.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 1
            )
            border.setBackgroundColor(resources.getColor(R.color.black))

            // Item name
            val nameText = TextView(this)
            nameText.text = names[i]
            nameText.textSize = 18f
            nameText.setTextColor(resources.getColor(R.color.black))
            nameText.setTypeface(null, android.graphics.Typeface.BOLD)

            // Category and quantity details
            val detailText = TextView(this)
            detailText.text = "${cats[i]} - ${quants[i]}"
            detailText.textSize = 14f
            detailText.setTextColor(resources.getColor(R.color.gray_dark))
            detailText.setPadding(0, 4, 0, 0)

            // Add views to the item layout
            itemLayout.addView(nameText)
            itemLayout.addView(detailText)
            itemLayout.addView(border)

            // Add the item to the main container
            container.addView(itemLayout)
        }
    }
}
