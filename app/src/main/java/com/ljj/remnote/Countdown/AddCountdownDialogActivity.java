package com.ljj.remnote.Countdown;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.ljj.remnote.RemLog;
import com.ljj.remnote.R;

public class AddCountdownDialogActivity extends Activity {

    //private MyDialog dialog;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_countdown);
        //dialog=new MyDialog(this);
        layout = (ConstraintLayout) findViewById(R.id.add_countdown_constraintLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonAddCountdownExit = findViewById(R.id.button_add_countdown_exit);
        buttonAddCountdownExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onExitButton(view);
            }
        });

        Button buttonAddCountdown = findViewById(R.id.button_add_countdown);
        buttonAddCountdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddButton(view);
            }
        });

        EditText editText = findViewById(R.id.editText_countdown_time);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                flush();
            }
        });
    }

    private void flush() {
        EditText editText = findViewById(R.id.editText_countdown_time);
        String textTime = editText.getText().toString();
        while (textTime.length() < 5) textTime = "0" + textTime;
        int l = textTime.length();
        int s = Integer.parseInt(textTime.substring(l - 2, l));
        int m = Integer.parseInt(textTime.substring(l - 4, l - 2));
        int h = Integer.parseInt(textTime.substring(0, l - 4));

        Button buttonAddCountdown = findViewById(R.id.button_add_countdown);
        buttonAddCountdown.setEnabled(m <= 60 && s <= 60 && (h + m + s != 0));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    private void onExitButton(View view) {
        this.finish();
    }

    private void onAddButton(View view) {
        EditText editTextName = findViewById(R.id.editText_countdown_name);
        String name = editTextName.getText().toString();

        EditText editTextTime = findViewById(R.id.editText_countdown_time);
        String textTime = editTextTime.getText().toString();
        while (textTime.length() < 5) textTime = "0" + textTime;
        int l = textTime.length();
        int s = Integer.parseInt(textTime.substring(l - 2, l));
        int m = Integer.parseInt(textTime.substring(l - 4, l - 2));
        int h = Integer.parseInt(textTime.substring(0, l - 4));

        RemLog.LogD("AddCountdownDialogActivity", "onAddButton name=" + name + ",h=" + h + ",m=" + m + ",s=" + s);
        CountdownManager.getInstance().addCountdownTask(name, h, m, s);

        this.finish();
    }
}