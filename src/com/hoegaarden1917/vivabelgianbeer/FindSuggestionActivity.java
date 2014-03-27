package com.hoegaarden1917.vivabelgianbeer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.Menu;
import android.view.Window;

import com.hoegaarden1917.vivabelgianbeer.Answers.* ;

public class FindSuggestionActivity extends Activity implements Runnable {

	ProgressDialog progressDialog;
	Thread thread;
//	Answers answers ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Get selected answer
		
	    progressDialog = new ProgressDialog(this);
	    progressDialog.setMessage("Now searching...");
	    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    
	    progressDialog.show();
	 
	    thread = new Thread(this);
	    thread.start();		
	}
	
    @Override
    public void run() {
    	int item_no = -1 ;
    	
        try {
        	item_no = FindSuggestionItem() ;
        	
            thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO é©ìÆê∂ê¨Ç≥ÇÍÇΩ catch ÉuÉçÉbÉN
            e.printStackTrace();
        }
        progressDialog.dismiss();
    	Intent intent = new Intent(this, ShowSuggestionActivity.class) ;
     	intent.putExtra("ITEM_NO", item_no) ;         	
		startActivity(intent);
		finish() ;
    }
 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private int FindSuggestionItem() {
		Intent intent = getIntent() ;
		Answers answers = (Answers)intent.getSerializableExtra("ANSWERS") ;
		
		int length = answers.Length() ;

		// Decoding answer for each question...
		for (int i = 0;i < length; i++) {
			Answers.answer a = answers.Get(i) ;		

			String str = "Q" + Integer.toString(i+1) + ":" ;
			
			switch (a) {
			case ANSWER_YES :
				str = "YES" ;
				break ;
			case ANSWER_NO :
				str = "NO" ;
				break ;
			case ANSWER_NOTSURE :
				str = "NOTSURE" ;
				break ;
			case ANSWER_NONE :
				str = "NONE" ;
				break ;
			}
			Log.d("ANSWER",str) ;
		}	

    	String aLine = "";
        int clist_length = 0 ;
        
        try {
        	InputStreamReader in = new InputStreamReader(getAssets().open("data/db.csv")) ;	        
        	LineNumberReader br = new LineNumberReader (in) ;
		
        	while (null!=(aLine = br.readLine())) {
        	}
        	clist_length = br.getLineNumber() ;
        	br.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}        

        Log.d("Total item:",String.valueOf(clist_length)) ;
        
        // Select beer. Random value 
        Random rand = new Random() ;
        int item_number = rand.nextInt(clist_length) ;
        Log.d ("Item:",Integer.toString(item_number)) ;		
        return item_number ;
	}	
}
