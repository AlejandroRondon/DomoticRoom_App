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

		int i,j=0;
		boolean wasFoundName=false;
		String defaultTabNamePlus =defaultTabName.concat(" ["+(j)+"]");// adding the index tab to the default tab name
		if(tabsCount<tabsAmountLimit){

			if(editDefaultTitle == true){
				InputText_Dialog getNameRoom_Dialog = new InputText_Dialog("New Room","Name","Ok","Cancel");
				getNameRoom_Dialog.show(fragmentManager, "tagPersonalizatedDialog");

				/*buscamos si hay alguna pestaña con el nombre
				 * 
				 *  por defecto */
				for(j=0;j<tabsAmountLimit;j++){
					defaultTabNamePlus=defaultTabName+" ["+(j+1)+"]";
					for(i=0;i<tabsCount;i++){
						if((tabsArray.get(i).getText().equals(defaultTabNamePlus))){
							Log.i("TabManager", "was found in [" + i+ "]");
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
			}
			//Creamos las pestañas y la agregamos al arraylist
			ActionBar.Tab newTab=abar.newTab().setText(defaultTabNamePlus).setIcon(icon);
		
			tabsArray.add(newTab);
			//Asociamos los listener a las pestañas
			tabsArray.get(tabsCount).setTabListener(new TabListenerManager(fragment));
			//Añadimos las pestañas a la action bar
			abar.addTab( tabsArray.get(tabsCount));
			abar.selectTab(tabsArray.get(tabsArray.size()-1));
			tabsCount++;
			Log.i("TabManager","New tab created, index: " + (tabsCount-1));
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
