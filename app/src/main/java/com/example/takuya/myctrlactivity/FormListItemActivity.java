package com.example.takuya.myctrlactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 検索つきのリストを実装する
 *
 * 検索用のEditBoxの表示・非表示は切り替え可能にする
 * 検索用のEditBoxの値をそのまま利用できるようにする
 * リストのインデックスを返すパターンに対応する
 *
 */
public class FormListItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity_list_item);

        Intent intent = getIntent();
        ArrayList<String> arr = intent.getStringArrayListExtra("arrayStringValue");

        //String message = intent.getStringExtra("textValue");

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("LIST","click --->" + position + " : " + id);

                //値を設定して呼び出し元に返す
                //画面の値を設定する
                EditText searchText = (EditText)findViewById(R.id.searchText);
                String searchTextValue = searchText.getText().toString();

                Intent intent = getIntent();
                intent.putExtra("textValue", searchTextValue);
                intent.putExtra("selectedIndex",position);

                setResult( Activity.RESULT_OK, intent );

                finish();
            }
        });

        //adapterを作成して設定
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arr
        );
        listView.setAdapter(adapter);



        EditText searchText = (EditText)findViewById(R.id.searchText);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //adapterを更新する
                updateAdapter();
            }
        });

        //searchText
        boolean bSearchTextVisible = intent.getBooleanExtra("searchText", true);
        if( bSearchTextVisible ) {
            searchText.setVisibility(View.VISIBLE);
        }else{
            searchText.setVisibility(View.GONE);
        }
    }

    public void onClickBack(View view) {

        //戻るボタンではsearchTextを設定
        //indexには-1を設定しておく
        EditText searchText = (EditText)findViewById(R.id.searchText);
        String searchTextValue = searchText.getText().toString();

        Intent intent = getIntent();
        intent.putExtra("textValue", searchTextValue);
        intent.putExtra("selectedIndex",-1);

        setResult( Activity.RESULT_OK, intent );

        finish();
    }

    private void updateAdapter(){
        ListView listView = (ListView)findViewById(R.id.listView);

        //adapterの再作成と再設定
        EditText searchText = (EditText) findViewById(R.id.searchText);
        String searchString = searchText.getText().toString();

        Intent intent = getIntent();
        ArrayList<String> arrOrg = intent.getStringArrayListExtra("arrayStringValue");

        ArrayList<String> arrNew = new ArrayList<String>();


        for(String orgItem: arrOrg){
            if ( orgItem.contains(searchString)) {
                arrNew.add(orgItem);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arrNew
        );
        listView.setAdapter(adapter);

    }

}
