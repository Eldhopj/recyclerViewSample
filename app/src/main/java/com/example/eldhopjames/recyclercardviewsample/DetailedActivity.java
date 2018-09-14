package com.example.eldhopjames.recyclercardviewsample;
/**
 * For showing the data on the items
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.eldhopjames.recyclercardviewsample.MainActivity.ITEM;

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
