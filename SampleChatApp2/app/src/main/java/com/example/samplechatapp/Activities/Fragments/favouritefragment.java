package com.example.samplechatapp.Activities.Fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplechatapp.Adapters.BookRecycleViewAdapter;
import com.example.samplechatapp.R;
import com.example.samplechatapp.Models.Book;
import com.example.samplechatapp.Models.alllists;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favouritefragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favouritefragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public favouritefragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favouritefragment.
     */
    // TODO: Rename and change types and number of parameters
    public static favouritefragment newInstance(String param1, String param2) {
        favouritefragment fragment = new favouritefragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favouritefragment, container, false);
        ArrayList<Book> booksArrayList = alllists.getInstance().getFavList();
        RecyclerView recyclerView = v.findViewById(R.id.recycleView);
        TextView textView = v.findViewById(R.id.textView);

        if(booksArrayList.size()==0) {
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }else {
            textView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            BookRecycleViewAdapter adapter = new BookRecycleViewAdapter(getContext(), booksArrayList,2);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setAdapter(adapter);

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
        }
        return v;
    }
}