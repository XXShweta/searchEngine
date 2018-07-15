package search.mediawiki.org.searchengine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import search.mediawiki.org.searchengine.search.mediawiki.org.searchengine.pojos.OutPut;
import search.mediawiki.org.searchengine.search.mediawiki.org.searchengine.pojos.Page;
import search.mediawiki.org.searchengine.search.mediawiki.org.searchengine.pojos.Query;


public class MainActivity extends AppCompatActivity {

    EditText editText;
    String queryString;
   private ArrayList<Page> data;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.textView);
        listView = findViewById(R.id.list);

    }

    public void searchResults(View view) {
        queryString = editText.getText().toString();
        Retrofit r = new Retrofit.Builder().baseUrl("https://en.wikipedia.org//w/").addConverterFactory(GsonConverterFactory.create()).build();
        SearchAPI searchAPI = r.create(SearchAPI.class);
        Call<OutPut> call = searchAPI.searchResults(queryString);

        call.enqueue(new Callback<OutPut>() {
            @Override
            public void onResponse(Call<OutPut> call, Response<OutPut> response) {
                OutPut ob = response.body();
                Query query = ob.getQuery();
                data = new ArrayList<Page>(query.getPages());
                listView.setAdapter(new ListAdapter(getApplicationContext(),data));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                       String url = data.get(position).getFullurl();
                       i.putExtra("URL",url);
                        Log.i("TAGGG",url);
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onFailure(Call<OutPut> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Please Close the Application", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
