package com.Domoticroomapp.domoticroom_app;


import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;


//jola
public class MainActivity extends Activity {
	final int Intent_KEYWORD = 12345;
    int fragmentToSet;
    TabManager tabManager;
    final int NumberOfTabs=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabManager = new TabManager(getActionBar(),NumberOfTabs);
 
      
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
  
		if (id == R.id.mnBluetoothSettings) {
        	fragmentToSet = R.layout.fragment_settings_bluetooth;

        	Intent i = new Intent(this,SettingsActivity.class);
			i.putExtra("paramFragmSet",fragmentToSet);
			startActivityForResult(i, Intent_KEYWORD);
		
            return true;
        }
        if (id == R.id.mnCustomize) {
        	fragmentToSet = R.layout.fragment_settings_customize;
        	Intent i = new Intent(this,SettingsActivity.class);
			i.putExtra("paramFragmSet",fragmentToSet);
			startActivityForResult(i, Intent_KEYWORD);
        	
            return true;
        }
        if (id == R.id.mnNew) {
            tabManager.newTab("Room", new Tab1Fragment());
        	return true;
        }
        if (id == R.id.mnDelete) {
        	tabManager.deleteTab();
        	return true;
        }
        if (id == R.id.mnSettings) {
        	
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
    

}
