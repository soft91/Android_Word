package com.example.yoon.word;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertWord extends AppCompatActivity {

    EditText insertWord_word;
    EditText insertWord_mean;
    EditText insertWord_memo;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_insert);
        setTitle("단어등록");

        insertWord_word = (EditText) findViewById(R.id.insertWord_word);
        insertWord_mean = (EditText) findViewById(R.id.insertWord_mean);
        insertWord_memo = (EditText) findViewById(R.id.insertWord_memo);

    }

    public void Insert(View v) {
        switch (v.getId()) {
            case R.id.InsertWord_check: {
                ContentValues addRowValue = new ContentValues();
                dbManager = new DBManager(this);

                if (insertWord_word.getText().toString().equals("")) {
                    Toast.makeText(this, "'단어'를 반드시 입력해주세요.", Toast.LENGTH_SHORT)
                            .show();
                } else if (insertWord_word.getText().toString().equals(" ")) {
                    Toast.makeText(this, "첫 글자는 공백이 들어갈 수 없습니다.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    addRowValue.put("words_name", insertWord_word.getText().toString());
                    addRowValue.put("words_mean", insertWord_mean.getText().toString());
                    addRowValue.put("words_memo", insertWord_memo.getText().toString());

                    long insertRecordId = dbManager.insert(addRowValue);
                    Toast.makeText(this, "등록 되었습니다.", Toast.LENGTH_SHORT).show();

                    Intent it = new Intent(this, MainActivity.class);
                    it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(it);
                    finish();
                }
                break;
            }
            case R.id.InsertWord_cancel: {
                Intent it = new Intent(this, MainActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(it);
                finish();
                break;
            }
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
            finish();
        }
        return super.dispatchKeyEvent(event);
    }//뒤로 가기

}
