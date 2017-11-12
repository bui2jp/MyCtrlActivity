package com.example.takuya.myctrlactivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
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
        String title = intent.getStringExtra("textTitle");
        String message = intent.getStringExtra("textValue");

        int maxLen = intent.getIntExtra("maxLen", 10);
        int inputType = intent.getIntExtra("inputType", InputType.TYPE_CLASS_TEXT);


        TextView textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewTitle.setText(title);

        EditText textView = (EditText) findViewById(R.id.editText);
        textView.setInputType(inputType);
        textView.setText(message);

        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(maxLen);
        textView.setFilters(filters);

        /*
        intent.putExtra("textTitle", "車両名");
        intent.putExtra("textValue", message);

        intent.putExtra("maxLen", 10);
        intent.putExtra("inputType", InputType.TYPE_CLASS_NUMBER);
        */


    }

    public void onClickBack(View view) {

        EditText textView = (EditText) findViewById(R.id.editText);
        String message = textView.getText().toString();

        //TODO:フォーマットチェックをおこなう errorの場合はエラーメッセージを表示
        if (message.length() < 5 ){
            String warningMsg = "入力範囲を確認してください。";

//            new AlertDialog.Builder(FormEditTextActivity.this)
            new AlertDialog.Builder(FormEditTextActivity.this)
                    .setTitle("入力エラー")
                    .setMessage(warningMsg)
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
