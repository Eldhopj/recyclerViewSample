package com.example.eldhopjames.recyclercardviewsample;
/**
 * For showing the data on the items
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.eldhopjames.recyclercardviewsample.MainActivity.DESCRIPTION;
import static com.example.eldhopjames.recyclercardviewsample.MainActivity.HEADING;

public class DetailedActivity extends AppCompatActivity {
    TextView headTV, descTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        /**Receiving extra values*/
        Intent intent = getIntent();
        String heading = intent.getStringExtra(HEADING);
        String desc = intent.getStringExtra(DESCRIPTION);

        headTV = findViewById(R.id.heading);
        descTV = findViewById(R.id.description);

        headTV.setText(heading);
        descTV.setText(desc);
    }
}
