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

import br.com.LuizMario.FarmaVet.DAO.CostumerDAO;
import br.com.LuizMario.FarmaVet.Entity.Costumer;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class FrmCadastroCustomer extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 996662760499600480L;
	private List<Costumer> costumers;
	private JTable tableCostumer;
	private JScrollPane scpCostumer;
	private GroupLayout groupLayout;

	public FrmCadastroCustomer() {
		setLocationRelativeTo(null);
		
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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCadastroCustomer.class.getResource("/res/logo.png")));
		setTitle("Projeto Sigma - Cadastro Custumer");
		setResizable(false);
		setBounds(100, 100, 499, 289);
		
		scpCostumer = new JScrollPane();
		scpCostumer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton btnSalvar = new JButton("Novo");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cosumterNovoOnClick();
			}
		});
		btnSalvar.setIcon(new ImageIcon(FrmCadastroCustomer.class.getResource("/res/incluir.png")));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				costumerEditarOnClick();
			}
		});
		btnEditar.setIcon(new ImageIcon(FrmCadastroCustomer.class.getResource("/res/editar.png")));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				costumerExcluirOnClick();
			}
		});
		btnExcluir.setIcon(new ImageIcon(FrmCadastroCustomer.class.getResource("/res/excluir.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmCadastroCustomer.class.getResource("/res/Sair.png")));
		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scpCostumer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scpCostumer, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditar)
						.addComponent(btnExcluir)
						.addComponent(btnCancelar))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnSalvar, btnEditar, btnExcluir, btnCancelar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnSalvar, btnEditar, btnExcluir, btnCancelar});
		
		refreshTableCostumer();
	}

	protected void refreshTableCostumer() {
		CostumerDAO costumerDAO = new CostumerDAO();
		String[][] costumerString = null;
		try {
			costumers = costumerDAO.listarTodos();
			costumerString = preencherArray(costumers);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		
		tableCostumer = new JTable();
		tableCostumer.setModel(new DefaultTableModel(
				costumerString,
			new String[] {
				"C\u00F3digo", "Nome", "Endere\u00E7o", "N\u00FAmero", "Telefone"
			}
		));
		tableCostumer.getColumnModel().getColumn(0).setPreferredWidth(44);
		tableCostumer.getColumnModel().getColumn(1).setPreferredWidth(122);
		tableCostumer.getColumnModel().getColumn(2).setPreferredWidth(138);
		tableCostumer.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableCostumer.getColumnModel().getColumn(4).setPreferredWidth(89);
		scpCostumer.setViewportView(tableCostumer);
		getContentPane().setLayout(groupLayout);
	}

	protected void costumerExcluirOnClick() {
		if (tableCostumer.getSelectedRow() == -1 ){
			JOptionPane.showMessageDialog(this, "Selecione um Costumer!!!");
		}else {
			CostumerDAO costumerDAO = new CostumerDAO();
			int opcao = JOptionPane.showConfirmDialog(this, "Desjesa Excluir esse costumer", "Projeto SIGIMA LTDA", JOptionPane.OK_CANCEL_OPTION);
			if (opcao == JOptionPane.OK_OPTION){
				try {
					costumerDAO.delete( costumers.get(tableCostumer.getSelectedRow()).getId() );
				} catch (PersistenciaException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Não foi possivel inserir \n" + e.getMessage() , "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		refreshTableCostumer();	
		}
	}

	protected void costumerEditarOnClick() {
		if (tableCostumer.getSelectedRow() == -1 ){
			JOptionPane.showMessageDialog(this, "Selecione um costumer !!!");
		}else {
			this.setVisible(false);
			FrmEditCostumer frame = new FrmEditCostumer(costumers.get(tableCostumer.getSelectedRow()), this);
			frame.setVisible(true);
		}
	}

	protected void cosumterNovoOnClick() {
		FrmNovoCostumer dialog = new FrmNovoCostumer(this);
		dialog.setVisible(true);
	}

	private String[][] preencherArray(List<Costumer> lista) {
		String[][] toReturn = new String[lista.size()][5];
		for (int i = 0; i < lista.size(); i++) {
			toReturn[i][0] = String.valueOf( lista.get(i).getId() );
			toReturn[i][1] = lista.get(i).getName();
			toReturn[i][2] = lista.get(i).getEndereco();
			toReturn[i][3] = String.valueOf( lista.get(i).getNumber() );
			toReturn[i][4] = lista.get(i).getTelefone();
		}
		return toReturn;
	}
}
