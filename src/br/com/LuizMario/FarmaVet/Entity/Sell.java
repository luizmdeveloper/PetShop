package br.com.LuizMario.FarmaVet.Entity;

import java.sql.Date;
import java.util.ArrayList;

public class Sell implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1549152591461709602L;
	private int id;
	private Date data;
	private User vendedor;
	private Float total;
	private ArrayList<SellItem> itens = new ArrayList<SellItem>();
	
	
	public Sell() {
		
	}

	public Sell(int id, Date data, User vendedor, Float total, ArrayList<SellItem> itens) {
		super();
		this.id = id;
		this.data = data;
		this.vendedor = vendedor;
		this.total = total;
		this.itens = itens;
	}

	public Sell(int id) {
		super();
		this.id = id;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public User getVendedor() {
		return vendedor;
	}

	public void setVendedor(User vendedor) {
		this.vendedor = vendedor;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
	
	public ArrayList<SellItem> getItens() {
		return itens;
	}

	public void setItens(ArrayList<SellItem> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		Sell other = (Sell) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
}
