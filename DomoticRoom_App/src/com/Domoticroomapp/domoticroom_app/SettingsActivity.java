//package com.Domoticroomapp.domoticroom_app;
//
//import android.app.Activity;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
//import android.os.Bundle;
//import android.app.Activity;
//import android.os.Bundle;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.lang.reflect.Method;
//import java.util.UUID;
//
//import com.Blackrondonapps.mybluetooth.R;
//
//  
//  
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothSocket;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Handler;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
////import android.widget.Toast;
//
//public class SettingsActivity extends Activity{
//	
//	/*************************BLUETOOTH******************************/
//	private static final String TAG = "bluetooth2";
//    
//	  Button btnOn, btnOff;
//	  TextView txtArduino;
//	  Handler h;
//	    
//	  final int RECIEVE_MESSAGE = 1;        // Status  for Handler
//	  private BluetoothAdapter btAdapter = null;
//	  private BluetoothSocket btSocket = null;
//	  private StringBuilder sb = new StringBuilder();
//	   
//	  private ConnectedThread mConnectedThread;
//	    
//	  // SPP UUID service
//	  private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
//	  
//	  // MAC-address of Bluetooth module (you must edit this line)
//	  private static String address = "00:15:FF:F3:9E:40";
//	  /***************************************/  
//	
//	@Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //Toast.makeText(this, "onCreate/Help", Toast.LENGTH_SHORT).show();
//        setContentView(R.layout.activity_settings);
//        
//        
//		Bundle intentCapture = getIntent().getExtras(); // object to receive information from activity main
//		int fragmentToSet = intentCapture.getInt("paramFragmSet");
//		
//		FragmentSettingsBluetooth fragmentSettingsBluetooth = new FragmentSettingsBluetooth();
//		FragmentSettingsCustomize fragmentSettingsCustomize = new FragmentSettingsCustomize();
//	
//		FragmentManager fragmentManager = getFragmentManager();
//		FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
//		//fragmentTransaction.setTransition(android.app.FragmentTransaction.TRANSIT_NONE);
//		if(fragmentToSet == R.layout.fragment_settings_bluetooth){
//			fragmentTransaction.add(android.R.id.content, fragmentSettingsBluetooth).commit();
//		}else{
//			fragmentTransaction.add(android.R.id.content, fragmentSettingsCustomize).commit();
//		}
//		
//		
//		
//	
//		/*************************BLUETOOTH******************************/
//	
//		  
//	    btnOn = (Button) findViewById(R.id.btnOn);                  // button LED ON
//	    btnOff = (Button) findViewById(R.id.btnOff);                // button LED OFF
//	    txtArduino = (TextView) findViewById(R.id.txtArduino);      // for display the received data from the Arduino
//	     
//	    h = new Handler() {
//	        public void handleMessage(android.os.Message msg) {
//	            switch (msg.what) {
//	            case RECIEVE_MESSAGE:                                                   // if receive massage
//	                byte[] readBuf = (byte[]) msg.obj;
//	                String strIncom = new String(readBuf, 0, msg.arg1);                 // create string from bytes array
//	                sb.append(strIncom);                                                // append string
//	                int endOfLineIndex = sb.indexOf("\r\n");                            // determine the end-of-line
//	                if (endOfLineIndex > 0) {                                            // if end-of-line,
//	                    String sbprint = sb.substring(0, endOfLineIndex);               // extract string
//	                    sb.delete(0, sb.length());                                      // and clear
//	                    txtArduino.setText("Data from Arduino: " + sbprint);            // update TextView
//	                    btnOff.setEnabled(true);
//	                    btnOn.setEnabled(true); 
//	                }
//	                //Log.d(TAG, "...String:"+ sb.toString() +  "Byte:" + msg.arg1 + "...");
//	                break;
//	            }
//	        };
//	    };
//	      
//	    btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
//	    checkBTState();
//	  
//	}
//	
//}
//

package com.Domoticroomapp.domoticroom_app;
import BluetoothPackage_SE.InterfaceCommunication;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;
  
  
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
  
public class SettingsActivity extends Activity  implements InterfaceKeyboard{
  
  private static final String TAG = "bluetooth2";


    
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  
    setContentView(R.layout.activity_settings);
  


	Bundle intentCapture = getIntent().getExtras(); // object to receive information from activity main
	int fragmentToSet = intentCapture.getInt("paramFragmSet");
	View view = getCurrentFocus();	//get the current view
	FragmentSettingsBluetooth fragmentSettingsBluetooth = new FragmentSettingsBluetooth();
	FragmentSettingsCustomize fragmentSettingsCustomize = new FragmentSettingsCustomize();
	
	FragmentManager fragmentManager = getFragmentManager();
	FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.setTransition(android.app.FragmentTransaction.TRANSIT_NONE);
	if(fragmentToSet == R.layout.fragment_settings_bluetooth){
		fragmentTransaction.add(android.R.id.content, fragmentSettingsBluetooth).commit();
	}else{
		fragmentTransaction.add(android.R.id.content, fragmentSettingsCustomize).commit();
	}


	    
		
    }

@Override
protected void onStart() {
	// TODO Auto-generated method stub
    //btnKey1 = (Button) findViewById(R.id.)
	super.onStart();
}

@Override
public void pressedKey(char charTosend) {
	// TODO Auto-generated method stub
}

@Override
public void writtenString(String stringsTosend) {
	// TODO Auto-generated method stub
}


}
