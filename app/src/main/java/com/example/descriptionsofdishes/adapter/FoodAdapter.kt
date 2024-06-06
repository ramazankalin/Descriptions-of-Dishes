package com.example.descriptionsofdishes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.descriptionsofdishes.R
import com.example.descriptionsofdishes.databinding.ItemFoodBinding
import com.example.descriptionsofdishes.model.Category
import com.example.descriptionsofdishes.util.downloadURL
import com.example.descriptionsofdishes.view.MainActivity
import com.example.descriptionsofdishes.view.home.HomeFragment

class FoodAdapter(var foodList : ArrayList<Category>, private var onClick: (position: Int)->Unit) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(var view : ItemFoodBinding) : RecyclerView.ViewHolder(view.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemFoodBinding>(inflater, R.layout.item_food,parent,false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.view.productTV.text = foodList[position].title
        holder.view.productIV.downloadURL(foodList[position].imageUrl)

        holder.view.cvItem.setOnClickListener {
           if(HomeFragment.interstitialAd!= null && HomeFragment.interstitialAd!!.isLoaded){
               HomeFragment.interstitialAd!!.show(MainActivity.activity)
           }else {
               Toast.makeText(holder.view.root.context, "Ad did not load", Toast.LENGTH_SHORT).show()
           }
            onClick(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Category>){
        foodList = newList as ArrayList<Category>
        notifyDataSetChanged()
    }
}
