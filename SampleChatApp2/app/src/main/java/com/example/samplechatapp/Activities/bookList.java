package com.example.samplechatapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplechatapp.Activities.Fragments.favouritefragment;
import com.example.samplechatapp.Activities.Fragments.homefragment;
import com.example.samplechatapp.Adapters.BookRecycleViewAdapter;
import com.example.samplechatapp.R;
import com.example.samplechatapp.databinding.ActivityBookListBinding;
import com.example.samplechatapp.Models.Book;
import com.example.samplechatapp.Models.alllists;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//import static com.example.samplechatapp.R.id.item3;

public class bookList extends AppCompatActivity {

    ActivityBookListBinding binding;
    private ArrayList<Book> booksArrayList = new ArrayList<>();
    private BookRecycleViewAdapter adapter;
    private ActionBarDrawerToggle toggle;
    private ProgressDialog progressDialog;

    private Book book;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//      Intializing FirebaseAuth
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting books..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String category = getIntent().getStringExtra("category");
        System.out.println("category => "+category);

        System.out.println("********************* opening booklist *************************");
        FirebaseDatabase.getInstance().getReference().child("books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                booksArrayList.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    System.out.println("category => "+category);
                    if(snapshot1.child("category").getValue().equals(category)){
                        book = new Book();
                        book.setAuthor(snapshot1.child("author").getValue(String.class));
                        book.setCategory(snapshot1.child("category").getValue(String.class));
                        book.setImageUrl(snapshot1.child("imageUrl").getValue(String.class));
                        book.setDesc(snapshot1.child("desc").getValue(String.class));
//                        book.setId(snapshot1.child("id").getValue(int.class));
                        book.setDownloadUrl(snapshot1.child("downloadUrl").getValue(String.class));
                        book.setName(snapshot1.child("name").getValue(String.class));
                        book.setPrice(snapshot.child("price").getValue(String.class));
//                        book.setIsbn(Integer.parseInt(snapshot.child("isbn").getValue(String.class)));
                        book.setPublishYear(snapshot.child("price").getValue(String.class));
                        book.setShortDesc(snapshot1.child("shortDesc").getValue(String.class));
                        booksArrayList.add(book);
                    }
                }
                progressDialog.dismiss();

                adapter = new BookRecycleViewAdapter(getApplicationContext(), alllists.getInstance().getAllBookList(),0);

//      TODO: create layout file for landscape and change spanCount to 4
                binding.recycleView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                binding.recycleView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(null,"Unable to fetch books from database",Toast.LENGTH_LONG).show();
            }
        });
        alllists.getInstance().setAllBookList(booksArrayList);


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

//      setting side navigation panel
        setSupportActionBar(binding.toolbar);

        toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, R.string.nav_open, R.string.nav_close);
        binding.drawer.addDrawerListener(toggle);
        toggle.syncState();
        binding.navmenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Toast.makeText(getApplicationContext(), "Home panel is open", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        binding.drawer.closeDrawer(GravityCompat.START);//reseting drawer to begining
                        break;
                    case R.id.profile:
                        Toast.makeText(getApplicationContext(), "profile is clicked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),profileform.class));
                        binding.drawer.closeDrawer(GravityCompat.START);//reseting drawer to begining
                        break;
                    case R.id.fav:
                        Toast.makeText(getApplicationContext(), "opening list", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("fav",2);
                        startActivity(intent);
                        binding.drawer.closeDrawer(GravityCompat.START);//reseting drawer to begining
                        break;
                    case R.id.userCreatedBookLists:
                        Toast.makeText(getApplicationContext(), "opening yourlist", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), listview.class));
                        binding.drawer.closeDrawer(GravityCompat.START);//reseting drawer to begining
                        break;
                    case R.id.logoutBtn:
                        Toast.makeText(getApplicationContext(),"Logout sucessfull..",Toast.LENGTH_SHORT).show();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        auth.signOut();
                        finish();
                        break;
                    default:
//                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
                return true;
            }
        });
        getSupportActionBar().setTitle("Books");
    }
}