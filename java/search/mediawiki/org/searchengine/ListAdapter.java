package search.mediawiki.org.searchengine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import search.mediawiki.org.searchengine.search.mediawiki.org.searchengine.pojos.Page;

public class ListAdapter extends BaseAdapter {
    Context mainActivity;
    ArrayList<Page> page_;

    public ListAdapter(Context mainActivity, ArrayList<Page> page_) {
        this.mainActivity = mainActivity;
        this.page_ = page_;
    }

    @Override
    public int getCount() {
        return page_.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.indi_view, null);
        TextView name = v.findViewById(R.id.name);
        TextView vicinity = v.findViewById(R.id.vicinity);
       ImageView imageView = v.findViewById(R.id.imageView);
        String imgURL = "https://media1.tenor.com/images/270da815a5869f232d3f7f7e0690b035/tenor.gif?itemid=5571203:";

        if( page_.get(position).getThumbnail() != null){
        imgURL = page_.get(position).getThumbnail().getImgPath();
       }

        name.setText(page_.get(position).getTitle());
        vicinity.setText(page_.get(position).getTerms().getDescription().get(0));
        new DownLoadImageTask(imageView).execute(imgURL);
        Log.i("TAGGGGGG",imgURL);

        return v;
    }


    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }


        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

}
