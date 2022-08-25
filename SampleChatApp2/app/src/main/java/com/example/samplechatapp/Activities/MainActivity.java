package com.example.samplechatapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.samplechatapp.Activities.Fragments.chatfragment;
import com.example.samplechatapp.Activities.Fragments.favouritefragment;
import com.example.samplechatapp.Activities.Fragments.homefragment;
import com.example.samplechatapp.Models.user;
import com.example.samplechatapp.Models.utils;
import com.example.samplechatapp.R;
import com.example.samplechatapp.databinding.ActivityMainBinding;
import com.example.samplechatapp.Models.Book;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private NavigationView nav;
    private DrawerLayout drawerLayout;
    private FirebaseAuth auth;
    private ActionBarDrawerToggle toggle;
    private BottomNavigationView bnav;
    FirebaseDatabase database;
    DatabaseReference db_reference;
    Fragment temp = null;

    Toolbar toolbar;


    private MenuView.ItemView home, fav, chats;

    int backButtonCount = 0;


    private Button home1, logoutBtn;
    private Book book = new Book();
    private int chatFragment = 1;
    private int homeFragment=1;
    private int favFragment=1;
    private static int fragmentno = 1;

    ActivityMainBinding binding;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(utils.getInstance().getuID());
//    sideNav sideNav = new sideNav();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();

//        setting side navigation panel
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.inflate(getLayoutInflater());
//        ================================getting data from firestore and storing it to class ===========================

        System.out.println("****************************************************************************************" +
                "\nutils.getInstance().getuID() => "+utils.getInstance().getuID());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("****************************************************************************************" +
                        "\nsnapshot => "+snapshot);
                user u = snapshot.getValue(user.class);
                try{
                    System.out.println("****************************************************************************************" +
                            "\nu.getName() => " + u.getName());
                    System.out.println("****************************************************************************************" +
                            "\nutils.getInstance() => " + utils.getInstance());

                    utils.getInstance().setName(u.getName());
                    utils.getInstance().setEmail(u.getEmail());
                    utils.getInstance().setIsPic(u.getProfileImage());
                    utils.getInstance().setuID(u.getuID());
                    utils.getInstance().setPhoneNo(u.getPhnNo());
                }catch (Exception e){
                    System.out.println("***************** error****************\n"+e);
                    Toast.makeText(getApplicationContext(),"No user data found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"Failed to retrieve data",Toast.LENGTH_SHORT).show();
                System.out.println("***************************************Failed to retrieve data************************************");
            }
        });

//   ================================================== side Nav =============================================================
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Toast.makeText(getApplicationContext(), "Home panel is open", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.Framecontainer, new homefragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);//reseting drawer to begining
                        break;
                    case R.id.profile:
                        Toast.makeText(getApplicationContext(), "Profile is clicked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),profileform.class));
                        drawerLayout.closeDrawer(GravityCompat.START);//reseting drawer to begining
                        break;
                    case R.id.fav:
                        Toast.makeText(getApplicationContext(), "Opening Favourites", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.Framecontainer, new favouritefragment()).commit();
                        fragmentno = 2;
                        bnav.setSelectedItemId(R.id.bottom_fav);
                        drawerLayout.closeDrawer(GravityCompat.START);//reseting drawer to begining
                        break;
                    case R.id.userCreatedBookLists:
//                        TODO: remove this => done
                        Toast.makeText(getApplicationContext(), "opening list", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), listview.class));
                        drawerLayout.closeDrawer(GravityCompat.START);//reseting drawer to begining
                        break;
                    case R.id.logoutBtn:
                        Toast.makeText(getApplicationContext(), "Logout sucessfull..", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        auth.signOut();
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        finish();
                        break;
                    default:
//                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
                return true;
            }
        });
//=============================================================================================================================
        getSupportActionBar().setTitle("Category");

//=============================================================================

