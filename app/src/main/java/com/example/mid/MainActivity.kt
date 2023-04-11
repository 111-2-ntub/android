package com.example.mid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mid.data.Phone
import com.example.mid.data.PhoneViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch




import java.util.*

class MainActivity : AppCompatActivity() {


private lateinit var mPhoneViewModel:PhoneViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter=MyAdapter()
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter=adapter

        mPhoneViewModel=ViewModelProvider(this).get(PhoneViewModel::class.java)
        mPhoneViewModel.getAll.observe(this, androidx.lifecycle.Observer {phone->
            Log.d("TAG", "onCreate: ${phone.size.toString()}")
            adapter.update(phone)
        })

        val btnCreate = findViewById<Button>(R.id.btnCreate)
        btnCreate.setOnClickListener({
            showDialog("新增", recyclerView)
        })
//        myAdapter.setDel(object : MyAdapter.DelListener {
//            override fun onDel(p: Int) {
//                phoneDao!!.delete(datalist[p])
//                onLoad(recyclerView)
//            }
//        })





    }

    override fun onStart() {
        super.onStart()
//        Log.d("TAG", "onStart: $")
//        disposable.add(viewModel.getall().subscribeOn(Schedulers.io()).observeOn(Android).subscribe({this}))

    }
    private fun onLoad(recyclerView: RecyclerView) {
//        var Inref = this
//        var Inphones: List<Phone>? =null
//        lifecycleScope.launch(Dispatchers.IO) {
//
//            Inphones = phoneDao!!.getAll()
//
//            Log.d("TAG", "onLoad: " + Inphones!!.size.toString())
//            Inref.runOnUiThread {
//                if (Inphones!!.size == 0) {
////                    phoneDao!!.insertAll(Phone(UUID.randomUUID().toString(),"ham","09090909090909"))
////                    phoneDao!!.insertAll(Phone(UUID.randomUUID().toString(),"cat","09090909090909"))
////                    phoneDao!!.insertAll(Phone(UUID.randomUUID().toString(),"dog","09090909090909"))
//                }else{
//                    print("hi"+ Inphones!![0].phone.toString())
//                }
//
//            }
//
//
//            recyclerView.adapter = myAdapter
//        }

    }

    private fun showDialog(message: String, recyclerView: RecyclerView) {
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
                        val name = dialog.txtName!!.text.toString()
                        val phone = dialog.txtPhone!!.text.toString()
                        if (TextUtils.isEmpty(name)&&TextUtils.isEmpty(phone)){
                            Log.d("TAG", "onConfirm: is empty")
                        }else{
                            Log.d("TAG", "onConfirm: before add")
                            var Inref = this
                            Inref.run {
                                mPhoneViewModel.addPhone(Phone(0,name,phone))
                            }
                            Log.d("TAG", "onConfirm: after add")

                        }


                    }

//                    myAdapter.
                    createDialog.dismiss()
                }
            }).show()
    }


}