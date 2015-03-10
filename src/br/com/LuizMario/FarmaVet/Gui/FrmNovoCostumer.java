package br.com.LuizMario.FarmaVet.Gui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.LuizMario.FarmaVet.DAO.CostumerDAO;
import br.com.LuizMario.FarmaVet.Entity.Costumer;
import br.com.LuizMario.FarmaVet.Exception.PersistenciaException;

public class FrmNovoCostumer extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2053776758340609408L;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JFormattedTextField txtTelefone;
	private MaskFormatter maskTelefone;
	private JFormattedTextField txtIdade;
	private FrmCadastroCustomer control;

	/**
	 * Create the dialog.
	 */
	public FrmNovoCostumer(FrmCadastroCustomer control) {
		this.control = control;
		setLocationRelativeTo(null);
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
		
		setTitle("Projeto SIGMA - Cadastro Costumer");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmNovoCostumer.class.getResource("/res/logo.png")));
		setBounds(100, 100, 373, 264);
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				costumerIncluirOnClick();
			}
		});
		btnSalvar.setIcon(new ImageIcon(FrmNovoCostumer.class.getResource("/res/incluir.png")));
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
				setVisible(false);
			}
		});
		btnCancelar.setIcon(new ImageIcon(FrmNovoCostumer.class.getResource("/res/Sair.png")));
		
		JLabel lbNome = new JLabel("Nome:");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lbIdade = new JLabel("Idade:");
		
		JLabel lbEndereo = new JLabel("Endere\u00E7o:");
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		
		JLabel lbNmero = new JLabel("Numero:");
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		
		JLabel lbBairro = new JLabel("Bairro:");
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		
		JLabel lbTelefone = new JLabel("Telefone:");
		
		try {
			maskTelefone = new MaskFormatter("(##) ####-####");
		} catch (ParseException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro na máscara \n" + e.getMessage());
		}
		txtTelefone = new JFormattedTextField();
		maskTelefone.install(txtTelefone);
		
		txtIdade = new JFormattedTextField();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lbEndereo)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(2)
											.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lbNmero)
										.addComponent(txtNumero, 0, 0, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lbNome)
										.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lbIdade)
											.addGap(10))
										.addComponent(txtIdade))))
							.addContainerGap(15, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtBairro, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbBairro))
							.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbTelefone)
								.addComponent(txtTelefone, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
							.addGap(78))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtIdade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbIdade)
							.addGap(26)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbEndereo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbNmero)
							.addGap(26)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbBairro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbTelefone)
							.addGap(26)))
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnSalvar, btnCancelar});
		getContentPane().setLayout(groupLayout);
		
	}


	protected void limparCampos() {
		txtNome.setText("");
		txtIdade.setText("");
		txtEndereco.setText("");
		txtNumero.setText("");
		txtTelefone.setText("");
		txtBairro.setText("");
	}

	protected void costumerIncluirOnClick() {
		Costumer costumer = new Costumer();
		costumer.setName(txtNome.getText());
		costumer.setIdade(Integer.parseInt(txtIdade.getText()));
		costumer.setEndereco(txtEndereco.getText());
		costumer.setBairro(txtBairro.getText());
		costumer.setNumber(Integer.parseInt(txtNumero.getText()));
		costumer.setTelefone(txtTelefone.getText());
		persistir(costumer);
	}

	private void persistir(Costumer costumer) {
		try {
			CostumerDAO costumerDAO = new CostumerDAO();
			costumerDAO.inserir(costumer);
			JOptionPane.showMessageDialog(this, "Costumer, inserido com sucesso");
			limparCampos();
			this.control.refreshTableCostumer();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Não foi possivel inserir \n" + e.getMessage() , "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
