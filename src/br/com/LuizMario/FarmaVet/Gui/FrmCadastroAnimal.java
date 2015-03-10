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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import br.com.LuizMario.FarmaVet.DAO.AnimalDAO;
import br.com.LuizMario.FarmaVet.DAO.CostumerDAO;
import br.com.LuizMario.FarmaVet.Entity.Animal;
import br.com.LuizMario.FarmaVet.Entity.Costumer;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class FrmCadastroAnimal extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3853389524355815736L;
	private JTable tableAnimal;
	private List<Animal> animais;
	private String[][] animalString;
	private JScrollPane scpAnimal;
	private GroupLayout groupLayout;

	/**
	 * Create the dialog.
	 */
	public FrmCadastroAnimal() {
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Projeto SIGMA - Cadastro Animal");
		
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCadastroAnimal.class.getResource("/res/logo.png")));
		setBounds(100, 100, 524, 320);
		
		scpAnimal = new JScrollPane();
		scpAnimal.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastroNovoAnimalOnClick();
			}
		});
		btnNovo.setIcon(new ImageIcon(FrmCadastroAnimal.class.getResource("/res/incluir.png")));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastroEditarAnimalOnClick();
			}
		});
		btnEditar.setIcon(new ImageIcon(FrmCadastroAnimal.class.getResource("/res/editar.png")));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastroExcluirAnimalOnClick();
			}
		});
		btnExcluir.setIcon(new ImageIcon(FrmCadastroAnimal.class.getResource("/res/excluir.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmCadastroAnimal.class.getResource("/res/Sair.png")));
		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scpAnimal, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNovo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scpAnimal, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo)
						.addComponent(btnEditar)
						.addComponent(btnExcluir)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnNovo, btnEditar, btnExcluir, btnCancelar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnNovo, btnEditar, btnExcluir, btnCancelar});
		
		animalString = null;
		try {
			AnimalDAO animalDAO = new AnimalDAO();
			animais = animalDAO.listarTodos();
			animalString = preencherString(animais);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro SQL \n"+e.getMessage(), "Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
		}
		
		refreshTableAnimal();
	}

	protected void refreshTableAnimal() {
		tableAnimal = new JTable();
		tableAnimal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAnimal.setModel(new DefaultTableModel(
				animalString,
			new String[] {
				"C\u00F3digo", "Cliente", "Animal", "Tipo", "Ra\u00E7a"
			}
		));
		tableAnimal.getColumnModel().getColumn(0).setPreferredWidth(47);
		tableAnimal.getColumnModel().getColumn(1).setPreferredWidth(146);
		tableAnimal.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableAnimal.getColumnModel().getColumn(3).setPreferredWidth(79);
		scpAnimal.setViewportView(tableAnimal);
		getContentPane().setLayout(groupLayout);
	}

	private String[][] preencherString(List<Animal> lista) {
		String[][] toReturn = new String[lista.size()][5];
		for (int i = 0; i < lista.size(); i++) {
			Costumer toReturnCostumer = new Costumer();
			CostumerDAO costumerDAO = new CostumerDAO();
			try {
				toReturnCostumer = costumerDAO.buscarPor(lista.get(i).getCostumer().getId());
			} catch (PersistenciaException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this,"Erro ao buscar usuário \n"+e.getMessage(), "Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
			}
			toReturn[i][0] = String.valueOf(lista.get(i).getId());
			toReturn[i][1] = toReturnCostumer.getName();
			toReturn[i][2] = lista.get(i).getNome();
			toReturn[i][3] = lista.get(i).getType().toString();
			toReturn[i][4] = lista.get(i).getBreed();
		}
		return toReturn;
	}

	protected void cadastroExcluirAnimalOnClick() {
		if (tableAnimal.getSelectedRow() == -1 ){
			JOptionPane.showMessageDialog(this, "Selecione um Animal");
		}else {
			AnimalDAO animalDAO = new AnimalDAO();
			try {
				int opcao = JOptionPane.showConfirmDialog(this, "Deseja Excluir ?", "Projeto SIGMA LTDA", JOptionPane.OK_CANCEL_OPTION);
				
				if (opcao == JOptionPane.OK_OPTION){
					animalDAO.delete(animais.get(tableAnimal.getSelectedRow()).getId());
					JOptionPane.showMessageDialog(this,"Animal, excluido com sucesso !!!");
				}
				
			} catch (PersistenciaException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this,"Erro ao excluir Animal \n"+e.getMessage(), "Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
			} 
		}
		refreshTableAnimal();
	}

	protected void cadastroEditarAnimalOnClick() {
		FrmEditAnimal dialog = new FrmEditAnimal(animais.get(tableAnimal.getSelectedRow()), this);
		dialog.setVisible(true);
	}

	protected void cadastroNovoAnimalOnClick() {
		FrmNovoAnimal dialog = new FrmNovoAnimal(this);
		dialog.setVisible(true);
	}
}
