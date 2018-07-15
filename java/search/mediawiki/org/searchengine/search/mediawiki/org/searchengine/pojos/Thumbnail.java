package search.mediawiki.org.searchengine.search.mediawiki.org.searchengine.pojos;

import com.google.gson.annotations.SerializedName;

public class Thumbnail {

    @SerializedName("source")
    private String imgPath ;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
