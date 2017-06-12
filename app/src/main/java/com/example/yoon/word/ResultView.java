package com.example.yoon.word;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultView extends AppCompatActivity {

    public DBManager dbManager = null;
    TextView resultView_word;
    TextView resultView_mean;
    TextView resultView_memo;
    Button resultView_update;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_view);

        setTitle("단어 확인");
        dbManager = DBManager.getInstance(this);

        Intent it = getIntent();
        id = it.getIntExtra("it_id", 0);
        String str_word = it.getStringExtra("it_name");
        String str_mean = it.getStringExtra("it_mean");
        String str_memo = it.getStringExtra("it_memo");

        resultView_word = (TextView) findViewById(R.id.resultView_word);
        resultView_mean = (TextView) findViewById(R.id.resultView_mean);
        resultView_memo = (TextView) findViewById(R.id.resultView_memo);

        resultView_word.setText(str_word);
        resultView_mean.setText(str_mean);
        resultView_memo.setText(str_memo);


    }

    public void update(View v){
        switch (v.getId()){
            case R.id.resultView_update: {
                Intent it = new Intent(this, UpdateWord.class);
                it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                it.putExtra("it_id", this.id);
                it.putExtra("it_name", resultView_word.getText().toString());
                it.putExtra("it_mean", resultView_mean.getText().toString());
                it.putExtra("it_memo", resultView_memo.getText().toString());
                startActivity(it);
                finish();
                break;
            }
            case R.id.resultView_cancel:{
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
                finish();
                break;
            }
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:

                Intent it = new Intent(ResultView.this, MainActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(it);
                finish();
                break;
        }

        return super.onKeyDown(keyCode, event);
    }
}
