package com.example.eldhopjames.recyclercardviewsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**1.Add dependencies
 2. Add recycler view  in the layout where it needed
 3.Create an layout for the items (list_liem.xml)
    4.Model Class
 5.Bind the data with recycler view using an mRecyclerAdapter
 6. By using setOnItemClickListener handle the mRecyclerView Item click then pass the value to the DetailedActivity

 Parcelable
 1.Implement Parcelable in ModelClass and import all methods
 2.Send parcel position into DetailedActivity from MainActivity
**/

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    //Define the variables
    private RecyclerView mRecyclerView;
    private List<ModelClass> mListItems;
    private RecyclerAdapter mRecyclerAdapter;
    Button addBtn,removeBtn;
    EditText editText;

    //Key constants for intentExtra
    public static final String ITEM = "item";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**Inside this list item we get all our values*/
        mListItems = new ArrayList<>();

        addBtn = findViewById(R.id.button_add);
        removeBtn = findViewById(R.id.button_remove);
        editText = findViewById(R.id.editText);

        initRecyclerView(mListItems);
        dummyData();

                /**
                 * Handles the recycler view item clicks
                 */
                mRecyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Here we start our detailed activity and pass the values of the clicked item into it
                        Intent detailedActivityIntent = new Intent(getApplicationContext(), DetailedActivity.class);
                        detailedActivityIntent.putExtra(ITEM,
                                mListItems.get(position)); // Get the position of the clicked item
                        startActivity(detailedActivityIntent);
                    }
                });

        /**
         * Swipe Right gesture to remove the item from the recycler view
         * */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem(viewHolder.getAdapterPosition()); // Passing the position into the remove function
            }
        }).attachToRecyclerView(mRecyclerView); // Attach it to the recycler view

    }

    /**Initializing recyclerView
     * @param listItems -> contains all the elements*/
    private void initRecyclerView(List<ModelClass> listItems){
        /**bind with xml*/
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true); // setting it to true allows some optimization to our view , avoiding validations when mRecyclerAdapter content changes

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)); //it can be GridLayoutManager or StaggeredGridLayoutManager

        /**set the mRecyclerAdapter to the recycler view*/
        mRecyclerAdapter = new RecyclerAdapter(listItems, this);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL)); // Divider decorations
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    /**Function to put dummy data into the recyclerView*/
    private void dummyData(){
        //dummy data
        for (int i = 0; i<10; i++) {
            ModelClass listItem =new ModelClass(
                    "Heading "+i,"Dummy description"
            );
            mListItems.add(listItem);                               /** adding dummy data into the List*/
        }
        //dummy data ends here

        mRecyclerAdapter.notifyDataSetChanged();
    }

    public void add(View view) {
        Log.d(TAG, "add: ");
        int position = Integer.parseInt(editText.getText().toString());
        addItem(position);
    }

    /**Adding item into a position of RecyclerView*/
    private void addItem(int position) {
        /**@params -> position to where we need to add the item, NOTE : It is optional
         @params -> passing the values into the model class
         */
        mListItems.add( position, new ModelClass("Heading"+Integer.toString(position)," New Item"));

        //notifyDataSetChanged refreshes the entire recycler view rather than updating it
        //mRecyclerAdapter.notifyDataSetChanged();

        //If we know the position to be inserted use
        mRecyclerAdapter.notifyItemInserted(position);
    }

    public void remove(View view) {
        Log.d(TAG, "remove: ");
        int position = Integer.parseInt(editText.getText().toString());
        removeItem(position);
    }

    /**Removing an item from a position of RecyclerView*/
    private void removeItem(int position) {
       /** @params -> position to where we need to add the item*/
        mListItems.remove(position);

        //notifyDataSetChanged refreshes the entire recycler view rather than updating it
        //mRecyclerAdapter.notifyDataSetChanged();

        //If we know the position to be removed use
        mRecyclerAdapter.notifyItemRemoved(position);
    }


}
