package br.com.LuizMario.FarmaVet.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.LuizMario.FarmaVet.Entity.Animal;
import br.com.LuizMario.FarmaVet.Entity.ScheduleType;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class ScheduleDAO implements GenericDAO<br.com.LuizMario.FarmaVet.Entity.Schedule> {

	private Connection con;
	 
	public void inserir(br.com.LuizMario.FarmaVet.Entity.Schedule schedule) throws PersistenciaException {
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "INSERT INTO schedule (idanimals,type,total,date) VALUES (?,?,?,?)";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1, schedule.getIdanimal().getId());
			statement.setString(2, schedule.getType().toString());
			statement.setFloat(3, schedule.getTotal());
			statement.setDate(4, new Date( schedule.getData().getTime()));

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

	 
	public void atualizar(br.com.LuizMario.FarmaVet.Entity.Schedule schedule, Integer id) throws PersistenciaException {
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "UPDATE schedule SET idanimals = ? ,type = ?,total = ?,date = ? WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1, schedule.getIdanimal().getId());
			statement.setString(2, schedule.getType().toString());
			statement.setFloat(3, schedule.getTotal());
			statement.setDate(4, new Date( schedule.getData().getTime()));
			statement.setInt(5, id);

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
			
			String sql = "DELETE FROM schedule WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1, id);

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

	 
	public ArrayList<br.com.LuizMario.FarmaVet.Entity.Schedule> listarTodos() throws PersistenciaException {
		ArrayList<br.com.LuizMario.FarmaVet.Entity.Schedule> listaAgenda = new ArrayList<br.com.LuizMario.FarmaVet.Entity.Schedule>();
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "SELECT * FROM schedule";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()){
				br.com.LuizMario.FarmaVet.Entity.Schedule schedule = new br.com.LuizMario.FarmaVet.Entity.Schedule();
				Animal animal = new Animal();
				animal.setId( rs.getInt("idanimals"));
				schedule.setId( rs.getInt("id"));
				schedule.setIdanimal(animal);
				schedule.setType( ScheduleType.valueOf( rs.getString("type")));
				schedule.setData(rs.getDate("date"));
				schedule.setTotal( rs.getFloat("total"));
				listaAgenda.add(schedule);
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
		return listaAgenda;
	}

	 
	public br.com.LuizMario.FarmaVet.Entity.Schedule buscarPor(Integer id) throws PersistenciaException {
		br.com.LuizMario.FarmaVet.Entity.Schedule schedule = new br.com.LuizMario.FarmaVet.Entity.Schedule();
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "SELECT * FROM schedule WHERE id = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()){
				Animal animal = new Animal();
				animal.setId( rs.getInt("idanimals"));
				schedule.setId( rs.getInt("id"));
				schedule.setIdanimal(animal);
				schedule.setType( ScheduleType.valueOf( rs.getString("type")));
				schedule.setTotal( rs.getFloat("total"));
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
		return schedule;
	}

	 
	public List<br.com.LuizMario.FarmaVet.Entity.Schedule> buscarPor(String nome) throws PersistenciaException {
		return null;
	}
	
	public List<br.com.LuizMario.FarmaVet.Entity.Schedule> buscarEntre (Date dateInicial, Date dateFinal) throws PersistenciaException{
		List<br.com.LuizMario.FarmaVet.Entity.Schedule> listaAgenda = new ArrayList<br.com.LuizMario.FarmaVet.Entity.Schedule>();
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "SELECT * FROM schedule WHERE DATE BETWEEN ? AND ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			statement.setDate(1, dateInicial);
			statement.setDate(2, dateFinal);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()){
				br.com.LuizMario.FarmaVet.Entity.Schedule schedule = new br.com.LuizMario.FarmaVet.Entity.Schedule();
				Animal animal = new Animal();
				animal.setId( rs.getInt("idanimals"));
				schedule.setId( rs.getInt("id"));
				schedule.setIdanimal(animal);
				schedule.setType( ScheduleType.valueOf( rs.getString("type")));
				schedule.setTotal( rs.getFloat("total"));
				schedule.setData( rs.getDate("date"));
				listaAgenda.add(schedule);
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
		return listaAgenda;	
	}

	public List<br.com.LuizMario.FarmaVet.Entity.Schedule> bucarAnimal(int id) throws PersistenciaException{
		List<br.com.LuizMario.FarmaVet.Entity.Schedule> listaAgenda = new ArrayList<br.com.LuizMario.FarmaVet.Entity.Schedule>();
		try {
			
			con = (Connection) br.com.LuizMario.FarmaVet.Connection.Connection.getInstance().getConnection();
			
			String sql = "SELECT * FROM schedule AS S INNER JOIN animals AS A ON S.idanimals = ?";
			
			java.sql.PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()){
				br.com.LuizMario.FarmaVet.Entity.Schedule schedule = new br.com.LuizMario.FarmaVet.Entity.Schedule();
				Animal animal = new Animal();
				animal.setId( rs.getInt("idanimals"));
				schedule.setId( rs.getInt("id"));
				schedule.setIdanimal(animal);
				schedule.setType( ScheduleType.valueOf( rs.getString("type")));
				schedule.setTotal( rs.getFloat("total"));
				schedule.setData( rs.getDate("date"));
				listaAgenda.add(schedule);
			}
			statement.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		} finally {
			try {
				con.close();
			} catch (final Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
		}
		return listaAgenda;
	}

}