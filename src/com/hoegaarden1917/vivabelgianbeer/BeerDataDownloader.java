package com.hoegaarden1917.vivabelgianbeer;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class BeerDataDownloader {
	
	private static final String className = "BelgianBeer" ;
	private static final String archiveName = "archive" ;
	Context context;

	private BeerList beerList = null ;
	
	public BeerDataDownloader(Context context) {
		this.context = context ;
		done = false ;
	}

	public int Get() {
		beerList = new BeerList(this.context) ;
		
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(className);
		query.findInBackground(new FindCallback<ParseObject>() {
	          
			@Override	
			public void done(List<ParseObject> beers, ParseException e) {
				if (e == null) {
					Log.d("Downloader:", beers.size() + "Beers found on the server, downloading...");
		              
					for (int i = 0; i < beers.size(); i++) {
						ParseObject object = beers.get(i);
						String objectId = object.getObjectId().toString();

						// Locate the objectId from the class
						// Locate the column named "ImageName" and set
						// the string
						ParseFile fileObject = (ParseFile) object.get(archiveName);
						String fileName = fileObject.getName();
						Log.d("Downloader", "ObjectID:" + objectId + ":" + fileName) ;

						fileObject.getDataInBackground(new GetDataCallback() {
							
							public void done(byte[] data,ParseException e) {

								if (e == null) {
									Log.d("Downloader","We've got data. size=" + data.length);
									beerList.Add(data) ;
									Log.d("COUNT:", Integer.toString(beerList.getCount()));
								} else {
									Log.d("Downloader","There was a problem downloading the data.");
								}
							}
							
						});
					}
				} else {
					Log.d("Downloader", "Error: " + e.getMessage());
				}
			}
		});

		return 0 ;
	}

	int count = 0 ;

	public boolean isFinished() { return done ; }

	private boolean done = false ;
}
