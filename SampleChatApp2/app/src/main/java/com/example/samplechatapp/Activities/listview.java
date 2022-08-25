package com.example.samplechatapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.samplechatapp.Activities.Fragments.favouritefragment;
import com.example.samplechatapp.Adapters.customListAdapter;
import com.example.samplechatapp.Models.Book;
import com.example.samplechatapp.Models.alllists;
import com.example.samplechatapp.R;
import com.example.samplechatapp.databinding.ActivityListviewBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class listview extends AppCompatActivity {

    ActivityListviewBinding binding;

    ArrayList<String> arrayList= new ArrayList<>();
    private ActionBarDrawerToggle toggle;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Map<String, ArrayList<Book>> map = new HashMap<String, ArrayList<Book>>();
        map = alllists.getInstance().getuserbooklist();

        if(map.size()==0){
            binding.cardView.setVisibility(View.GONE);
            binding.text.setVisibility(View.VISIBLE);

        }else {
            binding.cardView.setVisibility(View.VISIBLE);
            binding.text.setVisibility(View.GONE);
            System.out.println("*************************************************************");
            System.out.println("alllists.getuserbooklistKeys() => " + alllists.getuserbooklistKeys());
            Set keys = alllists.getuserbooklistKeys();
//             arrayList = new ArrayList<Object[]>(Collections.singleton(keys));
            try {
                for (Object item: keys) {
                    System.out.println("item => "+item.toString());
                    arrayList.add( item.toString());
                }
                System.out.println("arrayList => "+arrayList);
            }
            catch (Exception e){
                System.out.println("Exception => "+e);
            }


            customListAdapter adapter = new customListAdapter(this, arrayList);
            Intent intent = getIntent();
            String s = intent.getStringExtra("add");
            if(s == "add"){
                adapter.setBook(alllists.getInstance().getTemp());
            }

            binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            binding.recyclerView.setAdapter(adapter);
        }

//      setting side navigation panel
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setTitle("Your Lists");

//        we can setup up UP arrow by this
//        ==============================================================
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
        return super.onSupportNavigateUp();
    }
}