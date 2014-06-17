package com.example.gridimagesearch;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult implements Serializable{
	private static final long serialVersionUID = 6035474496001990191L;
	private String fullUrl;
	private String thumbUrl;
	
	public ImageResult (JSONObject obj) {
		try {
			this.setFullUrl(obj.getString("url"));
			this.setThumbUrl(obj.getString("tbUrl"));
		} catch (JSONException e) {
			this.setFullUrl(null);
			this.setThumbUrl(null);
		}
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public static ArrayList<ImageResult> fromJSONArray(
			JSONArray arr) {
		ArrayList<ImageResult> imgResult = new ArrayList<>();
		for (int i = 0; i < arr.length(); i++) {
			try {
				imgResult.add(new ImageResult(arr.getJSONObject(i)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return imgResult;
	}
	
	@Override
	public String toString() {
		return fullUrl;
		
	}
}
