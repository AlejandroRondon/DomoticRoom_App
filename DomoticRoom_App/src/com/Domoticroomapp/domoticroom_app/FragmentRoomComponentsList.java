package com.Domoticroomapp.domoticroom_app;

import java.util.ArrayList;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentRoomComponentsList extends Fragment{
	//private RoomComponent[] components;
	private ArrayList<RoomComponent> components;
	private ComponentsAdapter componentsAdapter;
	public FragmentRoomComponentsList(ArrayList<RoomComponent> components) {
		// TODO Auto-generated constructor stub
		this.components = components;
	}
	public FragmentRoomComponentsList() {
		// TODO Auto-generated constructor stub
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
	
	
	public boolean AddArrayItems(ArrayList<RoomComponent> componentsToadd){
		int i,j;
		boolean found=false;
		if((componentsToadd.size() == 0)||(componentsToadd == null) ){
			Log.e("FRAMENTROOMCOMPONENTSLIST","empty array of components");
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
					Log.i("FRAMENTROOMCOMPONENTSLIST","component found");
					found = false;
				}else{
					Log.i("FRAMENTROOMCOMPONENTSLIST","component not found");
					AddItem(componentsToadd.get(i));
				}

			}
		}
		return true;
	}
	public void AddItem(RoomComponent componentToadd){
		components.add(componentToadd);
		componentsAdapter.notifyDataSetChanged();
	}
    public ArrayList<RoomComponent> getSelectedItems(View view) {
        //Obtengo los elementos seleccionados de mi lista
        
    	SparseBooleanArray seleccionados = lstListado.getCheckedItemPositions();
    	ArrayList<RoomComponent> auxArray = new ArrayList<RoomComponent>();
        if(seleccionados==null || seleccionados.size()==0){
            //Si no había elementos seleccionados...
            Log.e("FRAGMENTROOMCOMPONENTLIST","No hay elementos seleccionados");
            return null;
        }else{
            //si los había, miro sus valores
 
            //Esto es para ir creando un mensaje largo que mostraré al final
            StringBuilder resultado=new StringBuilder();
            resultado.append("Se eliminarán los siguientes elementos:\n");
 
            //Recorro my "array" de elementos seleccionados
            final int size=seleccionados.size();
            for (int i=0; i<size; i++) {
                //Si valueAt(i) es true, es que estaba seleccionado
                if (seleccionados.valueAt(i)) {
                    //en keyAt(i) obtengo su posición
                	auxArray.add(components.get(i));
                    resultado.append("component "+seleccionados.keyAt(i)+ " added to array \n");
                }
    
               // lstListado.clearChoices();
            }
            Log.e("FRAGMENTROOMCOMPONENTLIST",resultado.toString());
            return auxArray;
          
        }
    }
    
    public void deleteSelectedItems(View view) {
        //Obtengo los elementos seleccionados de mi lista
        SparseBooleanArray seleccionados = lstListado.getCheckedItemPositions();
    
        if(seleccionados==null || seleccionados.size()==0){
            //Si no había elementos seleccionados...
            Log.e("FRAGMENTROOMCOMPONENTLIST","No hay elementos seleccionados");
        }else{
            //si los había, miro sus valores
 
            //Esto es para ir creando un mensaje largo que mostraré al final
            StringBuilder resultado=new StringBuilder();
            resultado.append("Se eliminarán los siguientes elementos:\n");
 
            //Recorro my "array" de elementos seleccionados
            final int size=seleccionados.size();
            for (int i=0; i<size; i++) {
                //Si valueAt(i) es true, es que estaba seleccionado
                if (seleccionados.valueAt(i)) {
                    //en keyAt(i) obtengo su posición
                    resultado.append("El elemento "+seleccionados.keyAt(i)+" estaba seleccionado\n");
                    components.remove(seleccionados.keyAt(i));
                    lstListado.clearChoices();
                }
                componentsAdapter.notifyDataSetChanged();
            }
            Log.e("FRAGMENTROOMCOMPONENTLIST",resultado.toString());
          
        }
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
