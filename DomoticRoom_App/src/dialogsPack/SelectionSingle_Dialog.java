package dialogsPack;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

	public class SelectionSingle_Dialog extends DialogFragment {
		String title;
		String list[];
		public SelectionSingle_Dialog(String title,String list[]){
			this.title=title;
			this.list= list;
		}
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	 
	     
	 
	        AlertDialog.Builder builder =
	                new AlertDialog.Builder(getActivity());
	 
	        builder.setTitle(title)
	           .setSingleChoiceItems(list,-1, new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int item) {
	                    Log.i("Dialogos", "Opción elegida: " + list[item]);
	                }
	            });
	 
	        return builder.create();
	    }
	}
