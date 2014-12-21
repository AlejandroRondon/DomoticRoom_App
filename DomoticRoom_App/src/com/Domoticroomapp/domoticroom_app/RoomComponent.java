package com.Domoticroomapp.domoticroom_app;



	public class RoomComponent
	{
	    private String IDcomponent;
	    private String DesComponent;
	    private String TitleComponent;
	    private int    ImageComponent;
	 
	    public RoomComponent(String TitleComponent,String IDcomponent, String DesComponent, int ImageComponent ){
	        this.IDcomponent = IDcomponent;
	        this.DesComponent = DesComponent;
	        this.TitleComponent = TitleComponent;
	        this.ImageComponent = ImageComponent;
	    }

		public String getIDcomponent() {
			return IDcomponent;
		}

		public String getDesComponent() {
			return DesComponent;
		}

		public int getImageComponent() {
			return ImageComponent;
		}

		public String getTitleComponent() {
			return TitleComponent;
		}

		public void setIDcomponent(String iDcomponent) {
			IDcomponent = iDcomponent;
		}

		public void setDesComponent(String desComponent) {
			DesComponent = desComponent;
		}

		public void setTitleComponent(String titleComponent) {
			TitleComponent = titleComponent;
		}

		public void setImageComponent(int imageComponent) {
			ImageComponent = imageComponent;
		}
		
		
		
	 
}
