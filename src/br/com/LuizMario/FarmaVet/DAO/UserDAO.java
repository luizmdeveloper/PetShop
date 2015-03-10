package br.com.LuizMario.FarmaVet.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.LuizMario.FarmaVet.Entity.User;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class UserDAO implements GenericDAO<User> {

	private Connection con;
	 
	public void inserir(User user) throws PersistenciaException {
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "INSERT INTO users (nome,login,passoword) VALUES (?,?,?)";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setString(1,user.getNome());
			statement.setString(2,user.getLogin());
			statement.setString(3,user.getPassoword());
			
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

	 
	public void atualizar(User user, Integer id) throws PersistenciaException {
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "UPDATE users "+ 
						 "set nome= ? ,login= ? ,passoword=?"+
						 "WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setString(1,user.getNome());
			statement.setString(2,user.getLogin());
			statement.setString(3,user.getPassoword());
			statement.setInt(4,id);
			
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
			
			String sql = "DELETE FROM users WHERE id=? "; 
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

	 
	public ArrayList<User> listarTodos() throws PersistenciaException {
		
		ArrayList<User> retorno = new ArrayList<User>();
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String SQL = " SELECT * FROM users";
			
			java.sql.PreparedStatement statement = con.prepareStatement(SQL);
			
			ResultSet resultSet =  statement.executeQuery();
			
			while (resultSet.next()){
				User user = new User();
				user.setId(resultSet.getInt ("id") );
				user.setNome(resultSet.getString("nome"));
				user.setLogin(resultSet.getString("login"));
				user.setPassoword(resultSet.getString("passoword"));
				retorno.add(user);	
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

	 
	public User buscarPor(Integer id) throws PersistenciaException {
		User user = new User();
		try {
			 
			con = (java.sql.Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String SQL = "SELECT * FROM users WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(SQL);
			
			statement.setInt(1, id);
			
			ResultSet resultSet =  statement.executeQuery();
			if (resultSet.next()){
				user.setId( resultSet.getInt("id"));
				user.setNome(resultSet.getString("nome"));
				user.setLogin(resultSet.getString("login"));
				user.setPassoword(resultSet.getString("passoword"));	
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
		return user;
	}

	 
	public List<User> buscarPor(String nome) throws PersistenciaException {
		List<User> retorno = new ArrayList<User>();
		try {
			con = (java.sql.Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String SQL = " SELECT * FROM users WHERE nome LIKE ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(SQL);
			
			statement.setString(1, "%"+nome+"%");
			
			ResultSet resultSet =  statement.executeQuery();
			
			while (resultSet.next()){
				User user = new User();
				user.setId( resultSet.getInt("id"));
				user.setNome(resultSet.getString("nome"));
				user.setLogin(resultSet.getString("login"));
				user.setPassoword(resultSet.getString("passoword"));
				retorno.add(user);	
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

	public int buscarUltimoRegistro () throws PersistenciaException  {
		int ultimo = 0;
		try {
			con = (java.sql.Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String SQL = " SELECT MAX(id) AS QUANTIDADE FROM users";
			
			java.sql.PreparedStatement statement = con.prepareStatement(SQL);
			
			ResultSet resultSet =  statement.executeQuery();
			
			if (resultSet.next()){
				ultimo = resultSet.getInt("QUANTIDADE");
			}
			resultSet.close();
			statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return ultimo;
		
	}
	
	public boolean logar(User user) throws PersistenciaException {
		boolean retorno = false;
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "SELECT * FROM users WHERE login = ? AND passoword = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setString(1,user.getLogin());
			statement.setString(2,user.getPassoword());
			
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				retorno = true;
			}
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
		return retorno;
	}
}
