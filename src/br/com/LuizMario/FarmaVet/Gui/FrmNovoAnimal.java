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

public class FrmNovoAnimal extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2939803896855362391L;
	private JTextField txtNome;
	private JTextField txtRaca;
	private JTextField txtIdade;
	private JComboBox<Object> cmbTipo;
	private JComboBox<Object> cmbCliente;
	private FrmCadastroAnimal control;

	/**
	 * Create the dialog.
	 */
	public FrmNovoAnimal(FrmCadastroAnimal control) {
		this.control = control;
		setLocationRelativeTo(null);
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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmNovoAnimal.class.getResource("/res/logo.png")));
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
				inserirNovoAnimalOnClick();
			}
		});
		btnNovo.setIcon(new ImageIcon(FrmNovoAnimal.class.getResource("/res/incluir.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmNovoAnimal.class.getResource("/res/Sair.png")));
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
	}

	protected void inserirNovoAnimalOnClick() {
		Animal animal = new Animal();
		animal.setNome(txtNome.getText());
		animal.setBreed(txtRaca.getText());
		animal.setIdade(Integer.parseInt(txtIdade.getText()));
		animal.setType((AnimalType) cmbTipo.getSelectedItem());
		Costumer costumer = selecionarCostumer(cmbCliente.getSelectedItem());
		animal.setCostumer(costumer);
		persistir(animal);
	}

	private void persistir(Animal animal) {
		AnimalDAO animalDAO = new AnimalDAO();
		try {
			animalDAO.inserir(animal);
			JOptionPane.showMessageDialog(this, "Animal inserido com sucesso !!!");
			limparCampos();
			this.control.refreshTableAnimal();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao inserir Animal \n"+e.getMessage(), "Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
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
		ArrayList<Costumer> costumers = new ArrayList<Costumer>();
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
