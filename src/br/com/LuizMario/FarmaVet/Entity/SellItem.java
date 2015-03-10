package br.com.LuizMario.FarmaVet.Entity;

public class SellItem implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3903263170433035213L;
	private int id;
	private Sell idsell;
	private Product product;
	private int quantidade;

	public SellItem() {
		
	}

	public SellItem(int id) {
		super();
		this.id = id;
	}

	public SellItem(int id, Sell idsell, Product product, int quantidade) {
		super();
		this.id = id;
		this.idsell = idsell;
		this.product = product;
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sell getIdsell() {
		return idsell;
	}

	public void setIdsell(Sell sell) {
		this.idsell = sell;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
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
		SellItem other = (SellItem) obj;
		if (id != other.id)
			return false;
		return true;
	}



}
