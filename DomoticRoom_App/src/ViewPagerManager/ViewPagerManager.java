package ViewPagerManager;

import java.util.ArrayList;

import com.Domoticroomapp.domoticroom_app.MainActivity;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPagerManager {
	/**
	 * The pager widget, which handles animation and allows swiping horizontally
	 * to access previous and next pages.
	 */
	ViewPager pager = null;
	android.support.v4.app.FragmentManager fragmentManager;
	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	ViewPagerAdapter pagerAdapter;
	MainActivity callingActivity;
	public ViewPagerManager(ViewPager pager,android.support.v4.app.FragmentManager fragmentManager) {
		super();
		this.pager = pager;
		//View a =callingActivity.getCurrentFocus();
		this.fragmentManager = fragmentManager;
		pagerAdapter = new ViewPagerAdapter(this.fragmentManager);
       
		

		this.pager.setAdapter(pagerAdapter);
        //this.pager.setVisibility(View.GONE);
	}

	public void AddPage(Fragment fragmentToAdd ){
		pagerAdapter.addFragment(fragmentToAdd);
		pagerAdapter.notifyDataSetChanged();
	}

	public void AddPagesPackage(ArrayList<Fragment> fragmentToAdd ){
		int i;
		for(i=0;i<fragmentToAdd.size();i++){
			pagerAdapter.addFragment(fragmentToAdd.get(i));
			pagerAdapter.notifyDataSetChanged();
		}
		
		
	}
	
	public void RemovePage(int index){
		pagerAdapter.removeFragment(index);
		pagerAdapter.notifyDataSetChanged();
	}
	
	public void RemoveAllPages(){
		pagerAdapter.removeAllFragments();
		pagerAdapter.notifyDataSetChanged();
	}

	public void ViewPagerSHOW(){
		this.pager.setVisibility(View.VISIBLE);
	}
	public void ViewPagerHIDE(){
		this.pager.setVisibility(View.GONE);
	}



}
