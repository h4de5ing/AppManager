package com.code19.appmanager.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.code19.appmanager.interfaces.OnDialogItemSelected;

/**
 * Created by gh0st on 2017/2/17.
 */

public class ViewUtils {
    public static void listDialog(Activity activity, String[] list, final OnDialogItemSelected onDialogItemSelected) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1);
        for (String s : list) {
            arrayAdapter.add(s);
        }
        dialog.setCancelable(true);
        dialog.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onDialogItemSelected.onItemSelected(i);
            }
        });
        if (!activity.isFinishing()) {
            dialog.show();
        }
    }
}
