package com.geoolekom.dgapservice.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.geoolekom.dgapservice.R;
import com.geoolekom.dgapservice.models.Post;


import java.util.List;

/**
 * Created by geoolekom on 06.12.16.
 */

public class FeedPostAdapter extends ArrayAdapter<Post> {

    public FeedPostAdapter(Context context, List<Post> posts) {
        super(context, R.layout.post_item, posts);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Post post = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.post_item, parent, false);
        }

        TextView titleView = (TextView) convertView.findViewById(R.id.post_title);
        TextView entryView = (TextView) convertView.findViewById(R.id.post_entry);
        Button plus = (Button) convertView.findViewById(R.id.plus_button);
        Button minus = (Button) convertView.findViewById(R.id.minus_button);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button plus = (Button) view;
                plus.setText("Liked");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button minus = (Button) view;
                minus.setText("Disliked");
            }
        });

        titleView.setText(post.getTitle());
        entryView.setText(Html.fromHtml(post.getEntry()));

        return convertView;
    }
}
