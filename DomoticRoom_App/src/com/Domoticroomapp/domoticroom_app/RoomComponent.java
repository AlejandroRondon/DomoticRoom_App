package com.Domoticroomapp.domoticroom_app;



	public class RoomComponent
	{
	    private String de;
	    private String asunto;
	    private String texto;
	 
	    public RoomComponent(String de, String asunto, String texto){
	        this.de = de;
	        this.asunto = asunto;
	        this.texto = texto;
	    }
	 
	    public String getDe(){
	        return de;
	    }
	 
	    public String getAsunto(){
	        return asunto;
	    }
	 
	    public String getTexto(){
	        return texto;
	    }
	}
