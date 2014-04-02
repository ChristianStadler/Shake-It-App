package de.dhbw.shake_it_app.data.operator;

import de.dhbw.shake_it_app.data.DataProvider;
import de.dhbw.shake_it_app.data.model.Session;

public class DataOperator {
	
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

}
