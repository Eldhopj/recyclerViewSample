package com.example.eldhopjames.recyclercardviewsample.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.eldhopjames.recyclercardviewsample.R
import com.example.eldhopjames.recyclercardviewsample.adapter.RecyclerAdapter
import com.example.eldhopjames.recyclercardviewsample.databinding.ActivityMainBinding
import com.example.eldhopjames.recyclercardviewsample.modelClass.ModelClass
import com.example.eldhopjames.recyclercardviewsample.utils.itemdecorators.VerticalItemDecorator
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_main.recyclerView

/**1.Add dependencies
 * 2. Add recycler view  in the layout where it needed
 * 3.Create an layout for the items (list_item.xml)
 * 4.Model Class
 * 5.Bind the data with recycler view using an mRecyclerAdapter
 * 6. By using setOnItemClickListener handle the mRecyclerView Item click then pass the value to the DetailedActivity
 *
 * Parcelable
 * 1.Implement Parcelable in ModelClass and import all methods
 * 2.Send parcel position into DetailedActivity from MainActivity
 */

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val recyclerAdapter: RecyclerAdapter by lazy { RecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        recyclerViewClicks()
        dummyData()
        gesture()
    }

    private fun recyclerViewClicks() {
        recyclerAdapter.setOnContentClickListener { modelClass, index ->
            Log.d(TAG, index.toString())
            onItemClick(modelClass)
        }
    }

    /**Initializing recyclerView */
    private fun initRecyclerView() {
        binding.recyclerView.run {
            setHasFixedSize(true) // setting it to true if all the items are have a fixed height and width, this allows some optimization to our view , avoiding validations when mRecyclerAdapter content changes
            isNestedScrollingEnabled = false
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.size_36dp)
            addItemDecoration(
                VerticalItemDecorator(
                    this@MainActivity,
                    paddingStarting = spacingInPixels
                )
            )
            adapter = recyclerAdapter
        }
    }

    /**
     * Handles the recycler view item clicks
     */
    private fun onItemClick(modelClass: ModelClass) {
        // Here we start our detailed activity and pass the values of the clicked item into it
        val detailedActivityIntent =
            Intent(applicationContext, DetailedActivity::class.java)
        detailedActivityIntent.putExtra(
            ITEM,
            modelClass
        ) // Get the position of the clicked item
        startActivity(detailedActivityIntent)
    }

    /**
     * Function to put dummy data into the recyclerView
     */
    private fun dummyData() { //dummy data
        val mListItems: MutableList<ModelClass> = ArrayList()
        for (i in 0..9) {
            val listItem: ModelClass = if (i % 2 == 0) {
                ModelClass(
                    "Heading $i", "Dummy description", 0, i
                )
            } else {
                ModelClass(
                    "Heading $i", "Dummy description", 1, i
                )
            }
            mListItems.add(listItem)
            /** adding dummy data into the List */
        }
        //dummy data ends here
        recyclerAdapter.submitList(mListItems)
        binding.recyclerView.scheduleLayoutAnimation() // use this if anim not working only

    }
    /**
     * For Pagination check : https://medium.com/@etiennelawlor/pagination-with-recyclerview-1cb7e66a502b
     */
    /**Adding item into a position of RecyclerView */
    fun add(view: View?) {
        Log.d(TAG, "add: ")
        val position = binding.editText.text.toString().toInt()

        /**@params -> position to where we need to add the item, NOTE : It is optional
         * @params -> passing the values into the model class
         */
        val typeValue: Int = if (position % 2 == 0) {
            0
        } else {
            1
        } //for to determine which recycler layoutOut view has to populate
        val modelClass = ModelClass("Heading$position", " New Item", typeValue, position)
        recyclerAdapter.addItem(modelClass, position)
    }

    /**Removing an item from a position of RecyclerView */
    fun remove(view: View?) {
        Log.d(TAG, "remove: ")
        val position = binding.editText.text.toString().toInt()
        /** @params -> position to where we need to add the item
         */
        recyclerAdapter.removeItem(position)
    }

    /**
     * Swipe Right gesture to remove the item from the recycler view
     */
    private fun gesture() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                target: ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                recyclerAdapter.removeItem(viewHolder.bindingAdapterPosition) // Passing the position into the remove function
            }
        }).attachToRecyclerView(recyclerView) // Attach it to the recycler view
    }

    companion object {
        private const val TAG = "MainActivity"

        //Key constants for intentExtra
        const val ITEM = "item"
    }
}
