package com.example.myfirstapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


import android.os.AsyncTask;

public class RetrieveData extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void...voids) {
		System.out.print("enter");
    	String myUrl = " http://test.arche.hk/api/location.php?lat=22.45224852899484&lng=114.16910264222204";
    	if (myUrl != null) {
        	try {
        		URL data = new URL(myUrl);
        		BufferedReader in = new BufferedReader(
        		        new InputStreamReader(data.openStream()));
                String s = "";
				String response = "";
                while ((s = in.readLine()) != null) {
                    response += s;
                }
                in.close();
                System.out.print("string read: " + response);
                return response;
        	} 
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        }
    	
		return "name: test, addr test, lng: 22, lat: 11, distance: 500";
	}
	
	protected void onPostExecute() {
        // TODO: check this.exception 
        // TODO: do something with the feed		
    }

}
