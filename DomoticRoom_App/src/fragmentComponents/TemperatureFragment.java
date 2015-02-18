package fragmentComponents;

import com.Domoticroomapp.domoticroom_app.R;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class TemperatureFragment extends Fragment implements View.OnClickListener, OnSeekBarChangeListener {
	private InterfaceFragments fragInterface;

	String codeComponent;
	String frame;
	TextView curTemp;
	SeekBar seekTemperature;
	Button bON,bOFF,bAUTO;
	public TemperatureFragment(String codeComponent){
		// TODO Auto-generated constructor stub
		this.codeComponent = codeComponent;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState) {
		View inflatedView = inflater.inflate(R.layout.fragment_temperature, container, false);
		LinearLayout ppalLayout = (LinearLayout) inflatedView.findViewById(R.id.ppalLayout);
		ppalLayout.setBackgroundColor(getResources().getColor(R.color.temperatureFragment));

		TextView	 title 		= (TextView)	 inflatedView.findViewById(R.id.tTitle);
		title.setText(title.getText().toString() + " (" + codeComponent + ")");

//		curTemp = (TextView) 
		
		seekTemperature = (SeekBar) inflatedView.findViewById(R.id.sBTemperature);

		seekTemperature.setOnSeekBarChangeListener(this);
		bON =(Button) inflatedView.findViewById(R.id.bTemperatureOn);
		bOFF =(Button) inflatedView.findViewById(R.id.bTemperatureOff);
		bAUTO =(Button) inflatedView.findViewById(R.id.bTemperatureAuto);

		bON.setOnClickListener(this);
		bOFF.setOnClickListener(this);
		bAUTO.setOnClickListener(this);
		return inflatedView;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		//		String subFrame = "";
		//
		//		if (progress < 10) {
		//			subFrame = "S00" + String.valueOf(progress);
		//		} else if (progress < 100) {
		//			subFrame = "S0" + String.valueOf(progress);
		//		} else {
		//			subFrame = "S" + String.valueOf(progress);
		//		}
		//		// String to send
		//		frame = "$" + codeComponent  +subFrame +"\n";
		//
		//		fragInterface.sendToBES(frame);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		String subFrame = "";
		int progress = seekTemperature.getProgress();
		if (progress < 10) {
			subFrame = "S00" + String.valueOf(progress);
		} else if (progress < 100) {
			subFrame = "S0" + String.valueOf(progress);
		} else {
			subFrame = "S" + String.valueOf(progress);
		}
		// String to send
		frame = "$" + codeComponent  +subFrame +"\n";

		fragInterface.sendToBES(frame);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String subFrame = "";

		switch (v.getId()) {
		case R.id.bTemperatureAuto:
			subFrame = "BAUT";
			break;
		case R.id.bTemperatureOn:
			subFrame = "B0ON";
			break;
		case R.id.bTemperatureOff:
			subFrame = "BOFF";
			break;
		}
		// String to send
		frame = "$" +codeComponent +subFrame +"\n";

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
