package br.com.LuizMario.FarmaVet.Util;

import br.com.LuizMario.FarmaVet.DAO.ProductDAO;
import br.com.LuizMario.FarmaVet.Entity.Sell;
import br.com.LuizMario.FarmaVet.Entity.SellItem;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class Estoque {
	
	public boolean verificarSaldo(int quantidade, int idProduct) throws PersistenciaException{
		return (new ProductDAO().verificarSaldo(idProduct)>=quantidade?true:false);
	}
	
	public void baixarEstoque(Sell sell) throws PersistenciaException{
		for (SellItem itens : sell.getItens()) {
			new ProductDAO().baixarEstoque(itens.getProduct().getId(), itens.getQuantidade());
		}
		
	}

}
