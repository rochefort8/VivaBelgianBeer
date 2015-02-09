package com.hoegaarden1917.vivabelgianbeer;

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		Parse.initialize(this, "rLfiUPlbIE5orN0Al07gpotnvIpqwTUpoQlkhjO0", "pZBeJfBhKqqPH21OwecKGgL978SXCwTockzjYW1Z");

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();

		// If you would like all objects to be private by default, remove this
		// line.
		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);
	}

}