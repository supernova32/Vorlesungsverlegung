package com.hft.vorlesungsverlegung;

import java.util.ArrayList;
import java.util.HashMap;


import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.DigitalClock;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class VorlesungActivity extends Activity {
	
	final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	HashMap<String, String> map = new HashMap<String, String>();
	HashMap<String, String> map1 = new HashMap<String, String>();
	HashMap<String, String> map2 = new HashMap<String, String>();
	HashMap<String, String> map3 = new HashMap<String, String>();
	HashMap<String, String> map4 = new HashMap<String, String>();
	ListView mainListView;
	int timeMove1 = 0;
	int timeMove2 = 0;
	int timeMove3 = 0;
	int timeMove4 = 0;
	int timeMove5 = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorlesung);
        setTitle("Meine Vorlesungen");
        map.put("Vorlesung", "Programmieren 1");
        map.put("Zeit", "8:00");
        list.add(map);        
        map1.put("Vorlesung", "Software Engineering");
        map1.put("Zeit", "9:45");
        list.add(map1);       
        map2.put("Vorlesung", "Datenbanken");
        map2.put("Zeit", "11:30");
        list.add(map2);        
        map3.put("Vorlesung", "Software Projekt 1");
        map3.put("Zeit", "14:00");
        list.add(map3);        
        map4.put("Vorlesung", "Programmieren 2");
        map4.put("Zeit", "17:30");
        list.add(map4);
        
        SimpleAdapter sa = new SimpleAdapter(
    	        this.getApplicationContext(),
    	        list,
    	        R.layout.vorlesung_details,
    	        new String[] { "Vorlesung", "Zeit"},
    	        new int[] { R.id.vorlesungName, R.id.vorlesungZeit});
        
        mainListView = (ListView)findViewById(R.id.vorlesungList); 
    	mainListView.setAdapter(sa);
    	mainListView.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
        		Intent myIntent = new Intent(view.getContext(), VerlegungView.class);
        		Bundle b = new Bundle();
        		b.putString("Vorlesung", list.get(position).get("Vorlesung"));
        		b.putString("Zeit", list.get(position).get("Zeit"));
        		b.putInt("Position", position);
        		myIntent.putExtras(b);
                startActivityForResult(myIntent, 1111);
            }
        });
    }

    /* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null){
			Bundle b = data.getExtras();
			if(b.getInt("NZ")==15){
				switch(Vorlesungen.valueOf(b.getString("Vorlesung").replaceAll(" ", ""))){
				case Programmieren1: timeMove1 += 15;
									 list.get(0).put("Zeit", "8:00+"+timeMove1);
									 break;
				case SoftwareEngineering: timeMove2 += 15;
									 list.get(1).put("Zeit", "9:45+"+timeMove2);
									 break;
				case Datenbanken: timeMove3 += 15;
									 list.get(2).put("Zeit", "11:30+"+timeMove3);
				 					 break;
				case SoftwareProjekt1: timeMove4 += 15;
									 list.get(3).put("Zeit", "14:00+"+timeMove4);
				 					 break;
				case Programmieren2: timeMove5 += 15;
									 list.get(4).put("Zeit", "17:30+"+timeMove5);
				 					 break;
				}
			}
			else if(b.getInt("NZ")==30){
				switch(Vorlesungen.valueOf(b.getString("Vorlesung").replaceAll(" ", ""))){
				case Programmieren1: timeMove1 += 30;
									 list.get(0).put("Zeit", "8:00+"+timeMove1);
									 break;
				case SoftwareEngineering: timeMove2 += 30;
									 list.get(1).put("Zeit", "9:45+"+timeMove2);
									 break;
				case Datenbanken: timeMove3 += 30;
									 list.get(2).put("Zeit", "11:30+"+timeMove3);
				 					 break;
				case SoftwareProjekt1: timeMove4 += 30;
									 list.get(3).put("Zeit", "14:00+"+timeMove4);
				 					 break;
				case Programmieren2: timeMove5 += 30;
									 list.get(4).put("Zeit", "17:30+"+timeMove5);
				 					 break;
				}
			}
			if(b.getInt("Ind")==1){
				String temp = "";
				if(b.getInt("Minute") < 10){
					temp = "0"+b.getInt("Minute");
				}else temp = ""+b.getInt("Minute");
				
				switch(Vorlesungen.valueOf(b.getString("Vorlesung").replaceAll(" ", ""))){
				case Programmieren1: list.get(0).put("Zeit", "Verlegt auf "+b.getInt("Hour")+":"+temp);
									 break;
				case SoftwareEngineering: list.get(1).put("Zeit", "Verlegt auf "+b.getInt("Hour")+":"+temp);
									 break;
				case Datenbanken: list.get(2).put("Zeit", "Verlegt auf "+b.getInt("Hour")+":"+temp);
				 					 break;
				case SoftwareProjekt1: list.get(3).put("Zeit", "Verlegt auf "+b.getInt("Hour")+":"+temp);
				 					 break;
				case Programmieren2: list.get(4).put("Zeit", "Verlegt auf "+b.getInt("Hour")+":"+temp);
				 					 break;
				}
			}
			if(b.getInt("Ausfall")==1){
				switch(Vorlesungen.valueOf(b.getString("Vorlesung").replaceAll(" ", ""))){
				case Programmieren1: list.get(0).put("Zeit", "Ausgefallen!");
									 break;
				case SoftwareEngineering: list.get(1).put("Zeit", "Ausgefallen!");
									 break;
				case Datenbanken: list.get(2).put("Zeit", "Ausgefallen!");
				 					 break;
				case SoftwareProjekt1: list.get(3).put("Zeit", "Ausgefallen!");
				 					 break;
				case Programmieren2: list.get(4).put("Zeit", "Ausgefallen!");
				 					 break;
				}
			}
			SimpleAdapter sa = new SimpleAdapter(
	    	        this.getApplicationContext(),
	    	        list,
	    	        R.layout.vorlesung_details,
	    	        new String[] { "Vorlesung", "Zeit"},
	    	        new int[] { R.id.vorlesungName, R.id.vorlesungZeit});
			mainListView.setAdapter(sa);
		}		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_vorlesung, menu);
        return true;
    }

    
}
