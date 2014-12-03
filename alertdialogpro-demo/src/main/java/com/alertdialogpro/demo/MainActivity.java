package com.alertdialogpro.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alertdialogpro.AlertDialogPro;
import com.alertdialogpro.ProgressDialogPro;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private int mTheme = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radio_group);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.theme_flavored_material:
                        // Use the default theme defined in xml.
                        mTheme = -1;
                        break;
                    case R.id.theme_material:
                        mTheme = R.style.Theme_AlertDialogPro_Material;
                        break;
                    case R.id.theme_material_light:
                        mTheme = R.style.Theme_AlertDialogPro_Material_Light;
                        break;
                    case R.id.theme_holo:
                        mTheme = R.style.Theme_AlertDialogPro_Holo;
                        break;
                    case R.id.theme_holo_light:
                        mTheme = R.style.Theme_AlertDialogPro_Holo_Light;
                        break;
                    default:
                        break;
                }
            }
        });

        findViewById(R.id.showMessage).setOnClickListener(this);
        findViewById(R.id.showProgress).setOnClickListener(this);
        findViewById(R.id.showProgressHorizontal).setOnClickListener(this);
        findViewById(R.id.showList).setOnClickListener(this);
        findViewById(R.id.showMultiChoiceList).setOnClickListener(this);
        findViewById(R.id.showSingleChoiceList).setOnClickListener(this);
        findViewById(R.id.showCustomView).setOnClickListener(this);
        findViewById(R.id.showNativeAlert).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showMessage:
                showMessageAlertDialog();
                break;
            case R.id.showProgress:
                showProgressDialog();
                break;
            case R.id.showProgressHorizontal:
                showProgressHorizontalDialog();
                break;
            case R.id.showList:
                showListAlertDialog();
                break;
            case R.id.showMultiChoiceList:
                showMultiChoiceListAlertDialog();
                break;
            case R.id.showSingleChoiceList:
                showSingleChoiceListAlertDialog();
                break;
            case R.id.showCustomView:
                showCustomViewDialog();
                break;
            case R.id.showNativeAlert:
                showNativeAlert();
                break;
        }
    }

    private void showNativeAlert() {
        final String[] list = new String[]{"Material theme", "Holo theme", "Custom theme"};
        new AlertDialog.Builder(this).setTitle(R.string.app_name).
                setMultiChoiceItems(list,
                        new boolean[]{false, false, false},
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    mCheckedItems.add(list[which]);
                                } else {
                                    mCheckedItems.remove(list[which]);
                                }
                                showToast(list[which] + " is " + (isChecked ? "checked" : "unchecked" + "."));
                            }
                        }).
                setNeutralButton("More info", new ButtonClickedListener("More info")).
                setNegativeButton("Cancel", new ButtonClickedListener("Cancel")).
                setPositiveButton("Choose", new ButtonClickedListener("Chose " + mCheckedItems.toString())).show();

    }

    private void showMessageAlertDialog() {
        new AlertDialogPro.Builder(this, mTheme).setTitle(R.string.app_name).
                setMessage("Hello, charming AlertDialogPro!").
                setPositiveButton("Nice Job", new ButtonClickedListener("Dismiss")).
                show();
    }

    private void showProgressDialog() {
        ProgressDialogPro dialog = new ProgressDialogPro(this, mTheme);
        dialog.setMessage("Hello, charming ProgressDialogPro!");
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setProgressStyle(ProgressDialogPro.STYLE_HORIZONTAL);
        dialog.show();
    }

    private void showProgressHorizontalDialog() {
        ProgressDialogPro dialog = new ProgressDialogPro(this, mTheme);
        dialog.setMessage("Hello, charming ProgressDialogPro!");
        dialog.setProgressStyle(ProgressDialogPro.STYLE_HORIZONTAL);
        dialog.setIndeterminate(true);
        dialog.setProgress(34);
        dialog.setMax(100);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void showListAlertDialog() {
        final String[] list = new String[]{"Argentina", "Canada", "China (中国)", "Japan (日本)",
                "United States"};
        new AlertDialogPro.Builder(this, mTheme).setTitle("Choose your country").
                setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast(list[which]);
                    }
                }).show();
    }


    private List<String> mCheckedItems = new ArrayList<String>();

    private void showMultiChoiceListAlertDialog() {
        final String[] list = new String[]{"Material theme", "Holo theme", "Custom theme"};
        new AlertDialogPro.Builder(this, mTheme).setTitle(R.string.app_name).
                setMultiChoiceItems(list,
                        new boolean[]{false, false, false},
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    mCheckedItems.add(list[which]);
                                } else {
                                    mCheckedItems.remove(list[which]);
                                }
                                showToast(list[which] + " is " + (isChecked ? "checked" : "unchecked" + "."));
                            }
                        }).
                setNeutralButton("More info", new ButtonClickedListener("More info")).
                setNegativeButton("Cancel", new ButtonClickedListener("Cancel")).
                setPositiveButton("Choose", new ButtonClickedListener("Chose " + mCheckedItems.toString())).show();

    }

    private String mCheckedItem;

    private void showSingleChoiceListAlertDialog() {
        final String[] list = new String[]{"Female", "Male"};
        int checkedItemIndex = 0;
        mCheckedItem = list[checkedItemIndex];
        new AlertDialogPro.Builder(this, mTheme).setTitle("Edit your gender").
                setSingleChoiceItems(list,
                        checkedItemIndex,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mCheckedItem = list[which];
                                showToast(mCheckedItem);
                            }
                        }).
                setNegativeButton("Cancel", new ButtonClickedListener("Cancel")).
                setPositiveButton("Save", new ButtonClickedListener(mCheckedItem + " has been chosen.")).show();
    }

    private void showCustomViewDialog() {
        AlertDialogPro.Builder builder = new AlertDialogPro.Builder(this, mTheme);
        builder.setTitle("Edit your name").setView(R.layout.input_view).setNegativeButton("Cancel", new ButtonClickedListener("Cancel")).
                setPositiveButton("Save", null).show();
    }


    private Toast mToast = null;

    private void showToast(CharSequence toastText) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
        mToast.show();
    }

    private class ButtonClickedListener implements DialogInterface.OnClickListener {
        private CharSequence mShowWhenClicked;

        public ButtonClickedListener(CharSequence showWhenClicked) {
            mShowWhenClicked = showWhenClicked;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            showToast("\"" + mShowWhenClicked + "\"" + " button clicked.");
        }
    }

}
