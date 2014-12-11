package com.Domoticroomapp.domoticroom_app;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
//import android.widget.Toast;

public class SettingsActivity extends Activity{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(this, "onCreate/Help", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_settings);
        
        
		Bundle intentCapture = getIntent().getExtras(); // object to receive information from activity main
		int fragmentToSet = intentCapture.getInt("paramFragmSet");
		
		FragmentSettingsBluetooth fragmentSettingsBluetooth = new FragmentSettingsBluetooth();
		FragmentSettingsCustomize fragmentSettingsCustomize = new FragmentSettingsCustomize();
	
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
		//fragmentTransaction.setTransition(android.app.FragmentTransaction.TRANSIT_NONE);
		if(fragmentToSet == R.layout.fragment_settings_bluetooth){
			fragmentTransaction.add(android.R.id.content, fragmentSettingsBluetooth).commit();
		}else{
			fragmentTransaction.add(android.R.id.content, fragmentSettingsCustomize).commit();
		}
	}
	
}

