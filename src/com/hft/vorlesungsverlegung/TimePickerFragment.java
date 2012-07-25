package com.hft.vorlesungsverlegung;


import java.util.Calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
	
	int result = 0;
	
	public interface TimePickerDialogListener {
        void onFinishTimePickerDialog();
    }
	
	public Bundle b = new Bundle();
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        Log.i("Position", ""+getActivity().getIntent().getExtras().getInt("Position"));
		switch(getActivity().getIntent().getExtras().getInt("Position")){
			case 0:
				hour = 8;
				minute = 45;
				break;
			case 1:
				hour = 10;
				minute = 30;
				break;
			case 2:
				hour = 12;
				minute = 15;
				break;
			case 3:
				hour = 14;
				minute = 45;
				break;
			case 4:
				hour = 18;
				minute = 15;
				break;
		}

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
	
	

	@Override	
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		b.putInt("Hour", hourOfDay);
        b.putInt("Minute", minute);
        Intent parent = getActivity().getIntent();
        parent.putExtras(b);
        TimePickerDialogListener activity = (TimePickerDialogListener) getActivity();
        activity.onFinishTimePickerDialog();
    }
	
	public Bundle getBundle(){
		return b;
	}
	
	public void setResult(){
		result = 1;
	}

	
	public void showDialog(){
		show(getFragmentManager(), "timePicker");
	}


}
