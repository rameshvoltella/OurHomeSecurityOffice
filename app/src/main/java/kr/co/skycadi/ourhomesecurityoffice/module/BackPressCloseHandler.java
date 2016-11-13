package kr.co.skycadi.ourhomesecurityoffice.module;

import android.app.Activity;
import android.widget.Toast;

public class BackPressCloseHandler
{
    // 객체 생성 및 변수 생성
    private long BackKeyPressdTime = 0; // 뒤로 가기 키를 누르는 시간.
    private Toast FinishToast; // 알림 메시지를 띄워줄 Toast 객체
    private Activity mActivity; // 액티비티 객체

    // 생성자를 통하여 메모리 생성과 액티비티 객체 데이터 초기화
    public BackPressCloseHandler(Activity context)
    {
        this.mActivity = context;
    }

    // 뒤로 가기 동작을 수행하는 메소드
    public void onBackPressed()
    {
        // 2초 안에 Back 버튼 터치가 감지되었을 때, 시간을 누적하고 종료 메시지 출력
        if (System.currentTimeMillis() > BackKeyPressdTime + 2000)
        {
            BackKeyPressdTime = System.currentTimeMillis();
            ShowGuide(); // 가이드 Toast 메시지 출력
            return; // 종료
        }

        // 추가적인 Back 버튼 터치가 2초 내로 감지되었을 때, 액티비티 종료.
        if(System.currentTimeMillis() <= BackKeyPressdTime + 2000)
        {
            mActivity.finish(); // 액티비티 종료
            FinishToast.cancel(); // 가이드 Toast 메시지 없앰.
        }
    }

    // 가이드 Toast 메시지 출력
    public void ShowGuide()
    {
        FinishToast = Toast.makeText(mActivity, "\'뒤로\' 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT); // 메시지 내용 설정
        FinishToast.show(); // 메시지 출력
    }
}
