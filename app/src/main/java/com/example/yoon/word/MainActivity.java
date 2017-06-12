package com.example.yoon.word;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BackPressCloseHandler backPressCloseHandler;
    ListView listView = null;
    DBManager dbManager = null;
    ArrayList<words> mData = null;
    BaseAdapterEx mAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("단어장");

        ListView listView = (ListView) findViewById(R.id.word_list);

        dbManager = DBManager.getInstance(this);

        String[] columns = new String[]{"_id", "words_name", "words_mean", "words_memo"};
        Cursor c = dbManager.query(columns, null, null, null, null, null);

        if (c != null) {
            mData = new ArrayList<words>();
            while (c.moveToNext()) {
                int id = c.getInt(0);
                String words_name = c.getString(1);
                String words_mean = c.getString(2);
                String words_memo = c.getString(3);
                mData.add(new words(id, words_name, words_mean, words_memo));
            }
            mAdapter = new BaseAdapterEx(this, mData);
            listView.setAdapter(mAdapter);
        }
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent it = new Intent(MainActivity.this, ResultView.class);
                it.putExtra("it_id", mData.get(position).id);
                it.putExtra("it_name", mData.get(position).words_name);
                it.putExtra("it_mean", mData.get(position).words_mean);
                it.putExtra("it_memo", mData.get(position).words_memo);
                startActivity(it);
                finish();

            }// 리스트 값 하나하나 클릭 할 수 있도록 하기 위한 것.

        });
        backPressCloseHandler = new BackPressCloseHandler(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.insert_word: {
                Intent it = new Intent(MainActivity.this, InsertWord.class);
                it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(it);
                finish();
                break;
            }
            case R.id.delete_word: {
                Intent it = new Intent(MainActivity.this, DeleteWord.class);
                it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(it);
                finish();
                break;
            }
            case R.id.help_word: {
                new AlertDialog.Builder(this)
                        .setTitle("도움말")
                        .setMessage(
                                "암기 할 단어를 등록하여 암기하는 방식의 간단한 단어장입니다.\n\n1. 등록 : 암기하고 싶은 단어의 의미와 간단한 메모(예문)을 입력합니다.\n\n2. 확인 : 리스트에서 단어를 눌러 해당 단어의 상세정보 확인이 가능합니다.\n\n3. 수정 : 단어 확인에서 수정버튼을 눌러 수정이 가능합니다.\n\n4. 삭제 : 해당 단어를 체크하여 삭제가 가능합니다.")
                        .setNegativeButton("확인",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                }).show();

                break;

            }
        }
        return super.onOptionsItemSelected(item);// 메뉴 테스트 함수
    }
}
