package de.dhbw.shake_it_app.data.operator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import de.dhbw.shake_it_app.data.DataProvider;
import de.dhbw.shake_it_app.data.KeyValue;
import de.dhbw.shake_it_app.data.model.Session;
import de.dhbw.shake_it_app.data.model.User;

public class DataOperator {
	
	private static DataOperator dataOperator;
	
	private DataOperator(){
		
	}
	
	public static DataOperator get(){
		if(dataOperator == null){
			dataOperator = new DataOperator();
		}
		return dataOperator;
	}
	
	public int returnCurrLocationIndex(long clubID){
		double cumulatedValues = 0;
		int amountValues = 0;
		Session[] sessions = (Session[]) DataProvider.get().getModel(DataProvider.Session, "filter=locationID&value="+clubID);
		for(int i=0; i<sessions.length;i++){
			if(sessions[i].getIsActive()){
				cumulatedValues+=sessions[i].getCurrentShakeIndex();
				amountValues++;
			}
		}
		return (int) Math.round(cumulatedValues/amountValues);
	}
	
	public int returnOverallLocationIndex(long clubID){
		double cumulatedValues = 0;
		int amountValues = 0;
		Session[] sessions = (Session[]) DataProvider.get().getModel(DataProvider.Session, "filter=locationID&value="+clubID);
		for(int i=0; i<sessions.length;i++){
			if(!sessions[i].getIsActive()){
				cumulatedValues+=sessions[i].getCurrentShakeIndex();
				amountValues++;
			}
		}
		return (int) Math.round(cumulatedValues/amountValues);
	}
	
	public int[] returnLocationIndices(long clubID){
		double cumulatedValues = 0;
		int amountValues = 0;
		int[] clubIndices = new int[2];
		Session[] sessions = (Session[]) DataProvider.get().getModel(DataProvider.Session, "filter=locationID&value="+clubID);
		for(int i=0; i<sessions.length;i++){
			if(!sessions[i].getIsActive()){
				cumulatedValues+=sessions[i].getCurrentShakeIndex();
				amountValues++;
			}
		}
		clubIndices[0] = (int) Math.round(cumulatedValues/amountValues);
		cumulatedValues = 0;
		amountValues = 0;
		for(int i=0; i<sessions.length;i++){
			if(sessions[i].getIsActive()){
				cumulatedValues+=sessions[i].getCurrentShakeIndex();
				amountValues++;
			}
		}
		clubIndices[1] = (int) Math.round(cumulatedValues/amountValues);
		return clubIndices;
	}
	
	public int returnOverallUserIndex(long userID){
		double cumulatedValues = 0;
		int amountValues = 0;
		Session[] sessions = (Session[]) DataProvider.get().getModel(DataProvider.Session, "filter=userID&value="+userID);
		for(int i=0; i<sessions.length;i++){
			if(!sessions[i].getIsActive()){
				cumulatedValues+=sessions[i].getCurrentShakeIndex();
				amountValues++;
			}
		}
		return (int) Math.round(cumulatedValues/amountValues);
		
	}
	
	public void saveRegisterData(String mail, String username, String password){
		User user = new User(0, username, mail, md5(password), 0);
		KeyValue.getInstance().setUser(DataProvider.get().createModel(DataProvider.User, user));
	}
	
	public User returnUser(long userID){
		User[] user = (User[]) DataProvider.get().getModel(DataProvider.User, "id="+userID);
		return user[0];
	}
	
	public String md5(String s) {
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();

	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i=0; i<messageDigest.length; i++)
	            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
	        return hexString.toString();

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";
	}

}
