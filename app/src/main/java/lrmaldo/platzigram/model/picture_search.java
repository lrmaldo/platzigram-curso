package lrmaldo.platzigram.model;

/**
 * Created by Leo on 23/01/2017.
 */

public class picture_search {
    String url_image;
    String username;

    public picture_search(String url_image, String username) {
        this.url_image = url_image;
        this.username = username;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
