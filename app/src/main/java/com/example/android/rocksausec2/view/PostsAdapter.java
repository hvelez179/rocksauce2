package com.example.android.rocksausec2.view;


import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.rocksausec2.R;
import com.example.android.rocksausec2.model.Data_;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{

    private static int SCREEN_WIDTH_SLICE_TO_USE = 3;
    private static int POST_ANIMATION_DURATION = 800;
    private static float POST_INITIAL_ALPHA = .3f;

    public interface PostsAdapterListener{

        void onItemClick(Data_ post);
    }

    Context mContext;
    PostsAdapterListener mListener;
    List<NetworkCall> mPosts;
    int mDistanceToMovePostX = 0;

    public PostsAdapter(ArrayList<Data_> posts, PostsAdapterListener listener) {

        mPosts = posts;
        mListener = listener;
    }

    public void clear() {

        mPosts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<NetworkCall> posts) {
        mPosts.addAll(posts);
        notifyDataSetChanged();
    }

    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext = parent.getContext();

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        mDistanceToMovePostX = display.getWidth() / SCREEN_WIDTH_SLICE_TO_USE;

        return new ViewHolder( LayoutInflater.from(mContext).inflate( R.layout.item_post_list, parent, false));

    }

    @Override
    public void onBindViewHolder(PostsAdapter.ViewHolder holder, int position) {

        if(mPosts == null || mPosts.isEmpty())
            return;

        final Data_ post = mPosts.get( position );

        if(!"".equals(post.getThumbnail())) {

            Ion.with(holder.imgAuthor).load(post.getThumbnail());
        }

        holder.txtAuthor.setText(post.getAuthor());
        holder.txtTitle.setText(post.getTitle());
        holder.txtComments.setText(post.getNumComments() + " Comments");
        holder.txtUps.setText(post.getUps() + " Ups");
        holder.txtDowns.setText(post.getDowns() + " Downs");
        holder.view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(mListener != null) {

                    mListener.onItemClick(post);
                }
            }
        });

        manageAnimation(holder.view);

    }

    @Override
    public int getItemCount() {
        return mPosts == null ? 0 : mPosts.size();
    }

    private void manageAnimation(View view) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {

            view.setAlpha(POST_INITIAL_ALPHA);
            view.setX(mDistanceToMovePostX);
            view.animate()
                    .setDuration(POST_ANIMATION_DURATION)
                    .alpha(1)
                    .translationX(0);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final View view;
        final ImageView imgAuthor;
        final TextView txtAuthor;
        final TextView txtTitle;
        final TextView txtComments;
        final TextView txtUps;
        final TextView txtDowns;
        boolean finishedAnimation;

        public ViewHolder(View itemView) {

            super(itemView);
            view = itemView;
            imgAuthor = itemView.findViewById(R.id.imgAuthor);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtComments = itemView.findViewById(R.id.txtComments);
            txtDowns = itemView.findViewById(R.id.txtDowns);
            txtUps = itemView.findViewById(R.id.txtUps);
            finishedAnimation = false;
        }
    }
}
