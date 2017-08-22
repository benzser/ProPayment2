package com.example.jaruwatsukhamjohn.propayment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.List;

/**
 * Created by Jaruwat Sukhamjohn on 15/4/2558.
 */
public class SingleChoiceDialogFragment extends DialogFragment {
    public static final String DATA = "items";
    public static final String SELECTED = "selected";
    private SelectionListener listener;
    public int select = 0;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (SelectionListener) activity;
        } catch (ClassCastException oops) {
            oops.printStackTrace();
        }
    }



    public interface SelectionListener {
        public void selectItem(int position);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Please Select");
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                listener.selectItem(select);
                dialog.dismiss();
            }
        });
        List<String> list = (List<String>) bundle.get(DATA);
        int position = bundle.getInt(SELECTED);
        CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
        dialog.setSingleChoiceItems(cs, position, selectItemListener);
        return dialog.create();
    }

    /* class PositiveButtonClickListener implements DialogInterface.OnClickListener
     {
         @Override
         public void onClick(DialogInterface dialog, int which)
         {
             dialog.dismiss();
         }
     }*/
    DialogInterface.OnClickListener selectItemListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // process
            if (listener != null) {
                select = which;
            }
        }
    };
}
