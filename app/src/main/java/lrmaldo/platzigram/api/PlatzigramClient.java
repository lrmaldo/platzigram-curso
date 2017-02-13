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
    private final static  String FIREBASE_BASE_URL ="https://platzigramproyect.firebaseio.com/";


    public PlatzigramClient() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(PostResponse.class, new PostResponseTypeAdapter())
                .create();

        retrofit = new  Retrofit.Builder()
                .baseUrl(FIREBASE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


    public PlatzigramFirebaseService getService(){
        return  retrofit.create(PlatzigramFirebaseService.class);
    }
}
