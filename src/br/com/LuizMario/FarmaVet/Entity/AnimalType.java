package br.com.LuizMario.FarmaVet.Entity;

public enum AnimalType {
	
	DOG("Dog"),CAT("Cat");
	private String descrition;
	
	AnimalType (String descrition) {
		this.descrition = descrition;
	}
	
	public String getDescrition (){
		return this.descrition;
	}

}
