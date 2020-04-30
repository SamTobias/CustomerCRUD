package br.com.samueltobias.customercrud.ui.customerlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.samueltobias.customercrud.R;
import br.com.samueltobias.customercrud.model.Customer;
import br.com.samueltobias.customercrud.ui.OnClickListener;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    private final Context context;
    private final List<Customer> list;
    private OnClickListener listener;

    CustomerListAdapter(Context context, List<Customer> list, OnClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.customer_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerListAdapter.ViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    void add(Customer customer) {
        list.add(customer);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.customer_list_customer_name);
        }
    }
}
