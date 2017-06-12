package com.example.yoon.word;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Yoon on 2017-05-28.
 */

public class BackPressCloseHandler {

    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;//첫 번째 누름.
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            Log.e("dd", "dd");
            System.exit(1);

            toast.cancel();//두 번째 누름.
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.",
                Toast.LENGTH_SHORT);
        toast.show();//한번 눌렀을 때 나타나는 알림창.
    }


}
