package de.dhbw.shake_it_app.data.operator;

import de.dhbw.shake_it_app.data.DataProvider;
import de.dhbw.shake_it_app.data.model.Session;

public class DataOperator {
	
	public int returnCurrLocationIndex(int clubID){
		double cumulatedValues = 0;
		int amountValues = 0;
		clubID = 3665004;
		Session[] sessions = (Session[]) DataProvider.get().getModel(DataProvider.Session, "filter=locationID&value="+clubID);
		amountValues = sessions.length;
		for(int i=0; i<amountValues;i++){
			cumulatedValues=+sessions[i].getCurrentShakeIndex();
		}
		return (int) Math.round(cumulatedValues/amountValues);
	}

}
