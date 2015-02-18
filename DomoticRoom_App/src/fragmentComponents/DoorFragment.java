package fragmentComponents;

import com.Domoticroomapp.domoticroom_app.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DoorFragment extends Fragment implements View.OnClickListener{

	String codeComponent;
	String frame;
	private InterfaceFragments fragInterface;
	Button bOpen;
	Button bClose;
	Button bLock;
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

		TextView	 title 		= (TextView)	 inflatedView.findViewById(R.id.tTitle);
		title.setText(title.getText().toString() + " (" + codeComponent + ")");
		
		bOpen = (Button) inflatedView.findViewById(R.id.bDoorOpen);
		bClose = (Button) inflatedView.findViewById(R.id.bDoorClose);
		bLock = (Button) inflatedView.findViewById(R.id.bDoorLock);
		bOpen.setOnClickListener(this);
		bClose.setOnClickListener(this);
		bLock.setOnClickListener(this);
		return inflatedView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String subFrame = "";
		
		switch (v.getId()) {
		case R.id.bDoorOpen:
			subFrame = "BOPE";
			break;

		case R.id.bDoorClose:
			subFrame = "BCLO";
			break;

		case R.id.bDoorLock:
			subFrame = "BLOC";
			break;
		}
		// String to send
		frame = "$" + codeComponent + subFrame +"\n";
		
		fragInterface.sendToBES(frame);
	}
	
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragInterface = (InterfaceFragments) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragInterface = null;
    }
}
