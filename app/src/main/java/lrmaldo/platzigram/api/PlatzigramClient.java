package lrmaldo.platzigram.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leo on 09/02/2017.
 */

public class PlatzigramClient {

    private Retrofit retrofit;
    private final static  String FIREBASE_BASE_URL ="https://hackaton-d0700.firebaseio.com/";


    public PlatzigramClient (Retrofit retrofit) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(PostResponse.class, new PostResponse())
                .create();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(FIREBASE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
        public PlatzigramFirebaseService getServices()
    {
    return  retrofit.create(PlatzigramFirebaseService.class);

    }
}
