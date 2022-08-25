package com.example.samplechatapp.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.samplechatapp.Activities.customList;
import com.example.samplechatapp.Activities.bookList;
import com.example.samplechatapp.Activities.specificList;
import com.example.samplechatapp.Models.Book;
import com.example.samplechatapp.Models.alllists;
import com.example.samplechatapp.Models.list;
import com.example.samplechatapp.R;
import com.example.samplechatapp.databinding.CustomListBinding;
import com.example.samplechatapp.databinding.PopuplistBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class customListAdapter extends RecyclerView.Adapter<customListAdapter.customListHolder> {


    Context context;
    Object[] keys;
    ArrayList<String> arrayList;
    Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public customListAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        System.out.println("arrayList => "+arrayList);
//        for(Object item:keys){
//            keys
//        }
    }

    @NonNull
    @Override
    public customListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_list,parent,false);
        return new customListHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull customListHolder holder, int position) {


        String l = arrayList.get(position);

        holder.binding.listname.setText(l);
        if(context.getClass()!= bookList.class){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, specificList.class);
                intent.putExtra("listname",l);
                context.startActivity(intent);
            }
        });
        holder.binding.addBtn.setVisibility(View.GONE);
        }
        if(context.getClass().equals(bookList.class)){
            holder.binding.addBtn.setVisibility(View.VISIBLE);
            holder.binding.addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String place = holder.binding.listname.getText().toString();
                    ArrayList<Book> arrayList = alllists.getInstance().getuserbooklist().get(place);
                    arrayList.add(book);
                    alllists.getInstance().addArrayList(place,arrayList);
                    Toast.makeText(context,"Added to "+place,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class customListHolder extends RecyclerView.ViewHolder {

        public CustomListBinding binding;

        public customListHolder(@NonNull View itemView) {
            super(itemView);


            binding = CustomListBinding.bind(itemView);
        }
    }

}
