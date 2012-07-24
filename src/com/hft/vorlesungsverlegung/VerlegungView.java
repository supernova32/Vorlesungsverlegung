package com.hft.vorlesungsverlegung;


import com.hft.vorlesungsverlegung.TimePickerFragment.TimePickerDialogListener;

import android.app.DialogFragment;
import android.app.FragmentManager;
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
		Toast.makeText(VerlegungView.this, "Vorlesung "+b.getString("Vorlesung")+" wird nach "+b.getInt("Hour")+":"+b.getInt("Minute")+" verlegt!", Toast.LENGTH_LONG).show();
		setResult(RESULT_OK, getIntent().putExtras(b));
		finish();
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
        Button b_20m = (Button)findViewById(R.id.button_20m);
        b_20m.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		Bundle b = new Bundle();
        		b.putString("Verlegung", "20");
                setResult(RESULT_OK, getIntent().putExtra("Zeit", 20));                
        		Toast.makeText(VerlegungView.this, "Vorlesung "+vName+" wird um 20 min. verlegt!", Toast.LENGTH_LONG).show();        		
        		finish();
            }
        });
        Button b_40m = (Button)findViewById(R.id.button_40m);
        b_40m.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		Bundle b = new Bundle();
        		b.putString("Verlegung", "40");
                setResult(RESULT_OK, getIntent().putExtra("Zeit", 40));                
        		Toast.makeText(VerlegungView.this, "Vorlesung "+vName+" wird um 40 min. verlegt!", Toast.LENGTH_LONG).show();        		
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
        
        
        
	}

}
