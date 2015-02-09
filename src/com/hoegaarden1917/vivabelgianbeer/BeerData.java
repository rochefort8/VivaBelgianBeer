package com.hoegaarden1917.vivabelgianbeer;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class BeerData extends Activity {
	
	public BeerData(byte[] data){
	}
	
	public String getName() { return name ; }
	public String getName_JP() { return name_JP ; }
	public String getImagePath() { return imagePath ; }
	public String getDescriptionPath() { return descriptionPath ; }
	public String getType() { return type ; }
	
	private String name, name_JP ;
	private String imagePath, descriptionPath ;
	private String type ;
	private byte[] data ;
}
