package com.Domoticroomapp.domoticroom_app;


import java.util.ArrayList;
import utilitiesApps.FrameManager;
import TabManager.TabManager;
import ViewPagerManager.ViewPagerAdapter;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;
import dialogsPack.Alert_Dialog;
import dialogsPack.Confirmation_Dialog;
import dialogsPack.InputText_Dialog;
import dialogsPack.SelectionMulti_Dialog;
import dialogsPack.SelectionSingle_Dialog;
import dialogsPack.Selection_Dialog;

public class MainActivity extends FragmentActivity {

	static final String STATE_SCORE = "playerScore";
	static final String STATE_LEVEL = "playerLevel";

	private LinearLayout layoutAnimado;
	final int Intent_KEYWORD = 12345;		//Key used to transmit information to the settings activity
	int fragmentToSet;						//Variable used to transmit the fragment to inflate to the settings activity
	TabManager tabManager;					//the principal manager of tabs in the action bar
	final int NumberOfTabs=5;				//Number of tabs allowed in the action bar
	ArrayList<FragmentRoom> rooms = new ArrayList<FragmentRoom>(); //array to save the different fragment rooms created
	FragmentRoomComponentsList roomcomponents;	//used to save the fragment created when the update button is clicked
	boolean roomcomponents_created = false;		//indicate if the room components was created
	/*Get Fragments manager in the different versions*/
	FragmentManager fragmentManager = getFragmentManager();
	android.support.v4.app.FragmentManager fragmentManagerCompat = getSupportFragmentManager();	//compatibility API
	/* The pager widget, which handles animation and allows swiping horizontally
	 * to access previous and next pages.*/
	ViewPager pager = null;
	/*The pager adapter, which provides the pages to the view pager widget.*/
	ViewPagerAdapter viewPagerAdapter;

	ArrayList<ArrayList<android.support.v4.app.Fragment>> fragmentComponents = new ArrayList<ArrayList<android.support.v4.app.Fragment>>();


	ArrayList<RoomComponent> auxiliarArrayRoomComponents = new ArrayList<RoomComponent>();
	ArrayList<android.support.v4.app.Fragment> auxiliarArrayListFragments = new ArrayList<android.support.v4.app.Fragment>();



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		layoutAnimado = (LinearLayout) findViewById(R.id.animado);
		Log.i("MainActivity", "DOMOTIC ROOM(OnCreate)");

		/*new instance of tab manager to control the action bar */
		Log.i("MainActivity", "Tabs manager Created");
		tabManager = new TabManager(this,getActionBar(),NumberOfTabs,getFragmentManager());

		// Instantiate a ViewPager
		pager = (ViewPager) findViewById(R.id.pager);
		// Create an adapter with the fragments we show on the ViewPager
		viewPagerAdapter = new ViewPagerAdapter(fragmentManagerCompat);
		pager.setAdapter(viewPagerAdapter);

		/**----------------------PROBES---------------------------------------------*/

		/**Probing ViewPager*/
		/*
		ArrayList<android.support.v4.app.Fragment> pages = new ArrayList<android.support.v4.app.Fragment>();
		pages.add(ScreenSlidePageFragment.newInstance(getResources()
				.getColor(R.color.darkgreen), 0));
		pages.add(ScreenSlidePageFragment.newInstance(getResources()
				.getColor(R.color.darkorange), 1));
		pages.add(ScreenSlidePageFragment.newInstance(getResources()
				.getColor(R.color.darkblue), 2));
		pages.add(ScreenSlidePageFragment.newInstance(getResources()
				.getColor(R.color.red), 3));
		pages.add(ScreenSlidePageFragment.newInstance(getResources()
				.getColor(R.color.orange), 4));
		AddPagesPackage(pages);
		 */

