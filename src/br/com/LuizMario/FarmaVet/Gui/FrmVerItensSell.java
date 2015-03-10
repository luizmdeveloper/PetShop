package br.com.LuizMario.FarmaVet.Gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.LuizMario.FarmaVet.DAO.ProductDAO;
import br.com.LuizMario.FarmaVet.DAO.SellItemDAO;
import br.com.LuizMario.FarmaVet.Entity.Product;
import br.com.LuizMario.FarmaVet.Entity.SellItem;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmVerItensSell extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 288980036733079570L;
	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private int idSell;
	private JTable tableSellItem;
	private JScrollPane scpSellItens;

	/**
	 * Create the dialog.
	 */
	public FrmVerItensSell(int idSell) {
		setLocationRelativeTo(null);
		this.idSell = idSell;
		setTitle("Projeto SIGMA - Ver Itens do Pedido");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		scpSellItens = new JScrollPane();
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnOk.setIcon(new ImageIcon(FrmVerItensSell.class.getResource("/res/Sair.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scpSellItens, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(180)
							.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scpSellItens, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOk, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
					.addGap(7))
		);
		ArrayList<SellItem> itens = new ArrayList<SellItem>();
		String[][] listaItens = null;
		SellItemDAO sellItemDAO = new SellItemDAO();
		try {
			itens = sellItemDAO.buscarItens(idSell);
			listaItens = preencherItens(itens);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao carregar itens\n"+e.getMessage(), "Projeto SIGMA", JOptionPane.ERROR_MESSAGE);
		}
		
		tableSellItem = new JTable();
		tableSellItem.setModel(new DefaultTableModel(
				listaItens,
			new String[] {
				"C\u00F3digo Venda", "Descricao", "Quantidade"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2978743017412377757L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableSellItem.getColumnModel().getColumn(0).setPreferredWidth(91);
		tableSellItem.getColumnModel().getColumn(1).setPreferredWidth(262);
		scpSellItens.setViewportView(tableSellItem);
		contentPanel.setLayout(gl_contentPanel);
	}

	private String[][] preencherItens(ArrayList<SellItem> itens) {
		String[][] toReturn = new String[itens.size()][3];
		for (int i = 0; i < itens.size(); i++) {
			toReturn[i][0] = String.valueOf(itens.get(i).getIdsell().getId());
			toReturn[i][1] = descricaoProduct(itens.get(i).getProduct().getId());
			toReturn[i][2] = String.valueOf(itens.get(i).getQuantidade());
		}
		return toReturn;
	}

	private String descricaoProduct(Integer id) {
		ProductDAO productDAO = new ProductDAO();
		Product product = new Product();
		try {
			product = productDAO.buscarPor(id);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao carregar a descricao do produto\n"+e.getMessage(),"Projeto SIGMA LTDA",JOptionPane.ERROR_MESSAGE);
		}
		return product.getNome();
	}
}
