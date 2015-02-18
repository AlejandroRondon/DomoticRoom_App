package fragmentComponents;

import com.Domoticroomapp.domoticroom_app.R;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LcdFragment extends Fragment implements View.OnClickListener {
	private InterfaceFragments fragInterface;
	String codeComponent;
	String frame;
	EditText txtToSend;
	Button bSend;
	public LcdFragment(String codeComponent){
		// TODO Auto-generated constructor stub
		this.codeComponent = codeComponent;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState) {
		View inflatedView = inflater.inflate(R.layout.fragment_lcd, container, false);
		LinearLayout ppalLayout = (LinearLayout) inflatedView.findViewById(R.id.ppalLayout);
		ppalLayout.setBackgroundColor(getResources().getColor(R.color.lcdFragment));

		TextView	 title 		= (TextView)	 inflatedView.findViewById(R.id.tTitle);
		title.setText(title.getText().toString() + " (" + codeComponent + ")");

		txtToSend = (EditText) inflatedView.findViewById(R.id.eLcdSend);
		
		bSend = (Button) inflatedView.findViewById(R.id.bLcdSend);
		bSend.setOnClickListener(this);
		return inflatedView;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String subFrame = "";

		switch (v.getId()) {
		case R.id.bLcdSend:
			subFrame = txtToSend.getText().toString();
			break;
		}
		// String to send
		frame = "$" +codeComponent +"BMSN"+subFrame +"\n";
		txtToSend.setText("");
		
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