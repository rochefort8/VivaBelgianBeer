package com.hoegaarden1917.vivabelgianbeer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class BeerData extends Activity {
	
	public BeerData(byte[] data,String name, Context context) {
		this.context = context ;
		
		FileOutputStream fileOutputStream = null ;
		FileInputStream  in = null ;
		String fileName = name + ".zip" ;
		
        try {
			fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			fileOutputStream.write(data) ;
			fileOutputStream.close() ;
			
			extract(fileName) ;
			archivePath = name ;
        } catch (Exception e) {
            e.printStackTrace(); 
        }
	}

	public void extract(String filename) {
	    ZipInputStream in = null;
	    BufferedOutputStream out = null;
	 
	    ZipEntry zipEntry = null;
	    int len = 0;
	     
	    try {
	        in = new ZipInputStream(context.openFileInput(filename)) ;
	    	
	        while ((zipEntry = in.getNextEntry()) != null) {
	            File newfile = new File(zipEntry.getName());
	 
	            FileOutputStream fos = context.openFileOutput(newfile.getName(),Context.MODE_PRIVATE) ;
	            out = new BufferedOutputStream(fos) ;
	 
	            byte[] buffer = new byte[1024];
	            while ((len = in.read(buffer)) != -1) {
	                out.write(buffer, 0, len);
	            }
	 
	            in.closeEntry();
	            out.close();
	            out = null;
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
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
