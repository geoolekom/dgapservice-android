package com.geoolekom.dgapservice.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.geoolekom.dgapservice.R;
import com.geoolekom.dgapservice.adapters.PostSummaryAdapter;
import com.geoolekom.dgapservice.models.Post;
import com.geoolekom.util.JsonFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FeedActivity extends Activity {

    private ListView list;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);

        LinearLayout layout = (LinearLayout) findViewById(R.id.feed);
        List<Post> posts = getPosts();

        PostSummaryAdapter adapter = new PostSummaryAdapter(this, posts);
        list = new ListView(this);
        list.setAdapter(adapter);
        /*
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                TextView text = (TextView) view;
                popMessage(text.getText());
            }
        });
        */

        layout.addView(list, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public void popMessage(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private List<Post> getPosts() {
        final List<Post> posts = new ArrayList<>();
        try {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject postsJson = JsonFactory.getFromUrl("http://a4713ae2.ngrok.io/api/posts/");
                        JSONArray postsArray = postsJson.getJSONArray("posts");
                        for (int i = postsArray.length() - 1; i >= 0; i--) {
                            JSONObject postData = postsArray.getJSONObject(i);
                            posts.add(new Post(
                                    postData.getString("title"),
                                    postData.getString("entry"),
                                    postData.getString("author")
                            ));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }
}
