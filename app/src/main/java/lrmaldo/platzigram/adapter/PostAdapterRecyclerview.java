package lrmaldo.platzigram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import lrmaldo.platzigram.R;
import lrmaldo.platzigram.model.Picture;
import lrmaldo.platzigram.model.Post;
import lrmaldo.platzigram.view.PictureDetailActivity;

/**
 * Created by Leo on 18/01/2017.
 */

public class PostAdapterRecyclerview extends RecyclerView.Adapter<PostAdapterRecyclerview.PictureViewHolder>{

    private ArrayList<Post> posts;
    private int resource;
    private Activity activity;

    public PostAdapterRecyclerview(ArrayList<Post> posts, int resource, Activity activity) {
        this.posts = posts;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);


        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.usernameCard.setText(post.getAuthor());
        holder.timeCard.setText(String.valueOf(post.getRelativeTimeStamp()));
        holder.likenumberCard.setText("1");
        Picasso.with(activity).load(post.getImageUrl()).into(holder.pictureCard);



        holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PictureDetailActivity.class);

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity,view,activity.getString(R.string.transitionName_picture)).toBundle());

                }else{
                    activity.startActivity(intent);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public class PictureViewHolder extends RecyclerView.ViewHolder{
        private ImageView pictureCard;
        private TextView usernameCard;
        private TextView timeCard;
        private  TextView likenumberCard;

        public PictureViewHolder(View itemView) {
            super(itemView);
        pictureCard     = (ImageView) itemView.findViewById(R.id.pictureCard);
        usernameCard    = (TextView) itemView.findViewById(R.id.usernameCardview);
        timeCard        = (TextView) itemView.findViewById(R.id.TimeCard);
        likenumberCard  = (TextView) itemView.findViewById(R.id.likeNumberCard);


        }

    }
}
