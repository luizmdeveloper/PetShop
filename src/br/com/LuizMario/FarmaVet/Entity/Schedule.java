package br.com.LuizMario.FarmaVet.Entity;

import java.sql.Date;

public class Schedule implements java.io.Serializable {

	private static final long serialVersionUID = -8172124964941924052L;
	private int id;
	private Animal idanimal;
	private Date data;
	private Float total;
	private ScheduleType type;
	
	public Schedule() {
		
	}

	public Schedule(int id) {
		super();
		this.id = id;
	}

	public Schedule(int id, Animal idanimal, Date data, Float total, ScheduleType type) {
		super();
		this.id = id;
		this.idanimal = idanimal;
		this.data = data;
		this.total = total;
		this.type = type;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Animal getIdanimal() {
		return idanimal;
	}


	public void setIdanimal(Animal idanimal) {
		this.idanimal = idanimal;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}


	public ScheduleType getType() {
		return type;
	}

	public void setType(ScheduleType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (id != other.id)
			return false;
		return true;
	}




}