//        setting bottom nav bar listener
        Intent intent =getIntent();
        if(intent!= null){
            favFragment = intent.getIntExtra("fav",1);
            chatFragment = intent.getIntExtra("chat",1);
            if(favFragment!=1){
                fragmentno = 2;
            }
            if(chatFragment!=1){
                fragmentno = 3;
            }
        }
        if(fragmentno==1){
                getSupportFragmentManager().beginTransaction().replace(R.id.Framecontainer, new homefragment()).commit();
                Book.setCurrentfragementid(R.layout.fragment_homefragment);
        }
        else if(fragmentno==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.Framecontainer, new favouritefragment()).commit();
        } else if(fragmentno==3){
            getSupportFragmentManager().beginTransaction().replace(R.id.Framecontainer, new chatfragment()).commit();
        }

        bnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        System.out.println("id -> " + item.getItemId());
                        temp = new homefragment();
                        fragmentno = 1;
                        item.setChecked(true);
                        toolbar.setTitle("Home");

//                        changeIcon(item);
                        break;

                    case R.id.bottom_fav:
                        temp = new favouritefragment();
                        item.setChecked(true);
                        fragmentno = 2;
                        toolbar.setTitle("Favourite");
//                        changeIcon(item);
//                        fav.setBackgroundColor(Color.rgb(125, 117, 235));
                        break;

                    case R.id.bottom_chats:
                        fragmentno = 3;
                        item.setChecked(true);
//                        verifyUser();
                        temp = new chatfragment();
                        toolbar.setTitle("chats");
//                        changeIcon(item);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
                Book.setCurrentfragementid(item.getItemId());
//                creating clickable bottom nav
                getSupportFragmentManager().beginTransaction().replace(R.id.Framecontainer, temp).commit();
                return false;

            }
        });
    }

//

    @Override
    public void onBackPressed() {

        if (backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finishAffinity();
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Press back button again to close", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
//        super.onBackPressed();
    }


//        icon.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    String path = "R.drawable.ic_"+icon.toString()+"_focused";
//                    icon.setImageResource(Integer.parseInt(path));
//                }else{
//                    String path = "R.drawable.ic_"+icon.toString();
//                    icon.setImageResource(Integer.parseInt(path));
//                }
//            }
//        });

//    }

//    private void setIcon(int resourceNo){
//        int[] btnMenu ={R.id.bottom_chats,R.id.bottom_fav,R.id.bottom_home};
//        for(int a=0;a<3;a++){
//            if(btnMenu[a] == resourceNo){
////                View v = findViewById(resourceNo);
//                findViewById(resourceNo).setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                    @Override
//                    public void onFocusChange(View v, boolean hasFocus) {
//                        if(hasFocus){
//
//                        }
//                    }
//                });
//            }
//        }
//    }
//    private void changeIcon(MenuItem item){
//
//        int[] btnMenu ={R.id.bottom_chats,R.id.bottom_fav,R.id.bottom_home};
//        String c = null;
//        for(int a=0;a<3;a++){
//            if(item.getItemId() == btnMenu[a]){
//                c = item.getTitle().toString().toLowerCase();
//                System.out.println(c);
//                System.out.println("R.drawable.ic_home_focused : "+R.drawable.ic_home_focused);
////                System.out.println("\n int value of c = "+Integer.parseInt("R.drawable.ic_"+c+"_focused"));
//                System.out.println("\n int value of c = "+R.drawable.ic_c_focused);
//
//                item.setIcon(R.drawable.ic_home_focused);
//            }
//            else {
//                item.setIcon(R.drawable.ic_home);
//            }
//        }
//    }

    private void initView() {
        nav = (NavigationView) findViewById(R.id.navmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        auth = FirebaseAuth.getInstance();
        home1 = findViewById(R.id.nav_home);
        logoutBtn = findViewById(R.id.logoutBtn);
        bnav = (BottomNavigationView) findViewById(R.id.bottom_nav);

        home = findViewById(R.id.bottom_home);
        fav = findViewById(R.id.bottom_fav);
        chats = findViewById(R.id.bottom_chats);

    }

}