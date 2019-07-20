package com.example.eldhopjames.recyclercardviewsample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eldhopjames.recyclercardviewsample.R;
import com.example.eldhopjames.recyclercardviewsample.adapter.RecyclerAdapter;
import com.example.eldhopjames.recyclercardviewsample.interfaces.OnItemClickListener;
import com.example.eldhopjames.recyclercardviewsample.modelClass.ModelClass;

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

        initRecyclerView();
        dummyData();
        gesture();

                /**
                 * Handles the recycler view item clicks
                 */
                mRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Here we start our detailed activity and pass the values of the clicked item into it
                        Intent detailedActivityIntent = new Intent(getApplicationContext(), DetailedActivity.class);
                        detailedActivityIntent.putExtra(ITEM,
                                mListItems.get(position)); // Get the position of the clicked item
                        startActivity(detailedActivityIntent);
                    }
                });
    }

    /**Initializing recyclerView*/
    private void initRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true); // setting it to true allows some optimization to our view , avoiding validations when mRecyclerAdapter content changes

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)); //it can be GridLayoutManager or StaggeredGridLayoutManager
        mRecyclerView.setNestedScrollingEnabled(false);

        mRecyclerAdapter = new RecyclerAdapter(this); //set the mRecyclerAdapter to the recycler view
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL)); // Divider decorations
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    /**Function to put dummy data into the recyclerView*/
    private void dummyData(){
        //dummy data
        for (int i = 0; i<10; i++) {
            ModelClass listItem;
            if (i%2 == 0) {
                 listItem = new ModelClass(
                        "Heading " + i, "Dummy description", 0
                );
            } else {
                listItem = new ModelClass(
                        "Heading " + i, "Dummy description", 1
                );
            }
            mListItems.add(listItem);                               /** adding dummy data into the List*/
        }
        //dummy data ends here

        mRecyclerAdapter.setData(mListItems);
    }

    /**
     * For Pagination check : https://medium.com/@etiennelawlor/pagination-with-recyclerview-1cb7e66a502b
     * */

    /**Adding item into a position of RecyclerView*/
    public void add(View view) {
        Log.d(TAG, "add: ");
        int position = Integer.parseInt(editText.getText().toString());
        /**@params -> position to where we need to add the item, NOTE : It is optional
         @params -> passing the values into the model class
         */
        int typeValue; //for to determine which recycler layoutOut view has to populate
        if (position%2 == 0){
            typeValue = 0;
        } else {
            typeValue = 1;
        }
        ModelClass modelClass = new ModelClass("Heading"+Integer.toString(position)," New Item",typeValue);
        mRecyclerAdapter.addItem(position,modelClass);
    }

    /**Removing an item from a position of RecyclerView*/
    public void remove(View view) {
        Log.d(TAG, "remove: ");
        int position = Integer.parseInt(editText.getText().toString());
        /** @params -> position to where we need to add the item*/
        mRecyclerAdapter.removeItem(position);
    }

    /**
     * Swipe Right gesture to remove the item from the recycler view
     * */
    private void gesture(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mRecyclerAdapter.removeItem(viewHolder.getAdapterPosition()); // Passing the position into the remove function
            }
        }).attachToRecyclerView(mRecyclerView); // Attach it to the recycler view
    }


}
