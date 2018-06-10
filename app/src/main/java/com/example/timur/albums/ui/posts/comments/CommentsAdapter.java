package com.example.timur.albums.ui.posts.comments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.timur.albums.R;
import com.example.timur.albums.data.entity.CommentsModel;
import com.example.timur.albums.data.entity.PostModels;

import java.util.ArrayList;

public class CommentsAdapter extends ArrayAdapter<CommentsModel> {

    private ArrayList<CommentsModel> mArrayList;
    private Context mContext;
    public CommentsAdapter(@NonNull Context context, ArrayList<CommentsModel> arrayList) {
        super(context, 0, arrayList);
        this.mArrayList = arrayList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comments_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CommentsModel models = getItem(position);
        if (models != null) {
            holder.mPostId.setText(String.valueOf(models.getPostId()));
            holder.mId.setText(String.valueOf(models.getId()));
            holder.mName.setText(models.getName());
            holder.mEmail.setText(models.getEmail());
            holder.mBody.setText(models.getBody());
        }
        return convertView;
    }

    public class ViewHolder{
        private TextView mPostId, mId;
        private TextView mName, mBody, mEmail;

        public ViewHolder(View view){
            mPostId = view.findViewById(R.id.tv_user_id);
            mName = view.findViewById(R.id.tv_name);
            mId = view.findViewById(R.id.tv_id);
            mBody = view.findViewById(R.id.tv_body);
            mEmail = view.findViewById(R.id.tv_email);
        }
    }
}
