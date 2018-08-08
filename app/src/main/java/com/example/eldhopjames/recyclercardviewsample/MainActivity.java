package com.example.eldhopjames.recyclercardviewsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**1.Add dependencies
 2. Add recycler view  in the layout where it needed
 3.Create an layout for the items (list_liem.xml)
    4.Model Class
 5.Bind the data with recycler view using an mAdapter
 6. By using setOnItemClickListener handle the mRecyclerView Item click then pass the value to the DetailedActivity
**/

public class MainActivity extends AppCompatActivity {

    //Define the variables
    private RecyclerView mRecyclerView;
    private List<ModelClass> mListItems;
    private Adapter mAdapter;

    //Key constants for intentExtra
    public static final String HEADING = "heading";
    public static final String DESCRIPTION = "desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**bind with xml*/
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true); // setting it to true allows some optimization to our view , avoiding validations when mAdapter content changes

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //it can be GridLayoutManager or StaggeredGridLayoutManager

        /**Inside this list item we get all our values*/
        mListItems = new ArrayList<>();

        //dummy data
        for (int i = 1; i<10; i++) {
            ModelClass listItem =new ModelClass(
                    "Heading "+i,"Dummy description"
            );
            mListItems.add(listItem);                               /** adding dummy data into the List*/
        }
        //dummy data ends here

        /**set the mAdapter to the recycler view*/
        mAdapter = new Adapter(mListItems, this);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL)); // Divider decorations
        mRecyclerView.setAdapter(mAdapter);

        /**
         * Handles the recycler view item clicks
         */
        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Here we start our detailed activity and pass the values of the clicked item into it
                Intent detailedActivityIntent = new Intent(getApplicationContext(), DetailedActivity.class);
                ModelClass clickedItem = mListItems.get(position); // We get the item at the clicked position out of our list items

                detailedActivityIntent.putExtra(HEADING, clickedItem.getHead());
                detailedActivityIntent.putExtra(DESCRIPTION, clickedItem.getDesc());

                startActivity(detailedActivityIntent);
            }
        });
    }

}
