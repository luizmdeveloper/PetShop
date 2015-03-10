package br.com.LuizMario.FarmaVet.Gui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import br.com.LuizMario.FarmaVet.DAO.AnimalDAO;
import br.com.LuizMario.FarmaVet.DAO.ScheduleDAO;
import br.com.LuizMario.FarmaVet.Entity.Animal;
import br.com.LuizMario.FarmaVet.Entity.Schedule;
import br.com.LuizMario.FarmaVet.Entity.ScheduleType;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class FrmEditSchedule extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 401321910394969765L;
	private JTextField txtTotal;
	private JComboBox<Object> cmbAnimal;
	private JComboBox<Object> cmbTipo;
	private ArrayList<Animal> animais;
	private JFormattedTextField txtData;
	private MaskFormatter maskData;
	@SuppressWarnings("unused")
	private Schedule schedule;
	private FrmCadastroSchedule control;

	/**
	 * Create the dialog.
	 */
	public FrmEditSchedule(Schedule schedule, FrmCadastroSchedule control) {
		this.control = control;
		setLocationRelativeTo(null);
		this.schedule = schedule;
		setTitle("Projeto SIGMA - Editar Schedule");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmEditSchedule.class.getResource("/res/logo.png")));
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
		
		setBounds(100, 100, 315, 266);
		
		JLabel lblAnimal = new JLabel("Animal:");
		
		cmbAnimal = new JComboBox<Object>();
		
		JLabel lblTipo = new JLabel("Tipo:");
		
		cmbTipo = new JComboBox<Object>();
		
		JLabel lblTotal = new JLabel("Data:");
		
		try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro na criação da maáscara \n"+e.getMessage(),"Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
		}
		
		txtData = new JFormattedTextField();
		maskData.install(txtData);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editarScheduleOnClick(schedule);
			}
		});
		btnNovo.setIcon(new ImageIcon(FrmEditSchedule.class.getResource("/res/incluir.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelarOnClick();
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmEditSchedule.class.getResource("/res/Sair.png")));
		
		JLabel lblTotal_1 = new JLabel("Total:");
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAnimal)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTipo)
										.addComponent(cmbTipo, 0, 145, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTotal)
										.addComponent(txtData, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
									.addGap(35))
								.addComponent(cmbAnimal, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
							.addGap(21))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTotal_1)
							.addContainerGap(271, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNovo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar)
							.addContainerGap(103, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAnimal)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cmbAnimal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTipo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cmbTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTotal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(lblTotal_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnNovo, btnCancelar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnNovo, btnCancelar});
		getContentPane().setLayout(groupLayout);
		inicarComboBoxs();
		iniciarCampos(schedule);
	}

	private void iniciarCampos(Schedule schedule) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String data = format.format(schedule.getData());
		txtTotal.setText(String.valueOf(schedule.getTotal()));
		txtData.setText(data);
		cmbTipo.setSelectedItem(schedule.getType());
		cmbAnimal.setSelectedItem(selecionarComboBoxAnimal(schedule.getIdanimal().getId())); 
	}

	private int selecionarComboBoxAnimal(Integer id) {
		int posicao = 0;
		for (int i = 0; i < animais.size(); i++) {
			if (animais.get(i).getId() == id){
				posicao = i;
			}
		}
		return posicao;
	}

	protected void editarScheduleOnClick(Schedule schedule) {
		Schedule scheduleEdit = new Schedule();
		Animal animal = new Animal();
		scheduleEdit.setId(schedule.getId());
		animal.setId(animais.get(cmbAnimal.getSelectedIndex()).getId());
		scheduleEdit.setData(converterData(txtData.getText()));
		scheduleEdit.setIdanimal(animal);
		scheduleEdit.setType((ScheduleType) cmbTipo.getSelectedItem());
		scheduleEdit.setTotal(Float.parseFloat(txtTotal.getText()));
		persistir(scheduleEdit);
	}

	private void persistir(Schedule schedule) {
		ScheduleDAO scheduleDAO = new ScheduleDAO();
		try {
			scheduleDAO.atualizar(schedule, schedule.getId());
			JOptionPane.showMessageDialog(this, "Schedule, foi alterado com sucesso !!");
			limparCampos();
			this.control.refreshShcedule();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro na alteração do Schedule \n"+e.getMessage(),"Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private Date converterData(String text) {
	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
	    java.sql.Date data = null;
	    try {
			data = new Date(format.parse(text).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro na conversão da Data \n"+e.getMessage(),"Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
		}  
		return data;
	}

	protected void cancelarOnClick() {
		limparCampos();
	}

	private void limparCampos() {
		cmbAnimal.setSelectedIndex(0);
		cmbTipo.setSelectedIndex(0);
		txtTotal.setText("");
		txtData.setText("");
	}

	private void inicarComboBoxs() {
		DefaultComboBoxModel<Object> dcmbTipo = new DefaultComboBoxModel<>(ScheduleType.values());
		cmbTipo.setModel(dcmbTipo);
		adicionarComboBoxAnimal();
	}

	private void adicionarComboBoxAnimal() {
		AnimalDAO animalDAO = new AnimalDAO();
		animais = new ArrayList<Animal>();
		try {
			animais = animalDAO.listarTodos();
			for (Animal animal : animais) {
				cmbAnimal.addItem(animal.getNome());
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao carregar aniamais no comboBox\n"+e.getMessage(), "Porjeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
}
