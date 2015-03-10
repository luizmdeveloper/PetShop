package br.com.LuizMario.FarmaVet.DAO;

import java.util.List;

import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public interface GenericDAO <T> {

	void inserir (T Obj) throws PersistenciaException;
		
	void atualizar (T obj, Integer id) throws PersistenciaException;
	
	void delete (Integer id) throws PersistenciaException;
	
	List<T> listarTodos () throws PersistenciaException;
		
	T buscarPor (Integer id) throws PersistenciaException;
		
	List<T> buscarPor (String nome) throws PersistenciaException;
		
}
