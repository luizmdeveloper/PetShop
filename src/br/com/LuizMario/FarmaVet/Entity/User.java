package br.com.LuizMario.FarmaVet.Entity;

public class User implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String login;
	private String passoword;
	
	public User (){
		
	}

	public User(int id, String nome, String login, String passoword) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.passoword = passoword;
	}

	public User(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassoword() {
		return passoword;
	}

	public void setPassoword(String passoword) {
		this.passoword = passoword;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	


}
