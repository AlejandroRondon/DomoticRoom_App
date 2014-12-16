package dialogsPack;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

public class Confirmation_Dialog extends DialogFragment {
	String title,msg,msgInButtonPos,msgInButtonNeg;
	public Confirmation_Dialog(String title,String msg,String msgInButtonPos,String msgInButtonNeg){
		this.title=title;
		this.msg=msg;
		this.msgInButtonPos=msgInButtonPos;
		this.msgInButtonNeg=msgInButtonNeg;
	}
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	 
	        AlertDialog.Builder builder =
	                new AlertDialog.Builder(getActivity());
	 
	        builder.setMessage(msg)
	        .setTitle(title)
	        .setPositiveButton(msgInButtonPos, new DialogInterface.OnClickListener()  {
	               public void onClick(DialogInterface dialog, int id) {
	                    Log.i("Dialogos", "Confirmacion Aceptada.");
	                    	
	                        dialog.cancel();
	                   }
	               })
	        .setNegativeButton(msgInButtonNeg, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                        Log.i("Dialogos", "Confirmacion Cancelada.");
	                        dialog.cancel();
	                   }
	               });
	 
	        return builder.create();
	    }
	
}
