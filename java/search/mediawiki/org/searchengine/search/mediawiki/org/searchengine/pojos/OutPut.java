package search.mediawiki.org.searchengine.search.mediawiki.org.searchengine.pojos;

import com.google.gson.annotations.SerializedName;

public class OutPut {

    @SerializedName("continue")
    private Continue aContinue;
    private Query query;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }



    public Continue getaContinue() {
        return aContinue;
    }

    public void setaContinue(Continue aContinue) {
        this.aContinue = aContinue;
    }
}
