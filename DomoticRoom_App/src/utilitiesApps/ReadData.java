package utilitiesApps;


import android.content.Context;
import android.widget.Toast;


public class ReadData {
	ValNumber validation = new ValNumber();
	String readNum;

	public float readFloat(Context context,String str){	//msg is a shot message to show before to take a number

			readNum =  str;
			if(!str.equals("")){
				if((validation.isFloat(readNum)) == true){
					return Float.parseFloat(readNum);
				}else{
					Toast.makeText(context, "Error: The value entered in isn't a valid number", Toast.LENGTH_SHORT).show();
					return 0;
				}
			}else{
				Toast.makeText(context, "Empty Field", Toast.LENGTH_SHORT).show();
				return 0;
			}
	
	
	}
	
	public float readFloatRange(Context context,String str,float Limit_inf,float Limit_sup){	//msg is a shot message to show before to take a number
	
		readNum =  str;
		if(!str.equals("")){
			if((validation.isFloat(readNum)) == true){
				float Number = Float.parseFloat(readNum);
				if(Number>=Limit_inf && Number <= Limit_sup){
					return Float.parseFloat(readNum);
				}else{
					Toast.makeText(context, "Error: Number out of range", Toast.LENGTH_SHORT).show();
					return Limit_inf-1;
				}
			}else{
				Toast.makeText(context, "Error: The value entered in isn't a valid number", Toast.LENGTH_SHORT).show();
				return Limit_sup+1;
			}
		}else{
			Toast.makeText(context, "Empty Field", Toast.LENGTH_SHORT).show();
			return Limit_sup+2;
		}


}
	

	public int readInt(Context context,String str){
			readNum = str;
			if(!str.equals("")){
				if((validation.isInteger(readNum)) == true){
					return Integer.parseInt(readNum);
				}else{
					Toast.makeText(context, "Error: The value entered in isn't a valid number", Toast.LENGTH_SHORT).show();
					return 0;
				}
			}else{
					Toast.makeText(context, "Empty Field", Toast.LENGTH_SHORT).show();
					return 0;
			}
		


	}

}
