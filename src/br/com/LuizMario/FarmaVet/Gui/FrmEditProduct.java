package br.com.LuizMario.FarmaVet.Gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.MaskFormatter;

import br.com.LuizMario.FarmaVet.DAO.ProductDAO;
import br.com.LuizMario.FarmaVet.Entity.Product;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class FrmEditProduct extends JDialog {

	private static final long serialVersionUID = -8649902132750557280L;
	private JTextField txtDescricao;
	private JTextField txtPreco;
	private MaskFormatter maskQuantidade;
	private JFormattedTextField txtQuantidade;
	@SuppressWarnings("unused")
	private Product product;
	private FrmCadastroProduto control;

	/**
	 * Create the dialog.
	 */
	public FrmEditProduct(Product product, FrmCadastroProduto control) {
		this.control = control;
		setLocationRelativeTo(null);
		setTitle("Projeto SIGMA - Editando Produto");
		this.product = product;
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
		setBounds(100, 100, 283, 216);
		JLabel lbDescricao = new JLabel("Descri\u00E7\u00E3o:");
		
		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		
		JLabel lbPreo = new JLabel("Pre\u00E7o:");
		
		txtPreco = new JTextField();
	
		
		JLabel lbQuantidade = new JLabel("Quantidade:");
		
		try {
			maskQuantidade = new MaskFormatter("########");
		} catch (ParseException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao construir a máscara Quantidade \n"+e.getMessage(), "Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
		}
		txtQuantidade = new JFormattedTextField();
		txtQuantidade.setColumns(10);
		maskQuantidade.install(txtQuantidade);
		
		JButton btnNewButton = new JButton("Salvar");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductOnClick(product);
			}
		});
		btnNewButton.setIcon(new ImageIcon(FrmEditProduct.class.getResource("/res/incluir.png")));
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
				setVisible(false);
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmEditProduct.class.getResource("/res/Sair.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbDescricao)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbPreo)
							.addGap(64)
							.addComponent(lbQuantidade))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtPreco, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbDescricao)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbPreo)
						.addComponent(lbQuantidade))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnNewButton, btnCancelar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnNewButton, btnCancelar});
		getContentPane().setLayout(groupLayout);
		carregarCampos(product);
	}

	private void carregarCampos(Product product) {
		txtDescricao.setText(product.getNome());
		txtPreco.setText(String.valueOf(product.getPreco()));
		txtQuantidade.setText(String.valueOf(product.getQuantidade()));
	}

	protected void limparCampos() {
		txtDescricao.setText("");
		txtPreco.setText("");
		txtQuantidade.setText("");
	}

	protected void ProductOnClick(Product product) {
		criarProduct(product);
	}

	private void criarProduct(Product product) {
		Product productEdit = new Product();
		productEdit.setId(product.getId());
		productEdit.setNome(txtDescricao.getText());
		productEdit.setPreco(Float.parseFloat(txtPreco.getText()));
		productEdit.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
		persistir(productEdit);
	}

	private void persistir(Product product) {
		try {
			ProductDAO productDAO = new ProductDAO();
			productDAO.atualizar(product, product.getId());
			JOptionPane.showMessageDialog(this, "Produto, atualizado com sucesso!!!");
			limparCampos();
			control.refreshTableProduct();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Não foi possivel Atualizar \n" + e.getMessage() , "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
