package br.com.LuizMario.FarmaVet.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.LuizMario.FarmaVet.Entity.Animal;
import br.com.LuizMario.FarmaVet.Entity.AnimalType;
import br.com.LuizMario.FarmaVet.Entity.Costumer;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class AnimalDAO implements GenericDAO<Animal> {

	private Connection con;
	
	
	public void inserir(Animal animal) throws PersistenciaException {
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "INSERT INTO animals (idconstumer,nome,idade,type,breed) VALUES (?,?,?,?,?)";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1, animal.getCostumer().getId());
			statement.setString(2, animal.getNome());
			statement.setInt(3, animal.getIdade());
			statement.setString(4, animal.getType().toString());
			statement.setString(5, animal.getBreed());
			
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
		}	
	}
		

	 
	public void atualizar(Animal animal, Integer id) throws PersistenciaException {
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "UPDATE animals SET "+ 
						 " idconstumer = ?, nome = ?, idade = ?, type = ?, breed = ? "+
						 "WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1, animal.getCostumer().getId());
			statement.setString(2, animal.getNome());
			statement.setInt(3, animal.getIdade());
			statement.setString(4, animal.getType().toString());
			statement.setString(5, animal.getBreed());
			statement.setInt(6, id);
			
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
		}
	}

	 
	public void delete(Integer id) throws PersistenciaException {
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "DELETE FROM animals WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
		}
	}

	 
	public ArrayList<Animal> listarTodos() throws PersistenciaException {
		ArrayList<Animal> listaAnimal = new ArrayList<Animal>();
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "SELECT * FROM animals";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()){
				Animal animal = new Animal();
				Costumer costumer = new Costumer();
				costumer.setId( rs.getInt("idconstumer"));
				animal.setId( rs.getInt("id"));
				animal.setCostumer(costumer);
				animal.setNome( rs.getString("nome"));
				animal.setIdade( rs.getInt("idade"));
				animal.setType( AnimalType.valueOf( rs.getString("type")));
				animal.setBreed( rs.getString("breed"));
				listaAnimal.add(animal);
			}
			statement.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
		}
		return listaAnimal;
	}	

	 
	public Animal buscarPor(Integer id) throws PersistenciaException {
	Animal animal = new Animal ();
	try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "SELECT * FROM animals WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()){
				Costumer costumer = new Costumer();
				costumer.setId( rs.getInt("idconstumer"));
				animal.setId( rs.getInt("id"));
				animal.setCostumer((costumer));
				animal.setNome( rs.getString("nome"));
				animal.setIdade( rs.getInt("idade"));
				animal.setType( AnimalType.valueOf( rs.getString("type")));
				animal.setBreed( rs.getString("breed"));
			}
			statement.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
		}
		return animal;
	}

	 
	public List<Animal> buscarPor(String nome) throws PersistenciaException {
		
		List<Animal> listaAnimais = new ArrayList<Animal>();
		try {
				
				con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
				
				String sql = "SELECT * FROM animals WHERE nome LIKE ?";
				
				java.sql.PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, "%"+nome+"%");
				ResultSet rs = statement.executeQuery();
				
				while (rs.next()){
					Animal animal = new Animal();
					Costumer costumer = new Costumer();
					costumer.setId( rs.getInt("idconstumer"));
					animal.setId( rs.getInt("id"));
					animal.setCostumer(costumer);
					animal.setNome( rs.getString("nome"));
					animal.setIdade( rs.getInt("idade"));
					animal.setType( AnimalType.valueOf( rs.getString("type")));
					animal.setBreed( rs.getString("breed"));
					listaAnimais.add(animal);
				}
				statement.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			} finally {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new PersistenciaException(e.getMessage(), e);
				}
			}
			return listaAnimais;
	}

}