		/**Probing Dialog boxes*/
		//ProbingDialogs();

	}	


	/*--------------------------------------Functions (SLOTS)-------------------------------------*/
	public void movetoroom(View view) {
		Log.v("MainActivity", "movetoroom has been called");

		SelectionSingle_Dialog roomsSelection = new  SelectionSingle_Dialog("Rooms", tabManager.getTabsTitles(),"OK","CANCEL");
		roomsSelection.show(fragmentManager,"rooms selector");
		/*later of here --> onUserSelectValue*/

	}
	public void forceToConnect(View view) {
	
	}
	public void onUserSelectValue(String selectedValue) {
		Log.v("MainActivity", "onUserSelectValue has been called");
		View view = getCurrentFocus();	//get the current view
		int indexTab;
		// TODO add your implementation.
		if(!selectedValue.equals("not selection")){
			/*find the tab with the name of the selection*/

			indexTab = tabManager.getIndexTabWithName(selectedValue);
			/**Add new components to the list of the room*/
			auxiliarArrayRoomComponents=roomcomponents.getSelectedItems(view);	//get arraylist of items selected
			Log.v("MainActivity", "Adding items to the room selected");
			rooms.get(indexTab).AddArrayItems(auxiliarArrayRoomComponents);
			/**add the fragments of the new components to the room*/

			/*get the array of fragments from the room*/
			auxiliarArrayListFragments = rooms.get(indexTab).getListComponentFragments();

			/*get the size of the two arrays to determine the amount of elements to add*/
			int size_updated,size_toupdate,size_diference,j;


			size_updated = auxiliarArrayListFragments.size();	//amount of fragments in the room
			size_toupdate = fragmentComponents.get(indexTab).size();	//amount the fragments in the array of arrays (fragmentComponents)
			size_diference = size_updated - size_toupdate;		//amount of fragments to add to fragmentComponents

			if(size_diference != 0){
				for(j=size_toupdate;j<size_updated;j++){
					fragmentComponents.get(indexTab).add(auxiliarArrayListFragments.get(j));
				}
			}
			//replaceAllPages(fragmentComponents.get(i));

			Log.v("MainActivity", "Deleting items from components");
			view= getCurrentFocus();
			roomcomponents.deleteSelectedItems(view);
		}else{
			Toast.makeText(this,"No selection",Toast.LENGTH_SHORT).show();
		}
	}
	/*-----------Functions used to establish the name of the tabs---------------------------*/
	public void doPositiveClick(String receiveString){
		Log.v("MainActivity", "doPositiveClick has been called");

		if(!receiveString.equals("")){
			Toast.makeText(this, "new name: " + receiveString, Toast.LENGTH_SHORT).show();
			tabManager.changeNameLastTab(receiveString);
		}else{
			Toast.makeText(this, "new name: default", Toast.LENGTH_SHORT).show();
		}
	}

	public void doNegativeClick(String receiveString){
		Log.v("MainActivity", "doNegativeClick has been called");

		Toast.makeText(this, "new name: " + receiveString, Toast.LENGTH_SHORT).show();
	}
	/*--------------------------------------------------------------------------------------*/

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

		/*-----------------------ITEMS IN THE SUBMENU--------------------------------------*/
		if (id == android.R.id.home){
			Log.v("MENU", "Home pressed");
			/*frame to emulate a frame of update*/
			String BluetoothFrame_Probe ="$BESLI01WI01TE01DO01SW01MO01SE01LC01LI02WI01TE03DO04WI02WI03";

			/*new instance of a framemanager to manage the frame received*/
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



		if (id == R.id.mnSettings) {
			Log.v("MENU", "Settings pressed");

			return true;
		}

		if (id == R.id.mnBluetoothSettings) {
			Log.v("MENU", "Bluetooth settings pressed");
			fragmentToSet = R.layout.fragment_settings_bluetooth;
			/*intent to transmit information to the settings activity*/
			Intent i = new Intent(this,SettingsActivity.class);
			i.putExtra("paramFragmSet",fragmentToSet);
			startActivityForResult(i, Intent_KEYWORD);

			return true;
		}
		if (id == R.id.mnCustomize) {
			Log.v("MENU", "Customize pressed");
			fragmentToSet = R.layout.fragment_settings_customize;
			/*intent to transmit information to the settings activity*/
			Intent i = new Intent(this,SettingsActivity.class);
			i.putExtra("paramFragmSet",fragmentToSet);
			startActivityForResult(i, Intent_KEYWORD);

			return true;
		}
		/*------------------------------------------------------------------------------------*/		
		if (id == R.id.mnNew) {
			Log.v("MENU", "New pressed");
			rooms.add(new FragmentRoom());
			if(tabManager.newTab("Room",R.drawable.ic_newroom,rooms.get(rooms.size()-1),true)){
				ArrayList<android.support.v4.app.Fragment> emptyList = new ArrayList<android.support.v4.app.Fragment>();
				fragmentComponents.add(emptyList);
			}

			return true;
		}
		if (id == R.id.mnDelete) {
			Log.v("MENU", "Delete pressed");
			int indexTabDeleted = tabManager.deleteTab();
			/*check if the tab that will be deleted is the components tab*/
			if (indexTabDeleted != -2){
				if(indexTabDeleted == -1){
					roomcomponents_created=false;
				}else{
					rooms.remove(indexTabDeleted);//delete fragment associated to the tab
					fragmentComponents.remove(indexTabDeleted);
				}
			}else{
				Toast.makeText(this,"There aren't rooms to delete",Toast.LENGTH_SHORT).show();
			}
			return true;
		}
		/*if (id == R.id.mnUpdate) {
			Log.v("MENU", "Update pressed");
			//frame to emulate a frame of update
			String BluetoothFrame_Probe ="$BESLI01WI01TE01DO01SW01MO01SE01LC01LI02WI01TE03DO04WI02WI03";

			//new instance of a framemanager to manage the frame received
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
		}*/
		if (id == R.id.mnEdit) {
			Log.v("MENU", "Edit pressed");
			//			if(this.pager.getVisibility()==View.GONE){
			//				this.pager.setVisibility(View.VISIBLE);
			//			}else{
			//				this.pager.setVisibility(View.GONE);
			//			}
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
		ArrayList<String> arraylista = new ArrayList<String>();
		arraylista.add("X");
		arraylista.add("Y");
		arraylista.add("Z");

		Selection_Dialog selection_Dialog = new Selection_Dialog("Selection", Lista);
		selection_Dialog.show(fragmentManager2, "tagSelection");
		/*--------------------*/
		SelectionSingle_Dialog selectionSingle_Dialog = new SelectionSingle_Dialog("Selection Single", arraylista,"OK","CANCEL");
		selectionSingle_Dialog.show(fragmentManager2, "tagSelectionSingle");
		/*--------------------*/
		SelectionMulti_Dialog selectionMulti_Dialog = new SelectionMulti_Dialog("Selection  Multi","Ready!", Lista);
		selectionMulti_Dialog.show(fragmentManager2, "tagSelectionMulti");
		/*--------------------*/
		InputText_Dialog getNameRoom_Dialog = new InputText_Dialog("Input text","Name Room","Ok","Cancel");
		getNameRoom_Dialog.show(fragmentManager2, "tagPersonalizatedDialog");
	}

	/*---------------------------VIEWPAGER FUNCTIONS----------------*/
	public void AddPage(Fragment fragmentToAdd ){
		viewPagerAdapter.addFragment(fragmentToAdd);
		viewPagerAdapter.notifyDataSetChanged();
	}

	public void AddPagesPackage(ArrayList<Fragment> fragmentToAdd ){
		int i;
		for(i=0;i<fragmentToAdd.size();i++){
			viewPagerAdapter.addFragment(fragmentToAdd.get(i));
			viewPagerAdapter.notifyDataSetChanged();
		}
	}
	public void RemovePage(int index){
		viewPagerAdapter.removeFragment(index);
		viewPagerAdapter.notifyDataSetChanged();
		pager.setAdapter(viewPagerAdapter);	//update the adapter
	}

	public void RemoveAllPages(){
		viewPagerAdapter.removeAllFragments();
		viewPagerAdapter.notifyDataSetChanged();
		pager.setAdapter(viewPagerAdapter);	//update the adapter
	}
	public void replaceAllPages(ArrayList<Fragment> fragmentsToAdd){
		RemoveAllPages();
		AddPagesPackage(fragmentsToAdd);
	}
	public void ViewPagerSHOW(){
		//animar(true);
		//this.pager.setVisibility(View.VISIBLE);
		layoutAnimado.setVisibility(View.VISIBLE);
	}
	public void ViewPagerHIDE(){
		//animar(false);
		//this.pager.setVisibility(View.GONE);
		layoutAnimado.setVisibility(View.GONE);
	}
	public void loadFragmentsOnVP(String tabName){	//load the fragments in the ViewPager when any tab is selected
		int indexTab=0;
		if(tabName.equals("Components")){
			ViewPagerHIDE();
		}else{
			indexTab = tabManager.getIndexTabWithName(tabName);
			if(indexTab != -1){
				if(fragmentComponents.get(indexTab).size() != 0){
					replaceAllPages(fragmentComponents.get(indexTab));
					ViewPagerSHOW();
				}else{
					ViewPagerHIDE();
				}
			}

		}
	}

	@Override
	public void onBackPressed() {

		// Return to previous page when we press back button
		if (pager.getCurrentItem() == 0)
			super.onBackPressed();
		else
			pager.setCurrentItem(pager.getCurrentItem() - 1);

	}

	public void setFragmentView(int index){
		pager.setCurrentItem(index);
	}
	/*Used later*/
	private void animar(boolean mostrar)
	{
		AnimationSet set = new AnimationSet(true);
		Animation animation = null;
		if (mostrar)
		{
			//desde la esquina inferior derecha a la superior izquierda
			//			animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
			animation = new TranslateAnimation(1.0f, 0.0f,  1.0f,  1f);
		}
		else
		{    //desde la esquina superior izquierda a la esquina inferior derecha
			//			animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
			animation = new TranslateAnimation( 0.0f, 1.0f, 0.0f, 1.0f);
		}
		//duración en milisegundos
		animation.setDuration(5000);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(set, 0.50f);


		layoutAnimado.setLayoutAnimation(controller);
		layoutAnimado.startAnimation(animation);
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save the user's current game state
		//savedInstanceState.putParcelable(STATE_SCORE, (Parcelable) tabManager);
		//savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);

		// Always call the superclass so it can save the view hierarchy state
		super.onSaveInstanceState(savedInstanceState);
	}

	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Always call the superclass so it can restore the view hierarchy
		super.onRestoreInstanceState(savedInstanceState);

		// Restore state members from saved instance
		//
		tabManager = savedInstanceState.getParcelable(STATE_SCORE);
		// mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
	}
}

