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

public class FrmCadastroUsuario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7441448000865651846L;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmSenha;
	private jFrmCadastroUsuario control;
	
	public FrmCadastroUsuario(jFrmCadastroUsuario control) {
		this.control = control;
		setLocationRelativeTo(null);
		setTitle("Projeto SIGMA - Cadastro Usu\u00E1rio");
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
		setBounds(100, 100, 307, 253);
		
		JLabel lbNome = new JLabel("Nome:");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lbLogin = new JLabel("Login:");
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		
		JLabel lbSenha = new JLabel("Senha:");
		
		txtSenha = new JPasswordField();
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				incluirUserOnClick();
			}
		});
		btnSalvar.setIcon(new ImageIcon(FrmCadastroUsuario.class.getResource("/res/incluir.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelarOnClick();
				setVisible(false);
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmCadastroUsuario.class.getResource("/res/Sair.png")));
		
		txtConfirmSenha = new JPasswordField();
		
		JLabel lblNewLabel = new JLabel("Confirme Senha:");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtLogin)
						.addComponent(lbNome)
						.addComponent(lbLogin, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar))
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbSenha))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(txtConfirmSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(120, Short.MAX_VALUE))
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
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnCancelar, btnSalvar});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {txtConfirmSenha, txtSenha});
		getContentPane().setLayout(groupLayout);
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

	protected void incluirUserOnClick() {
		User persisteUser = moverUser();
		String confirmar = new String(txtConfirmSenha.getPassword());
		if (! persisteUser.getPassoword().equals(confirmar) ){
			JOptionPane.showMessageDialog(this, "Senha Diferente, por favor digite novamente", "Erro", JOptionPane.ERROR_MESSAGE);
		}else{
			presistir(persisteUser);
			JOptionPane.showMessageDialog(this, "Usuário cadastro com sucesso");
			control.refreshTable();
			limparCampos();
		}
	}
	
	protected void presistir (User user){
		try {
			UserDAO userDAO = new UserDAO();
			userDAO.inserir(user);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Não foi possivel inserir \n" + e.getMessage() , "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	protected User moverUser() {
		User user = new User();
		user.setLogin(txtLogin.getText());
		user.setNome(txtNome.getText());
		String passsowrd = new String( txtSenha.getPassword() ); 
		user.setPassoword(passsowrd);
		return user;
	}
}
