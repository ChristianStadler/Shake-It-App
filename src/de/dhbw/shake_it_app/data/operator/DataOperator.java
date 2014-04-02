package de.dhbw.shake_it_app.data.operator;

import de.dhbw.shake_it_app.data.DataProvider;
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
	
	public int returnCurrLocationIndex(int clubID){
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
	
	public int returnOverallLocationIndex(int clubID){
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
	
	public int returnOverallUserIndex(int userID){
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

}
