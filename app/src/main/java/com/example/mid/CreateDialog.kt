package com.example.mid

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView



class CreateDialog(context: Context):Dialog(context){

    private var message: String?= null

    private var cancelListener: IOnCancelListener? = null
    private var confirmListener: IOnConfirmListener? = null
    public var txtName:EditText?=null
    public var txtPhone:EditText?=null

    fun setMessage(message: String?): CreateDialog {
        this.message = message
        return this
    }

    fun setConfirm(Listener: IOnConfirmListener): CreateDialog {
        this.confirmListener = Listener
        return this
    }

    fun setCancel(Listener: IOnCancelListener): CreateDialog {
        this.cancelListener = Listener
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_dialog)

        val btNegative: Button = findViewById(R.id.dialog_negative)
        val btPositive: Button= findViewById(R.id.dialog_positive)
        val tvContent: TextView = findViewById(R.id.dialog_yes_no_message)
        txtName=findViewById(R.id.username)
        txtPhone=findViewById(R.id.password)
        tvContent.text= message
        btPositive.setOnClickListener(this::clickListener)
        btNegative.setOnClickListener(this::clickListener)
    }

    private fun clickListener(v: View){
        when(v.id){
            R.id.dialog_positive -> {
                confirmListener?.onConfirm(this)

            }
            R.id.dialog_negative -> {
                cancelListener?.onCancel(this)
            }
        }
    }

    interface IOnCancelListener {
        fun onCancel(dialog: CreateDialog?)
    }

    interface IOnConfirmListener {
        fun onConfirm(dialog: CreateDialog?)
    }
}