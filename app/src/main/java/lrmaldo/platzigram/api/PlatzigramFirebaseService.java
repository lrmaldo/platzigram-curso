package lrmaldo.platzigram.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Leo on 09/02/2017.
 */

public interface PlatzigramFirebaseService  {

    @GET("post.json")
    Call<PostResponse> getPostList();
}
