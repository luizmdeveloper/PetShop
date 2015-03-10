package br.com.LuizMario.FarmaVet.Exception;

public class PersistenciaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2830040909657390796L;

	public PersistenciaException (String msg, Exception exercao){
		super (msg, exercao);
	}

	public PersistenciaException (String msg){
		super (msg);
	}

}
