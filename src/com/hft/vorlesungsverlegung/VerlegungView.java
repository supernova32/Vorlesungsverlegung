package com.hft.vorlesungsverlegung;


import com.hft.vorlesungsverlegung.TimePickerFragment.TimePickerDialogListener;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TimePicker;
import android.widget.Toast;

public class VerlegungView extends FragmentActivity implements TimePickerDialogListener{
	
	public void showTimerDialog(){
		FragmentManager fm = getFragmentManager();
		TimePickerFragment newFragment = new TimePickerFragment();
		newFragment.show(fm, "timePicker");
	}
	
	@Override
	public void onFinishTimePickerDialog() {
		Bundle b = getIntent().getExtras();
		b.putInt("Ind", 1);
		String temp = "";
		if(b.getInt("Minute") < 10){
			temp = "0"+b.getInt("Minute");
		}else temp = ""+b.getInt("Minute");
		Toast.makeText(VerlegungView.this, "Vorlesung "+b.getString("Vorlesung")+" wird nach "+b.getInt("Hour")+":"+temp+" verlegt!", Toast.LENGTH_LONG).show();
		setResult(RESULT_OK, getIntent().putExtras(b));
		finish();
	}
	
	int result = 0;
	public void tempFunc(){
		result = 1;
		
	}
	
	public int showAlert(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Soll diese Vorlesung wirklich ausfallen?")
	       .setCancelable(false)
	       .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   setResult(RESULT_OK, getIntent().putExtra("Ausfall", 1));
	        	   Toast.makeText(VerlegungView.this, " Die Vorlesung fŠllt aus!", Toast.LENGTH_LONG).show();
	        	   finish();
	           }
	       })
	       .setNegativeButton("Nein", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	           }
	       });
		AlertDialog alert = builder.create();
		alert.show();
		return result;
    }
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verlegung);
        Bundle b = getIntent().getExtras();
        final String vName = b.getString("Vorlesung");
        String vZeit = b.getString("Zeit");
        TextView nameView = (TextView)findViewById(R.id.vorles);
        nameView.setText(vName);
        TextView zeitView = (TextView)findViewById(R.id.zeit);
        zeitView.setText(vZeit);
        Button b_15m = (Button)findViewById(R.id.button_15m);
        b_15m.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		Bundle b = new Bundle();
        		b.putString("Verlegung", "15");
                setResult(RESULT_OK, getIntent().putExtra("NZ", 15));                
        		Toast.makeText(VerlegungView.this, "Vorlesung "+vName+" wird um 15 min. verlegt!", Toast.LENGTH_LONG).show();        		
        		finish();
            }
        });
        Button b_30m = (Button)findViewById(R.id.button_30m);
        b_30m.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		Bundle b = new Bundle();
        		b.putString("Verlegung", "40");
                setResult(RESULT_OK, getIntent().putExtra("NZ", 30));                
        		Toast.makeText(VerlegungView.this, "Vorlesung "+vName+" wird um 30 min. verlegt!", Toast.LENGTH_LONG).show();        		
        		finish();
            }
        });
        Button b_ind = (Button)findViewById(R.id.button_ind);
        OnClickListener myClick = new OnClickListener() {
        	public void onClick(View view) {
        		showTimerDialog();        	    
            }
        };
        b_ind.setOnClickListener(myClick);
        
        Button b_ausf = (Button)findViewById(R.id.button_ausfallen);
        b_ausf.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		showAlert();
        	}
        });     
        
        
	}

}
