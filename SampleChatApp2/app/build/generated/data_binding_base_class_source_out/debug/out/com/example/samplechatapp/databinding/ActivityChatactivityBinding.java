// Generated by view binder compiler. Do not edit!
package com.example.samplechatapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.example.samplechatapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityChatactivityBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView attachment;

  @NonNull
  public final ImageView camera;

  @NonNull
  public final CardView cardView;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final EditText msgBox;

  @NonNull
  public final RecyclerView recycleView;

  @NonNull
  public final ImageView sendBtn;

  @NonNull
  public final Toolbar toolbar;

  private ActivityChatactivityBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView attachment, @NonNull ImageView camera, @NonNull CardView cardView,
      @NonNull LinearLayout linearLayout, @NonNull EditText msgBox,
      @NonNull RecyclerView recycleView, @NonNull ImageView sendBtn, @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.attachment = attachment;
    this.camera = camera;
    this.cardView = cardView;
    this.linearLayout = linearLayout;
    this.msgBox = msgBox;
    this.recycleView = recycleView;
    this.sendBtn = sendBtn;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityChatactivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityChatactivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_chatactivity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityChatactivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.attachment;
      ImageView attachment = rootView.findViewById(id);
      if (attachment == null) {
        break missingId;
      }

      id = R.id.camera;
      ImageView camera = rootView.findViewById(id);
      if (camera == null) {
        break missingId;
      }

      id = R.id.cardView;
      CardView cardView = rootView.findViewById(id);
      if (cardView == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = rootView.findViewById(id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.msgBox;
      EditText msgBox = rootView.findViewById(id);
      if (msgBox == null) {
        break missingId;
      }

      id = R.id.recycleView;
      RecyclerView recycleView = rootView.findViewById(id);
      if (recycleView == null) {
        break missingId;
      }

      id = R.id.sendBtn;
      ImageView sendBtn = rootView.findViewById(id);
      if (sendBtn == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = rootView.findViewById(id);
      if (toolbar == null) {
        break missingId;
      }

      return new ActivityChatactivityBinding((ConstraintLayout) rootView, attachment, camera,
          cardView, linearLayout, msgBox, recycleView, sendBtn, toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
