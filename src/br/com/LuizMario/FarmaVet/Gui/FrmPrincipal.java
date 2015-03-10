package br.com.LuizMario.FarmaVet.Gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class FrmPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6205600715751499653L;
	private JPanel contentPane;

	public FrmPrincipal() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);  
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Gustavo\\Pictures\\Sistema\\png\\logo.png"));
		setTitle("Projeto Sigma");
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBarPrincipal = new JMenuBar();
		setJMenuBar(menuBarPrincipal);
		
		JMenu menuCadastro = new JMenu("Cadastro");
		menuBarPrincipal.add(menuCadastro);
		
		JMenuItem menuItemUsuario = new JMenuItem("Usuario");
		menuItemUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userOnClick();
			}
		});
		menuItemUsuario.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/res/usuario.png")));
		menuCadastro.add(menuItemUsuario);
		
		JMenuItem menuItemProduto = new JMenuItem("Produto");
		menuItemProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmCadastroProduto dialog = new FrmCadastroProduto();
				dialog.setVisible(true);
			}
		});
		menuItemProduto.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/res/produto.png")));
		menuCadastro.add(menuItemProduto);
		
		JMenuItem menuItemAnimal = new JMenuItem("Animal");
		menuItemAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmCadastroAnimal dialog = new FrmCadastroAnimal();
				dialog.setVisible(true);
			}
		});
		menuItemAnimal.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/res/animal.png")));
		menuCadastro.add(menuItemAnimal);
		
		JMenuItem menuItemCostumer = new JMenuItem("Costumer");
		menuItemCostumer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerOnClick();
			}
		});
		menuItemCostumer.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/res/customer.png")));
		menuCadastro.add(menuItemCostumer);
		
		JMenu menuMovimento = new JMenu("Movimento");
		menuBarPrincipal.add(menuMovimento);
		
		JMenuItem menuItemAgendar = new JMenuItem("Agendar");
		menuItemAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scheduleOnCilck();
			}
		});
		menuItemAgendar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/res/agenda.png")));
		menuMovimento.add(menuItemAgendar);
		
		JMenuItem menuItemPedido = new JMenuItem("Pedido");
		menuItemPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sellOnClick();
			}
		});
		menuItemPedido.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/res/vendas.png")));
		menuMovimento.add(menuItemPedido);
		
		JMenu menuSair = new JMenu("Sair");
		menuBarPrincipal.add(menuSair);
		
		JMenuItem menuItemSair = new JMenuItem("Sair");
		menuItemSair.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/res/Sair.png")));
		menuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventSair) {
				sairOnClick();
			}
		});
		menuSair.add(menuItemSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 251, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
		
	}

	protected void sellOnClick() {
		FrmCadastroSell dialog = new FrmCadastroSell();
		dialog.setVisible(true);
	}

	protected void scheduleOnCilck() {
		FrmCadastroSchedule dialog = new FrmCadastroSchedule();
		dialog.setVisible(true);
	}

	protected void customerOnClick() {
		FrmCadastroCustomer frame = new FrmCadastroCustomer();
		frame.setVisible(true);
	}

	public void sairOnClick() {
		int selecdOption = JOptionPane.showConfirmDialog(this, "Deseja Realmente Sair", "Projeto SIGMA LTDA", JOptionPane.YES_NO_OPTION);
		if (selecdOption == JOptionPane.YES_OPTION){
			FrmLogin dialog = new FrmLogin();
			dialog.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(false);
		}
	}
	
	public void userOnClick() {
		jFrmCadastroUsuario dialog = new jFrmCadastroUsuario();
		dialog.setVisible(true);
	}

}
