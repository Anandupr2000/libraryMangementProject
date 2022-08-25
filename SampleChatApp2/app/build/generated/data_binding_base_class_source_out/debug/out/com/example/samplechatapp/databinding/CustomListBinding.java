// Generated by view binder compiler. Do not edit!
package com.example.samplechatapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.samplechatapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CustomListBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView addBtn;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final TextView listname;

  @NonNull
  public final View view;

  private CustomListBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView addBtn,
      @NonNull ConstraintLayout constraintLayout, @NonNull TextView listname, @NonNull View view) {
    this.rootView = rootView;
    this.addBtn = addBtn;
    this.constraintLayout = constraintLayout;
    this.listname = listname;
    this.view = view;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static CustomListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CustomListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.custom_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CustomListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_btn;
      ImageView addBtn = rootView.findViewById(id);
      if (addBtn == null) {
        break missingId;
      }

      id = R.id.constraintLayout;
      ConstraintLayout constraintLayout = rootView.findViewById(id);
      if (constraintLayout == null) {
        break missingId;
      }

      id = R.id.listname;
      TextView listname = rootView.findViewById(id);
      if (listname == null) {
        break missingId;
      }

      id = R.id.view;
      View view = rootView.findViewById(id);
      if (view == null) {
        break missingId;
      }

      return new CustomListBinding((ConstraintLayout) rootView, addBtn, constraintLayout, listname,
          view);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
