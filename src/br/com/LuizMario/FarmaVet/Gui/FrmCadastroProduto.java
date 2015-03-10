package br.com.LuizMario.FarmaVet.Gui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

import br.com.LuizMario.FarmaVet.DAO.ProductDAO;
import br.com.LuizMario.FarmaVet.Entity.Product;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class FrmCadastroProduto extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6101320608358130344L;
	private JTable tableProducts;
	private List<Product> products;
	private GroupLayout groupLayout;
	private JScrollPane scpProduct;
	

	public FrmCadastroProduto() {
		setLocationRelativeTo(null);
		setTitle("Projeto SIGMA - Cadastro Produto");
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
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCadastroProduto.class.getResource("/res/logo.png")));
		setBounds(100, 100, 426, 259);
		
		scpProduct = new JScrollPane();
		scpProduct.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productNovoOnClick();
			}
		});
		btnNovo.setIcon(new ImageIcon(FrmCadastroProduto.class.getResource("/res/incluir.png")));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productEditOnClick();
			}
		});
		btnEditar.setIcon(new ImageIcon(FrmCadastroProduto.class.getResource("/res/editar.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelarOnClick();
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmCadastroProduto.class.getResource("/res/Sair.png")));
		
		JButton button = new JButton("Excluir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productExcluirOnClick();
			}
		});
		button.setIcon(new ImageIcon(FrmCadastroProduto.class.getResource("/res/excluir.png")));
		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scpProduct, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNovo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scpProduct, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo)
						.addComponent(btnEditar)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnNovo, btnEditar, btnCancelar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnNovo, btnEditar, btnCancelar});
		
		refreshTableProduct();
	}

	protected void productExcluirOnClick() {
		if (tableProducts.getSelectedRow() == -1 ){
			JOptionPane.showMessageDialog(this, "Selecione um produto !!!!");
		}else{
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja Excluir ?", "Projeto SIGMA LTDA", JOptionPane.OK_CANCEL_OPTION);
			if (opcao == JOptionPane.OK_OPTION){
				try {
					ProductDAO productDAO = new ProductDAO();
					productDAO.delete(products.get(tableProducts.getSelectedRow()).getId());
				} catch (PersistenciaException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Não foi possivel excluir \n" + e.getMessage() , "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
			refreshTableProduct();
		}
		
	}

	protected void productEditOnClick() {
		if (tableProducts.getSelectedRow() == -1 ){
			JOptionPane.showMessageDialog(this, "Selecione um produto !!!");
		} else {
			FrmEditProduct dialog = new FrmEditProduct(products.get(tableProducts.getSelectedRow()), this);
			dialog.setVisible(true);
		}
		
	}

	protected void productNovoOnClick() {
		FrmNovoProduct dialog = new FrmNovoProduct(this);
		dialog.setVisible(true);
	}

	protected void refreshTableProduct() {
		String[][] productsString = null;
		
		try {
			ProductDAO productDAO = new ProductDAO();
			products = productDAO.listarTodos();
			productsString = preencherTable(products); 
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		
		tableProducts = new JTable();
		tableProducts.setModel(new DefaultTableModel(
			productsString,
			new String[] {
				"C\u00F3digo", "Descri\u00E7\u00E3o", "Pre\u00E7o", "Quantidade"
			}
		));
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(156);
		tableProducts.getColumnModel().getColumn(2).setPreferredWidth(59);
		scpProduct.setViewportView(tableProducts);
		getContentPane().setLayout(groupLayout);
	}

	private String[][] preencherTable(List<Product> lista) {
		String[][] toReturn = new String[lista.size()][4];
		for (int i = 0; i < lista.size(); i++) {
			toReturn[i][0] = String.valueOf(lista.get(i).getId());
			toReturn[i][1] = lista.get(i).getNome();
			toReturn[i][2] = String.valueOf(lista.get(i).getPreco());
			toReturn[i][3] = String.valueOf(lista.get(i).getQuantidade());
		}
		return toReturn;
	}

	protected void cancelarOnClick() {
		this.setVisible(false);
	}
}
