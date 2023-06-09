package com.example.mid
// 11136005 羅毓翔
// 11136007 林哲卉
// 11136008 郭溱卉
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mid.data.Phone
import com.example.mid.data.PhoneViewModel

class MainActivity : FragmentActivity() {


private lateinit var mPhoneViewModel:PhoneViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment)
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        val adapter=MyAdapter()
//        val linearLayoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = linearLayoutManager
//        recyclerView.adapter=adapter
//
//        mPhoneViewModel=ViewModelProvider(this).get(PhoneViewModel::class.java)
//        mPhoneViewModel.getAll.observe(this, androidx.lifecycle.Observer {phone->
//            Log.d("TAG", "onCreate: ${phone.size.toString()}")
//            adapter.update(phone)
//        })


//        val btnCreate = findViewById<Button>(R.id.btnCreate)
//        btnCreate.setOnClickListener({
//            showCreateDialog("新增")
//        })


//        adapter.setDel(object : MyAdapter.DelListener {
//            override fun onDel(p: Int) {
//                Log.d("TAG", "onDel: before")
//                mPhoneViewModel.delPhone(adapter.getItem(p))
//                Log.d("TAG", "onDel: ater")
//                Log.d("TAG", "onDel: ${mPhoneViewModel.getAll.value?.size}")
//
//            }
//        })
//        adapter.setEdit(object : MyAdapter.EditListener {
//            override fun onEdit(p: Int) {
//                showEditDialog("編輯",adapter.getItem(p))
//            }
//
//        })







    }



    private fun showCreateDialog(message: String) {
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
                    Log.d("TAG", "onConfirm: enter ")

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
                    }else{
                        Log.d("TAG", "onConfirm: else${dialog?.txtName?.text}")

                    }
                    createDialog.dismiss()
                }
            }).show()
    }
    fun showEditDialog(message: String,toEditPhone:Phone) {
        val createDialog = CreateDialog(this)
        createDialog
            .setMessage(message)
            .setEditMessage(toEditPhone)
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
                            Log.d("TAG", "onConfirm: before edit")
                            var Inref = this
                            Inref.run {
                                mPhoneViewModel.update(Phone(toEditPhone.id,name,phone))
                            }
                            Log.d("TAG", "onConfirm: after edit")
                        }
                    }
                    createDialog.dismiss()
                }
            }).show()
    }

}