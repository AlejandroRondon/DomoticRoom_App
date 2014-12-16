package dialogsPack;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

	public class SelectionMulti_Dialog extends DialogFragment {
		String title;
		String list[];
		String msgInButton;
		public SelectionMulti_Dialog(String title,String msgInButton,String list[]){
			this.title=title;
			this.list= list;
			this.msgInButton=msgInButton;
		}
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	 
	     
	 
	        AlertDialog.Builder builder =
	                new AlertDialog.Builder(getActivity());
	 
	        builder.setTitle(title).setPositiveButton(msgInButton, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            })
	        .setMultiChoiceItems(list, null,
	            new DialogInterface.OnMultiChoiceClickListener() {
	            public void onClick(DialogInterface dialog, int item, boolean isChecked) {
	                 Log.i("Dialogos", "Opción elegida: " + list[item]);
	           }
	    });
	 
	        return builder.create();
	    }
	}
