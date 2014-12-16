package dialogsPack;
import com.Domoticroomapp.domoticroom_app.*;
import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
public class InputText_Dialog extends DialogFragment{
	String title,label,msgInButtonPos,msgInButtonNeg;
	View viewFragmentInflated;
	MainActivity callingActivity;
	public InputText_Dialog(String title,String label,String msgInButtonPos,String msgInButtonNeg) {
		this.title=title;
		this.label=label;
		this.msgInButtonPos=msgInButtonPos;
		this.msgInButtonNeg=msgInButtonNeg;

	}

	@SuppressLint("InflateParams") @Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		/*Inflate and customize the fragment*/
		viewFragmentInflated=inflater.inflate(R.layout.fragment_dialog_inputtext, null);
		TextView labelInfragment = (TextView) viewFragmentInflated.findViewById(R.id.tlabel);
		labelInfragment.setText(label);


		builder.setView(viewFragmentInflated)
		.setPositiveButton(msgInButtonPos, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				EditText newName = (EditText)  viewFragmentInflated.findViewById(R.id.eEditText);
				Log.i("Input text", newName.getText().toString());
				callingActivity.doPositiveClick(newName.getText().toString());
				dialog.cancel();

			}
		}).setNegativeButton(msgInButtonNeg, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Log.i("Input text","default");
				callingActivity.doNegativeClick("default");
				dialog.cancel();
			}
		}).setTitle(title).setIcon(R.drawable.ic_newroom);

		return builder.create();
	}
	//	    @Override
	//	    public void onClick(DialogInterface dialog, int position) {
	//
	//			String value = "20";
	//	        Log.d("Quantity: ", value);
	//	        MainActivity callingActivity = (MainActivity) getActivity();
	//	        callingActivity.onUserSelectValue(value);
	//	        dialog.dismiss();
	//	    }

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		callingActivity = (MainActivity) getActivity();
	}




}
