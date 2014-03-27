package com.hoegaarden1917.vivabelgianbeer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hoegaarden1917.vivabelgianbeer.Answers.* ;

public class QuestionActivity extends Activity implements OnClickListener {

	Button yesButton, noButton, notsureButton;
	int requestCode = 1;	
	
	TextView questionText ;
	int currentQuestionNumber = 1 ;
	int question_string[] = {
//			R.string.question_0,R.string.question_1, R.string.question_2
			R.string.question_0
	} ;
	int maxQuestionNumber = question_string.length ;
	Answers answers ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_questions);

		yesButton = (Button)findViewById(R.id.button_yes) ;
        yesButton.setOnClickListener(this);     
		noButton = (Button)findViewById(R.id.button_no) ;
        noButton.setOnClickListener(this);     
		notsureButton = (Button)findViewById(R.id.button_start) ;
        notsureButton.setOnClickListener(this);     
        questionText = (TextView)findViewById(R.id.beerNameDescription);
        drawQuestionText() ;       
        
        answers = new Answers(maxQuestionNumber) ;
        
	}

    @Override
	public void onClick(View v) {
    	int checkedId = -1;
    	Answers.answer a ;

    	assert(currentQuestionNumber <= maxQuestionNumber) ;
    	
   	    switch(v.getId()){
   	    case R.id.button_yes:
   			a = answer.ANSWER_YES ; 
   			break ;
   	    case R.id.button_no:
   			a = answer.ANSWER_NO ;	
   			break ;
   	    case R.id.button_start :
   			a = answer.ANSWER_NOTSURE ;	
   			break ;
		default :
			a = answer.ANSWER_NONE ; 
			break ;
   	    }
   	    answers.Put(currentQuestionNumber - 1, a) ;
   	
    	if (currentQuestionNumber < maxQuestionNumber) {
    	    // Prepare next question in TextView 
        	currentQuestionNumber++ ;
    		drawQuestionText() ;
    	} else { 
    		// Done, move to answer view
        	Intent intent = new Intent(this, FindSuggestionActivity.class) ;
         	intent.putExtra("ANSWERS", answers) ;         	
    		startActivityForResult(intent, requestCode);
    		finish() ;
    	}
	}	
   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	  protected void onActivityResult(
	    int requestCode, 
	    int resultCode, 
	    Intent data) {
	        
	    if(requestCode == this.requestCode){      
	      if(resultCode == 1){
	    	  currentQuestionNumber = 1 ;
	      } else{
	        Toast.makeText(
	          this, 
	          "Cancel was clicked.", 
	          Toast.LENGTH_LONG).show();
	      }
	    }
	 }
	
	
	private void drawQuestionText() {
		
    	Resources res = getResources();
    	String str = (String) res.getText(question_string[currentQuestionNumber-1]);
        questionText.setText(str);
        
	}
}
