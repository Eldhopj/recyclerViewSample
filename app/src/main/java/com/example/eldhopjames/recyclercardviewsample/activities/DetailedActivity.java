package com.example.eldhopjames.recyclercardviewsample.activities;
/**
 * For showing the data on the items
 */

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eldhopjames.recyclercardviewsample.R;
import com.example.eldhopjames.recyclercardviewsample.modelClass.ModelClass;

import static com.example.eldhopjames.recyclercardviewsample.activities.MainActivity.ITEM;

public class DetailedActivity extends AppCompatActivity {
    TextView headTV, descTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        headTV = findViewById(R.id.heading);
        descTV = findViewById(R.id.description);

        /**Receiving extra values*/
        Intent intent = getIntent();
        ModelClass clickedItem = intent.getParcelableExtra(ITEM); //The values inside the clicked item object is in the clicked item

        //Get data out from the clickedItem
        headTV.setText(clickedItem.getHead());
        descTV.setText(clickedItem.getDesc());
    }
}
