package kr.co.skycadi.ourhomesecurityoffice.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;

import kr.co.skycadi.ourhomesecurityoffice.R;

// 스플래시 화면.
public class SplashActivity extends Activity
{
    // 네트워크 관리 변수 선언
    boolean isInternetWiMax = false; // 4G망 상태 값 저장.
    boolean isInternetWiFi = false; // WiFi망 상태 값 저장.
    boolean isInternetMobile = false; // 3G망 상태 값 저장.

    private Handler ControlHandler; // 연결 확인 메시지를 띄워줄 ProgressDialog를 제어할 Handler 객체 선언.
    private ProgressDialog NetworkProgressDialog; // 연결 확인 중이라는 메시지를 띄워줄 ProgressDialog 객체 선언.

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_activity);

        ControlHandler = new Handler(); // ProgressDialog를 제어 해줄 Handler 객체 생성.

        // 연결 상태를 확인하기 위한 ConnectivityManager 객체 생성 및 선언.
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        // 연결 상태망 확인.
        // 3G, 4G, WiFi 등 정상적인 연결이 확인이 되었을 때.
        if(cm.getActiveNetworkInfo() != null)
        {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo(); // 현재 접속된 네트워크의 정보를 가지고 온다.

            // 4G로 연결 될 경우에 처리하는 구간.
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIMAX)
            {
                isInternetWiMax = true; // 4G 상태값 변화.
                RunNetworkProgressDialog(); // ProgressDialog 실행.
            }

            // WiFi로 연결 될 경우에 처리하는 구간.
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
            {
                isInternetWiFi = true; // WiFi 상태값 변화.
                RunNetworkProgressDialog(); // ProgressDialog 실행.
            }

            // 3G로 연결 될 경우에 처리하는 구간.
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
            {
                isInternetMobile = true; // 3G 상태값 변화.
                RunNetworkProgressDialog(); // ProgressDialog 실행.
            }
        }

        // 연결이 정상적으로 되지 않았을 때.
        else
        {
            // 다이얼로그 메시지를 띄워서 네트워크 연결 되도록 함!
            AlertDialog.Builder ErrMsgBuilder = new AlertDialog.Builder(this); // AlertDialog 사용을 위한 Builder 생성

            // AlertDialog 속성 설정
            ErrMsgBuilder.setTitle("네트워크 연결 오류!") // 제목 설정
                    .setMessage("네트워크가 연결되지 않았습니다. 연결된 네트워크를 확인해주세요!") // 메시지 설정
                    .setPositiveButton("확인", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            android.os.Process.killProcess(android.os.Process.myPid()); // 액티비티 죽임
                        }
                    });

            AlertDialog ErrMsgDialog = ErrMsgBuilder.create(); // 알림창 객체 생성
            ErrMsgDialog.show(); // 알림창 띄우기
        }
    }

    // 네트워크 연결이 정상적으로 확인되었을 때, ProgressDialog 창을 실행시켜주는 메소드.
    // 3초 이후에, LoginActivity로 이동함.
    private void RunNetworkProgressDialog()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                NetworkProgressDialog = ProgressDialog.show(SplashActivity.this, "", "네트워크 정상 연결 여부를 확인중입니다.", true);

                // Handler에 연결하여 ProgressDialog 실행 및 관련 동작 수행.
                ControlHandler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            if(NetworkProgressDialog != null && NetworkProgressDialog.isShowing())
                            {
                                // 로그인 화면으로 이동.
                                Intent SplashToLogin = new Intent(SplashActivity.this, LoginActivity.class);
                                startActivity(SplashToLogin);
                                finish();
                            }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, 3000); // 3초 뒤 화면 전환.
            }
        });
    }
}