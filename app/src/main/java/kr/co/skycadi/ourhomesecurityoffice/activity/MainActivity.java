package kr.co.skycadi.ourhomesecurityoffice.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import kr.co.skycadi.ourhomesecurityoffice.R;
import kr.co.skycadi.ourhomesecurityoffice.module.BackPressCloseHandler;

public class MainActivity extends Activity
{
    // 객체 선언
    private BackPressCloseHandler backPressCloseHandler; // 뒤로 가기 종료를 수행하는 핸들러 객체 선언

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);

        // 객체 생성.
        backPressCloseHandler = new BackPressCloseHandler(this); // 뒤로 가기 종료를 수행하는 핸들러 객체 생성
    }

    // 뒤로 가기 버튼 눌렀을 때 핸들러 동작 수행
    @Override
    public void onBackPressed()
    {
        backPressCloseHandler.onBackPressed();
    }
}
