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

public class MotorFragment extends Fragment implements View.OnClickListener, OnSeekBarChangeListener{
	private InterfaceFragments fragInterface;
	String codeComponent;
	String frame;
	SeekBar seekMotor;
	Button bForward,bBackward;
	public MotorFragment(String codeComponent){
		// TODO Auto-generated constructor stub
		this.codeComponent = codeComponent;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState) {
		View inflatedView = inflater.inflate(R.layout.fragment_motor, container, false);
		LinearLayout ppalLayout = (LinearLayout) inflatedView.findViewById(R.id.ppalLayout);
		ppalLayout.setBackgroundColor(getResources().getColor(R.color.motorFragment));

		TextView	 title 		= (TextView)	 inflatedView.findViewById(R.id.tTitle);
		title.setText(title.getText().toString() + " (" + codeComponent + ")");

		seekMotor = (SeekBar) inflatedView.findViewById(R.id.sBMotor);
		seekMotor.setOnSeekBarChangeListener(this);
		bForward =(Button) inflatedView.findViewById(R.id.bMotorForward);
		bBackward =(Button) inflatedView.findViewById(R.id.bMotorBack);
		
		bBackward.setOnClickListener(this);
		bForward.setOnClickListener(this);
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
//		frame = "$" +codeComponent +subFrame +"\n";
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
		int progress = seekMotor.getProgress();
		if (progress < 10) {
			subFrame = "S00" + String.valueOf(progress);
		} else if (progress < 100) {
			subFrame = "S0" + String.valueOf(progress);
		} else {
			subFrame = "S" + String.valueOf(progress);
		}
		// String to send
		frame = "$" +codeComponent +subFrame +"\n";

		fragInterface.sendToBES(frame);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String subFrame = "";

		switch (v.getId()) {
		case R.id.bMotorBack:
			subFrame = "BBAC";
			break;
		case R.id.bMotorForward:
			subFrame = "BFOR";
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
