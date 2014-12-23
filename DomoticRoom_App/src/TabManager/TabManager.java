package TabManager;

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
	ActionBar.Tab tabComponents;
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
		abar.setDisplayHomeAsUpEnabled(true);
		
	}

	public boolean newTab(String defaultTabName,int icon,Fragment fragment,boolean editDefaultTitle)
	{	
		Log.i("TabManager","Creating New Tab...");

		int i,j=0;	//simply counters
		boolean wasFoundName=false;//flag to indicate if the name exist in any tab
		
		String defaultTabNamePlus =defaultTabName.concat(" ["+(j)+"]");// adding the index tab to the default tab name
		ActionBar.Tab newTab; //new tab to add at the action bar
		ActionBar.Tab auxTab;
		if(tabsCount<tabsAmountLimit){

			if(editDefaultTitle == true){
				InputText_Dialog getNameRoom_Dialog = new InputText_Dialog("New Room","Name","Ok","Cancel");
				getNameRoom_Dialog.show(fragmentManager, "tagPersonalizatedDialog");

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
				newTab=abar.newTab().setText(defaultTabNamePlus).setIcon(icon);
			}else{
				newTab=abar.newTab().setText(defaultTabName).setIcon(icon);

			}
			//Creamos las pestañas y la agregamos al arraylist
		
			tabsArray.add(newTab);
			//Asociamos los listener a las pestañas
			tabsArray.get(tabsCount).setTabListener(new TabListenerManager(fragment));
			
			
			if(editDefaultTitle == true){
				abar.addTab( tabsArray.get(tabsCount),0);
			}else{
				abar.addTab( tabsArray.get(tabsCount));
			}
			//Añadimos las pestañas a la action bar
			abar.selectTab(tabsArray.get(tabsArray.size()-1));
			tabsCount++;
			Log.i("TabManager","...New tab created, index: " + (tabsCount-1) + " ,default name: " + tabsArray.get(tabsArray.size()-1).getText().toString());
		}else{
			Log.e("TabManager","Limit number of tabs has already been created" );
		}	

		return true;
	}

	public void deleteTab(){
		if(tabsCount>0){
			ActionBar.Tab tabToDelete =	abar.getSelectedTab();
			abar.removeTab(tabToDelete);
			int indexTabToDelete  = tabsArray.lastIndexOf(tabToDelete);
			tabsArray.remove(indexTabToDelete);
			tabsCount--;
			Log.i("TabManager","Tab deleted, Actual index: " + (tabsCount-1));
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
