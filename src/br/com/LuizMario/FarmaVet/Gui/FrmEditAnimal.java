package br.com.LuizMario.FarmaVet.Gui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.LuizMario.FarmaVet.DAO.AnimalDAO;
import br.com.LuizMario.FarmaVet.DAO.CostumerDAO;
import br.com.LuizMario.FarmaVet.Entity.Animal;
import br.com.LuizMario.FarmaVet.Entity.AnimalType;
import br.com.LuizMario.FarmaVet.Entity.Costumer;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class FrmEditAnimal extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2939803896855362391L;
	private JTextField txtNome;
	private JTextField txtRaca;
	private JTextField txtIdade;
	private JComboBox<Object> cmbTipo;
	private JComboBox<Object> cmbCliente;
	@SuppressWarnings("unused")
	private Animal animal;
	private ArrayList<Costumer> costumers;
	private FrmCadastroAnimal control;
	/**
	 * Create the dialog.
	 */
	public FrmEditAnimal(Animal animal, FrmCadastroAnimal control) {
		this.control = control;
		setLocationRelativeTo(null);
		this.animal = animal;
		setTitle("Projeto SIGMA - Novo Animal");
		
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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmEditAnimal.class.getResource("/res/logo.png")));
		setBounds(100, 100, 305, 330);
		
		JLabel lblNome = new JLabel("Nome:");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblRa = new JLabel("Ra\u00E7a:");
		
		txtRaca = new JTextField();
		txtRaca.setColumns(10);
		
		JLabel lblIdade = new JLabel("Idade:");
		
		txtIdade = new JTextField();
		txtIdade.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		
		cmbTipo = new JComboBox<Object>();
		
		JLabel lblCliente = new JLabel("Cliente:");
		
		cmbCliente = new JComboBox<Object>();
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarAnimalOnClick(animal);
			}
		});
		btnNovo.setIcon(new ImageIcon(FrmEditAnimal.class.getResource("/res/incluir.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmEditAnimal.class.getResource("/res/Sair.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTipo)
						.addComponent(cmbTipo, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCliente)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNovo)
							.addGap(18)
							.addComponent(btnCancelar))
						.addComponent(lblNome)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblRa)
									.addComponent(txtRaca, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(2)
										.addComponent(txtIdade, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
									.addComponent(lblIdade))
								.addGap(28))
							.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
							.addComponent(cmbCliente, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblRa)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtRaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblTipo))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblIdade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtIdade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cmbTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblCliente)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cmbCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar))
					.addGap(21))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnNovo, btnCancelar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnNovo, btnCancelar});
		getContentPane().setLayout(groupLayout);
		inicializarCombo();
		iniciarCampos(animal);
	}

	protected void atualizarAnimalOnClick(Animal animal) {
		Animal animalEdit = new Animal();
		animalEdit.setId(animal.getId());
		animalEdit.setNome(txtNome.getName());
		animalEdit.setBreed(txtRaca.getName());
		animalEdit.setType((AnimalType) cmbTipo.getSelectedItem());
		Costumer costumer = selecionarCostumer(cmbCliente.getSelectedItem());
		animalEdit.setCostumer(costumer);
		animalEdit.setIdade(Integer.parseInt(txtIdade.getText()));
		persistir(animalEdit);
	}

	private void iniciarCampos(Animal animal) {
		txtIdade.setText(String.valueOf(animal.getIdade()));
		txtNome.setText(animal.getNome());
		txtRaca.setText(animal.getBreed());
		cmbTipo.setSelectedItem(animal.getType());
		cmbCliente.setSelectedIndex(selecionarComboBox(animal.getCostumer().getId()));
	}

	private int selecionarComboBox(int id) {
		CostumerDAO costumerDAO = new CostumerDAO();
		Costumer costumer = new Costumer();
		int posicao = 0;
		try {
			costumer = costumerDAO.buscarPor(id);
			for (int i = 0; i < costumers.size(); i++) {
				if (costumers.get(i).getName().equals(costumer.getName())){
					posicao = i;
				}
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao selecionar costumer no combo \n"+e.getMessage(), "Porjeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
		}
		return posicao;
	}

	private void persistir(Animal animal) {
		AnimalDAO animalDAO = new AnimalDAO();
		try {
			animalDAO.atualizar(animal, animal.getId());
			JOptionPane.showMessageDialog(this, "Animal atualizado com sucesso !!!");
			limparCampos();
			this.control.refreshTableAnimal();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao atualizar Animal \n"+e.getMessage(), "Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limparCampos() {
		txtIdade.setText("");
		txtNome.setText("");
		txtRaca.setText("");
		cmbCliente.setSelectedIndex(0);
		cmbTipo.setSelectedIndex(0);
	}

	private Costumer selecionarCostumer(Object selectedItem) {
		CostumerDAO costumerDAO = new CostumerDAO();
		Costumer costumer = new Costumer();
		try {
			costumer = costumerDAO.buscar(String.valueOf(selectedItem));
		} catch (PersistenciaException e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar Costumer \n"+e.getMessage(),"Porjeto SGIMA LTDA", JOptionPane.ERROR_MESSAGE);
		}
		return costumer;
	}

	private void inicializarCombo() {
		DefaultComboBoxModel<Object> dcmbTipo = new DefaultComboBoxModel<Object>(AnimalType.values());
		cmbTipo.setModel(dcmbTipo);
		carregarCamposComboCliete();
	}

	private void carregarCamposComboCliete() {
		costumers = new ArrayList<Costumer>();
		CostumerDAO costumerDAO = new CostumerDAO();
		try {
			costumers = costumerDAO.listarTodos();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao carregar Customer \n"+e.getMessage(), "Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
		}
		addCombo(costumers);
	}

	private void addCombo(ArrayList<Costumer> costumers) {
		for (Costumer costumer : costumers) {
			cmbCliente.addItem(costumer.getName());
		}
	}
}
