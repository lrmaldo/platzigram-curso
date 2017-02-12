package lrmaldo.platzigram.api;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import lrmaldo.platzigram.model.Post;

/**
 * Created by Leo on 09/02/2017.
 */

public class PostResponseTypeAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter out, Object value) throws IOException {

    }

    @Override
    public Object read(JsonReader reader) throws IOException {
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
        return post;
    }
}
