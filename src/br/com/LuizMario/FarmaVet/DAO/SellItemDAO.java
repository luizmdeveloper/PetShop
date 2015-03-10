package br.com.LuizMario.FarmaVet.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.LuizMario.FarmaVet.Entity.Product;
import br.com.LuizMario.FarmaVet.Entity.Sell;
import br.com.LuizMario.FarmaVet.Entity.SellItem;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class SellItemDAO implements GenericDAO<br.com.LuizMario.FarmaVet.Entity.SellItem> {

	private Connection con;
	 
	public void inserir(br.com.LuizMario.FarmaVet.Entity.SellItem sellItem)	throws PersistenciaException {
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "INSERT INTO sellsitem (idsells,idproducts,quantidade) VALUES (?,?,?)";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1, sellItem.getIdsell().getId());
			statement.setInt(2, sellItem.getProduct().getId());
			statement.setInt(3, sellItem.getQuantidade());
			
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

	 
	public void atualizar(br.com.LuizMario.FarmaVet.Entity.SellItem sellItem,Integer id) throws PersistenciaException {
		
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "UPDATE sellsitem SET idsells = ? ,idproducts = ?,quantidade = ? WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1, sellItem.getIdsell().getId());
			statement.setInt(2, sellItem.getProduct().getId());
			statement.setInt(3, sellItem.getQuantidade());
			statement.setInt(4, id);
			
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

	 
	public void delete(Integer id) throws PersistenciaException {
	try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "DELETE FROM sellsitem WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1,id);
			
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
	
	public void deleteItensVenda(Integer id) throws PersistenciaException {
		try {
				
				con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
				
				String sql = "DELETE FROM sellsitem WHERE idsells = ?";
				
				java.sql.PreparedStatement statement = con.prepareStatement(sql);
				
				statement.setInt(1,id);
				
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

	 
	public List<br.com.LuizMario.FarmaVet.Entity.SellItem> listarTodos() throws PersistenciaException {
		List<br.com.LuizMario.FarmaVet.Entity.SellItem> listaItens = new ArrayList<br.com.LuizMario.FarmaVet.Entity.SellItem>();
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "SELECT * FROM sellsitem";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();
			while (rs.next()){
				br.com.LuizMario.FarmaVet.Entity.SellItem sellItem = new br.com.LuizMario.FarmaVet.Entity.SellItem();
				Sell sell = new Sell();
				Product product = new Product();
				product.setId( rs.getInt("idproducts"));
				sell.setId( rs.getInt("idsells"));
				sellItem.setId( rs.getInt("id"));
				sellItem.setIdsell(sell);
				sellItem.setProduct(product);
				sellItem.setQuantidade( rs.getInt("quantidade"));
				listaItens.add(sellItem);
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
		return listaItens;
	}

	 
	public br.com.LuizMario.FarmaVet.Entity.SellItem buscarPor(Integer id) throws PersistenciaException {
		br.com.LuizMario.FarmaVet.Entity.SellItem item = new br.com.LuizMario.FarmaVet.Entity.SellItem();
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "SELECT * FROM sellsitem WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				Sell sell = new Sell();
				Product product = new Product();
				product.setId( rs.getInt("idproducts"));
				sell.setId( rs.getInt("idsells"));
				item.setId( rs.getInt("id"));
				item.setIdsell(sell);
				item.setProduct(product);
				item.setQuantidade( rs.getInt("quantidade"));
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
		return item;
	}

	 
	public List<br.com.LuizMario.FarmaVet.Entity.SellItem> buscarPor(String nome) throws PersistenciaException {

		return null;
	}
	
	public ArrayList<SellItem> buscarItens(Integer idSell)  throws PersistenciaException{
		ArrayList<SellItem> itens = new ArrayList<SellItem>();
		try {
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "select * from sellsitem where idsells = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1, idSell);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()){
				SellItem item = new SellItem();
				Sell sell = new Sell(rs.getInt("idsells"));
				Product product = new Product(rs.getInt("idproducts"));
				item.setId(rs.getInt("id"));
				item.setIdsell(sell);
				item.setProduct(product);
				item.setQuantidade(rs.getInt("quantidade"));
				itens.add(item);
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
		return itens;
	}
}
