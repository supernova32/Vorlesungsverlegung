package com.hft.vorlesungsverlegung;

import java.util.ArrayList;
import java.util.HashMap;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class VorlesungActivity extends Activity {
	
	final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	HashMap<String, String> map = new HashMap<String, String>();
	HashMap<String, String> map1 = new HashMap<String, String>();
	HashMap<String, String> map2 = new HashMap<String, String>();
	HashMap<String, String> map3 = new HashMap<String, String>();
	HashMap<String, String> map4 = new HashMap<String, String>();
	HashMap<String, String> map5 = new HashMap<String, String>();
	ListView mainListView; 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorlesung);       
        map.put("Vorlesung", "Mathematik 2");
        map.put("Zeit", "8:00");
        list.add(map);        
        map1.put("Vorlesung", "Verteilte Systeme");
        map1.put("Zeit", "9:45");
        list.add(map1);       
        map2.put("Vorlesung", "Datenbank");
        map2.put("Zeit", "11:30");
        list.add(map2);        
        map3.put("Vorlesung", "BWL");
        map3.put("Zeit", "14:00");
        list.add(map3);        
        map4.put("Vorlesung", "Programmieren 2");
        map4.put("Zeit", "15:45");
        list.add(map4);
        map5.put("Vorlesung", "Lineare Algebra");
        map5.put("Zeit", "17:30");
        list.add(map5);
        
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
        		//Toast.makeText(VorlesungActivity.this, "ItemClick at item " + list.get(position).get("cameraName"), Toast.LENGTH_LONG ).show();
            }
        });
    }

    /* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null){
			Bundle b = data.getExtras();
			
			if(b.getInt("Zeit")==20){
				switch(Vorlesungen.valueOf(b.getString("Vorlesung").replaceAll(" ", ""))){
				case Mathematik2: list.get(0).put("Zeit", list.get(0).get("Zeit")+"+20");
									 break;
				case VerteilteSysteme: list.get(1).put("Zeit", list.get(1).get("Zeit")+"+20");
									 break;
				case Datenbank: list.get(2).put("Zeit", list.get(2).get("Zeit")+"+20");
				 					 break;
				case BWL: list.get(3).put("Zeit", list.get(3).get("Zeit")+"+20");
				 					 break;
				case Programmieren2: list.get(4).put("Zeit", list.get(4).get("Zeit")+"+20");
				 					 break;
				case LineareAlgebra: list.get(5).put("Zeit", list.get(5).get("Zeit")+"+20");
				 					 break;
				}
			}
			else if(b.getInt("Zeit")==40){
				switch(Vorlesungen.valueOf(b.getString("Vorlesung").replaceAll(" ", ""))){
				case Mathematik2: list.get(0).put("Zeit", list.get(0).get("Zeit")+"+40");
									 break;
				case VerteilteSysteme: list.get(1).put("Zeit", list.get(1).get("Zeit")+"+40");
									 break;
				case Datenbank: list.get(2).put("Zeit", list.get(2).get("Zeit")+"+40");
				 					 break;
				case BWL: list.get(3).put("Zeit", list.get(3).get("Zeit")+"+40");
				 					 break;
				case Programmieren2: list.get(4).put("Zeit", list.get(4).get("Zeit")+"+40");
				 					 break;
				case LineareAlgebra: list.get(5).put("Zeit", list.get(5).get("Zeit")+"+40");
				 					 break;
				}
			}
			if(b.getInt("Ind")==1){
				switch(Vorlesungen.valueOf(b.getString("Vorlesung").replaceAll(" ", ""))){
				case Mathematik2: list.get(0).put("Zeit", b.getInt("Hour")+":"+b.getInt("Minute"));
									 break;
				case VerteilteSysteme: list.get(1).put("Zeit", b.getInt("Hour")+":"+b.getInt("Minute"));
									 break;
				case Datenbank: list.get(2).put("Zeit", b.getInt("Hour")+":"+b.getInt("Minute"));
				 					 break;
				case BWL: list.get(3).put("Zeit", b.getInt("Hour")+":"+b.getInt("Minute"));
				 					 break;
				case Programmieren2: list.get(4).put("Zeit", b.getInt("Hour")+":"+b.getInt("Minute"));
				 					 break;
				case LineareAlgebra: list.get(5).put("Zeit", b.getInt("Hour")+":"+b.getInt("Minute"));
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
