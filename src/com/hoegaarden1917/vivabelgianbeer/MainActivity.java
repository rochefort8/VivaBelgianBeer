package com.hoegaarden1917.vivabelgianbeer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	Button startButton ;
	int requestCode = 1;	
	
	TextView questionText ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		startButton = (Button)findViewById(R.id.button_start) ;
		startButton.setOnClickListener(this);     
	}

    @Override
	public void onClick(View v) {
   	    switch(v.getId()){
   	    case R.id.button_start:
        	Intent intent = new Intent(this, QuestionActivity.class) ;
    		startActivityForResult(intent, requestCode);
   			break ;
		default :
			break ;
   	    }    	
	}	
   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
