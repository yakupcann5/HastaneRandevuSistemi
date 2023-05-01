package com.example.hastanerandevusistemi.ui.my_appointments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.data.local.entities.AppointmentEntity
import com.example.hastanerandevusistemi.databinding.MyAppointmentItemBinding
import com.example.hastanerandevusistemi.domain.model.Appointment

class MyAppointmentRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var myAppointmentList: ArrayList<AppointmentEntity> = arrayListOf()

    class ViewHolder(view: MyAppointmentItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        val sentBinding: MyAppointmentItemBinding = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MyAppointmentItemBinding>(
            inflater,
            R.layout.my_appointment_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).sentBinding.appointment = myAppointmentList[position]
    }

    override fun getItemCount(): Int {
        return myAppointmentList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(myAppointmentList: ArrayList<AppointmentEntity>) {
        this.myAppointmentList.clear()
        this.myAppointmentList.addAll(myAppointmentList)
        notifyDataSetChanged()
    }
}