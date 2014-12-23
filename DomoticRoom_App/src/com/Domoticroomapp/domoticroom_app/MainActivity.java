package com.Domoticroomapp.domoticroom_app;


import java.util.ArrayList;

import utilitiesApps.FrameManager;
import TabManager.TabManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import dialogsPack.Alert_Dialog;
import dialogsPack.Confirmation_Dialog;
import dialogsPack.InputText_Dialog;
import dialogsPack.SelectionMulti_Dialog;
import dialogsPack.SelectionSingle_Dialog;
import dialogsPack.Selection_Dialog;

//jola
public class MainActivity extends FragmentActivity {
	final int Intent_KEYWORD = 12345;		//Key used to transmit information to the settings activity
	int fragmentToSet;						//Variable used to transmit the fragment to inflate to the settings activity
	TabManager tabManager;					//the principal manager of tabs in the action bar
	final int NumberOfTabs=5;				//Number of tabs allowed in the action bar
	ArrayList<FragmentRoom> rooms = new ArrayList<FragmentRoom>(); //array to save the different fragment rooms created
	FragmentRoomComponentsList roomcomponents;	//used to save the fragment created when the update button is clicked
	boolean roomcomponents_created = false;		//indicate if the room was created
	ArrayList<RoomComponent> auxiliar = new ArrayList<RoomComponent>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tabManager = new TabManager(getActionBar(),NumberOfTabs,getFragmentManager());

		/*Probing Dialog boxes*/
		//ProbingDialogs();

	}	


	public void movetoroom(View view) {
		Log.i("FRAGMENTROOMCOMPONENTLIST", "button pressed");
		
		auxiliar=roomcomponents.getSelectedItems(view);
		rooms.get(0).AddArrayItems(auxiliar);
		//rooms.add(new FragmentRoom(roomcomponents.getSelectedItems(view)));
		
		//tabManager.newTab("Room",R.drawable.ic_newroom,rooms.get(rooms.size()-1),true);

		roomcomponents.deleteSelectedItems(view);
		
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

	/*--------------------------------MENU OPTIONS-----------------------------------*/
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
			
			rooms.add(new FragmentRoom());
			
			tabManager.newTab("Room",R.drawable.ic_newroom,rooms.get(rooms.size()-1),true);
	
			return true;
		}
		if (id == R.id.mnDelete) {
			tabManager.deleteTab();
			return true;
		}
		if (id == R.id.mnUpdate) {
			String BluetoothFrame_Probe ="$BESLI01WI01TE01DO01SW01MO01SE01LC01LI02WI01TE03DO04";
			FrameManager frameManager = new FrameManager();
			if(!frameManager.setFrame(BluetoothFrame_Probe)){
				Log.w("MainActivity", "Error setFrame");
			}else{
				int i;
				for(i=0;i< frameManager.getNumberComponents();i++){
					Log.i("MainActivity", frameManager.getBESComponent(i));
				}
				
				if(roomcomponents_created == false){
					roomcomponents = new FragmentRoomComponentsList(frameManager.constructRoomComponents());
					tabManager.newTab("Components",R.drawable.ic_components,roomcomponents ,false);
					roomcomponents_created= true;
				}else{
					roomcomponents.AddArrayItems(frameManager.constructRoomComponents());
					
				}
			}
			return true;
		}
		if (id == R.id.mnEdit) {
	//		rooms.get(0).AddArrayItems(auxiliar);
//			rooms.get(0).AddArrayItems(roomcomponents.getSelectedItems(view)));
//			rooms.get(0).AddItem(new RoomComponent("new", "new", "new", R.drawable.ic_empty));
//			roomcomponents.AddItem(new RoomComponent("new", "new", "new", R.drawable.ic_empty));
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
