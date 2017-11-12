package com.example.takuya.myctrlactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new AlertDialog.Builder(this)
//                .setTitle("title")
//                .setMessage("message")
//                .setPositiveButton("OK", null)
//                .show();

    }

    public void onActivityResult( int requestCode, int resultCode, Intent intent )
    {
        // startActivityForResult()の際に指定した識別コードとの比較
        if( requestCode == 1001 ){

            // 返却結果ステータスとの比較
            if( resultCode == Activity.RESULT_OK ){

                // 返却されてきたintentから値を取り出す
                String str = intent.getStringExtra( "textValue" );

                //画面へ設定する
                TextView editText = (TextView) findViewById(R.id.textView1);
                editText.setText(str);
            }
        }

        if( requestCode == 2001 ){
            if( resultCode == Activity.RESULT_OK ){

                // 返却されてきたintentから値を取り出す
                String str = intent.getStringExtra( "textValue" );

                int index = intent.getIntExtra("selectedIndex", -1);

                str = str + ":" + index;

                //画面へ設定する
                TextView editText = (TextView) findViewById(R.id.textView2);
                editText.setText(str);
            }
        }
    }

    //edit text
    public void onClickEditText(View view) {
        Intent intent = new Intent(this, FormEditTextActivity.class);

        //mainの値を渡す
        TextView editText = (TextView) findViewById(R.id.textView1);
        String message = editText.getText().toString();

        intent.putExtra("textTitle", "車両名");
        intent.putExtra("textValue", message);

        intent.putExtra("maxLen", 10);
        intent.putExtra("inputType", InputType.TYPE_CLASS_NUMBER);

        //startActivity(intent);
        int requestCode = 1001;

        //値を受け取る為にstartActivityForResultで起動する
        startActivityForResult(intent,requestCode);
    }

    //list
    public void onClickList(View view) {

        Intent intent = new Intent(this, FormListItemActivity.class);

        //mainの値を渡す
        TextView editText = (TextView) findViewById(R.id.textView2);
        String message = editText.getText().toString();

        ArrayList<String> carlist = new ArrayList<String>();
        for(int i = 0; i<10; i++ ){
            carlist.add("data - " + String.valueOf(i));
        }
        carlist.add("abcdefg");
        carlist.add("hijklmn");
        carlist.add("opqrstu");
        carlist.add("vwxyz");

        intent.putExtra("arrayStringValue",carlist);

        int requestCode = 2001;

        //値を受け取る為にstartActivityForResultで起動する
        //startActivity(intent);
        startActivityForResult(intent,requestCode);
    }

    public void onClickGrid(View view) {

//        Intent intent = new Intent(this, FormGridItemActivity.class);
//
//        //mainの値を渡す
//        TextView editText = (TextView) findViewById(R.id.textView3);
//        String message = editText.getText().toString();
//
//        //intent.putExtra("", message);
//        startActivity(intent);intent

    }

}

