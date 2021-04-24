package com.example.alertdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AlertDialogManager.DialogListener {

    Button btnStandardDialog, btnSingleChoiceDialog, btnMultipleChoiceDialog, btnCustomDialog;
    String[] array = {"a", "b", "c"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStandardDialog = findViewById(R.id.btnStandardDialog);
        btnSingleChoiceDialog = findViewById(R.id.btnSingleChoiceDialog);
        btnMultipleChoiceDialog = findViewById(R.id.btnMultipleChoiceDialog);
        btnCustomDialog = findViewById(R.id.btnCustomDialog);

        btnStandardDialog.setOnClickListener(this);
        btnSingleChoiceDialog.setOnClickListener(this);
        btnMultipleChoiceDialog.setOnClickListener(this);
        btnCustomDialog.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStandardDialog:
                showStandardDialog();
                break;
            case R.id.btnSingleChoiceDialog:
                showSingleChoiceDialog();
                break;
            case R.id.btnMultipleChoiceDialog:
                showMultipleChoiceDialog();
                break;
            case R.id.btnCustomDialog:
                showCustomDialog();
                break;
        }
    }

    private void showStandardDialog() {
        DialogModel model = new DialogModel(this, this, DialogModel.DIALOG_STANDARD);
        model.setTitle("Title");
        model.setPositiveButtonText("Ok");
        model.setNegativeButtonText("Cancel");

        AlertDialogManager fragment = new AlertDialogManager(model);
        fragment.show(getSupportFragmentManager(), "standard dialog");
        fragment.setCancelable(false);
    }

    private void showSingleChoiceDialog() {
        DialogModel model = new DialogModel(this, this, DialogModel.DIALOG_SINGLE_CHOICE);
        model.setTitle("Title");
        model.setPositiveButtonText("Ok");
        model.setNegativeButtonText("Cancel");
        model.setChoiceArray(array);

        AlertDialogManager fragment = new AlertDialogManager(model);
        fragment.show(getSupportFragmentManager(), "single choice dialog");
        fragment.setCancelable(false);
    }

    private void showMultipleChoiceDialog() {
        DialogModel model = new DialogModel(this, this, DialogModel.DIALOG_MULTIPLE_CHOICE);
        model.setTitle("Title");
        model.setPositiveButtonText("Ok");
        model.setNegativeButtonText("Cancel");
        model.setChoiceArray(array);
        boolean[] checkedMultiChoiceArray = new boolean[]{false, false, false};
        model.setCheckedMultiChoiceArray(checkedMultiChoiceArray);

        AlertDialogManager fragment = new AlertDialogManager(model);
        fragment.show(getSupportFragmentManager(), "multiple choice dialog");
        fragment.setCancelable(false);
    }

    private void showCustomDialog() {
        DialogModel model = new DialogModel(this, this, DialogModel.DIALOG_CUSTOM);
        model.setCustomLayout(R.layout.custom_layout);

        AlertDialogManager fragment = new AlertDialogManager(model);
        fragment.show(getSupportFragmentManager(), "custom dialog");
        fragment.setCancelable(false);
    }

    @Override public void onDialogPositiveClick(DialogInterface dialog) {
        Toast.makeText(this, "Ok clicked", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    @Override public void onDialogNegativeClick(DialogInterface dialog) {
        Toast.makeText(this, "Cancel clicked", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }
}