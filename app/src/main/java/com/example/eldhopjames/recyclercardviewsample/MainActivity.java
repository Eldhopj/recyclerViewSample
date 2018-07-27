package com.example.eldhopjames.recyclercardviewsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**1.Add dependencies
 2. Add recycler view  in the layout where it needed
 3.Create an layout for the items (list_liem.xml)
    4.Model Class
 5.Bind the data with recycler view using an adapter
 6.Implement the Adapter.OnItemClickListener and handle the recyclerView Item click then pass the value to the DetailedActivity
**/

public class MainActivity extends AppCompatActivity implements Adapter.OnItemClickListener {
    /**
     * Implement the OnItemClickListener interface
     */

    //Define the variables
    private RecyclerView recyclerView;
    private List<ModelClass> listitems;
    private Adapter adapter;

    //Key constants for intentExtra
    public static final String HEADING = "heading";
    public static final String DESCRIPTION = "desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**bind with xml*/

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // setting it to true allows some optimization to our view , avoiding validations when adapter content changes

        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //it can be GridLayoutManager or StaggeredGridLayoutManager

        /**Inside this list item we get all our values*/
        listitems = new ArrayList<>();

        //dummy data
        for (int i = 1; i<10; i++) {
            ModelClass listItem =new ModelClass(
                    "Heading "+i,"Dummy description"
            );
            listitems.add(listItem);                               /** adding dummy data into the List*/
        }
        //dummy data ends here

        /**set the adapter to the recycler view*/
        adapter = new Adapter(listitems,this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this); // For item onclick
    }

    /**
     * Handles the recycler view item clicks
     */
    @Override
    public void onItemClick(int position) {
        // Here we start our detailed activity and pass the values of the clicked item into it
        Intent detailedActivityIntent = new Intent(this, DetailedActivity.class);
        ModelClass clickedItem = listitems.get(position); // We get the item at the clicked position out of our list items

        detailedActivityIntent.putExtra(HEADING, clickedItem.getHead());
        detailedActivityIntent.putExtra(DESCRIPTION, clickedItem.getDesc());

        startActivity(detailedActivityIntent);
    }
}
