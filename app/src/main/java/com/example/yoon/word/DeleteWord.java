package com.example.yoon.word;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteWord extends AppCompatActivity {

    public DBManager dbManager = null;
    ListView listView = null;
    ArrayList<words2> arr = null;
    BaseAdapterEx2 adapter = null;
    int id;
    public static boolean check = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_word);

        setTitle("단어 삭제");

        listView = (ListView) findViewById(R.id.word_list);
        Intent it = getIntent();

        id = it.getIntExtra("it_id", 0);

        dbManager = DBManager.getInstance(this);
        String[] columns = new String[]{"_id", "words_name", "words_mean", "words_memo"};
        Cursor c = dbManager.query(columns, null, null, null, null, null);
        if (c != null) {
            arr = new ArrayList<words2>();
            while (c.moveToNext()) {
                int id = c.getInt(0);
                String words_name = c.getString(1);
                String words_mean = c.getString(2);
                String words_memo = c.getString(3);
                arr.add(new words2(id, words_name, words_mean, words_memo));
            }// 삭제 리스트에 보여주는 것 들.

            adapter = new BaseAdapterEx2(this, arr);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    adapter.getItem(position).select = !adapter
                            .getItem(position).select;
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.delete_word_menu, menu);
        return super.onCreateOptionsMenu(menu);// 단어장 만들기 함수
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.all_delete: {
                for (int i = 0; i < adapter.getCount(); i++) {
                    adapter.getItem(i).select = check;
                }
                adapter.notifyDataSetChanged();
                check = !check;
                break;
            }// 전체선택

            case R.id.delete_words: {
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (adapter.getItem(i).select) {

                        int deleteRecordCnt = dbManager.delete(
                                "_id = " + adapter.getItem(i).id, null);//삭제 쿼리
                        check = true;

                        Toast.makeText(this, "삭제 되었습니다.", Toast.LENGTH_SHORT)
                                .show();
                        Intent it = new Intent(this, MainActivity.class);
                        it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(it);
                        finish();
                    }

                }

                if (check == false) {
                    Toast.makeText(this, "삭제할 대상을 선택해주세요.", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    check = false;
                }

                break;
            }// 선택 삭제.

        }

        return super.onOptionsItemSelected(item);// 메뉴 테스트 함수
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            // 하드웨어 뒤로가기 버튼에 따른 이벤트 설정
            case KeyEvent.KEYCODE_BACK:

                Intent it = new Intent(this, MainActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(it);
                finish();
                break;
        }

        return super.onKeyDown(keyCode, event);
    }
}
