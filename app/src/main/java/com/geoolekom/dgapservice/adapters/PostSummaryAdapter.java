package com.geoolekom.dgapservice.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.geoolekom.dgapservice.R;
import com.geoolekom.dgapservice.models.Post;


import java.util.List;

/**
 * Created by geoolekom on 06.12.16.
 */

public class PostSummaryAdapter extends ArrayAdapter<Post> {

    public PostSummaryAdapter(Context context, List<Post> posts) {
        super(context, R.layout.feed_item, posts);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post post = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_item, parent, false);
        }

        TextView titleView = (TextView) convertView.findViewById(R.id.post_title);
        TextView entryView = (TextView) convertView.findViewById(R.id.post_entry);

        titleView.setText(post.getTitle());
        entryView.setText(Html.fromHtml(post.getEntry()));

        return convertView;
    }
}
