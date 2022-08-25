package com.example.samplechatapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.samplechatapp.Activities.chatactivity;
import com.example.samplechatapp.R;
import com.example.samplechatapp.databinding.RawConversationBinding;
import com.example.samplechatapp.Models.user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.userViewHolder>{

    RawConversationBinding binding;
    Context context;
    ArrayList<user> users;

    public userAdapter(Context context, ArrayList<user> users) {
        this.context = context;
        this.users = new ArrayList<>();
        this.users = users;
        System.out.println("userAdapter users ArrayList -> "+this.users);
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.raw_conversation,parent,false);
        return new userViewHolder(v);
    }

    /**
     * adding data to user class
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {
        user user = users.get(position);

        String senderId = FirebaseAuth.getInstance().getUid();
        String senderRoom =  senderId +  user.getuID();
        FirebaseDatabase.getInstance().getReference()
                .child("chats")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){

                            if(snapshot.child("lastMsgTime").getValue(Long.class)!=null) {
                                long time = snapshot.child("lastMsgTime").getValue(Long.class);
                            }
                            if(snapshot.child("lastMsg").getValue(String.class) != null) {

                                String lastMsg = snapshot.child("lastMsg").getValue(String.class);
                                holder.binding.lastmsg.setText(lastMsg);

                            }else {
                                holder.binding.lastmsg.setText("Tap to chat");
                            }
//                            holder.binding.lastmsgtime.setText((int)time);
                        }
                        else {
                            System.out.println("holder.binding.lastmsg => "+holder.binding.lastmsg);
                            holder.binding.lastmsg.setText("Tap to chat");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        holder.binding.userName.setText(user.getName());

        holder.binding.lastmsg.setVisibility(View.VISIBLE);
        holder.binding.lastmsg.setText("Tap to chat");
        Glide.with(context)
                .load(user.getProfileImage())
                .placeholder(R.drawable.avatar_account_dummy_profilepic)
                .into(holder.binding.userProfileImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, chatactivity.class);
                System.out.println("useradapter : user.getName() -> "+user.getName());
                intent.putExtra("Name",user.getName());
                intent.putExtra("UID",user.getuID());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class userViewHolder extends RecyclerView.ViewHolder{

        RawConversationBinding binding;//this is alternative for findViewById()
        public userViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = RawConversationBinding.bind(itemView);
        }
    }
}
