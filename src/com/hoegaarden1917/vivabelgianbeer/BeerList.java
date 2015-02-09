package com.hoegaarden1917.vivabelgianbeer;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BeerList {

	public BeerList (Context context) {
		this.context = context ;
		_beerList = new ArrayList<BeerData>() ;
	}
	
	public int Add(byte[] data) 
	{	
		FileOutputStream fileOutputStream = null ;
		int size = getCount();
		String fileName = Integer.toString(size+1) + ".zip" ;
		
        try {
			fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			fileOutputStream.write(data) ;
			fileOutputStream.close() ;
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        
		BeerData d = new BeerData(data) ;
		_beerList.add(d) ;
		return 0 ;
	}
	public int getCount() { return _beerList.size() ; }
	
	private ArrayList<BeerData> _beerList ;
	private	Context context ;
} ;

