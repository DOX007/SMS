// Generated by view binder compiler. Do not edit!
package com.simplemobiletools.smsmessenger.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.simplemobiletools.commons.views.MyTextInputLayout;
import com.simplemobiletools.smsmessenger.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogRenameConversationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextInputEditText renameConvEditText;

  @NonNull
  public final MyTextInputLayout renameConvInputLayout;

  private DialogRenameConversationBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextInputEditText renameConvEditText,
      @NonNull MyTextInputLayout renameConvInputLayout) {
    this.rootView = rootView;
    this.renameConvEditText = renameConvEditText;
    this.renameConvInputLayout = renameConvInputLayout;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogRenameConversationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogRenameConversationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_rename_conversation, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogRenameConversationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rename_conv_edit_text;
      TextInputEditText renameConvEditText = ViewBindings.findChildViewById(rootView, id);
      if (renameConvEditText == null) {
        break missingId;
      }

      id = R.id.rename_conv_input_layout;
      MyTextInputLayout renameConvInputLayout = ViewBindings.findChildViewById(rootView, id);
      if (renameConvInputLayout == null) {
        break missingId;
      }

      return new DialogRenameConversationBinding((ConstraintLayout) rootView, renameConvEditText,
          renameConvInputLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
