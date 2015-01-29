package Bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.util.Log;

class ConnectedThread extends Thread {
    final InputStream mmInStream;
    final OutputStream mmOutStream;
        
	 static final String TAG = "thread tag";
	 final int RECIEVE_MESSAGE = 1;        // Status  for Handler
	 Handler h;

    public ConnectedThread(BluetoothSocket socket,Handler h) {
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
  
        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) { }
  
        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }
  
    public void run() {
        byte[] buffer = new byte[256];  // buffer store for the stream
        int bytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs
        while (true) {
            try {
                // Read from the InputStream
                bytes = mmInStream.read(buffer);        // Get number of bytes and message in "buffer"
                try{
                	h.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();     // Send to message queue Handler
                }catch(NullPointerException np){
                	Log.i("putiado", "hp!");
                	//break;
                }
            } catch (IOException e) {
                break;
            }
        }
    }
  
    /* Call this from the main activity to send data to the remote device */
    public void write(String message) {
        Log.d(TAG, "...Data to send: " + message + "...");
        byte[] msgBuffer = message.getBytes();
        try {
            mmOutStream.write(msgBuffer);
        } catch (IOException e) {
            Log.d(TAG, "...Error data send: " + e.getMessage() + "...");     
          }
    }
}