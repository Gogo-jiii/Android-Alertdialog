package com.example.alertdialog;

import android.app.Activity;
import android.content.Context;

public class DialogModel {

    private String title;
    private String positiveButtonText;
    private String negativeButtonText;
    private String[] choiceArray;
    private boolean[] checkedMultiChoiceArray;
    private int customLayout;
    private Context context;
    private Activity activity;
    private String dialogType;

    public static final String DIALOG_STANDARD = "DIALOG_STANDARD";
    public static final String DIALOG_SINGLE_CHOICE = "DIALOG_SINGLE_CHOICE";
    public static final String DIALOG_MULTIPLE_CHOICE = "DIALOG_MULTIPLE_CHOICE";
    public static final String DIALOG_CUSTOM = "DIALOG_CUSTOM";

    public DialogModel(MainActivity activity, Context context, String dialogType) {
        this.context = context;
        this.activity = activity;
        this.dialogType = dialogType;
    }


    public boolean[] getCheckedMultiChoiceArray() {
        return checkedMultiChoiceArray;
    }

    public void setCheckedMultiChoiceArray(boolean[] checkedMultiChoiceArray) {
        this.checkedMultiChoiceArray = checkedMultiChoiceArray;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPositiveButtonText() {
        return positiveButtonText;
    }

    public void setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
    }

    public String getNegativeButtonText() {
        return negativeButtonText;
    }

    public void setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
    }

    public String[] getChoiceArray() {
        return choiceArray;
    }

    public void setChoiceArray(String[] choiceArray) {
        this.choiceArray = choiceArray;
    }

    public int getCustomLayout() {
        return customLayout;
    }

    public void setCustomLayout(int customLayout) {
        this.customLayout = customLayout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getDialogType() {
        return dialogType;
    }

    public void setDialogType(String dialogType) {
        this.dialogType = dialogType;
    }
}
