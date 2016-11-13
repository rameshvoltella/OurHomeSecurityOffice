package kr.co.skycadi.ourhomesecurityoffice.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kr.co.skycadi.ourhomesecurityoffice.R;
import kr.co.skycadi.ourhomesecurityoffice.module.BackPressCloseHandler;

// 로그인 액티비티.
public class LoginActivity extends Activity
{
    // 객체 선언.
    private BackPressCloseHandler backPressCloseHandler; // 뒤로 가기 종료를 수행하는 핸들러 객체 선언

    // 로그인 관련 객체 선언
    private EditText et_LoginID; // 아이디를 입력하는 EditText.
    private EditText et_LoginPW; // 비밀번호를 입력하는 EditText.
    private String InputLoginID; // EditText로 입력 받은 로그인 아이디.
    private String InputLoginPW; // EditText로 입력 받은 로그인 비밀번호.
    private Button btn_Login; // 로그인 버튼.

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_activity);

        // 객체 생성.
        backPressCloseHandler = new BackPressCloseHandler(this); // 뒤로 가기 종료를 수행하는 핸들러 객체 생성.

        // 로그인 관련 객체 생성.
        et_LoginID = (EditText)findViewById(R.id.et_login_enter_id); // 아이디를 입력하는 EditText
        et_LoginPW = (EditText)findViewById(R.id.et_login_enter_pw); // 비밀번호를 입력하는 EditText
        btn_Login = (Button)findViewById(R.id.btn_login_login); // 로그인 버튼

        // 로그인 버튼을 눌렀을 때의 처리.
        btn_Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // EditText로 입력 받은 아이디와 비밀번호를 String 객체로 변환함.
                InputLoginID = et_LoginID.getText().toString();
                InputLoginPW = et_LoginPW.getText().toString();

                // 아이디나 비밀번호에 공백이 있을 경우에 처리
                if(InputLoginID.isEmpty() || InputLoginPW.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "빠진 항목이 있으니 확인해주세요.", Toast.LENGTH_SHORT).show();
                }

                // 그렇지 않을 경우 정상 처리
                else
                {
                    // 로그인이 완료될 경우에 화면 전환.
                    Intent LoginToMain = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(LoginToMain);
                    finish();
                }
            }
        });
    }

    // 뒤로 가기 버튼 눌렀을 때 핸들러 동작 수행
    @Override
    public void onBackPressed()
    {
        backPressCloseHandler.onBackPressed();
    }
}