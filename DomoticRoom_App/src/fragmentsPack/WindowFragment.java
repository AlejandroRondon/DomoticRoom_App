package fragmentsPack;

import com.Domoticroomapp.domoticroom_app.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WindowFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_window, container, false);
	}
}
