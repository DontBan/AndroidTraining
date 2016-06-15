package jp.mixi.assignment.serializable.beg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private Activity mActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;

        NetworkClient client = new NetworkClient();
        String user = client.getUser(123);
        String friends = client.getFriends();
        // 1. NetworkClientからgetUserでJSONのデータを取得し、取得したデータを適切なクラスを作成し、当てはめてください。
        // 2. さらにgetFriendsで友人の名前の一覧をListViewで表示してください。
        // 3. タップした友人の全情報をDetailActivityで表示してください。

        // userを解析
        User userData = parseJSONString(user);

        // ListViewで友人たちを表示
        JSONArray array = null;
        try {
            array = new JSONArray(friends);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int count = array.length();
        final User[] friendsData = new User[count];
        for (int i = 0; i < count; i++) {
            try {
                friendsData[i] = parseJSONString(array.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // 友人たちの名前の一覧を表示する
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            list.add(friendsData[i].getName());
        }
        ListView listView = (ListView) findViewById(R.id.ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 選んだ友人の詳細を別アクティビティで表示
                Intent intent = new Intent(mActivity, DetailActivity.class);
                intent.putExtra("FriendData", friendsData[i]);
                startActivity(intent);
            }
        });
    }

    private User parseJSONString(String s) {
        User user = new User();
        try {
            JSONObject jsonObject = new JSONObject(s);
            user.setId(jsonObject.getInt("id"));
            user.setName((jsonObject.getString("name")));
            /* ageは記録がない場合がある */
            if (jsonObject.has("age")) {
                user.setAge(jsonObject.getInt("age"));
            } else {
                user.setAge(0);
            }
            /* keywordは記載がない場合がある */
            if (jsonObject.has("keyword")) {
                user.setKeyword(jsonObject.getString("keyword"));
            } else {
                user.setKeyword("");
            }
            /* joinDate 参加日時はJSONObjectの入れ子 */
            JSONObject jsonJoinDate = jsonObject.getJSONObject("joinDate");
            User.JoinDate joinDate = new User.JoinDate();
            joinDate.setYear(jsonJoinDate.getString("year"));
            joinDate.setMonth(jsonJoinDate.getString("month"));
            joinDate.setDate(jsonJoinDate.getString("date"));
            user.setJoinDate(joinDate);

            /* status　も入れ子 */
            User.Status status = new User.Status();
            JSONObject jsonStatus = jsonObject.getJSONObject("status");
            String postedTime = jsonStatus.getString("postedTime");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                status.setPostedDate(format.parse(postedTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            status.setText(jsonStatus.getString("text"));
            user.setStatus(status);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
