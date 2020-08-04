package br.com.samueltobias.customercrud.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.samueltobias.customercrud.R
import br.com.samueltobias.customercrud.domain.model.Customer
import br.com.samueltobias.customercrud.view.OnClickListener
import java.util.*

class CustomerListAdapter internal constructor(
        private val context: Context, private val clickListener: OnClickListener
) : RecyclerView.Adapter<CustomerListAdapter.ViewHolder>() {
    private var customers: MutableList<Customer> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.customer_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.customerNameTextView.text = customers[position].name
        holder.itemView.setOnClickListener { clickListener.onClick(position) }
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    fun setCustomers(customers: List<Customer>) {
        this.customers = customers.toMutableList()
        notifyDataSetChanged()
    }

    fun add(customer: Customer) {
        customers.add(customer)
        notifyDataSetChanged()
    }

    fun getCustomers(): List<Customer> {
        return customers
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var customerNameTextView: TextView = itemView.findViewById(R.id.customer_list_customer_name)
    }
}