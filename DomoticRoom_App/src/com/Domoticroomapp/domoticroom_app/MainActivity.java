package com.Domoticroomapp.domoticroom_app;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity {
	final int Intent_KEYWORD = 12345;
	
	int fragmentToSet =0;
	
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("ROOM 1"),
            Tab1.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("ROOM 2"),
        	Tab2.class, null);
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
        if (id == R.id.mnBluetooth) {
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
        return super.onOptionsItemSelected(item);
    }
}
