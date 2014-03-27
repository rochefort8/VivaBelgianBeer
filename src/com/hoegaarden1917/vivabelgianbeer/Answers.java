package com.hoegaarden1917.vivabelgianbeer;

import java.io.Serializable;

public class Answers implements Serializable {

	enum answer {
		ANSWER_NONE,
		ANSWER_YES,
		ANSWER_NO,
		ANSWER_NOTSURE,
	} ;
	
	public Answers (int n) {
		data = new answer[n] ;
		numberOfArray = n ;
	}
	
	public answer Get(int n) {
		if (numberOfArray <= n) return answer.ANSWER_NONE ;
		return data[n] ;
	}
	public void Put(int n, answer a) {
		if (numberOfArray <= n) return  ;
		data[n] = a ;
	}
	public int Length() { return numberOfArray ; }
	
	private answer data[] ;
	private int numberOfArray ;
} ;
