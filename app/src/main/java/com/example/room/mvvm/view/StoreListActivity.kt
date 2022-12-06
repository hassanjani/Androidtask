package com.example.room.mvvm.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.mvvm.R
import com.example.room.mvvm.adapter.CustomAdapter
import com.example.room.mvvm.model.StoreTableModel
import com.example.room.mvvm.viewmodel.LoginViewModel
import com.example.room.mvvm.viewmodel.StoreViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

//data class ItemsViewModel(val image: Int, val text: String) {
//}

class StoreListActivity : AppCompatActivity() {
    lateinit var storeViewModel: StoreViewModel
    lateinit var context: Context
    lateinit var recyclerview: RecyclerView
    lateinit var data:ArrayList<StoreTableModel>
    lateinit var adapter:CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_list)

        // getting the recyclerview by its id
        context = this@StoreListActivity

        storeViewModel = ViewModelProvider(this).get(StoreViewModel::class.java)


         recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
         data = ArrayList<StoreTableModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
//        for (i in 1..20) {
//            data.add(StoreTableModel(R.drawable.ic_baseline_face_24, "Item " + i))
//        }

        // This will pass the ArrayList to our Adapter
         adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter



        myFun();


}

    private fun myFun() {
        lifecycleScope.launch{

            var mylist:List<StoreTableModel> =  storeViewModel.getAllData(context)!!
            Log.d("mssg",mylist.size.toString())

            val data = ArrayList<StoreTableModel>()

            mylist.forEach { element->
               data.add(element)
            }
            adapter= CustomAdapter(data)
            recyclerview.adapter = adapter


        }

     fun myFun1(context: Context,name: String) {
        lifecycleScope.launch{

            storeViewModel.deletedata(context,name)




        }

        }



    }

}