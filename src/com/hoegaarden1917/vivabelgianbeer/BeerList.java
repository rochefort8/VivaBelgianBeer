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
		String baseName = Integer.toString(getCount() + 1)  ;
		BeerData d = new BeerData(data, baseName,context ) ;
		_beerList.add(d) ;
		return 0 ;
	}
	public int getCount() { return _beerList.size() ; }
	
	private ArrayList<BeerData> _beerList ;
	private	Context context ;
} ;

