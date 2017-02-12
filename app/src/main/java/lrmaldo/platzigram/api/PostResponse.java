package lrmaldo.platzigram.api;

import java.util.ArrayList;

import lrmaldo.platzigram.model.Post;

/**
 * Created by Leo on 09/02/2017.
 */

public class PostResponse {
    ArrayList <Post>  postArrayList = new ArrayList<>();

    public void setPostArrayList(ArrayList<Post> postList){
        this.postArrayList = postList;
    }
    public ArrayList<Post>  getPostList(){
        return postArrayList;
    }
}
