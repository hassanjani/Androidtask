package com.example.room.mvvm.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.room.mvvm.R
import com.example.room.mvvm.model.StoreTableModel
import com.example.room.mvvm.repository.StoreRepository
import com.example.room.mvvm.view.StoreListActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


//import com.example.room.mvvm.view.ItemsViewModel

class CustomAdapter(private val mList: List<StoreTableModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
   lateinit var context:Context
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item

        context=parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel:StoreTableModel = mList[position]

        // sets the image to the imageview from our itemHolder class
//        holder.imageView.setImageURI(Uri.parse(ItemsViewModel.Image))
        holder.imageView.setImageResource(R.drawable.ic_baseline_face_24)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.Name


        holder.imagedelete.setOnClickListener {
            GlobalScope.launch {
                StoreRepository.deleteItem(context, ItemsViewModel.Name)

            }
            Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT)

            val intent = Intent(context, StoreListActivity::class.java)
            context.startActivity(intent)
            (context as Activity).finish()

        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val imagedelete: ImageView = itemView.findViewById(R.id.imageviewdelete)




    }

}
