package dialogsPack;

import java.util.ArrayList;

import com.Domoticroomapp.domoticroom_app.MainActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

	public class SelectionSingle_Dialog extends DialogFragment {
		String title;
		String list[];
		String selection = "not selection";
		String msgInButtonPos,msgInButtonNeg;
		MainActivity callingActivity;
		public SelectionSingle_Dialog(String title,ArrayList<String> list,String msgInButtonPos,String msgInButtonNeg){
			this.title=title;
			this.msgInButtonPos=msgInButtonPos;
			this.msgInButtonNeg=msgInButtonNeg;
			/*CONVERTION ARRAYLIST<String> TO ARRAY of Strings*/
			String[] auxList = new String[list.size()];
			int i;
			for(i=0;i<list.size();i++){
				auxList[i] = list.get(i);
			}
			this.list = auxList;
		}
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	 
	     
	 
	        AlertDialog.Builder builder =
	                new AlertDialog.Builder(getActivity());
	 
	        builder.setTitle(title)
	           .setSingleChoiceItems(list,-1, new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int item) {
	                    Log.i("SelectionDialog", "Option Selected: " + list[item]);
	                    selection = list[item];	//update the option selected
	                }
	            }).setPositiveButton(msgInButtonPos, new DialogInterface.OnClickListener()  {
		               public void onClick(DialogInterface dialog, int id) {
		            	   
		                    Log.i("SelectionDialog", "Positive button pressed: " + selection);

		                    	callingActivity.onUserSelectValue(selection);
		                        dialog.cancel();
		                   }
		               })
		        .setNegativeButton(msgInButtonNeg, new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int id) {
		                        Log.i("SelectionDialog", "Negative button pressed");
		                        dialog.cancel();
		                   }
		               });

	 
	        return builder.create();
	    }
	    
		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			callingActivity = (MainActivity) getActivity();
		}
	}
