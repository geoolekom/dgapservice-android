package com.geoolekom.dgapservice.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.geoolekom.dgapservice.R;
import com.geoolekom.dgapservice.adapters.DetailPostAdapter;
import com.geoolekom.dgapservice.adapters.FeedPostAdapter;
import com.geoolekom.dgapservice.models.Comment;
import com.geoolekom.dgapservice.models.Post;
import com.geoolekom.util.JsonFactory;
import com.geoolekom.util.UrlFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by geoolekom on 06.12.16.
 */

public class PostActivity extends Activity {

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Post post;
    private List<Comment> comments = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle initBundle = getIntent().getExtras();
        int postId = initBundle.getInt("post_id");
        popMessage(Integer.toString(postId));
        setContentView(R.layout.post);

        getPost(postId);
        LinearLayout layout = (LinearLayout) findViewById(R.id.post_detail);

        final DetailPostAdapter adapter = new DetailPostAdapter(this, comments);
        ListView list = new ListView(this);
        list.setAdapter(adapter);
/*
        CardView postView = (CardView) findViewById(R.id.post_card);
        TextView titleView = (TextView) postView.findViewById(R.id.post_title);
        TextView entryView = (TextView) postView.findViewById(R.id.post_entry);

        titleView.setText(post.getTitle());
        entryView.setText(post.getEntry());

        layout.addView(postView);
*/
        layout.addView(list, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

    }

    public void popMessage(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void getPost(final int id) {
        try {
            post = executor.submit(new Callable<Post>() {
                @Override
                public Post call() {
                    try {
                        JSONObject postsJson = JsonFactory.getFromUrl(UrlFactory.getUrl() + "/api/posts/?id=" + id);
                        JSONArray postsArray = postsJson.getJSONArray("posts");
                        JSONObject postData = postsArray.getJSONObject(0);
                        JSONArray commentsJson = postData.getJSONArray("comments");
                        for (int i = 0; i < commentsJson.length(); i++) {
                            JSONObject commentJson = commentsJson.getJSONObject(i);
                            comments.add(new Comment(
                                    commentJson.getString("author"),
                                    commentJson.getString("entry")
                            ));
                        }
                        return new Post(
                                postData.getInt("id"),
                                postData.getString("title"),
                                postData.getString("entry"),
                                postData.getString("author")
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
