package com.Domoticroomapp.domoticroom_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.app.FragmentManager;
import dialogsPack.*;

//jola
public class MainActivity extends FragmentActivity {
	final int Intent_KEYWORD = 12345;
	int fragmentToSet;
	TabManager tabManager;
	final int NumberOfTabs=5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tabManager = new TabManager(getActionBar(),NumberOfTabs,getFragmentManager());


		/*Probing Dialog boxes*/
		//ProbingDialogs();

	}	
	public void onUserSelectValue(String selectedValue) {

		// TODO add your implementation.
	}
	public void doPositiveClick(String receiveString){

		if(!receiveString.equals("")){
			Toast.makeText(this, "new name: " + receiveString, Toast.LENGTH_SHORT).show();
			tabManager.changeNameLastTab(receiveString);
		}else{
			Toast.makeText(this, "new name: default", Toast.LENGTH_SHORT).show();
		}
	}

		public void doNegativeClick(String receiveString){
			Toast.makeText(this, "new name: " + receiveString, Toast.LENGTH_SHORT).show();
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
				tabManager.newTab("Room", new TabsFragment());
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



		void ProbingDialogs(){
			/*Probing Dialog boxes*/
			android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
			Alert_Dialog alert_Dialog = new Alert_Dialog("Information", "Training Alert dialogs", "Ok Master!");
			alert_Dialog.show(fragmentManager, "tagAlerta");
			/*--------------------*/
			FragmentManager fragmentManager2 = getFragmentManager();
			Confirmation_Dialog  confirmation_Dialog = new Confirmation_Dialog("Information", "Training confirmation dialogs", "Ok Master!","No Master");
			confirmation_Dialog.show(fragmentManager2,"tagConfirmation");
			/*--------------------*/
			String Lista[] = {"A","B","C"};
			Selection_Dialog selection_Dialog = new Selection_Dialog("Selection", Lista);
			selection_Dialog.show(fragmentManager2, "tagSelection");
			/*--------------------*/
			SelectionSingle_Dialog selectionSingle_Dialog = new SelectionSingle_Dialog("Selection Single", Lista);
			selectionSingle_Dialog.show(fragmentManager2, "tagSelectionSingle");
			/*--------------------*/
			SelectionMulti_Dialog selectionMulti_Dialog = new SelectionMulti_Dialog("Selection  Multi","Ready!", Lista);
			selectionMulti_Dialog.show(fragmentManager2, "tagSelectionMulti");
			/*--------------------*/
			InputText_Dialog getNameRoom_Dialog = new InputText_Dialog("Input text","Name Room","Ok","Cancel");
			getNameRoom_Dialog.show(fragmentManager2, "tagPersonalizatedDialog");
		}


	}
