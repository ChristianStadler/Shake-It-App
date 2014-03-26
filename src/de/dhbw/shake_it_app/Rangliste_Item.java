package de.dhbw.shake_it_app;

public class Rangliste_Item {

		 
		private String username;
		private String imageName;
		private int avgIndexUser;
	 
	    public String getUsername() {
	        return username;
	    }
	 
	    public void setUsername(String username) {
	        this.username = username;
	    }
	 
	    public int getAvgIndexUser() {
	        return avgIndexUser;
	    } 
	 
	    public void setAvgIndexUser(int avgIndexUser) {
	        this.avgIndexUser = avgIndexUser;
	    }
	    
	    public String getImage(){

			return imageName;
	    	
	    }
	    
	    public void setImage(String imageName){
	    	this.imageName = imageName;
	    }
	 
	 
	    @Override
	    public String toString() {
	        return "[ username=" + username + ", avgIndexUser=" + 
	        		avgIndexUser +"]";
	    }
	}

