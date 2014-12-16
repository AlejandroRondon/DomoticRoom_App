package com.Domoticroomapp.domoticroom_app;

import java.util.ArrayList;
import dialogsPack.InputText_Dialog;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.util.Log;


public class TabManager {
	private ActionBar abar;
	private int tabsCount;
	private int tabsAmountLimit;
	
	FragmentManager fragmentManager ;
	ArrayList<ActionBar.Tab>  tabsArray= new ArrayList<ActionBar.Tab>();
	
	/*CONSTRUCTOR*/
	public TabManager(ActionBar actionBar,int AmountLimit,FragmentManager fragmentManager){	//the fragment manager is neccesary to the dialog text input
		tabsAmountLimit=AmountLimit;
		this.fragmentManager = fragmentManager;
		tabsCount =0;
		//Obtenemos una referencia a la actionbar
		abar = actionBar;

		//Establecemos el modo de navegación por pestañas
		abar.setNavigationMode(
				ActionBar.NAVIGATION_MODE_TABS);
	}

	public boolean newTab(String defaultTabName,Fragment fragment)
	{
		if(tabsCount<tabsAmountLimit){
			InputText_Dialog getNameRoom_Dialog = new InputText_Dialog("New Room","Name","Ok","Cancel");
			getNameRoom_Dialog.show(fragmentManager, "tagPersonalizatedDialog");
			
		    //Creamos las pestañas y la agregamos al arraylist
			ActionBar.Tab newTab=abar.newTab().setText(defaultTabName+" ["+tabsCount+"]" );
			tabsArray.add(newTab);
	        //Asociamos los listener a las pestañas
	        tabsArray.get(tabsCount).setTabListener(new TabListenerManager(fragment));
	        //Añadimos las pestañas a la action bar
	        abar.addTab( tabsArray.get(tabsCount));
	        tabsCount++;
	        Log.i("TabManager","New tab created, index: " + (tabsCount-1));
		}
        
        return true;
	}
	
	public void deleteTab(){
		if(tabsCount>0){
		abar.removeTab(abar.getSelectedTab());
		//arra
		tabsCount--;
		Log.i("TabManager","New tab created, index: " + (tabsCount-1));
		}
	}
	public void changeNameTab(int index,String newName){
		tabsArray.get(index).setText(newName);
	}
	public void changeNameLastTab(String newName){	//Used to establish the name at the last tab created
		tabsArray.get(tabsCount-1).setText(newName);
	}


	
}
