package br.com.LuizMario.FarmaVet.Entity;

public class Product implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8330010871997799451L;
	private int id;
	private String nome;
	private float preco;
	private int quantidade;
	
	public Product() {
	
	}

	public Product(int id) {
		super();
		this.id = id;
	}

	public Product(int id, String nome, float preco, int quantidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public float getPreco() {
		return preco;
	}


	public void setPreco(float preco) {
		this.preco = preco;
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
