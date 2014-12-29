package TabManager;

import java.util.ArrayList;

import com.Domoticroomapp.domoticroom_app.MainActivity;

import dialogsPack.InputText_Dialog;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;

import android.util.Log;


public class TabManager {

	private ActionBar abar;	//instance of an action bar object
	private int tabsCount;	//tabs counter
	private int tabsAmountLimit;	//number of tabs allowed

	FragmentManager fragmentManager;//instance of the fragmentmanager
	ArrayList<ActionBar.Tab>  tabsArray= new ArrayList<ActionBar.Tab>();//array to save the rooms tabs
	ActionBar.Tab tabComponents;//single variable to save the room components
	MainActivity callingActivity;	//used to call functions from MainActivity
	/*CONSTRUCTOR*/
	public TabManager(MainActivity callingActivity,ActionBar actionBar,int AmountLimit,FragmentManager fragmentManager){	//the fragment manager is neccesary to the dialog text input
		tabsAmountLimit=AmountLimit;
		this.fragmentManager = fragmentManager;
		this.callingActivity = callingActivity;
		tabsCount =0;
		//get the reference to the action bar
		abar = actionBar;
		//Establish the navigation mode in the action bar
		abar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//activate the icon button in the application bar name
		abar.setDisplayHomeAsUpEnabled(true);

	}

	public boolean newTab(String defaultTabName,int icon,Fragment fragment,boolean roomORcomponents)
	{	
		Log.i("TabManager","Creating New Tab...");

		int i,j=0;	//simply counters
		boolean wasFoundName=false;//flag to indicate if the name exist in any tab

		String defaultTabNamePlus =defaultTabName.concat(" ["+(j)+"]");// adding the index tab to the default tab name
		ActionBar.Tab newTab; //new tab to add at the action bar

		if(tabsCount<tabsAmountLimit){	//verify if the action bar is full

			/*search in the tabs any tab with the same default name */
			Log.i("TabManager", "finding defaut name to the new tab");
			for(j=0;j<tabsAmountLimit;j++){
				defaultTabNamePlus=defaultTabName+" ["+(j+1)+"]";
				for(i=0;i<tabsCount;i++){
					if((tabsArray.get(i).getText().equals(defaultTabNamePlus))){
						//Log.i("TabManager", "was found in [" + i+ "]");
						wasFoundName=true;
						break;
					}

				}
				if(wasFoundName == false){
					break;
				}else{
					wasFoundName = false;
				}

			}
			//Creamos las pestañas y la agregamos al arraylist
			if(roomORcomponents == true){
				//Creating dialog to input the new name
				InputText_Dialog getNameRoom_Dialog = new InputText_Dialog("New Room","Name","Ok","Cancel");
				getNameRoom_Dialog.show(fragmentManager, "tagPersonalizatedDialog");
				newTab=abar.newTab().setText(defaultTabNamePlus).setIcon(icon);
				/**/
				tabsArray.add(newTab);
				//Asociamos los listener a las pestañas
				tabsArray.get(tabsCount).setTabListener(new TabListenerManager(callingActivity,fragment));
				//Añadimos las pestañas a la action bar
				abar.addTab( tabsArray.get(tabsCount),0);
				abar.selectTab(tabsArray.get(tabsArray.size()-1));
				tabsCount++;
				Log.i("TabManager","...New tab created, index: " + (tabsCount-1) + " ,default name: " + tabsArray.get(tabsArray.size()-1).getText().toString());
			}else{
				newTab=abar.newTab().setText(defaultTabName).setIcon(icon);
				/**/
				tabComponents = newTab;
				//Asociamos los listener a las pestañas
				tabComponents.setTabListener(new TabListenerManager(callingActivity,fragment));
				//Añadimos las pestañas a la action bar
				abar.addTab( tabComponents);
				abar.selectTab(tabComponents);
				Log.i("TabManager","...New Components tab created");
				
			}

		}else{
			Log.e("TabManager","Limit number of tabs has already been created" );
		}	

		return true;
	}

	public int deleteTab(){	//return index of the tab deleted,-1 if the tab deleted is the components tab,-2 if there are not tabs to delete
		Log.i("TabManager","Deleting selected Tab...");
		ActionBar.Tab tabToDelete =	abar.getSelectedTab();
		if(tabToDelete != null){
			abar.removeTab(tabToDelete);
			if(!tabToDelete.getText().toString().equals("Components")){
				//if(tabsCount>0){
					int indexTabToDelete  = tabsArray.lastIndexOf(tabToDelete);
					tabsArray.remove(indexTabToDelete);
					tabsCount--;
					Log.i("TabManager","Tab deleted, Actual index: " + (tabsCount-1));
					return indexTabToDelete;
				//}
			}else{
				Log.i("TabManager","Components tab deleted");
				return -1;
			}
		}else{
			Log.e("TabManager","Aborting delete Tab(there isn't tab selected)");
			return -2;
		}
	}
	public void changeNameTab(int index,String newName){
		tabsArray.get(index).setText(newName);
	}
	public void changeNameLastTab(String newName){	//Used to establish the name at the last tab created
		tabsArray.get(tabsCount-1).setText(newName);
	}
	public ArrayList<String> getTabsTitles(){
		ArrayList<String> titles = new ArrayList<String>();
		int i;
		for(i=0;i<tabsCount;i++){
			titles.add( tabsArray.get(i).getText().toString());
		}
		return titles;

	}

	public int getTabsCount() {
		return tabsCount;
	}

	public String getTabName(int index) {
		return tabsArray.get(index).getText().toString();
	}

	public ActionBar.Tab getCurrentTab(){
		return abar.getSelectedTab();
	}








}
