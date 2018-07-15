package search.mediawiki.org.searchengine;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import search.mediawiki.org.searchengine.search.mediawiki.org.searchengine.pojos.OutPut;


public interface SearchAPI {
    @GET("api.php?action=query&format=json&prop=pageimages|pageterms|info&inprop=url&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpslimit=10")
    Call<OutPut> searchResults(@Query("gpssearch") String str);
}
