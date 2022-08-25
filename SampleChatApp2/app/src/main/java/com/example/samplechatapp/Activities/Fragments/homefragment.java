package com.example.samplechatapp.Activities.Fragments;

import android.app.ProgressDialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplechatapp.Activities.Login;
import com.example.samplechatapp.Adapters.BookRecycleViewAdapter;
import com.example.samplechatapp.R;
import com.example.samplechatapp.databinding.FragmentHomefragmentBinding;
import com.example.samplechatapp.Models.Book;
import com.example.samplechatapp.Models.alllists;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homefragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homefragment extends Fragment {

    FragmentHomefragmentBinding binding;
    private FirebaseDatabase database;

    private BookRecycleViewAdapter adapter;
    private ArrayList<Book> categoryArrayList;
    private String categoryname,imageurl;
    private String category[];
    private ProgressDialog progressDialog;

    // TODO: Rename parameter arguments, choose names that match

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;

    public homefragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homefragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homefragment newInstance(String param1, String param2) {
        homefragment fragment = new homefragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        binding = FragmentHomefragmentBinding.inflate(getLayoutInflater().inflate());

        System.out.println("******************** opening home fragment **********************");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_homefragment, container, false);
        recyclerView = v.findViewById(R.id.recycleView);
//        ImageView img = v.findViewById(R.id.bookimg);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.Framecontainer,new homefragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
//            }
//        });
//        ImageView img = (ImageView)v.findViewById(R.id.bookimg);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.Framecontainer,new homefragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
//            }
//        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

//        return binding.recycleView;

        categoryArrayList = new ArrayList<>();

        System.out.println("booksArrayList : "+ categoryArrayList);
//        Book book;

//        for(int i=1;i<=10;i++) {
//            book = new Book(i, "Wings of Fire", "APJ Abdul Kalam",
//                    "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1588286863l/634583._SY475_.jpg",
//                    "An Autobiography by A.P.J. Abdul Kalam",
//                    "Wings of Fire is an autography of APJ Abdul Kalam covering his early life " +
//                            "and his work in Indian space research and missile programs. It is the story of a boy from a" +
//                            " humble background who went on to become a key player in Indian space research/Indian missile programs " +
//                            "and later became the president of India.");
//            categoryArrayList.add(book);
//        }
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading category..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        FirebaseDatabase.getInstance().getReference().child("Category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categoryArrayList.clear();

                System.out.println("snapshot.getKey()() => "+snapshot.getKey());
                for(DataSnapshot snapshot1:snapshot.getChildren()){
//                    System.out.println("snapshot1.getKey()() => "+snapshot1.getKey());
//
                    categoryname = snapshot1.getKey();
                    System.out.println("categoryname => "+categoryname);
//
                    imageurl = snapshot1.child("imageUrl").getValue(String.class);
                    System.out.println("imageurl => "+imageurl);
//
//                    category = new String[]{categoryname, imageurl};
//                    System.out.println("category => "+category);
//
//                    categoryArrayList.add(category);
                    Book book1 = new Book();
                    book1.setName(categoryname);
                    book1.setImageUrl(imageurl);
                    categoryArrayList.add(book1);
                    System.out.println("categoryArrayList => "+categoryArrayList);
                }
                progressDialog.dismiss();
                System.out.println("home Arraylist size => "+ categoryArrayList.size());
                alllists.getInstance().setCategoryArrayList(categoryArrayList);
                System.out.println("home Arraylist size => "+ categoryArrayList.size());

                adapter = new BookRecycleViewAdapter( getContext(),alllists.getInstance().getCategoryArrayList(),1);

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(null,"Unable to fetch books from database",Toast.LENGTH_LONG).show();
            }
        });


        final int spacing = 25;

// apply spacing
        recyclerView.setPadding(spacing, spacing, spacing, spacing);
        recyclerView.setClipToPadding(false);
        recyclerView.setClipChildren(false);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(spacing, spacing, spacing, spacing);
            }
        });
//        adapter.notifyDataSetChanged();

        return v;
    }
}