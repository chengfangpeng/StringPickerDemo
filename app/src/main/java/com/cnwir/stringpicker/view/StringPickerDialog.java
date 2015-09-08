package com.cnwir.stringpicker.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.cnwir.stringpicker.R;

/**
 *
 * 在该类中创建一个dialog
 * 复写了onCreateDialog方法去创建一个dialog,为了省事我利用了AlertDialog
 * 如果觉得效果不是太好
 * 当然也可以复写onCreateView方法自定义Dialog中的所有内容,并且自定义回调接口
 * Created by cfp on 15-9-8.
 */
public class StringPickerDialog extends DialogFragment {

    private OnClickListener mListener;

    private Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        if (!(activity instanceof OnClickListener)) {
            throw new RuntimeException("callback is must implements StringPickerDialog.OnClickListener!");
        }
        mListener = (OnClickListener) activity;
        mActivity = activity;
        super.onAttach(activity);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View view = inflater.inflate(R.layout.string_picker_dialog, null, false);

        final StringPicker stringPicker = (StringPicker) view.findViewById(R.id.string_picker);
        final Bundle params = getArguments();
        if (params == null) {
            throw new RuntimeException("params is null!");
        }

        final String[] values = params.getStringArray(getValue(R.string.string_picker_dialog_values));
        stringPicker.setValues(values);

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//        builder.setTitle(getValue(R.string.string_picker_dialog_title));
        builder.setPositiveButton(getValue(R.string.string_picker_dialog_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onClick(stringPicker.getCurrentValue());
            }
        });
//        builder.setNegativeButton(getValue(R.string.string_picker_dialog_cancel), null);
         builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    private String getValue(final int resId) {
        return mActivity.getString(resId);
    }

    public interface OnClickListener {
        void onClick(final String value);
    }

}