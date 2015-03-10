package br.com.LuizMario.FarmaVet.Gui;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.LuizMario.FarmaVet.DAO.ProductDAO;
import br.com.LuizMario.FarmaVet.Entity.Product;
import br.com.LuizMario.FarmaVet.Entity.SellItem;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;
import br.com.LuizMario.FarmaVet.Util.Estoque;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmAdicionarItens extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8861978869822513414L;
	private JTextField txtQuantidade;
	private JComboBox<Object> cmbProduct;
	private ArrayList<SellItem> itens;
	private ArrayList<Product> products;
	private FrmNovoSell control;
	private FrmEditSell edit;

	/**
	 * Create the dialog.
	 */
	public FrmAdicionarItens(ArrayList<SellItem> itens, FrmNovoSell control) {
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
		setLocationRelativeTo(null);
		this.itens = itens;
		this.control = control;
		setResizable(false);
		setTitle("Projeto SIGMA -Adicionar Itens");
		setBounds(100, 100, 403, 137);
		JLabel lblProduto = new JLabel("Produto:");
		cmbProduct = new JComboBox<Object>();
		JLabel lblQuantidade = new JLabel("Quantidade:");
		
		txtQuantidade = new JTextField();
		txtQuantidade.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvarOnClick();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(FrmAdicionarItens.class.getResource("/res/incluir.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelarOnClick();
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmAdicionarItens.class.getResource("/res/Sair.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdicionar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.RELATED, 183, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cmbProduct, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProduto))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblQuantidade)
								.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))))
					.addGap(2))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduto)
						.addComponent(lblQuantidade))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionar)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnAdicionar, btnCancelar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnAdicionar, btnCancelar});
		getContentPane().setLayout(groupLayout);
		iniciarComboBoxProduct();
	}

	public FrmAdicionarItens(ArrayList<SellItem> itens, FrmEditSell edit) {
		setLocationRelativeTo(null);
		this.itens = itens;
		this.edit = edit;
		setResizable(false);
		setTitle("Projeto SIGMA -Adicionar Itens");
		setBounds(100, 100, 403, 137);
		JLabel lblProduto = new JLabel("Produto:");
		cmbProduct = new JComboBox<Object>();
		JLabel lblQuantidade = new JLabel("Quantidade:");
		
		txtQuantidade = new JTextField();
		txtQuantidade.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvarOnClick();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(FrmAdicionarItens.class.getResource("/res/incluir.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelarOnClick();
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmAdicionarItens.class.getResource("/res/Sair.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdicionar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.RELATED, 183, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cmbProduct, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProduto))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblQuantidade)
								.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))))
					.addGap(2))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduto)
						.addComponent(lblQuantidade))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionar)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnAdicionar, btnCancelar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnAdicionar, btnCancelar});
		getContentPane().setLayout(groupLayout);
		iniciarComboBoxProduct();
	}
	
	protected void salvarOnClick() {
		try {
			SellItem item = new SellItem();
			Product product = new Product(products.get(cmbProduct.getSelectedIndex()).getId());
			item.setProduct(product);
			item.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
			if (! new Estoque().verificarSaldo(item.getQuantidade(), item.getProduct().getId())){
				JOptionPane.showMessageDialog(this, "Item sem saldo, por favor verifique o saldo do item", "Porjeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
			}else {
				this.itens.add(item);
				
				if (this.control == null){
					this.edit.refreshItens(itens);
				}else{
					control.refreshItens(this.itens);
				}
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu algum erro ao adicionar o item\n"+e.getMessage(),"Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void cancelarOnClick() {
		limparCampos();
		this.setVisible(false);
	}

	public void limparCampos() {
		cmbProduct.setSelectedIndex(0);
		txtQuantidade.setText("");
	}

	private void iniciarComboBoxProduct() {
		ProductDAO productDAO = new ProductDAO();
		try {
			products = productDAO.listarTodos();
			preencherComboBoxProduct(products);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		
	}

	private void preencherComboBoxProduct(ArrayList<Product> products) {
		for (Product product : products) {
			cmbProduct.addItem(product.getNome());
		}
	}
}
