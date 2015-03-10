package br.com.LuizMario.FarmaVet.Gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
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
import br.com.LuizMario.FarmaVet.DAO.SellDAO;
import br.com.LuizMario.FarmaVet.DAO.UserDAO;
import br.com.LuizMario.FarmaVet.Entity.Product;
import br.com.LuizMario.FarmaVet.Entity.Sell;
import br.com.LuizMario.FarmaVet.Entity.SellItem;
import br.com.LuizMario.FarmaVet.Entity.User;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;
import br.com.LuizMario.FarmaVet.Util.Estoque;

public class FrmNovoSell extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8554273810063313390L;
	private JTable tableItens;
	private JComboBox<Object> cmbVendedor;
	private JFormattedTextField txtData;
	private JScrollPane scpItens;
	private GroupLayout groupLayout;
	private ArrayList<User> users;
	private JLabel lbTotal;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private Sell sell;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmNovoSell dialog = new FrmNovoSell();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmNovoSell() {
		setLocationRelativeTo(null);
		setTitle("Projeto SIGMA - Novo Pedido de Venda");
		
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
		setBounds(100, 100, 405, 377);
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		
		cmbVendedor = new JComboBox<Object>();
		
		JLabel lblData = new JLabel("Data:");
		
		txtData = new JFormattedTextField();
		txtData.setEditable(false);
		
		scpItens = new JScrollPane();
		scpItens.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton btnAdicionarItens = new JButton("Adicionar Itens");
		btnAdicionarItens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionarItensOnClick();
			}
		});
		btnAdicionarItens.setIcon(new ImageIcon(FrmNovoSell.class.getResource("/res/produto.png")));
		
		JButton btnDeletar = new JButton("Deletar Item");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deletarItensOnClick();
			}
		});
		btnDeletar.setIcon(new ImageIcon(FrmNovoSell.class.getResource("/res/Sair.png")));
		
		lbTotal = new JLabel("R$ 00,00");
		lbTotal.setForeground(Color.BLUE);
		lbTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inserirNovoSellOnClick();
			}
		});
		btnSalvar.setIcon(new ImageIcon(FrmNovoSell.class.getResource("/res/incluir.png")));
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelarOnClick();
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmNovoSell.class.getResource("/res/Sair.png")));
		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblData)
								.addComponent(txtData, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVendedor)
								.addComponent(cmbVendedor, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(scpItens, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnAdicionarItens)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnDeletar)
								.addGap(7)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
							.addComponent(lbTotal)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblVendedor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cmbVendedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblData)
							.addGap(1)
							.addComponent(txtData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionarItens, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeletar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scpItens, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbTotal))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnAdicionarItens, btnDeletar});
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnSalvar, btnCancelar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnAdicionarItens, btnDeletar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnSalvar, btnCancelar});
		iniciarComboBoxVendedor();
		criarTable();
		iniciarCampos();
	}

	protected void inserirNovoSellOnClick() {
		this.sell.setVendedor(users.get(cmbVendedor.getSelectedIndex()));
		persistir(this.sell);
	}

	private void persistir(Sell sell) {
		SellDAO sellDAO = new SellDAO();
		try {
			sellDAO.inserir(sell);
			JOptionPane.showMessageDialog(this, "Venda Cadastrada com sucesso !!!!");
			new Estoque().baixarEstoque(sell);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao inserir sell \n"+e.getMessage(),"Projeto SIGMA LTDA",JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void deletarItensOnClick() {
		if (tableItens.getSelectedRow() == -1 ){
			JOptionPane.showMessageDialog(this, "Selecione um iten");
		}else{
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja Realmente Excluir ? ", "Projeto SIGMA LTDA", JOptionPane.YES_NO_CANCEL_OPTION);
			if (opcao == JOptionPane.YES_OPTION){
				sell.getItens().remove(tableItens.getSelectedRow());
				refreshItens(sell.getItens());
				calcularTotal(sell.getItens());
			}
		}
	}

	private void criarTable() {
		String[][] item = null;
		tableItens = new JTable();
		tableItens.setModel(new DefaultTableModel(
				item,
			new String[] {
				"Descricao", "Quantidade"
			}
		) { private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableItens.getColumnModel().getColumn(0).setPreferredWidth(205);
		scpItens.setViewportView(tableItens);
		getContentPane().setLayout(groupLayout);
		
	}

	public void adicionarItensOnClick() {
		FrmAdicionarItens dialog = new FrmAdicionarItens(sell.getItens(), this);
		dialog.setVisible(true);
	}

	protected void cancelarOnClick() {
		cmbVendedor.setSelectedIndex(0);
		lbTotal.setText("R$ 00,00");
		this.setVisible(false);
	}

	private void iniciarCampos() {
		sell = new Sell();
		sell.setData(new Date(new java.util.Date().getTime()));
		SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdfData.format(sell.getData());
		txtData.setText(data);
	}

	private void iniciarComboBoxVendedor() {
		UserDAO userDAO = new UserDAO();
		users = new ArrayList<User>();
		try {
			users = userDAO.listarTodos();
			preencherComboBoxVendedor(users);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao carregar vendedores\n"+e.getMessage(),"Projeto SIGMA LTDA",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void preencherComboBoxVendedor(ArrayList<User> users) {
		for (User user : users) {
			cmbVendedor.addItem(user.getNome());
		}
	}

	public void refreshItens(ArrayList<SellItem> itens) {
		String[][] item = null;
		item = preencherItens(itens);
		tableItens.setModel(new DefaultTableModel(
				item,
			new String[] {
				"Descricao", "Quantidade"
			}
		) { private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableItens.getColumnModel().getColumn(0).setPreferredWidth(205);
		scpItens.setViewportView(tableItens);
		getContentPane().setLayout(groupLayout);
		calcularTotal(itens);
	}

	private void calcularTotal(ArrayList<SellItem> itens) {
		float total = 0;
		for (SellItem sellItem : itens) {
			total += sellItem.getQuantidade() * preco(sellItem.getProduct().getId());
		}
		lbTotal.setText("R$ "+String.valueOf(total));
	}

	private float preco(Integer id) {
		ProductDAO productDAO = new ProductDAO();
		Product product = new Product();
		try {
			product = productDAO.buscarPor(id);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao carregar Preço do produto\n"+e.getMessage(),"Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
		}
		return product.getPreco();
	}

	private String[][] preencherItens(ArrayList<SellItem> itens) {
		String[][] toReturn = new String[itens.size()][2];
		for (int i = 0; i < itens.size(); i++) {
			toReturn[i][0] = descricaoProduto(itens.get(i).getProduct().getId());
			toReturn[i][1] = String.valueOf(itens.get(i).getQuantidade());
		}
		return toReturn;
	}

	private String descricaoProduto(int id) {
		Product product = new Product();
		ProductDAO productDAO = new ProductDAO();
		try {
			product = productDAO.buscarPor(id);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao carregar descrição do produto\n"+e.getMessage(),"Projeto SIGMA LTDA",JOptionPane.ERROR_MESSAGE);
		}
		return product.getNome();
	}
}
