package br.com.LuizMario.FarmaVet.Entity;

public enum ScheduleType {

	BANHO("Tomar Banho"),TOSA("Tosar animal"); 
	private String descrition;
	
	ScheduleType (String descrition ){
		this.descrition = descrition;
	}
	
	public String getDescrition (){
		return this.descrition;
	}
}
