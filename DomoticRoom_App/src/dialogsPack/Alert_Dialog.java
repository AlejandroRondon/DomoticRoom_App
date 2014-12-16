package dialogsPack;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import java.lang.String;

public class Alert_Dialog extends DialogFragment {
	String title,msg,msgInButton;
	public Alert_Dialog(String title,String msg,String msgInButton){
		this.title=title;
		this.msg=msg;
		this.msgInButton=msgInButton;
	}
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
 
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
 
        builder.setMessage(msg)
               .setTitle(title)
               .setPositiveButton(msgInButton, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.cancel();
                   }
               });
 
        return builder.create();
    }
}
