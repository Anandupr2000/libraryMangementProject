package com.example.samplechatapp.Activities.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.samplechatapp.Activities.MainActivity;
import com.example.samplechatapp.Activities.registerWthPhnNo;
import com.example.samplechatapp.Adapters.BookRecycleViewAdapter;
import com.example.samplechatapp.Adapters.userAdapter;
import com.example.samplechatapp.Models.user;
import com.example.samplechatapp.Models.utils;
import com.example.samplechatapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link chatfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class chatfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private userAdapter useradapter;
    private FirebaseDatabase database;
    DatabaseReference db_reference;

    private ShimmerRecyclerView recyclerView;
    private ArrayList<user> userArrayList = new ArrayList<>();


    public chatfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment chatfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static chatfragment newInstance(String param1, String param2) {
        chatfragment fragment = new chatfragment();
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
        View v = inflater.inflate(R.layout.fragment_chatfragment, container, false);
//        ShimmerRecyclerView shimmerRecycler = (ShimmerRecyclerView) v.findViewById(R.id.recyclerView);

        database = FirebaseDatabase.getInstance();
        recyclerView = (ShimmerRecyclerView) v.findViewById(R.id.recycleView);

        System.out.println("******************************************************************************");
        System.out.println("utils.getInstance().getuID() => " + utils.getInstance().getuID());
        db_reference = database.getReference().child("users").child(utils.getInstance().getuID());

        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                user u = snapshot.getValue(user.class);
                System.out.println("u => " + u);
                try {
                    u.getPhnNo();
                    System.out.println("********************" +
                            "\nFirebaseAuth.getInstance().getCurrentUser().getUid() => " + FirebaseAuth.getInstance().getCurrentUser().getUid() +
                            "\nutils.getInstance().getuID() => " + utils.getInstance().getuID() +
                            "\n u.getPhnNo.length() =>" + u.getPhnNo().length());

                    if (u.getPhnNo().length() == 4) {

                        startActivity(new Intent(getContext(), registerWthPhnNo.class));
                        Toast.makeText(getContext(), "Redirecting you to register phone number", Toast.LENGTH_LONG).show();
                    } else {
                        Context context = getContext();
                        useradapter = new userAdapter(context, userArrayList);


                        System.out.println("context => " + context);
                        System.out.println("recyclerView => " + recyclerView);

                        recyclerView.setLayoutManager(new LinearLayoutManager(context));

                        recyclerView.setAdapter(useradapter);

                        recyclerView.showShimmerAdapter();

                        /**
                         * getting values from Realtime database(firebase)
                         */
                        database = FirebaseDatabase.getInstance();
                        database.getReference().child("users").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                clearing Arraylist before adding data to it
                                userArrayList.clear();

                                try {
//              looping through the data in child node that we getting containing users info by specifying the class

                                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                        System.out.println("snapshot --> " + snapshot1);
//
                                        user userObj = snapshot1.getValue(user.class);
                                        if (!utils.getInstance().getuID().equals(userObj.getuID())) {
                                            userArrayList.add(userObj);
                                        }
                                    }
//                notify adapter about the changed data
                                    useradapter.notifyDataSetChanged();
                                    recyclerView.hideShimmerAdapter();
                                } catch (Exception e) {

                                    System.out.println("Exception occured : " + e);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                System.out.println("Exception occured : " + error);
                                Toast.makeText(getContext(), "Unable to connect to server", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                } catch (Exception e) {
                    System.out.println("*****************************************************************");
                    System.out.println("Execption in chat => " + e);
                    Toast.makeText(getContext(), "You doesn't properly registered", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Database fetch failed");
            }
        });
        recyclerView.hideShimmerAdapter();

        return v;
    }
}