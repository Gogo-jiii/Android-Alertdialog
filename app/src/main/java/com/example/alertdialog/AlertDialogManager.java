package com.example.alertdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Arrays;
import java.util.List;

import static com.example.alertdialog.DialogModel.DIALOG_CUSTOM;
import static com.example.alertdialog.DialogModel.DIALOG_MULTIPLE_CHOICE;
import static com.example.alertdialog.DialogModel.DIALOG_SINGLE_CHOICE;
import static com.example.alertdialog.DialogModel.DIALOG_STANDARD;

public class AlertDialogManager extends DialogFragment {

    private AlertDialog.Builder builder;
    private Dialog dialog;
    private DialogModel model;
    private DialogListener dialogListener;
    private List<String> selectedMultiChoiceItems;

    public AlertDialogManager(DialogModel model) {
        this.model = model;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        switch (model.getDialogType()) {
            case DIALOG_STANDARD:
                dialog = createStandardDialog();
                break;
            case DIALOG_SINGLE_CHOICE:
                dialog = createSingleChoiceDialog(model.getChoiceArray());
                break;
            case DIALOG_MULTIPLE_CHOICE:
                dialog = createMultiChoiceDialog(model.getChoiceArray());
                break;
            case DIALOG_CUSTOM:
                dialog = createCustomDialog(model.getActivity(), model.getCustomLayout());
                break;
        }
        return dialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogListener = (DialogListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Dialog createStandardDialog() {
        builder = new AlertDialog.Builder(model.getContext());
        builder.setMessage(model.getTitle());

        builder.setPositiveButton(model.getPositiveButtonText(),
                (dialog, id) -> {
                    dialogListener.onDialogPositiveClick(dialog);
                });

        builder.setNegativeButton(model.getNegativeButtonText(),
                (dialog, id) -> {
                    dialogListener.onDialogNegativeClick(dialog);
                });

        return builder.create();
    }

    AlertDialog createSingleChoiceDialog(String[] array) {
        AlertDialog.Builder builder = new AlertDialog.Builder(model.getContext());
        builder.setTitle(model.getTitle());

        builder.setSingleChoiceItems(array, 0,
                (dialog, which) -> {
                    Toast.makeText(model.getContext(), array[which], Toast.LENGTH_SHORT).show();
                });

        builder.setPositiveButton(model.getPositiveButtonText(),
                (dialog, which) -> {
                    dialogListener.onDialogPositiveClick(dialog);
                });

        builder.setNegativeButton(model.getNegativeButtonText(),
                (dialog, which) -> {
                    dialogListener.onDialogNegativeClick(dialog);
                });

        return builder.create();
    }

    AlertDialog createMultiChoiceDialog(String[] array) {
        AlertDialog.Builder builder = new AlertDialog.Builder(model.getContext());
        builder.setTitle(model.getTitle());

        selectedMultiChoiceItems = Arrays.asList(array);

        builder.setMultiChoiceItems(array, model.getCheckedMultiChoiceArray(),
                (dialogInterface, which, isChecked) -> {
                    model.getCheckedMultiChoiceArray()[which] = isChecked;
                    String currentItem = selectedMultiChoiceItems.get(which);
                    Toast.makeText(model.getContext(), currentItem,
                            Toast.LENGTH_SHORT).show();
                });

        builder.setPositiveButton(model.getPositiveButtonText(),
                (dialog, which) -> {
                    for (int i = 0; i < selectedMultiChoiceItems.size(); i++) {
                        if (model.getCheckedMultiChoiceArray()[i] == true) {
                            Log.d("TAG", selectedMultiChoiceItems.get(i));
                        }
                    }
                });

        builder.setNegativeButton(model.getNegativeButtonText(),
                (dialog, which) -> {
                    dialogListener.onDialogNegativeClick(dialog);
                });

        return builder.create();
    }

    AlertDialog createCustomDialog(Activity activity, int layout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(model.getContext());
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(layout, null);
        builder.setView(dialogView);

        Button button = dialogView.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            dialogListener.onDialogPositiveClick(getDialog());
        });
        return builder.create();
    }

    interface DialogListener {
        public void onDialogPositiveClick(DialogInterface dialog);

        public void onDialogNegativeClick(DialogInterface dialog);
    }
}
