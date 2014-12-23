package TabManager;

import com.Domoticroomapp.domoticroom_app.R;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.util.Log;

public class TabListenerManager implements ActionBar.TabListener {
	
	private Fragment fragment;
	
	public TabListenerManager(Fragment fg)
	{
		this.fragment = fg;
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		//Log.v("ActionBar", "Tab: " + tab.getText() + " reselected.");
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		//Log.v("ActionBar", "Tab: " + tab.getText() + " selected.");
		ft.replace(R.id.ppal_container, fragment);
	}
	
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		//Log.v("ActionBar", "Tab: " + tab.getText() + " deselected.");
		ft.remove(fragment);
	}
}
