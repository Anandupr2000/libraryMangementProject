package com.example.samplechatapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.samplechatapp.Adapters.MessageAdapter;
import com.example.samplechatapp.Models.Message;
import com.example.samplechatapp.R;
import com.example.samplechatapp.databinding.ActivityChatactivityBinding;
import com.example.samplechatapp.databinding.RawConversationBinding;
import com.example.samplechatapp.databinding.SenderLayoutBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class chatactivity extends AppCompatActivity {

    ActivityChatactivityBinding binding;
    MessageAdapter adapter;
    ArrayList<Message> messages;
    FirebaseDatabase database;
    FirebaseAuth auth;


    String SenderRoom, ReceiverRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        System.out.println("********************ChatActivity started*****************");
        super.onCreate(savedInstanceState);
        binding = ActivityChatactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        messages = new ArrayList<>();

//        attaching adapter
        adapter = new MessageAdapter(this, messages);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleView.setAdapter(adapter);


        if (getIntent().equals(null)) {
            System.out.println("chatactivity intent get is null");
        } else {
            System.out.println("chatactivity intent -> " + getIntent() + "\ngetIntent().getStringExtra(\"name\") -> " +
                    getIntent().getStringExtra("Name"));
            String Name = getIntent().getStringExtra("Name");
            String receiverUid = getIntent().getStringExtra("UID");
            String senderUid = FirebaseAuth.getInstance().getUid();

            SenderRoom = senderUid + receiverUid;
            ReceiverRoom = receiverUid + senderUid;

            database = FirebaseDatabase.getInstance();

            database.getReference()
                    .child("chats")
                    .child(SenderRoom)
                    .child("messages")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            System.out.println("=============setting RecycleView===========");
//                            clearing and adding new messages
                            messages.clear();
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                Message message1 = snapshot1.getValue(Message.class);//Type casting
                                messages.add(message1);
                                System.out.println("message1.getMessage() => " + message1.getMessage());
                            }
                            for (Message a : messages) {
                                System.out.println("messages from ArrayList'messages'=> " + a.getMessage());
                            }


                            System.out.println("Values in recycleview textbox -> " + binding.recycleView.getAdapter());

                            binding.recycleView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();//refreshing data in adapter
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "Error connecting to server", Toast.LENGTH_SHORT).show();
                        }
                    });

            binding.sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String msgtxt = binding.msgBox.getText().toString();
                    System.out.println("sendBtn OnClickListener invoked");
                    System.out.println("Text sent :" + msgtxt);

                    Date date = new Date();

//                    storing push key for sender and receiver id
                    String randomKey = database.getReference().push().getKey();



                    Message message = new Message(msgtxt, senderUid, date.getTime());
                    binding.msgBox.setText("");

                    HashMap<String,Object> lastMsgObj = new HashMap<>();
                    lastMsgObj.put("lastmessage",message.getMessage());
                    lastMsgObj.put("lastMsgTime",date.getTime());

                    database.getReference().child("chats").child(SenderRoom).updateChildren(lastMsgObj);
                    database.getReference().child("chats").child(ReceiverRoom).updateChildren(lastMsgObj);

                    database.getReference().child("chats")
                            .child(SenderRoom)
                            .child("messages")
//                            .push()//adds unique node under which values are saved
//                            adding random key
                            .child(randomKey)
                            .setValue(message)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    database.getReference().child("chats")
                                            .child(ReceiverRoom)
                                            .child("messages")
                                            .child(randomKey)
                                            .setValue(message)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {

                                                    Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_SHORT).show();
                                                    adapter.notifyDataSetChanged();
                                                }
                                            });
                                    HashMap<String,Object>  lastMsgObj = new HashMap<>();
                                    lastMsgObj.put("lastmessage",message.getMessage());
                                    lastMsgObj.put("lastMsgTime",date.getTime());

                                    database.getReference().child("chats").child(SenderRoom).updateChildren(lastMsgObj);
                                    database.getReference().child("chats").child(ReceiverRoom).updateChildren(lastMsgObj);
                                }
                            });

                }
            });
//        setting name in action bar
            System.out.println("chatactivity : Name -> " + Name);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(Name);

//        we can setup up UP arrow by this
//        ==============================================================
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void supportNavigateUpTo(@NonNull Intent upIntent) {
        finish();
        super.onBackPressed();
    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }

//    ====================================================================
}

