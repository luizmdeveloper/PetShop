package br.com.LuizMario.FarmaVet.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.LuizMario.FarmaVet.Entity.Product;
import br.com.LuizMario.FarmaVet.Entity.Sell;
import br.com.LuizMario.FarmaVet.Entity.SellItem;
import br.com.LuizMario.FarmaVet.Entity.User;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

import com.mysql.jdbc.Statement;

public class SellDAO implements GenericDAO<Sell> {

	private Connection con;
	 
	public void inserir(Sell sell) throws PersistenciaException {
		int idSell = 0;
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "INSERT INTO sells (date,vendedor,total) VALUES (?,?,?)";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			float total = 0;
			total = calcularTotal(sell.getItens());
			statement.setDate(1, sell.getData());
			statement.setInt(2, sell.getVendedor().getId());
			statement.setFloat(3, total);
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()){
				idSell = rs.getInt(1);
			}
			
			statement.close();
			
			inserirItens(sell, idSell);
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

	private float calcularTotal(ArrayList<SellItem> itens) throws PersistenciaException {
		float totalReturn = 0;
		for (SellItem sellItem : itens) {
			totalReturn += sellItem.getQuantidade() * precoProduct(sellItem.getProduct().getId());
		}
		return totalReturn;
	}

	private int precoProduct(Integer id) throws PersistenciaException {
		ProductDAO productDAO = new ProductDAO();
		Product product = new Product();
		product = productDAO.buscarPor(id);
		return product.getQuantidade();
	}

	private void inserirItens(Sell sell, int idSell) {
		for (SellItem item : sell.getItens()) {
			try {
				Sell sl = new Sell(idSell);
				Product product = new Product(item.getProduct().getId());
				SellItem sellItem = new SellItem();
				sellItem.setIdsell(sl);
				sellItem.setProduct(product);
				sellItem.setQuantidade(item.getQuantidade());
				SellItemDAO sellItemDAO = new SellItemDAO();
				sellItemDAO.inserir(sellItem);
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
		}
		
	}
 
	public void atualizar(Sell sell, Integer id) throws PersistenciaException {
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "UPDATE sells SET vendedor = ? ,total = ? WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			float total = 0;
			total = calcularTotal(sell.getItens());
			
			statement.setInt(1, sell.getVendedor().getId());
			statement.setFloat(2, total);
			statement.setInt(3, id);
			
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

	public void delete(Integer id) throws PersistenciaException {
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			new SellItemDAO().deleteItensVenda(id);
			
			String sql = "DELETE FROM sells WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1,id);
			
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

	public ArrayList<Sell> listarTodos() throws PersistenciaException {
		ArrayList<Sell> listaSell = new ArrayList<Sell>();
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "SELECT * FROM sells";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()){
				Sell sell = new Sell();
				User user = new User();
				user.setId(rs.getInt("vendedor"));
				sell.setData(rs.getDate("date"));
				sell.setId( rs.getInt("id"));
				sell.setTotal( rs.getFloat("total"));
				sell.setVendedor(user);
				listaSell.add(sell);
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
		return listaSell;
	}

	 
	public Sell buscarPor(Integer id) throws PersistenciaException {
		Sell sell = new Sell();
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "SELECT * FROM sells";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				User user = new User();
				user.setId(rs.getInt("vendedor"));
				sell.setData(rs.getDate("date"));
				sell.setId( rs.getInt("id"));
				sell.setTotal( rs.getFloat("total"));
				sell.setVendedor(user);
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
		return sell;
	}

	public List<Sell> buscarPor(String nome) throws PersistenciaException {
		return null;
	}
	
	public List<Sell> buscarEntre(Date dataInicial, Date dataFinal) throws PersistenciaException {
		List<Sell> listaSell = new ArrayList<Sell>();
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "SELECT * FROM sells WHERE date BETWEEN ? AND ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setDate(1, dataInicial);
			statement.setDate(2, dataFinal);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()){
				Sell sell = new Sell();
				User user = new User();
				user.setId(rs.getInt("vendedor"));
				sell.setData(rs.getDate("date"));
				sell.setId( rs.getInt("id"));
				sell.setTotal( rs.getFloat("total"));
				sell.setVendedor(user);
				listaSell.add(sell);
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
		return listaSell;
	}

	
}
