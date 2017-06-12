package com.example.yoon.word;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateWord extends AppCompatActivity {

    public DBManager dbManager = null;
    Button updateWord_check, updateWord_cancel;
    EditText updateWord_word, updateWord_mean, updateWord_memo;
    int id;
    ArrayList<words> arr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_word);
        setTitle("단어 수정");

        dbManager = DBManager.getInstance(this);

        Intent it = getIntent();

        id = it.getIntExtra("it_id",0);

        String str_word = it.getStringExtra("it_name");
        String str_mean = it.getStringExtra("it_mean");
        String str_memo = it.getStringExtra("it_memo");

        updateWord_word = (EditText) findViewById(R.id.updateWord_word);
        updateWord_mean = (EditText) findViewById(R.id.updateWord_mean);
        updateWord_memo = (EditText) findViewById(R.id.updateWord_memo);
        updateWord_check = (Button) findViewById(R.id.updateWord_check);
        updateWord_cancel = (Button) findViewById(R.id.updateWord_cancel);

        updateWord_word.setText(str_word);
        updateWord_mean.setText(str_mean);
        updateWord_memo.setText(str_memo);

    }

    public void update(View v) {
        switch (v.getId()) {
            case R.id.updateWord_check: {
                Intent it = new Intent(this, MainActivity.class);

                dbManager = DBManager.getInstance(this);

                ContentValues updateRowValue = new ContentValues();

                updateRowValue.put("words_name", updateWord_word.getText().toString());
                updateRowValue.put("words_mean", updateWord_mean.getText().toString());
                updateRowValue.put("words_memo", updateWord_memo.getText().toString());

                int updateRecordCnt = dbManager.update(updateRowValue, "_id = "
                        + this.id, null);
                Toast.makeText(this, "수정되었습니다.", Toast.LENGTH_SHORT).show();

                startActivity(it);
                finish();
                break;

            }
            case R.id.updateWord_cancel: {
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
                finish();
                break;
            }
        }
    }
}
