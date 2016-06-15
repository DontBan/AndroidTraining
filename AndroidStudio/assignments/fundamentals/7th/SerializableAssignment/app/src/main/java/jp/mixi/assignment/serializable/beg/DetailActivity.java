package jp.mixi.assignment.serializable.beg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent received = getIntent();
        User friendData = received.getParcelableExtra("FriendData");
        // 友人のデータを表示する
        // ID
        TextView tvUserId = (TextView) findViewById(R.id.userId);
        tvUserId.setText(String.valueOf(friendData.getId()));
        // 名前
        TextView tvUserName = (TextView) findViewById(R.id.userName);
        tvUserName.setText(friendData.getName());
        // 年齢
        TextView tvUserAge = (TextView) findViewById(R.id.userAge);
        tvUserAge.setText(String.valueOf(friendData.getAge()));
        // キーワード
        TextView tvUserKeyword = (TextView) findViewById(R.id.userKeyword);
        tvUserKeyword.setText(friendData.getKeyword());
        // 参加日時
        TextView tvUserJoinDate = (TextView) findViewById(R.id.userJoinDate);
        String joinDate = friendData.getJoinDate().getYear() + "/"
                + friendData.getJoinDate().getMonth() + "/"
                + friendData.getJoinDate().getDate();
        tvUserJoinDate.setText(joinDate);
        // 送信時刻
        TextView tvUserPostedTime = (TextView) findViewById(R.id.userPostedTime);
        tvUserPostedTime.setText(friendData.getStatus().getPostedDate().toString());
        // テキスト
        TextView tvUserText = (TextView) findViewById(R.id.userText);
        tvUserText.setText(friendData.getStatus().getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
