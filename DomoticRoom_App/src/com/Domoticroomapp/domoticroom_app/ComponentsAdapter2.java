package com.Domoticroomapp.domoticroom_app;
//package com.Domoticroomapp.domoticroom_app;
//
//import java.util.ArrayList;
//
//import android.app.Activity;
//import android.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//class ComponentsAdapter extends ArrayAdapter<RoomComponent> {
//
//	Activity context;
//	ArrayList<RoomComponent> components;
//	ComponentsAdapter(Fragment context,ArrayList<RoomComponent> components) {
//		super(context.getActivity(), R.layout.listitem_roomcomponent);
//		this.context = context.getActivity();
//		this.components = components;
//	}
//
//	public View getView(int position, View convertView, ViewGroup parent) {
//		LayoutInflater inflater = context.getLayoutInflater();
//		View item = inflater.inflate(R.layout.listitem_roomcomponent, null);
//
//		TextView TitleComponent = (TextView)item.findViewById(R.id.tTitleComponent);
//		TitleComponent.setText(components.get(position).getTitleComponent());
//
//		TextView IDComponent = (TextView)item.findViewById(R.id.tIDComponent);
//		IDComponent.setText(components.get(position).getIDcomponent());
//
//		TextView DesComponent = (TextView)item.findViewById(R.id.tDesComponent);
//		DesComponent.setText(components.get(position).getDesComponent());
//
//		ImageView ImageComponent = (ImageView)item.findViewById(R.id.ivImageComponent);
//		ImageComponent.setImageResource(components.get(position).getImageComponent());
//
//
//
//		return(item);
//	}
//}
