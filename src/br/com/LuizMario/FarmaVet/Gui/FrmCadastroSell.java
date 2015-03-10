package br.com.LuizMario.FarmaVet.Gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import br.com.LuizMario.FarmaVet.DAO.SellDAO;
import br.com.LuizMario.FarmaVet.DAO.UserDAO;
import br.com.LuizMario.FarmaVet.Entity.Sell;
import br.com.LuizMario.FarmaVet.Entity.User;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class FrmCadastroSell extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8554273810063313390L;
	private JTable tableSell;
	private GroupLayout groupLayout;
	private JScrollPane scpSell;
	private ArrayList<Sell> sells;


	/**
	 * Create the dialog.
	 */
	public FrmCadastroSell() {
		setLocationRelativeTo(null);
		setTitle("Projeto SIGMA - Pedido de Venda");
		setBounds(100, 100, 594, 304);
		setResizable(false);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		scpSell = new JScrollPane();
		scpSell.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				novoSellOnclick();
			}
		});
		btnNovo.setIcon(new ImageIcon(FrmCadastroSell.class.getResource("/res/incluir.png")));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editarSellOnClick();
			}
		});
		btnEditar.setIcon(new ImageIcon(FrmCadastroSell.class.getResource("/res/editar.png")));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					excluirSellOnClick();
			}
		});
		btnExcluir.setIcon(new ImageIcon(FrmCadastroSell.class.getResource("/res/excluir.png")));
		
		JButton btnVerItensDo = new JButton("Ver itens do pedido");
		btnVerItensDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verItensOnClick();
			}
		});
		btnVerItensDo.setIcon(new ImageIcon(FrmCadastroSell.class.getResource("/res/produto.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmCadastroSell.class.getResource("/res/Sair.png")));
		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scpSell, GroupLayout.PREFERRED_SIZE, 564, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNovo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVerItensDo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scpSell, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo)
						.addComponent(btnEditar)
						.addComponent(btnExcluir)
						.addComponent(btnVerItensDo)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnNovo, btnEditar, btnExcluir, btnVerItensDo, btnCancelar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnNovo, btnEditar, btnExcluir, btnCancelar});
		
		refreshSells();
	}

	protected void excluirSellOnClick() {
		if (tableSell.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(this, "Selecione um sell");
		} else {
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja Excluir o sell ?", "Projeto SIGMA LTDA", JOptionPane.YES_NO_CANCEL_OPTION);
			if (opcao == JOptionPane.YES_OPTION) {
				SellDAO sellDAO = new SellDAO();
				try {
					sellDAO.delete(sells.get(tableSell.getSelectedRow()).getId());
					JOptionPane.showMessageDialog(this, "Venda apagada com sucesso");
					refreshSells();
				} catch (PersistenciaException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Erro ao excluir o sell \n"+e.getMessage(),"Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	protected void editarSellOnClick() {
		if (tableSell.getSelectedRow() == -1 ){
			JOptionPane.showMessageDialog(this, "Selecione algum sell");
		}else{
			FrmEditSell dialog = new FrmEditSell(sells.get(tableSell.getSelectedRow()));
			dialog.setVisible(true);
		}	 
	}

	protected void verItensOnClick() {
		if (tableSell.getSelectedRow() == -1 ){
			JOptionPane.showMessageDialog(this, "Selecione algum sell");
		}else{
			FrmVerItensSell dialog = new FrmVerItensSell(sells.get(tableSell.getSelectedRow()).getId());
			dialog.setVisible(true);
		}	
	}

	protected void novoSellOnclick() {
		FrmNovoSell dialog = new FrmNovoSell();
		dialog.setVisible(true);
	}

	public void refreshSells() {
		String[][] vendas = null;
		SellDAO sellDAO = new SellDAO();
		try {
			sells = sellDAO.listarTodos();
			vendas = preencher(sells);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		tableSell = new JTable();
		tableSell.setModel(new DefaultTableModel(
				vendas,
			new String[] {
				"Data", "Vendedor", "Total"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1897122608631679217L;
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableSell.getColumnModel().getColumn(0).setPreferredWidth(52);
		tableSell.getColumnModel().getColumn(1).setPreferredWidth(224);
		scpSell.setViewportView(tableSell);
		getContentPane().setLayout(groupLayout);
	}

	private String[][] preencher(ArrayList<Sell> sells) {
		String[][] toReturn = new String[sells.size()][3];
		for (int i = 0; i < sells.size(); i++) {
			SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdfData.format(sells.get(i).getData());
			toReturn[i][0] = data;
			toReturn[i][1] = nomeVendedor(sells.get(i).getVendedor().getId());
			toReturn[i][2] = String.valueOf(sells.get(i).getTotal());
		}
		return toReturn;
	}

	private String nomeVendedor(int id) {
		UserDAO userDAO = new UserDAO();
		User user = new User();
		try {
			user = userDAO.buscarPor(id);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao carregar o usuário\n"+e.getMessage(),"Projeto SIGMA LTDA",JOptionPane.ERROR_MESSAGE);
		}
		return user.getNome();
	}

}
