package br.com.LuizMario.FarmaVet.Gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import br.com.LuizMario.FarmaVet.DAO.UserDAO;
import br.com.LuizMario.FarmaVet.Entity.User;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;
import java.awt.Toolkit;

public class FrmLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -501941567743838624L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLogin.class.getResource("/res/logo.png")));
		this.setLocationRelativeTo(null);
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
		setTitle("Projeto SIGMA - Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 322, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLogin = new JLabel("Login:");
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		
		txtSenha = new JPasswordField();
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logarOnClick();
			}
		});
		btnLogin.setIcon(new ImageIcon(FrmLogin.class.getResource("/res/loginButon.png")));
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnSair.setIcon(new ImageIcon(FrmLogin.class.getResource("/res/Sair.png")));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmLogin.class.getResource("/res/login.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(label)
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnLogin)
							.addGap(18)
							.addComponent(btnSair)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblSenha)
								.addComponent(lblLogin)
								.addComponent(txtSenha, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
								.addComponent(txtLogin))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(120))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addGap(61))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblLogin)
							.addGap(2)
							.addComponent(txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblSenha)
							.addGap(7)
							.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSair))
							.addGap(20))))
		);
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {btnLogin, btnSair});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnLogin, btnSair});
		contentPane.setLayout(gl_contentPane);
	}

	protected void logarOnClick() {
		User user = new User();
		user.setLogin(txtLogin.getText());
		user.setPassoword( new String(txtSenha.getPassword()));
		verificarLogin(user);
	}

	private void verificarLogin(User user) {
		UserDAO userDAO = new UserDAO();
		try {
			if(userDAO.logar(user)){
				FrmPrincipal dialog = new FrmPrincipal();
				dialog.setVisible(true);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setVisible(false);
			}else{
				JOptionPane.showMessageDialog(this, "Login e senha errado, por favor tente novamente","Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
				limparCampos();
				txtLogin.setRequestFocusEnabled(true);
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Aconteceu um erro ao logar\n"+e.getMessage(),"Projeto SIGMA LTDA", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void limparCampos() {
		txtLogin.setText("");
		txtSenha.setText("");
	}
}
