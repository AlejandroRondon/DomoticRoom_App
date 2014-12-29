package fragmentComponents;

import com.Domoticroomapp.domoticroom_app.R;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DoorFragment extends Fragment {
	
	String codeComponent;
	public DoorFragment(String codeComponent){
		// TODO Auto-generated constructor stub
		this.codeComponent = codeComponent;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState) {
		View inflatedView = inflater.inflate(R.layout.fragment_door, container, false);
		LinearLayout ppalLayout = (LinearLayout) inflatedView.findViewById(R.id.ppalLayout);
		ppalLayout.setBackgroundColor(getResources().getColor(R.color.doorFragment));
		return inflatedView;
	}
}
