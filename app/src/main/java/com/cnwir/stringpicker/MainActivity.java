package com.cnwir.stringpicker;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cnwir.stringpicker.view.StringPickerDialog;


public class MainActivity extends ActionBarActivity implements
        View.OnClickListener, StringPickerDialog.OnClickListener {

    private static final String TAG = StringPickerDialog.class.getSimpleName();

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        //将数据传到StringPickerDialog中
        bundle.putStringArray(getString(R.string.string_picker_dialog_values), getStringArray());
        StringPickerDialog dialog = new StringPickerDialog();
        dialog.setArguments(bundle);
        //显示dialog
        dialog.show(getSupportFragmentManager(), TAG);
    }

    /**
     * 该方法为dialog中点击确定后的回调方法
     * @param value
     */

    @Override
    public void onClick(String value) {
        mTextView.setText(value);
    }

    /**
     * 获得要添加的数据
     * @return
     */

    private String[] getStringArray() {
        return new String[] {"18岁","19岁","20岁","21岁","22岁","23岁","24岁",
                "25岁","26岁","27岁","28岁","29岁","30岁","31岁"
             };
    }

}