package com.example.mid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    var datalist = ArrayList<dataBean>()

    //        arrayOf(
//        ,
//        dataBean("cat", "09090909090909"),
//        dataBean("dog", "09090909090909")
//    )
    val myAdapter = MyAdapter(datalist)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        datalist.add(dataBean("ham", "09090909090909"))
        datalist.add(dataBean("cat", "09090909090909"))
        datalist.add(dataBean("dog", "09090909090909"))
        val btnCreate = findViewById<Button>(R.id.btnCreate)
        btnCreate.setOnClickListener({
            showDialog("新增")
        })
        myAdapter.setDel(object :MyAdapter.DelListener{
            override fun onDel(p:Int) {
                datalist.removeAt(p)
                myAdapter.update(datalist)
                recyclerView.adapter = myAdapter
                Log.d("TAG", "onDel: ${datalist.size}")
            }
        })

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        myAdapter.update(datalist)
        recyclerView.adapter = myAdapter
        Log.d("adapter length", "onCreate: ${myAdapter.itemCount}")


    }

    private fun showDialog(message: String) {
        val createDialog = CreateDialog(this)
        createDialog
            .setMessage(message)
            .setCancel(object : CreateDialog.IOnCancelListener {
                override fun onCancel(dialog: CreateDialog?) {
                    createDialog.dismiss()
                }
            })
            .setConfirm(object : CreateDialog.IOnConfirmListener {
                override fun onConfirm(dialog: CreateDialog?) {

                    if (dialog?.txtName?.text != null && dialog.txtPhone?.text != null) {
                        val name = dialog.txtName!!.text
                        val phone = dialog.txtPhone!!.text
                        datalist.add(dataBean(name.toString(), phone.toString()))
                    }

//                    myAdapter.
                    createDialog.dismiss()
                }
            }).show()
    }


}