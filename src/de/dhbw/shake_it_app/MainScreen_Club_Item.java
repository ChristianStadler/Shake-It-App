package de.dhbw.shake_it_app;

public class MainScreen_Club_Item {
	
	 private String clubName;
	    private int aktClubIndex;
	    private int avgClubIndex;
	    private long clubItem;
	 
	    public String getClubName() {
	        return clubName;
	    }
	 
	    public void setClubName(String clubName) {
	        this.clubName = clubName;
	    }
	 
	    public int getAktClubIndex() {
	        return aktClubIndex;
	    }
	 
	    public void setAktClubIndexe(int aktClubIndex) {
	        this.aktClubIndex = aktClubIndex;
	    }
	 
	    public int getAvgClubIndex() {
	        return avgClubIndex;
	    }
	 
	    public void setAvgClubIndex(int avgClubIndex) {
	        this.avgClubIndex = avgClubIndex;
	    }
	 
	    @Override
	    public String toString() {
	        return "[ Club =" + clubName + ", aktueller Index =" + 
	        		aktClubIndex + " , durchschnittl. Index =" + avgClubIndex + "]";
	    }
		public long getClubId() {
			return clubItem;
		}

		public void setClubId(long id) {
			this.clubItem = id;
			// TODO Auto-generated method stub
			
		}

}
