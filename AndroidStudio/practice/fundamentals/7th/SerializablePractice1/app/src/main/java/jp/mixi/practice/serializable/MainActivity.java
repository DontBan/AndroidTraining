package jp.mixi.practice.serializable;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. 取得したデータをUserクラスにマッピングしてください。
        // 2. UserクラスにParcelableインターフェイスを実装してください。
        // 各項目を画面に表示してください
        NetworkClient client = new NetworkClient();
        String user = client.getUser(123);

        // JSONの文字列userを解析しUserクラスに入れる
        User you = new User();
        try {
            JSONObject jsonObject = new JSONObject(user);
            you.setId(jsonObject.getInt("id"));
            you.setName(jsonObject.getString("name"));
            // 年齢の記載が無い場合がある
            if (jsonObject.has("age")) {
                you.setAge(jsonObject.getInt("age"));
            } else {
                you.setAge(0);
            }
            // キーワードの記載がない場合がある
            if (jsonObject.has("keyword")) {
                you.setKeyword(jsonObject.getString("keyword"));
            } else {
                you.setKeyword("");
            }
            // 入れ子のJSONObject
            JSONObject status = jsonObject.getJSONObject("status");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
            String postedTime = status.getString("postedTime");
            Date d = sdf.parse(postedTime);
            User.Status s = new User.Status();
            s.setPostedDate(d);
            s.setText(status.getString("text"));
            you.setStatus(s);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // activity_mainに反映
        TextView userId = (TextView) findViewById(R.id.userId);
        userId.setText(String.valueOf(you.getId()));
        TextView userName = (TextView) findViewById(R.id.userName);
        userName.setText(you.getName());
        TextView userAge = (TextView) findViewById(R.id.userAge);
        userAge.setText(String.valueOf(you.getAge()));
        TextView userKeyword = (TextView) findViewById(R.id.userKeyword);
        userKeyword.setText(you.getKeyword());
        TextView userStatusText = (TextView) findViewById(R.id.userStatusText);
        userStatusText.setText(you.getStatus().getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

