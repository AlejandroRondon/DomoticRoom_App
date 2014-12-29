package ViewPagerManager;

import java.util.ArrayList;
import java.util.List;
 
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
 
/**
 * 
 * @author amatellanes
 * 
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
 
    // List of fragments which are going to set in the view pager widget
    List<Fragment> fragments;
    /**
     * Constructor
     * 
     * @param fm
     *            interface for interacting with Fragment objects inside of an
     *            Activity
     */
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<Fragment>();
    }
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> listFragments) {
        super(fm);
        this.fragments = listFragments;
    }
    
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
    /**
     * Add a new fragment in the list.
     * 
     * @param fragment
     *            a new fragment
     */
    public void addFragment(Fragment fragment) {
        this.fragments.add(fragment);
    }
    
    public void removeFragment(int index) {
        this.fragments.remove(index);
        
    }
    public void removeAllFragments() {
        this.fragments.removeAll(fragments);
    }
 
    @Override
    public Fragment getItem(int arg0) {
        return this.fragments.get(arg0);
    }
 
    @Override
    public int getCount() {
        return this.fragments.size();
    }
 
}
