package com.example.eldhopjames.recyclercardviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**1.Add dependencies
    2. Add recycler view widget in the layout where it needed (here its in Main Activity)
    3.Create an separate layout for to "hold all the items inside the recycler view in card view" (list_liem.xml)
    4.Model Class
    5.Bind the data with recycler view needs an adapter
**/

public class MainActivity extends AppCompatActivity {

    /**Define the variables*/
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ModelClass> listitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**bind with xml*/
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /**Inside this list item we get all our values*/
        listitems = new ArrayList<>();

        //dummy data
        for (int i=1;i<10;i++)
        {
            ModelClass listItem =new ModelClass(
                    "Heading "+i,"Dummy description"
            );
            listitems.add(listItem);                               /** adding dummy data into the List*/
        }
        //dummy data ends here

        /**set the adapter to the recycler view*/
        adapter = new Adapter(listitems,this);
        recyclerView.setAdapter(adapter);


    }
}
