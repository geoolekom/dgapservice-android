package com.geoolekom.dgapservice.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.geoolekom.dgapservice.R;
import com.geoolekom.dgapservice.models.Comment;
import com.geoolekom.dgapservice.models.Post;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by geoolekom on 06.12.16.
 */

public class DetailPostAdapter extends ArrayAdapter<Comment> {

    public DetailPostAdapter(Context context, List<Comment> comments) {
        super(context, R.layout.comment_item, comments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comment comment = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_item, parent, false);
        }

        TextView authorView = (TextView) convertView.findViewById(R.id.comment_author);
        TextView entryView = (TextView) convertView.findViewById(R.id.comment_entry);

        authorView.setText(comment.getAuthor());
        entryView.setText(Html.fromHtml(comment.getEntry()));

        return convertView;
    }
}
