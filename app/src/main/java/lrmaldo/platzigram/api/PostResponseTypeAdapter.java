package lrmaldo.platzigram.api;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import lrmaldo.platzigram.model.Post;

/**
 * Created by Leo on 09/02/2017.
 */

public class PostResponseTypeAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter out, Object value) throws IOException {

    }


    @Override
    public PostResponse read(JsonReader in) throws IOException {

        final PostResponse response = new PostResponse();
        ArrayList<Post> postList = new ArrayList<Post>();
        in.beginObject();

        while (in.hasNext()){
            Post post = null;
            try {
                post = readPost(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            postList.add(post);
        }

        in.endObject();
        response.setPostArrayList(postList);

        return response;

    }

    public Post readPost(JsonReader reader)throws Exception{
        Post post = new Post();
        reader.nextName();
        reader.beginObject();
        while(reader.hasNext()){
            String next = reader.nextName();
            switch (next){
                case  "author":
                    post.setAuthor(reader.nextString());
                    break;
                case "imageUrl":
                    post.setImageUrl(reader.nextString());
                    break;
                case "timestampCreated":
                    post.setTimestampCreated(reader.nextDouble());
                    break;

            }
        }
        reader.endObject();
        return  post;
    }
}
