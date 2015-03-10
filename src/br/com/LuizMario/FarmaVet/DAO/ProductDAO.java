package br.com.LuizMario.FarmaVet.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.LuizMario.FarmaVet.Entity.Product;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class ProductDAO implements GenericDAO<Product> {

	private Connection con;
	 
	public void inserir(Product product) throws PersistenciaException {
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "INSERT INTO products (nome,preco,quantidade) VALUES (?, ?, ?)";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setString(1,product.getNome());
			statement.setFloat(2,product.getPreco());
			statement.setInt(3,product.getQuantidade());
			
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

	 
	public void atualizar(Product product, Integer id) throws PersistenciaException {
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "UPDATE products SET "+ 
						 " nome = ?, preco = ?, quantidade = ? "+
						 "WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setString(1,product.getNome());
			statement.setFloat(2,product.getPreco());
			statement.setInt(3,product.getQuantidade());
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
			
			String sql = "DELETE FROM products WHERE id=? "; 
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

	 
	public ArrayList<Product> listarTodos() throws PersistenciaException {
		ArrayList<Product> retorno = new ArrayList<Product>();
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String SQL = " SELECT * FROM products";
			
			java.sql.PreparedStatement statement = con.prepareStatement(SQL);
			
			ResultSet resultSet =  statement.executeQuery();
			
			while (resultSet.next()){
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setNome(resultSet.getString("nome"));
				product.setPreco(resultSet.getFloat("preco"));
				product.setQuantidade(resultSet.getInt("quantidade"));
				retorno.add(product);	
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

	 
	public Product buscarPor(Integer id) throws PersistenciaException {
		Product product = new Product();
		try {
			 
			con = (java.sql.Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String SQL = "SELECT * FROM products WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(SQL);
			
			statement.setInt(1, id);
			
			ResultSet resultSet =  statement.executeQuery();
			if (resultSet.next()){
				product.setId( resultSet.getInt("id"));
				product.setNome( resultSet.getString("nome") );
				product.setPreco( resultSet.getFloat("preco") );
				product.setQuantidade( resultSet.getInt("quantidade"));
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
		return product;
	}

	 
	public List<Product> buscarPor(String nome) throws PersistenciaException {
		List<Product> retorno = new ArrayList<Product>();
		try {
			con = (java.sql.Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String SQL = " SELECT * FROM products WHERE nome LIKE ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(SQL);
			
			statement.setString(1, "%"+nome+"%");
			
			ResultSet resultSet =  statement.executeQuery();
			
			while (resultSet.next()){
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setNome(resultSet.getString("nome"));
				product.setPreco(resultSet.getFloat("preco"));
				product.setQuantidade(resultSet.getInt("quantidade"));
				retorno.add(product);
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
	
	public int verificarSaldo(int idProduct) throws PersistenciaException{	
		int quantidade = 0;
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = " SELECT products.quantidade FROM products "+
						 " WHERE products.id = ? ";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, idProduct);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				quantidade = rs.getInt("products.quantidade");
			}
			rs.close();
			statement.close();
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
		return quantidade;
	}
	
	public void baixarEstoque(int idProduct, int quantidadePedido)throws PersistenciaException{	
		int quantidade = 0;
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = " SELECT quantidade FROM products "+
						 " WHERE id = ? ";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, idProduct);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				quantidade = rs.getInt("quantidade");
			}
			rs.close();
			statement.close();
			atualizarSaldo(quantidade, quantidadePedido, idProduct);
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


	private void atualizarSaldo(int quantidade, int quantidadePedido, int idProduct) throws PersistenciaException {
		int saldo = quantidade - quantidadePedido;
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = " UPDATE products SET "+
					     " quantidade = ? "+
						 " WHERE id = ? ";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, saldo);
			statement.setInt(2, idProduct);
			statement.execute();
			statement.close();
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
}
