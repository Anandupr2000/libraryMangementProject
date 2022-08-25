package com.example.samplechatapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplechatapp.Models.Message;
import com.example.samplechatapp.R;
import com.example.samplechatapp.databinding.ActivityChatactivityBinding;
import com.example.samplechatapp.databinding.ReceiverLayoutBinding;
import com.example.samplechatapp.databinding.SenderLayoutBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter {

    ArrayList<Message> messages;
    Context context;
    ViewGroup parent;
    /**
     * these two variables will be used to determine message is sent or received by the method  getItemViewType
     */
    final int ITEM_SENT = 1;
    final int ITEM_RECEIVE = 2;

    public MessageAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.parent = parent;
        if(viewType==ITEM_SENT){

//            set layout for sender
            View view = LayoutInflater.from(context).inflate(R.layout.sender_layout,parent,false);
            System.out.println("RecyclerView intiated for ITEM_SENT");
            return new SentViewHolder(view);

        }else {

//            set layout for receiver
            View view = LayoutInflater.from(context).inflate(R.layout.receiver_layout,parent,false);
            System.out.println("RecyclerView intiated for ITEM_RECEIVE");
            return new ReceiveViewHolder(view);
        }
    }

    /**
     * since we r using two view holder ,this method will return the view type at the time of view recycling
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
//        storing message from Arraylist to Message object
        Message message = messages.get(position);
        if(FirebaseAuth.getInstance().getUid().equals(message.getSenderId())){
//            if id of sender message is equal to signed in user then the message is snt otherwise received
            return ITEM_SENT;
        }else {
//            if id of sender message is not equal to signed in user then the message is snt otherwise received
            return ITEM_RECEIVE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        String senderId =  FirebaseAuth.getInstance().getUid();

        if(holder.getClass() == SentViewHolder.class){
//            if class is form sentviewholder then set the sender message text
            SentViewHolder viewHolder = (SentViewHolder)holder;//type cast holder to viewHolder
            viewHolder.senderLayoutBinding.sendermsg.setText(message.getMessage());
            System.out.println("Message Adapter \nSender\n message.getMessage() => "+message.getMessage());
            System.out.println("sendermsg.setText(message.getMessage()) => "+viewHolder.senderLayoutBinding.sendermsg.getText());
        }else{

//            if class is form reciverviewholder then set the reciver message text
            ReceiveViewHolder viewHolder = (ReceiveViewHolder)holder;//type cast holder to viewHolder
            viewHolder.receiverLayoutBinding.receivermsg.setText(message.getMessage());
            System.out.println("Message Adapter \nReceiver\n message.getMessage() => "+message.getMessage());
            System.out.println("receivermsg.setText(message.getMessage()) => "+viewHolder.receiverLayoutBinding.receivermsg.getText());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    /**
     * ViewHolder for sender, creating constructor id necessary
     */
    public class SentViewHolder extends RecyclerView.ViewHolder{

        SenderLayoutBinding senderLayoutBinding;
        public SentViewHolder(@NonNull View itemView) {

            super(itemView);
            senderLayoutBinding = SenderLayoutBinding.bind(itemView);
        }
    }

    /**
     * ViewHolder for receiver, creating constructor id necessary
     */
    public class ReceiveViewHolder extends RecyclerView.ViewHolder{

        ReceiverLayoutBinding receiverLayoutBinding;
        public ReceiveViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverLayoutBinding = ReceiverLayoutBinding.bind(itemView);
        }
    }
}
