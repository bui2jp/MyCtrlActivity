package com.example.takuya.myctrlactivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 入力制限付きのTextViewを実装する
 * フォーマットチェックも実装する（エラーの場合はメッセージを表示する）
 */
public class FormEditTextActivity extends AppCompatActivity {
//public class FormEditTextActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity_edit_text);

        Intent intent = getIntent();
        String message = intent.getStringExtra("textValue");

        EditText textView = (EditText) findViewById(R.id.editText);
        textView.setText(message);

    }


    public void onClickBack(View view) {

        EditText textView = (EditText) findViewById(R.id.editText);
        String message = textView.getText().toString();

        //TODO:フォーマットチェックをおこなう errorの場合はエラーメッセージを表示
        if (message.length() < 5 ){
            String warningMsg = "入力範囲を確認してください。";

//            new AlertDialog.Builder(FormEditTextActivity.this)
            new AlertDialog.Builder(FormEditTextActivity.this)
                    .setTitle("title")
                    .setMessage("message")
                    .setPositiveButton("OK", null)
                    .show();

            return;
        }


        //画面の値を設定する
        Intent intent = getIntent();
        intent.putExtra("textValue", message);

        //String message = intent.getStringExtra("textValue");

        setResult( Activity.RESULT_OK, intent );

        finish();
    }

}
