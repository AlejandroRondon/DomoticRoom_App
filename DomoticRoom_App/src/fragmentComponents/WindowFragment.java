package fragmentComponents;

import com.Domoticroomapp.domoticroom_app.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class WindowFragment extends Fragment {
	String codeComponent;
	public WindowFragment(String codeComponent){
		// TODO Auto-generated constructor stub
		this.codeComponent = codeComponent;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState) {
		View inflatedView = inflater.inflate(R.layout.fragment_window, container, false);
		LinearLayout ppalLayout = (LinearLayout) inflatedView.findViewById(R.id.ppalLayout);
		ppalLayout.setBackgroundColor(getResources().getColor(R.color.windowFragment));
		return inflatedView;	
		}
}
