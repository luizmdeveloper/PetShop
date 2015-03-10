package br.com.LuizMario.FarmaVet.Gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Component;

import javax.swing.ImageIcon;

import br.com.LuizMario.FarmaVet.DAO.UserDAO;
import br.com.LuizMario.FarmaVet.Entity.User;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmEditUsuario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7441448000865651846L;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private User user;
	private JPasswordField txtConfirmSenha;
	private jFrmCadastroUsuario control;

	/**
	 * Create the dialog.
	 */
	public FrmEditUsuario(User user, jFrmCadastroUsuario control) {
		this.control = control;
		setLocationRelativeTo(null);
		setTitle("Projeto SIGMA - Editar Usu\u00E1rio");
		this.user = user;
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
		setResizable(false);
		setBounds(100, 100, 290, 253);
		
		JLabel lbNome = new JLabel("Nome:");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lbLogin = new JLabel("Login:");
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		
		JLabel lbSenha = new JLabel("Senha:");
		
		txtSenha = new JPasswordField();
		
		JLabel lblNewLabel = new JLabel("Confirme Senha:");
		
		txtConfirmSenha = new JPasswordField();
		
		moverUser(this.user);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editUserOnClick(user);
			}
		});
		btnSalvar.setIcon(new ImageIcon(FrmEditUsuario.class.getResource("/res/incluir.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelarOnClick();
				setVisible(false);
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmEditUsuario.class.getResource("/res/Sair.png")));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lbNome)
						.addComponent(lbLogin, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbSenha))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtConfirmSenha, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(txtLogin, Alignment.LEADING)
							.addComponent(txtNome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbLogin)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbSenha)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtConfirmSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnSalvar, btnCancelar});
		getContentPane().setLayout(groupLayout);
	}

	private void moverUser(User user) {
		txtNome.setText(user.getNome());
		txtLogin.setText(user.getLogin());
		txtSenha.setText(user.getPassoword());
		txtConfirmSenha.setText(user.getPassoword());
		txtNome.setRequestFocusEnabled(true);
	}

	protected void editUserOnClick(User user) {
		User userEdit = new User();
		String senha = new String(txtSenha.getPassword());
		String confirmSenha = new String (txtConfirmSenha.getPassword());
		if (! senha.equals(confirmSenha) ){
			JOptionPane.showMessageDialog(this, "Digite a senha correta","Erro", JOptionPane.ERROR_MESSAGE);
			txtSenha.setRequestFocusEnabled(true);
		} else {
			userEdit.setId(user.getId());
			userEdit.setNome(txtNome.getText());
			userEdit.setLogin(txtLogin.getText());
			userEdit.setPassoword(senha);
			persistir(userEdit);
			JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso!!!");
			limparCampos();
			this.control.refreshTable();
		}
	}

	protected void cancelarOnClick() {
		limparCampos();
	}

	private void limparCampos() {
		txtNome.setText("");
		txtLogin.setText("");
		txtSenha.setText("");
		txtConfirmSenha.setText("");
	}
	
	protected void persistir (User user){
		try {
			UserDAO userDAO = new UserDAO();
			userDAO.atualizar(user, user.getId());
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Não foi possivel inserir \n" + e.getMessage() , "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
