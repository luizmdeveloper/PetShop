package br.com.LuizMario.FarmaVet.Gui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import br.com.LuizMario.FarmaVet.DAO.AnimalDAO;
import br.com.LuizMario.FarmaVet.DAO.ScheduleDAO;
import br.com.LuizMario.FarmaVet.Entity.Animal;
import br.com.LuizMario.FarmaVet.Entity.Schedule;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class FrmCadastroSchedule extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8429717785711089409L;
	private JTable tableSchedule;
	private GroupLayout groupLayout;
	private JScrollPane scpSchedule;
	private ArrayList<Schedule> schedules;

	/**
	 * Create the dialog.
	 */
	public FrmCadastroSchedule() {
		setTitle("Projeto SIGMA - Cadastro Schedule");
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCadastroSchedule.class.getResource("/res/logo.png")));
		setBounds(100, 100, 450, 300);
		
		scpSchedule = new JScrollPane();
		scpSchedule.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				novoSccheduleOnClick();
			}
		});
		btnNovo.setIcon(new ImageIcon(FrmCadastroSchedule.class.getResource("/res/incluir.png")));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarScheduleOnClick();
			}
		});
		btnEditar.setIcon(new ImageIcon(FrmCadastroSchedule.class.getResource("/res/editar.png")));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				excluirScheduleOnClick();
			}
		});
		btnExcluir.setIcon(new ImageIcon(FrmCadastroSchedule.class.getResource("/res/excluir.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				return;
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmCadastroSchedule.class.getResource("/res/Sair.png")));
		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scpSchedule, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNovo)
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
					.addGap(19)
					.addComponent(scpSchedule, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo)
						.addComponent(btnEditar)
						.addComponent(btnExcluir)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(25))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnNovo, btnEditar, btnExcluir, btnCancelar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnNovo, btnEditar, btnExcluir, btnCancelar});
		
		refreshShcedule();
	}

	protected void refreshShcedule() {
		ScheduleDAO shceduleDAO = new ScheduleDAO();
		schedules = new ArrayList<Schedule>();
		String[][] shcedulesString = null;
		try {
			schedules = shceduleDAO.listarTodos();
			shcedulesString = preenhcerString(schedules);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao carregar Agenda \n "+e.getMessage(), "Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
		}
		
		tableSchedule = new JTable();
		tableSchedule.setModel(new DefaultTableModel(
				shcedulesString,
			new String[] {
				"Animal", "Tipo", "Total", "Data"
			}
		));
		tableSchedule.getColumnModel().getColumn(0).setPreferredWidth(127);
		tableSchedule.getColumnModel().getColumn(1).setPreferredWidth(109);
		tableSchedule.getColumnModel().getColumn(2).setPreferredWidth(80);
		scpSchedule.setViewportView(tableSchedule);
		getContentPane().setLayout(groupLayout);
	}

	private String[][] preenhcerString(ArrayList<Schedule> lista) {
		String[][] toReturn = new String[lista.size()][4];
		for (int i = 0; i < lista.size(); i++) {
			Animal animal = retornarNome(lista.get(i).getIdanimal().getId());
			SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdfData.format(lista.get(i).getData());
			toReturn[i][0] = animal.getNome();
			toReturn[i][1] = lista.get(i).getType().toString();
			toReturn[i][2] = String.valueOf(lista.get(i).getTotal());
			toReturn[i][3] = data;
		}
		return toReturn;
	}

	private Animal retornarNome(Integer id) {
		AnimalDAO animalDAO = new AnimalDAO();
		Animal animal = new Animal();
		try {
			animal = animalDAO.buscarPor(id);
		} catch (PersistenciaException e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar Animail \n "+e.getMessage(),"Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return animal;
	}

	protected void excluirScheduleOnClick() {
		if (tableSchedule.getSelectedRow() == -1 ){
			JOptionPane.showMessageDialog(this, "Selecione um Schedule !!!");
		}else{
			int opcao = JOptionPane.showConfirmDialog(this, "Deseja Excluir ?", "Projeto SIGMA LTDA", JOptionPane.YES_NO_CANCEL_OPTION);
			
			if (opcao == JOptionPane.OK_OPTION){
				ScheduleDAO scheduleDAO = new ScheduleDAO();
				try {
					scheduleDAO.delete(schedules.get(tableSchedule.getSelectedRow()).getId());
				} catch (PersistenciaException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Erro na exclusão Schedule \n","Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
				}
			}
			refreshShcedule();
		}
	}

	protected void atualizarScheduleOnClick() {
		if (tableSchedule.getSelectedRow() == -1 ){
			JOptionPane.showMessageDialog(this, "Selecione um Schedule !!!");
		}else{
			FrmEditSchedule dialog = new FrmEditSchedule(schedules.get(tableSchedule.getSelectedRow()), this);
			dialog.setVisible(true);
		}
	}

	protected void novoSccheduleOnClick() {
		FrmNovoSchedule dialog = new FrmNovoSchedule(this);
		dialog.setVisible(true);
	}
}
