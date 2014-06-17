package com.example.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;


public class SearchActivity extends Activity{
	
	private final static int REQUEST_CODE = 50;
	
	private String size  = "";
	private String color = "";
	private String type  = "";
	private String site  = "";
	
	EditText etQuery;
	Button btnSearch;
	GridView gvResults;
	ArrayList<ImageResult> imgResult = new ArrayList<>();
	ImageResultArrayAdapter imageAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imgResult);
		gvResults.setAdapter(imageAdapter);
		
		gvResults.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int pos,
					long arg3) {
				Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				i.putExtra("obj", imgResult.get(pos));
				Log.d("DEBUG", imgResult.toString());
				startActivity(i);
			}
			
		});
		
		gvResults.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				customLoadMoreDataFromApi(page);
			}
			
		});		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }
	
	// Append more data into the adapter	
	protected void customLoadMoreDataFromApi(int page) {
		// This method probably sends out a network request and appends new data items to your adapter. 
	    // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
	    // Deserialize API response and then construct new objects to append to the adapter
		
		String query = etQuery.getText().toString();
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" 
				   + "start=" + page + "&imgtype=" + this.type + "&imgsz=" + this.size
				   + "&imgcolor=" + this.color + "&as_sitesearch=" + this.site
				   + "&v=1.0&q=" + Uri.encode(query), 
				   new JsonHttpResponseHandler(){
					   
					   @Override
					   public void onSuccess(JSONObject response) {
						   JSONArray imageJsonResults = null;
						   try {
							   imageJsonResults = response.getJSONObject("responseData")
									   					  .getJSONArray("results");
							   imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
							   Log.d("DEBUG", imgResult.toString());
						   } catch (JSONException e) {
							   e.printStackTrace();
						   }
					   }
				   });
		
	}

	public void onImageSearch(View v) {
		String query = etQuery.getText().toString();
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" 
				   + "start=" + 0 + "&imgtype=" + this.type + "&imgsz=" + this.size
				   + "&imgcolor=" + this.color + "&as_sitesearch=" + this.site
				   + "&v=1.0&q=" + Uri.encode(query), 
				   new JsonHttpResponseHandler(){
					   
					   @Override
					   public void onSuccess(JSONObject response) {
						   JSONArray imageJsonResults = null;
						   try {
							   imageJsonResults = response.getJSONObject("responseData")
									   					  .getJSONArray("results");
							   imgResult.clear();
							   imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
							   Log.d("DEBUG", imgResult.toString());
						   } catch (JSONException e) {
							   e.printStackTrace();
						   }
					   }
				   });
	}
	
	public void setupViews() {
		etQuery   = (EditText) findViewById(R.id.etQuery);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		gvResults = (GridView) findViewById(R.id.gvResult);
	}
	
	public void onSettings(MenuItem mi) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, SettingsActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  // REQUEST_CODE is defined above
	  if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
	     // Extract name value from result extras
	     this.size  = data.getExtras().getString("size");
	     this.color = data.getExtras().getString("color");
	     this.type  = data.getExtras().getString("type");
	     this.site  = data.getExtras().getString("site");
	  }
	} 
	
}
