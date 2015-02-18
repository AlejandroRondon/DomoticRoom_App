package fragmentComponents;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.Domoticroomapp.domoticroom_app.R;

public class LightFragment extends Fragment implements View.OnClickListener, OnSeekBarChangeListener {
	private InterfaceFragments fragInterface;

	String codeComponent;
	String frame;
	SeekBar seekLight;
	Button bON,bOFF,bAUTO;
	public LightFragment(String codeComponent){
		// TODO Auto-generated constructor stub
		this.codeComponent = codeComponent;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState) {
		View inflatedView = inflater.inflate(R.layout.fragment_light, container, false);
		LinearLayout ppalLayout = (LinearLayout) inflatedView.findViewById(R.id.ppalLayout);
		ppalLayout.setBackgroundColor(getResources().getColor(R.color.lightFragment));

		TextView title = (TextView) inflatedView.findViewById(R.id.tTitle);
		title.setText(title.getText().toString() + " (" + codeComponent + ")");

		seekLight = (SeekBar) inflatedView.findViewById(R.id.sBLight);
		seekLight.setOnSeekBarChangeListener(this);
		
		bON =(Button) inflatedView.findViewById(R.id.bLigthOn);
		bOFF =(Button) inflatedView.findViewById(R.id.bLightOff);
		bAUTO =(Button) inflatedView.findViewById(R.id.bLightAuto);
		
		bON.setOnClickListener(this);
		bOFF.setOnClickListener(this);
		bAUTO.setOnClickListener(this);
		return inflatedView;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		String subFrame = "";
		
		if (progress < 10) {
			subFrame = "S00" + String.valueOf(progress);
		} else if (progress < 100) {
			subFrame = "S0" + String.valueOf(progress);
		} else {
			subFrame = "S" + String.valueOf(progress);
		}
		// String to send
		frame = "$" +codeComponent +	subFrame +	"\n";
		
		fragInterface.sendToBES(frame);
	}
	
	
	
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
//		String subFrame = "";
//		int progress = seekLight.getProgress();
//		if (progress < 10) {
//			subFrame = "S00" + String.valueOf(progress);
//		} else if (progress < 100) {
//			subFrame = "S0" + String.valueOf(progress);
//		} else {
//			subFrame = "S" + String.valueOf(progress);
//		}
//		// String to send
//		frame = "$" +codeComponent +	subFrame +	"\n";
//		
//		fragInterface.sendToBES(frame);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String subFrame = "";

		switch (v.getId()) {
		case R.id.bLightAuto:
			subFrame = "BAUT";
			break;
		case R.id.bLigthOn:
			subFrame = "B0ON";
			break;
		case R.id.bLightOff:
			subFrame = "BOFF";
			break;
		}		
		// String to send
		frame = "$" +codeComponent +	subFrame +	"\n";

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
