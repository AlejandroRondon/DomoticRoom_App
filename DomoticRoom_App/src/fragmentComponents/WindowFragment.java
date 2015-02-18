package fragmentComponents;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.Domoticroomapp.domoticroom_app.R;

public class WindowFragment extends Fragment implements OnSeekBarChangeListener {
	private InterfaceFragments fragInterface;
	String codeComponent;
	String frame;
	SeekBar seekWindowHeight;
	SeekBar seekWindowOpening;

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

		TextView	 title 		= (TextView)	 inflatedView.findViewById(R.id.tTitle);
		title.setText(title.getText().toString() + " (" + codeComponent + ")");

		seekWindowHeight = (SeekBar) inflatedView.findViewById(R.id.sBWindowHeight);
		seekWindowOpening = (SeekBar) inflatedView.findViewById(R.id.sBWindowOpening);
		
		seekWindowHeight.setOnSeekBarChangeListener(this);
		seekWindowOpening.setOnSeekBarChangeListener(this);
		return inflatedView;	
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		String subFrame = "";

		 if (seekBar.equals(seekWindowOpening)) {
			if (progress < 10) {
				subFrame = "O00" + String.valueOf(progress);
			} else if (progress < 100) {
				subFrame = "O0" + String.valueOf(progress);
			} else {
				subFrame = "O" + String.valueOf(progress);
			}
		}
		// String to send
		frame = "$" +codeComponent  +subFrame +	"\n";

		fragInterface.sendToBES(frame);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		String subFrame = "";
		int progress;
		if (seekBar.equals(seekWindowHeight)) {
			progress = seekWindowHeight.getProgress();
			if (progress < 10) {
				subFrame = "H00" + String.valueOf(progress);
			} else if (progress < 100) {
				subFrame = "H0" + String.valueOf(progress);
			} else {
				subFrame = "H" + String.valueOf(progress);
			}
		} 
		// String to send
		frame = "$" +codeComponent  +subFrame +	"\n";

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
