package com.hoegaarden1917.vivabelgianbeer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hoegaarden1917.vivabelgianbeer.Answers.* ;

public class ShowSuggestionActivity extends Activity implements OnClickListener {

	Button backButton ;
	ArrayList<String>[] clist;//ここにデータを格納
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_suggestion);
		

//		backButton = (Button)findViewById(R.id.button_back);
//		backButton.setOnClickListener(this) ;

		// Get selected answer
		Intent intent = getIntent() ;
		
		int item_number = intent.getIntExtra("ITEM_NO",0) ;
		
		// Read CSV
        clist = CSV("data/db.csv",4) ;
        int clist_length = clist[0].size() ;
		
        Log.d ("Item:",Integer.toString(item_number)) ;

		String beerNameString = clist[2].get(item_number) ;
		String beerNameJapaneseString = clist[3].get(item_number) ;
        String beerDataBasename = clist[1].get(item_number);
        
		// Draw
			// Original name
        TextView beerNameText = (TextView)findViewById(R.id.beernameText);
        beerNameText.setText(beerNameString) ;
        
        	// Name in Japanese
        TextView beerNameJapaneseText = (TextView)findViewById(R.id.beername_japaneseText);
        beerNameJapaneseText.setText(beerNameJapaneseString) ;
		
        	// Description
        AssetManager as = getResources().getAssets();
        String beerDescriptionString = null ;
        
        try{
        	String file_path = "data/" + beerDataBasename + ".txt" ;
            InputStream in = as.open(file_path);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            StringBuilder strs = new StringBuilder();
            while ((str = br.readLine()) != null) {
            	strs.append(str);
            }
            br.close();
            beerDescriptionString = strs.toString();
            TextView beerDescriptionText = (TextView)findViewById(R.id.beer_descriptionText);
            beerDescriptionText.setText(beerDescriptionString) ;
     
        } catch (Exception e){
            e.printStackTrace();
        }
		
        	// Image
		try {
			String file_path = "data/" + beerDataBasename + ".jpg" ;	
			InputStream bitmap=as.open(file_path);
			Bitmap bmp=BitmapFactory.decodeStream(bitmap);
			ImageView image = (ImageView)findViewById(R.id.beerImage);
			image.setImageBitmap(bmp) ;
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }        
		
		String fn = "/sdcard/img/" ;
		fn += Integer.toString(1) ;
		fn += ".jpg" ;
		Log.d("TAG",fn) ;
		// Push {0-4}.jpg beforehand !!
	}
	
	public ArrayList<String>[] CSV(String pass,int youso) {
        try {
        	int count =0;
        	clist = new ArrayList[youso];
                            //初期化
        	while(count<youso){
        		clist[count] =new ArrayList<String>();
        		count++;
        	}
         AssetManager as = getResources().getAssets();
         InputStream fin = as.open(pass);
 
         BufferedReader br = new BufferedReader(new InputStreamReader(fin, "UTF-8"));
         String line = "";
         while ((line = br.readLine()) != null) {
        	 StringTokenizer st = new StringTokenizer(line, ",");
        	 int i=0;
        	 while (st.hasMoreTokens()) {
        		 String tmp= st.nextToken();
        		 clist[i].add(tmp);
        		 i++;
        	 }
          }
          br.close();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
        return clist;
	}
	
    private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }
    
	@Override
	public void onClick(View v) {
	    switch(v.getId()){
//	    case R.id.button_back:
//	      setResult(1);
//	      finish();
//	      break;
	    default :
	    	break ;
	    }
	}
}

