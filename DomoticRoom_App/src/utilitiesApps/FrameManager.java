package utilitiesApps;

import java.util.ArrayList;

import com.Domoticroomapp.domoticroom_app.*;

import android.content.res.Resources;
import android.util.Log;

public class FrameManager {
	int numberComponents;
	String frame;
	boolean extractionStatus;
	ArrayList<String> BESComponents = new ArrayList<String>();

	public FrameManager(){
	}

	private boolean checkFrameLenght(){
		Log.v("FrameManager", "checkFrameLenght");
		Log.v("FrameManager", "Frame: " + frame);
		Log.v("FrameManager", "Frame.Lenght: " + frame.length());
		if(frame.length()%4 == 0){
			numberComponents= frame.length() / 4 ;
			Log.v("FrameManager", "number components: " + numberComponents);
			return true;
		}
		return false;
	}

	private boolean extractComponents(){
		int i=0;
		int start,end;
		String subFrame;
		if(checkFrameLenght()){
			Log.v("FrameManager", "number components: " + numberComponents);
			for(i=0;i<numberComponents;i++){
				start=i*4;
				end= i*4+4;
				subFrame = frame.substring(start,end);
				Log.v("FrameManager", "subframe extracted: " + subFrame);
				if(i==0){
					if(!subFrame.equals("$BES")){
						Log.e("FrameManager","There isn´t start sequence " + subFrame);
						return false;
					}//$BES
				}else{
					if(!checkSintax(subFrame)){
						Log.e("FrameManager","Incorrect Sintax " + subFrame);
						return false;
					}//CheckSintax
					if(BESComponents.contains(subFrame)){
						Log.w("FrameManager","Repeated Component " + subFrame);
						// return false;
					}else{
						Log.i("FrameManager","Component added " + subFrame);
						BESComponents.add(subFrame);
					}//Contains
				}//for
			}//i==0
		}else{
			Log.e("FrameManager","Incorrect Lenght " );
			return false;
		}
		numberComponents = BESComponents.size();//update 
		return true;
	}
	private boolean checkSintax(String subFrame){
		String prefix=subFrame.substring(0,2); 
		String ID = subFrame.substring(2,4);
		ValNumber valNumber = new ValNumber();

		if((prefix == "LI") || (prefix == "WI") || (prefix == "DO") || (prefix == "TE") || (prefix == "SW") || (prefix == "MO") || (prefix == "SE") || (prefix == "LC")  ){
			Log.e("FrameManager","Incorrect Prefix " + prefix );
			return false;
		}
		if(!valNumber.isInteger(ID)){
			Log.e("FrameManager","Incorrect ID " + ID);
			return false;
		}

		return true;
	}

	public int getNumberComponents() {
		numberComponents = BESComponents.size();
		return numberComponents;
	}

	public String getFrame() {
		return frame;
	}

	public boolean getExtractionStatus() {
		return extractionStatus;
	}

	public boolean setFrame(String frame) {
		this.frame = frame;
		if(extractComponents()){
			Log.i("FrameManager","Succesfull Frame Extraction! " );
			extractionStatus = true;
			return true;
		}else{
			Log.e("FrameManager","Error Frame Extraction! " );
			BESComponents.clear();
			extractionStatus = false;
			return false;
		}
	}

	public String getPrefix(String subFrame){
		return subFrame.substring(0,2); 
	}

	public String getBESComponent(int index){
		if(extractionStatus == true){
			return BESComponents.get(index);
		}else{
			Log.e("FrameManager","Error Frame Extraction! " );
			return null;
		}
	}

	public ArrayList<RoomComponent> constructRoomComponents(){

		ArrayList<RoomComponent> components = new ArrayList<RoomComponent>();
		int i;
		String prefix;
		for(i=0;i<BESComponents.size();i++)
		{
			Log.i("FrameManager", "Constructing RoomComponents array");
			prefix = getPrefix(BESComponents.get(i));
			Log.i("FrameManager", "Prefix: "+prefix);
			if(prefix.equals("LI")){
				Log.i("FrameManager", "added");
				components.add(new RoomComponent("Light", BESComponents.get(i), "Component to manage one light",R.drawable.ic_light));
			}else if(prefix.equals("WI")){
				components.add(new RoomComponent("Window",BESComponents.get(i), "Component to manage one window",R.drawable.ic_windows));
			}else if(prefix.equals("DO")){
				components.add(new RoomComponent("Door", BESComponents.get(i), "Component to manage a door security",R.drawable.ic_door));
			}else if(prefix.equals("TE")){
				components.add(new RoomComponent("Temperature", BESComponents.get(i), "Component to manage the temperature",R.drawable.ic_temperature));
			}else if(prefix.equals("SW")){
				components.add(new RoomComponent("Switch",BESComponents.get(i), "Component to manage one switch",R.drawable.ic_switch));
			}else if(prefix.equals("MO")){
				components.add(new RoomComponent("Motor", BESComponents.get(i), "Component to manage one motor",R.drawable.ic_motor));
			}else if(prefix.equals("SE")){
				components.add(new RoomComponent("Servomotor", BESComponents.get(i), "Component to manage one servomotor",R.drawable.ic_servo));
			}else if(prefix.equals("LC")){
				components.add(new RoomComponent("LCD", BESComponents.get(i), "Component to manage the LCD display in the master",R.drawable.ic_lcd));
			}
		}


		return components;
	}



}
