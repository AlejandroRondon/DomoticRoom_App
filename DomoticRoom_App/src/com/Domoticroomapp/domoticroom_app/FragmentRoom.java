package com.Domoticroomapp.domoticroom_app;

import java.util.ArrayList;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import utilitiesApps.FrameManager;
import fragmentComponents.*;
public class FragmentRoom extends Fragment{
	//private RoomComponent[] components;
	private ArrayList<RoomComponent> components = new ArrayList<RoomComponent>();
	private ArrayList<android.support.v4.app.Fragment> fragmentComponents = new ArrayList<android.support.v4.app.Fragment>();
	private ComponentsAdapter componentsAdapter;
//	private String roomName;
	public FragmentRoom() {
		// TODO Auto-generated constructor stub
		
		/*Probe component*/
		RoomComponent room = new RoomComponent("Empty", "Empty", "Empty", R.drawable.ic_empty);
		components.add(room);
	}
	public FragmentRoom(ArrayList<RoomComponent> components) {
		// TODO Auto-generated constructor stub
		this.components = components;
	}

	private ListView lstListado;

	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_room, container, false);
	}
	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		

		
		lstListado = (ListView)getView().findViewById(R.id.LstListadoroom);
		componentsAdapter = new ComponentsAdapter(this);
		lstListado.setAdapter(componentsAdapter);

	}
	public boolean AddArrayItems(ArrayList<RoomComponent> componentsToadd){
		int i,j;
		boolean found=false;
		if((componentsToadd.size() == 0)||(componentsToadd == null) ){
			Log.e("FRAMENTROOM","empty array of components");
			return false;
		}else{
			for(i=0;i<componentsToadd.size();i++){
				for(j=0;j<components.size();j++){
					if(components.get(j).getIDcomponent().equals(componentsToadd.get(i).getIDcomponent())){
						found=true;
						break;
					}
				}
				
				if(found){
					Log.i("FRAMENTROOM","component found");
					found = false;
				}else{
					Log.i("FRAMENTROOM","component not found");
					AddItem(componentsToadd.get(i));
				}

			}
		}
		return true;
	}
	public void AddItem(RoomComponent componentToadd){
		android.support.v4.app.Fragment framentToadd;
		String prefix;
		components.add(componentToadd);
		prefix = FrameManager.getPrefix(componentToadd.getIDcomponent());
		if(prefix.equals("LI")){
			framentToadd = new LightFragment(componentToadd.getIDcomponent());
//			components.add(new RoomComponent("Light", BESComponents.get(i), "Component to manage one light",R.drawable.ic_light));
		}else if(prefix.equals("WI")){
			framentToadd = new WindowFragment(componentToadd.getIDcomponent());
//			components.add(new RoomComponent("Window",BESComponents.get(i), "Component to manage one window",R.drawable.ic_windows));
		}else if(prefix.equals("DO")){
			framentToadd = new DoorFragment(componentToadd.getIDcomponent());
//			components.add(new RoomComponent("Door", BESComponents.get(i), "Component to manage a door security",R.drawable.ic_door));
		}else if(prefix.equals("TE")){
			framentToadd = new TemperatureFragment(componentToadd.getIDcomponent());
//			components.add(new RoomComponent("Temperature", BESComponents.get(i), "Component to manage the temperature",R.drawable.ic_temperature));
		}else if(prefix.equals("SW")){
			framentToadd = new SwitchFragment(componentToadd.getIDcomponent());
//			components.add(new RoomComponent("Switch",BESComponents.get(i), "Component to manage one switch",R.drawable.ic_switch));
		}else if(prefix.equals("MO")){
			framentToadd = new MotorFragment(componentToadd.getIDcomponent());
//			components.add(new RoomComponent("Motor", BESComponents.get(i), "Component to manage one motor",R.drawable.ic_motor));
		}else if(prefix.equals("SE")){
			framentToadd = new ServoFragment(componentToadd.getIDcomponent());
//			components.add(new RoomComponent("Servomotor", BESComponents.get(i), "Component to manage one servomotor",R.drawable.ic_servo));
		}else if(prefix.equals("LC")){
			framentToadd = new LcdFragment(componentToadd.getIDcomponent());
//			components.add(new RoomComponent("LCD", BESComponents.get(i), "Component to manage the LCD display in the master",R.drawable.ic_lcd));
		}else{
			framentToadd = new LightFragment(componentToadd.getIDcomponent());
		}
		fragmentComponents.add(framentToadd);
		Log.d("FRAGMENTROOM", "Component fragment created");
		componentsAdapter.notifyDataSetChanged();
		
	}

    
    

	/*-------------------------------------------------------------------------------------------------------*/
	class ComponentsAdapter extends ArrayAdapter<RoomComponent> {

		Activity context;

		ComponentsAdapter(Fragment context) {
			super(context.getActivity(), R.layout.listitem_roomcomponent, components);
			this.context = context.getActivity();
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.listitem_room, null);

			TextView TitleComponent = (TextView)item.findViewById(R.id.tTitleComponent);
			TitleComponent.setText(components.get(position).getTitleComponent());

			TextView IDComponent = (TextView)item.findViewById(R.id.tIDComponent);
			IDComponent.setText(components.get(position).getIDcomponent());

			TextView DesComponent = (TextView)item.findViewById(R.id.tDesComponent);
			DesComponent.setText(components.get(position).getDesComponent());

			ImageView ImageComponent = (ImageView)item.findViewById(R.id.ivImageComponent);
			ImageComponent.setImageResource(components.get(position).getImageComponent());



			return(item);
		}
	}
}
