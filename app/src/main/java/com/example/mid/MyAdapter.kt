package com.example.mid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MyAdapter(dataSet: ArrayList<dataBean>,) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var dataSet = dataSet
    private var delListener: MyAdapter.DelListener? = null


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView
        val txtPhone: TextView
        val btnDel: ImageButton
        val btnEdit: ImageButton

        init {
            // Define click listener for the ViewHolder's View.
            txtName = view.findViewById(R.id.txtName)
            txtPhone = view.findViewById(R.id.txtPhone)
            btnDel = view.findViewById(R.id.btnDel)
            btnEdit = view.findViewById(R.id.btnEdit)
        }
    }

    fun update(datasets: ArrayList<dataBean>) {
        dataSet = datasets
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_row, parent, false)


        return ViewHolder(view)
    }

    fun setDel(Listener: MyAdapter.DelListener) {
        this.delListener = Listener

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = dataSet[position].name
        holder.txtPhone.text = dataSet[position].phone
        holder.btnDel.setOnClickListener {
            delListener?.onDel(position)
        }


    }

    override fun getItemCount() = dataSet.size

    interface DelListener {
        fun onDel(p:Int)
    }
}
