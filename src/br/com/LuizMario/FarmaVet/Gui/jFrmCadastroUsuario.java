package br.com.LuizMario.FarmaVet.Gui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Toolkit;

import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

import br.com.LuizMario.FarmaVet.DAO.UserDAO;
import br.com.LuizMario.FarmaVet.Entity.User;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

import javax.swing.table.DefaultTableModel;


public class jFrmCadastroUsuario extends JDialog {
 
	private static final long serialVersionUID = 6978968899116019365L;
	private JTable tableUsers;
	private JScrollPane scpUsers;
	private List<User> users;

	public jFrmCadastroUsuario() {
		setLocationRelativeTo(null);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(jFrmCadastroUsuario.class.getResource("/res/logo.png")));
		setTitle("Projeto Sigma - Cadastro Usu\u00E1rio");
		setResizable(false);
		setBounds(100, 100, 450, 269);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userOnClick();
			}
		});
		btnNovo.setIcon(new ImageIcon(jFrmCadastroUsuario.class.getResource("/res/incluir.png")));
		btnNovo.setSelectedIcon(new ImageIcon(jFrmCadastroUsuario.class.getResource("/res/incluir.png")));
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editUser();
			}
		});
		btnEditar.setIcon(new ImageIcon(jFrmCadastroUsuario.class.getResource("/res/editar.png")));
		
		scpUsers = new JScrollPane();
		scpUsers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
			}
		});
		btnSair.setIcon(new ImageIcon(jFrmCadastroUsuario.class.getResource("/res/Sair.png")));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				excluirOnClick(tableUsers.getSelectedRow());
			}
		});
		btnExcluir.setIcon(new ImageIcon(jFrmCadastroUsuario.class.getResource("/res/excluir.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scpUsers, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNovo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSair)))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scpUsers, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnSair))
						.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnNovo, btnEditar, btnSair});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnNovo, btnEditar, btnSair, btnExcluir});
	
		refreshTable();
		
		
		getContentPane().setLayout(groupLayout);
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
	}

	protected void excluirOnClick(int id) {
		if (id == -1 ){
			JOptionPane.showMessageDialog(this, "Selecione um usuário!!!");
		}else {
			int option = JOptionPane.showConfirmDialog(this, "Deseja Realmente Excluir", "SIGMA LTDA", JOptionPane.YES_NO_CANCEL_OPTION);
			
			if (option == JOptionPane.OK_OPTION){
				excluir (users.get(tableUsers.getSelectedRow()));
			}
		}
		refreshTable();
	}

	private void excluir(User user) {
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.delete(user.getId());
			JOptionPane.showMessageDialog(this,"Usuario excluído com sucesso !!");
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Não foi possivel inserir \n" + e.getMessage() , "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void editUser() {
		if (tableUsers.getSelectedRow() == -1 ){
			JOptionPane.showMessageDialog(this, "Nenhum usuário, selecionado!!!");
		}else {
			FrmEditUsuario framEditUsuario = new FrmEditUsuario(users.get(tableUsers.getSelectedRow()), this);
			framEditUsuario.setVisible(true);
		}
	}

	public void refreshTable() {
		UserDAO userDAO = new UserDAO();
		users = null;
		String[][] userString = null;
		try {
			users = userDAO.listarTodos();
			userString = preencherValores(users);
		} catch (PersistenciaException e1) {
			e1.printStackTrace();
		}
		
		tableUsers = new JTable();
		tableUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableUsers.setModel(new DefaultTableModel(
			userString,
			new String[] {"C\u00F3digo", "Nome", "Login"}
		));
		tableUsers.getColumnModel().getColumn(0).setPreferredWidth(44);
		tableUsers.getColumnModel().getColumn(1).setPreferredWidth(144);
		scpUsers.setViewportView(tableUsers);
	}
		

	private String[][] preencherValores(List<User> users) {
		String[][] toReturn = new String[users.size()][3];
		for (int i=0; i < users.size(); i++){
			toReturn[i][0] = String.valueOf(users.get(i).getId());
			toReturn[i][1] = users.get(i).getNome();
			toReturn[i][2] = users.get(i).getLogin();
		}
		return toReturn;
	}

	public void userOnClick() {
		FrmCadastroUsuario dialog = new FrmCadastroUsuario(this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
}
