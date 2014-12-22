package com.Domoticroomapp.domoticroom_app;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentRoomList extends Fragment{
	//private RoomComponent[] components;
	private ArrayList<RoomComponent> components;
	private ComponentsAdapter componentsAdapter;
	public FragmentRoomList(ArrayList<RoomComponent> components) {
		// TODO Auto-generated constructor stub
		this.components = components;
	}

	private ListView lstListado;

	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_roomcomponentslist, container, false);
	}
	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);

		lstListado = (ListView)getView().findViewById(R.id.LstListado);
		lstListado.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
		componentsAdapter = new ComponentsAdapter(this);
		lstListado.setAdapter(componentsAdapter);
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
			View item = inflater.inflate(R.layout.listitem_roomcomponent, null);

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
