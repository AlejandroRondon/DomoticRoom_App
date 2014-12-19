package utilitiesApps;

import java.util.ArrayList;

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
	
	


	
}
