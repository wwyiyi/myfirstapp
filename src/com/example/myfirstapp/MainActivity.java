package com.example.myfirstapp;

import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import android.app.Activity;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;


public class MainActivity extends Activity {
	private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.print("onCreate");
        setMap();
        try {
			setMarkers();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setMap() {
    	mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
    	mMap.addMarker(new MarkerOptions()
    	.position(new LatLng(22.45224852899484, 114.16910264222204))
    	.title("My Location")
    	.snippet("ADDR" + "\n" + "100"));
    	mMap.addMarker(new MarkerOptions()
    	.position(new LatLng(22.459484, 114.22204))
    	.title("My Location2")
    	.snippet("ADDR" + "\n" + "100"));
    }
    
    public void setMarkers() throws InterruptedException, ExecutionException {   
    	String response;
        @SuppressWarnings("unused")
		int hos = 0, mu = 0, lib = 0, play = 0, park = 0;
        try {
        	response = new RetrieveData().execute().get();
        	System.out.print("async response: " + response);
    		StringTokenizer s = new StringTokenizer(response, ":\",{}");
            String t;
            while(s.hasMoreTokens()) {
            	t = s.nextToken();
            	switch(Attr.getCat(t)) {
            		case hospital: 
            			hos++;
            			addMarker(s);
            			break;
            		case museum: 
            			mu++;
            			addMarker(s);
            			break;
            		case playground:
            			play++;
            			addMarker(s);
            			break;
            		case park:
            			park++;
            			addMarker(s);
            			break;
            		case library:
            			lib++;
            			addMarker(s);
            			break;
            		default:
            			
            	}
            }
		} catch (Exception e) {
		}
        
    }
    
    public void addMarker(StringTokenizer s) {
    	String myName = "name";
    	String myAddr = "addr";
        double myLng = 0, myLat = 0, myDist = -1;
    	outter:
		while(s.hasMoreTokens()) {
			String t = s.nextToken();
			switch(Attr.getCat(t)) {
				case name:
					myName = s.nextToken();
					break;
				case address:
					myAddr = s.nextToken();
					break;
				case lng:
					myLng = Double.parseDouble(s.nextToken());
					break;
				case lat:
					myLat = Double.parseDouble(s.nextToken());
					break;
				case distance: 
					myLat = Double.parseDouble(s.nextToken());
					break outter;
				default:
			}
		}
    	mMap.addMarker(new MarkerOptions()
    	.position(new LatLng(myLat, myLng))
    	.title(myName)
    	.snippet(myAddr + "\n" + myDist));
    }
}