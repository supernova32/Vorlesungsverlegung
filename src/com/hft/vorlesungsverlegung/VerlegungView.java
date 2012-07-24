package com.hft.vorlesungsverlegung;


import com.hft.vorlesungsverlegung.TimePickerFragment.TimePickerDialogListener;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VerlegungView extends FragmentActivity implements TimePickerDialogListener{
	
	public void showTimerDialog(){
		FragmentManager fm = getFragmentManager();
		TimePickerFragment newFragment = new TimePickerFragment();
		newFragment.show(fm, "timePicker");
	}
	
	public void showAlert(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Studierende werden sofort informiert! Sind Sie sicher?")
	       .setCancelable(false)
	       .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
	           @Override
			public void onClick(DialogInterface dialog, int id) {
	        	   showTimerDialog();
	           }
	       })
	       .setNegativeButton("Nein", new DialogInterface.OnClickListener() {
	           @Override
			public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	           }
	       });
		AlertDialog alert = builder.create();
		alert.show();
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
	
	public void showAusfallAlert(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Soll diese Vorlesung wirklich ausfallen?")
	       .setCancelable(false)
	       .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
	           @Override
			public void onClick(DialogInterface dialog, int id) {
	        	   setResult(RESULT_OK, getIntent().putExtra("Ausfall", 1));
	        	   Toast.makeText(VerlegungView.this, " Die Vorlesung fŠllt aus!", Toast.LENGTH_LONG).show();
	        	   finish();
	           }
	       })
	       .setNegativeButton("Nein", new DialogInterface.OnClickListener() {
	           @Override
			public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	           }
	       });
		AlertDialog alert = builder.create();
		alert.show();
    }
	
	public int showVerAlert(int min){
		final int min1 = min;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Studierende werden sofort informiert! Sind Sie sicher?")
	       .setCancelable(false)
	       .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
	           @Override
			public void onClick(DialogInterface dialog, int id) {
	        	   setResult(RESULT_OK, getIntent().putExtra("NZ", min1));
	        	   Toast.makeText(VerlegungView.this, " Die Vorlesung wŸrde um "+min1+" min. verschoben!", Toast.LENGTH_LONG).show();
	        	   finish();
	           }
	       })
	       .setNegativeButton("Nein", new DialogInterface.OnClickListener() {
	           @Override
			public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	           }
	       });
		AlertDialog alert = builder.create();
		alert.show();
		return min1;
    }
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verlegung);
        setTitle("Vorlesung Verlegen");
        Bundle b = getIntent().getExtras();
        final String vName = b.getString("Vorlesung");
        String vZeit = "Startet um: "+b.getString("Zeit");
        TextView nameView = (TextView)findViewById(R.id.vorles2);
        nameView.setText(vName);
        TextView zeitView = (TextView)findViewById(R.id.zeit2);
        zeitView.setText(vZeit);
        Button b_15m = (Button)findViewById(R.id.button_15);
        b_15m.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View view) {
        		/*Bundle b = new Bundle();
        		b.putString("Verlegung", "15");
                setResult(RESULT_OK, getIntent().putExtra("NZ", 15));                
        		Toast.makeText(VerlegungView.this, "Vorlesung "+vName+" wird um 15 min. verlegt!", Toast.LENGTH_LONG).show();        		
        		finish();*/
        		showVerAlert(15);
            }
        });
        Button b_30m = (Button)findViewById(R.id.button_30);
        b_30m.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View view) {
        		/*Bundle b = new Bundle();
        		b.putString("Verlegung", "40");
                setResult(RESULT_OK, getIntent().putExtra("NZ", 30));                
        		Toast.makeText(VerlegungView.this, "Vorlesung "+vName+" wird um 30 min. verlegt!", Toast.LENGTH_LONG).show();        		
        		finish();*/
        		showVerAlert(30);
            }
        });
        Button b_ind = (Button)findViewById(R.id.button_ind);
        OnClickListener myClick = new OnClickListener() {
        	@Override
			public void onClick(View view) {
        		showAlert();        	    
            }
        };
        b_ind.setOnClickListener(myClick);
        
        Button b_ausf = (Button)findViewById(R.id.button_ausfallen);
        b_ausf.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View view) {
        		showAusfallAlert();
        	}
        });     
        
        
	}

}
