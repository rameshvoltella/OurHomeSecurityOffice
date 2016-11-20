package kr.co.skycadi.ourhomesecurityoffice.activity;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import kr.co.skycadi.ourhomesecurityoffice.R;
import kr.co.skycadi.ourhomesecurityoffice.module.BackPressCloseHandler;

public class MainActivity extends Activity
{
    // 객체 선언
    private BackPressCloseHandler backPressCloseHandler; // 뒤로 가기 종료를 수행하는 핸들러 객체 선언.
    private Button btn_slide; // 슬라이드 메뉴를 열어주는 버튼.

    // DrawerLayout 관련한 객체 생성
    private DrawerLayout MainDrawerLayout; // DrawerLayout 뼈대.
    private View MainDrawerView; // DrawerLayout에 깔리는 View.

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);

        // 객체 생성.
        backPressCloseHandler = new BackPressCloseHandler(this); // 뒤로 가기 종료를 수행하는 핸들러 객체 생성.
        btn_slide = (Button)findViewById(R.id.btn_main_slide_open); // 슬라이드 메뉴를 열어주는 버튼.

        // DrawerLayout 관련 객체 선언
        MainDrawerLayout = (DrawerLayout)findViewById(R.id.main_drawer_layout); // DrawerLayout 뼈대.
        MainDrawerView = (View)findViewById(R.id.main_drawer); // DrawerLayout에 깔리는 View.

        // 슬라이드 메뉴 열기.
        btn_slide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 슬라이드 메뉴를 열어주면서 View 들을 꺼냄.
                MainDrawerLayout.openDrawer(MainDrawerView);
            }
        });

        // DrawerLayout 사용을 위한 DrawListener 선언
        MainDrawerLayout.addDrawerListener(MainDrawerListener);
    }

    // DrawerListener 객체 선언
    DrawerLayout.DrawerListener MainDrawerListener = new DrawerLayout.DrawerListener()
    {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset)
        {

        }

        @Override
        public void onDrawerOpened(View drawerView)
        {

        }

        @Override
        public void onDrawerClosed(View drawerView)
        {

        }

        @Override
        public void onDrawerStateChanged(int newState)
        {

        }
    };

    // 뒤로 가기 버튼 눌렀을 때 핸들러 동작 수행
    @Override
    public void onBackPressed()
    {
        backPressCloseHandler.onBackPressed();
    }
}
