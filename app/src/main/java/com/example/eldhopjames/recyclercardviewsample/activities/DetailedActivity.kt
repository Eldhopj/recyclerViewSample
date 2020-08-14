package com.example.eldhopjames.recyclercardviewsample.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eldhopjames.recyclercardviewsample.R
import com.example.eldhopjames.recyclercardviewsample.modelClass.ModelClass
import kotlinx.android.synthetic.main.activity_detailed.*

/**
 * For showing the data on the items
 */
class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        /**Receiving extra values */
        val intent = intent
        val clickedItem: ModelClass? =
            intent.getParcelableExtra(MainActivity.ITEM) //The values inside the clicked item object is in the clicked item

        //Get data out from the clickedItem
        heading.text = clickedItem?.head
        description.text = clickedItem?.desc
    }
}