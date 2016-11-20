package kr.co.skycadi.ourhomesecurityoffice.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import kr.co.skycadi.ourhomesecurityoffice.R;
import kr.co.skycadi.ourhomesecurityoffice.module.BackPressCloseHandler;

// 회원가입(아이디 & 비밀번호 생성 화면)
public class JoinIDPWActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.join_id_pw_activity);
    }

    @Override
    public void onBackPressed()
    {
        // 로그인 & 회원가입 화면으로 이동함.
        Intent JoinToLogin = new Intent(JoinIDPWActivity.this, LoginActivity.class);
        startActivity(JoinToLogin);
        finish();
    }
}
