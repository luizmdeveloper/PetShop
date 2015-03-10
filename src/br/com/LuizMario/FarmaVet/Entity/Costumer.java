package br.com.LuizMario.FarmaVet.Entity;

public class Costumer implements java.io.Serializable {

	private static final long serialVersionUID = 3069097471683488635L;
	private int id;
	private String name;
	private int idade;
	private String endereco;
	private int number;
	private String bairro;
	private String telefone;
	
	
	public Costumer() {
	}

	public Costumer(int id) {
		this.id = id;
	}

	public Costumer(int id, String name, int idade, String endereco, int number, String bairro, String telefone) {
		super();
		this.id = id;
		this.name = name;
		this.idade = idade;
		this.endereco = endereco;
		this.number = number;
		this.bairro = bairro;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getIdade() {
		return idade;
	}


	public void setIdade(int idade) {
		this.idade = idade;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
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
		Costumer other = (Costumer) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
