package com.Domoticroomapp.domoticroom_app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
 
public class FragmentRoomComponentsList extends Fragment {
 
    private RoomComponent[] component =
            new RoomComponent[]{
                new RoomComponent("Persona 1", "Asunto del correo 1", "Texto del correo 1"),
                new RoomComponent("Persona 2", "Asunto del correo 2", "Texto del correo 2"),
                new RoomComponent("Persona 3", "Asunto del correo 3", "Texto del correo 3"),
                new RoomComponent("Persona 4", "Asunto del correo 4", "Texto del correo 4"),
                new RoomComponent("Persona 5", "Asunto del correo 5", "Texto del correo 5")};
 
    private ListView lstListado;
 
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
 
        return inflater.inflate(R.layout.fragment_roomcomponentslist, container, false);
    }
 
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
 
        lstListado = (ListView)getView().findViewById(R.id.LstListado);
 
        lstListado.setAdapter(new AdaptadorCorreos(this));
    }
 
    class AdaptadorCorreos extends ArrayAdapter<RoomComponent> {
 
            Activity context;
 
            AdaptadorCorreos(Fragment context) {
                super(context.getActivity(), R.layout.listitem_roomcomponent, component);
                this.context = context.getActivity();
            }
 
            public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_roomcomponent, null);
 
            TextView lblDe = (TextView)item.findViewById(R.id.LblDe);
            lblDe.setText(component[position].getDe());
 
            TextView lblAsunto = (TextView)item.findViewById(R.id.LblAsunto);
            lblAsunto.setText(component[position].getAsunto());
 
            return(item);
        }
        }
}