package search.mediawiki.org.searchengine.search.mediawiki.org.searchengine.pojos;

import com.google.gson.annotations.SerializedName;

public class Continue {

   private int gpsoffset;
    @SerializedName("continue")
   private String c;


    public int getGpsoffset() {
        return gpsoffset;
    }

    public void setGpsoffset(int gpsoffset) {
        this.gpsoffset = gpsoffset;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
