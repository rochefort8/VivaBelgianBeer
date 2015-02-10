package com.hoegaarden1917.vivabelgianbeer;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class BeerData extends Activity {
	
	public BeerData(byte[] data,String name, Context context) {
		this.context = context ;
		
		FileOutputStream fileOutputStream = null ;
		String fileName = name + ".zip" ;
		
        try {
			fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			fileOutputStream.write(data) ;
			fileOutputStream.close() ;
			
			archivePath = name ;
        } catch (Exception e) {
            e.printStackTrace(); 
        }
	}
	
	public String getName() { return name ; }
	public String getName_JP() { return name_JP ; }
	public String getImagePath() { return imagePath ; }
	public String getDescriptionPath() { return descriptionPath ; }
	public String getType() { return type ; }

	private Context context ;
	private String name, name_JP ;
	private String archivePath ;
	private String imagePath, descriptionPath ;
	private String type ;
}
