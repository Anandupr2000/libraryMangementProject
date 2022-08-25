package com.example.samplechatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.example.samplechatapp.Adapters.BookRecycleViewAdapter;
import com.example.samplechatapp.Models.Book;
import com.example.samplechatapp.Models.alllists;
import com.example.samplechatapp.R;
import com.example.samplechatapp.databinding.ActivitySpecificListBinding;

import java.util.ArrayList;

public class specificList extends AppCompatActivity {

    ActivitySpecificListBinding binding;
    BookRecycleViewAdapter adapter;
    String listname;
    ArrayList<Book> arraylist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySpecificListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent =getIntent();
        listname = intent.getStringExtra("listname");
        System.out.println("*************************************************************************");
        System.out.println(" alllists.getInstance().getuserbooklist().get(listname) => "+ alllists.getInstance().getuserbooklist().get(listname));
        System.out.println(" alllists.getInstance().getuserbooklist()=> "+ alllists.getInstance().getuserbooklist());
        arraylist = alllists.getInstance().getuserbooklist().get(listname);

        adapter = new BookRecycleViewAdapter(this, arraylist,0);

//      TODO: create layout file for landscape and change spanCount to 4
        binding.recycleView.setLayoutManager(new GridLayoutManager(this,2));
        binding.recycleView.setAdapter(adapter);

// apply spacing

        final int spacing = 20;

        binding.recycleView.setPadding(spacing, spacing, spacing, spacing);
        binding.recycleView.setClipToPadding(false);
        binding.recycleView.setClipChildren(false);
        binding.recycleView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(spacing, spacing, spacing, spacing);
            }
        });

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(listname);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),listview.class));
        finish();
        return super.onSupportNavigateUp();
    }
}