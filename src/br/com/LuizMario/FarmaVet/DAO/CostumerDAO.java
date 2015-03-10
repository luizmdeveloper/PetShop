package br.com.LuizMario.FarmaVet.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.LuizMario.FarmaVet.Entity.Costumer;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class CostumerDAO implements GenericDAO<Costumer> {

	private Connection con;
	 
	public void inserir(Costumer costumer) throws PersistenciaException {
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "INSERT INTO constumers (name,idade,endereco,number,bairro,telefone) VALUES (?, ?, ?, ?, ?, ?)";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setString(1,costumer.getName());
			statement.setInt(2,costumer.getIdade());
			statement.setString(3,costumer.getEndereco());
			statement.setInt(4,costumer.getNumber());
			statement.setString(5,costumer.getBairro());
			statement.setString(6,costumer.getTelefone());
			
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

	 
	public void atualizar(Costumer costumer, Integer id) throws PersistenciaException {
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "UPDATE constumers SET "+ 
						 " name= ? , idade = ?, endereco = ?, number = ?, bairro = ?, telefone = ?"+
						 "WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setString(1,costumer.getName());
			statement.setInt(2,costumer.getIdade());
			statement.setString(3,costumer.getEndereco());
			statement.setInt(4,costumer.getNumber());
			statement.setString(5,costumer.getBairro());
			statement.setString(6,costumer.getTelefone());
			statement.setInt(7,id);
			
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
			
			String sql = "DELETE FROM constumers WHERE id=? "; 
			java.sql.PreparedStatement statement = con.prepareStatement(sql);

			statement.setInt(1,id);
			
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

	 
	public ArrayList<Costumer> listarTodos() throws PersistenciaException {
		ArrayList<Costumer> retorno = new ArrayList<Costumer>();
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String SQL = " SELECT * FROM constumers";
			
			java.sql.PreparedStatement statement = con.prepareStatement(SQL);
			
			ResultSet resultSet =  statement.executeQuery();
			
			while (resultSet.next()){
				Costumer costumer = new Costumer();
				costumer.setId(resultSet.getInt("id"));
				costumer.setName(resultSet.getString("name"));
				costumer.setIdade(resultSet.getInt("idade"));
				costumer.setEndereco(resultSet.getString("endereco"));
				costumer.setNumber(resultSet.getInt("number"));
				costumer.setBairro(resultSet.getString("bairro"));
				costumer.setTelefone(resultSet.getString("telefone"));
				retorno.add(costumer);	
			}
			resultSet.close();
			statement.close();
			}catch (Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new PersistenciaException(e.getMessage(), e);
				}
			}
	return retorno;
	}

	 
	public Costumer buscarPor(Integer id) throws PersistenciaException {
		Costumer costumer = new Costumer();
		try {
			 
			con = (java.sql.Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String SQL = "SELECT * FROM constumers WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(SQL);
			
			statement.setInt(1, id);
			
			ResultSet resultSet =  statement.executeQuery();
			if (resultSet.next() ){
				costumer.setId( resultSet.getInt("id"));
				costumer.setName( resultSet.getString("name") );
				costumer.setIdade( resultSet.getInt("idade") );
				costumer.setEndereco( resultSet.getString("endereco") );
				costumer.setNumber( resultSet.getInt("number") );
				costumer.setBairro( resultSet.getString("bairro") );
				costumer.setTelefone( resultSet.getString("telefone") );	
			}
						
			resultSet.close();
			statement.close();
		}catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
		}
		return costumer;
	}

	 
	public List<Costumer> buscarPor(String nome) throws PersistenciaException {
		List<Costumer> retorno = new ArrayList<Costumer>();
		try {
			con = (java.sql.Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String SQL = " SELECT * FROM constumers WHERE name LIKE ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(SQL);
			
			statement.setString(1, "%"+nome+"%");
			
			ResultSet resultSet =  statement.executeQuery();
			
			while (resultSet.next()){
				Costumer costumer = new Costumer();
				costumer.setId(resultSet.getInt("id"));
				costumer.setName(resultSet.getString("name"));
				costumer.setIdade(resultSet.getInt("idade"));
				costumer.setEndereco(resultSet.getString("endereco"));
				costumer.setNumber(resultSet.getInt("number"));
				costumer.setBairro(resultSet.getString("bairro"));
				costumer.setTelefone(resultSet.getString("telefone"));
				retorno.add(costumer);	
			}
			resultSet.close();
			statement.close();
			}catch (Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new PersistenciaException(e.getMessage(), e);
				}
			}
	return retorno;
	}
	
	public Costumer buscar(String nome) throws PersistenciaException {
		Costumer retorno = new Costumer();
		try {
			con = (java.sql.Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String SQL = " SELECT * FROM constumers WHERE name = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(SQL);
			
			statement.setString(1, nome);
			
			ResultSet resultSet =  statement.executeQuery();
			
			while (resultSet.next()){
				retorno.setId(resultSet.getInt("id"));
				retorno.setName(resultSet.getString("name"));
				retorno.setIdade(resultSet.getInt("idade"));
				retorno.setEndereco(resultSet.getString("endereco"));
				retorno.setNumber(resultSet.getInt("number"));
				retorno.setBairro(resultSet.getString("bairro"));
				retorno.setTelefone(resultSet.getString("telefone"));	
			}
			resultSet.close();
			statement.close();
			}catch (Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new PersistenciaException(e.getMessage(), e);
				}
			}
	return retorno;
	}
	

}
