package com.example.trackit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackit.databinding.RvStyleBinding;

public class Rvadapter extends ListAdapter<Expensecard,Rvadapter.ViewHolder> {
    public Rvadapter(){
     super(CALLBACK);
    }
    private static final DiffUtil.ItemCallback<Expensecard> CALLBACK=new DiffUtil.ItemCallback<Expensecard>() {

        @Override
        public boolean areItemsTheSame(@NonNull Expensecard oldItem, @NonNull Expensecard newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Expensecard oldItem, @NonNull Expensecard newItem) {
            return oldItem.getType().equals(newItem.getType()) && oldItem.getAmount().equals(newItem.getAmount());
        }
    };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_style,parent ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Expensecard expensecard=getItem(position);
         holder.rvStyleBinding.textView2.setText(expensecard.getType());
         holder.rvStyleBinding.textView3.setText(expensecard.getAmount());
    }
    public Expensecard getExpense(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RvStyleBinding rvStyleBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvStyleBinding=RvStyleBinding.bind(itemView) ;
        }
    }
}
