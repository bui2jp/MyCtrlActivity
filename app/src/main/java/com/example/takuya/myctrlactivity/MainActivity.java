package com.example.takuya.myctrlactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //edit text
    public void onClickEditText(View view) {
        Intent intent = new Intent(this, FormEditTextActivity.class);

        //mainの値を渡す
        TextView editText = (TextView) findViewById(R.id.textView1);
        String message = editText.getText().toString();

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void onClickList(View view) {

        Intent intent = new Intent(this, FormListItemActivity.class);

        //mainの値を渡す
        TextView editText = (TextView) findViewById(R.id.textView2);
        String message = editText.getText().toString();

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    public void onClickGrid(View view) {

        Intent intent = new Intent(this, FormGridItemActivity.class);

        //mainの値を渡す
        TextView editText = (TextView) findViewById(R.id.textView3);
        String message = editText.getText().toString();

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

}

