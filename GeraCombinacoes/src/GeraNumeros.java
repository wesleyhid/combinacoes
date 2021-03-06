import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class GeraNumeros {

	private JFrame frmGeraoDeCombines;
	private JTextField txtnummin;
	private JTextField txtnummax;
	private JTextField txtqtnum;
	private JButton btnNewButton;
	private JTextField txt_path;
	private JTextField txt_seq;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeraNumeros window = new GeraNumeros();
					window.frmGeraoDeCombines.setVisible(true);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
			
	}

	/**
	 * Create the application.
	 */
	public GeraNumeros() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGeraoDeCombines = new JFrame();
		frmGeraoDeCombines.setTitle("Gerador de combina\u00E7\u00F5es");
		frmGeraoDeCombines.getContentPane().setLayout(null);
		frmGeraoDeCombines.setSize(470,266);
		frmGeraoDeCombines.setLocationRelativeTo(null);

		JLabel lblNmMnimo = new JLabel("N\u00FAm. m\u00EDnimo");
		lblNmMnimo.setBounds(10, 11, 123, 14);
		frmGeraoDeCombines.getContentPane().add(lblNmMnimo);
		
		txtnummin = new JTextField();
		txtnummin.setBounds(143, 8, 90, 20);
		frmGeraoDeCombines.getContentPane().add(txtnummin);
		txtnummin.setColumns(10);
		
		JLabel lblNmMximo = new JLabel("N\u00FAm. m\u00E1ximo");
		lblNmMximo.setBounds(10, 36, 123, 14);
		frmGeraoDeCombines.getContentPane().add(lblNmMximo);
		
		txtnummax = new JTextField();
		txtnummax.setBounds(143, 33, 90, 20);
		frmGeraoDeCombines.getContentPane().add(txtnummax);
		txtnummax.setColumns(10);
		
		JLabel lblQuantDeNmeros = new JLabel("Quant. de n\u00FAmeros");
		lblQuantDeNmeros.setBounds(10, 61, 123, 14);
		frmGeraoDeCombines.getContentPane().add(lblQuantDeNmeros);
		
		txtqtnum = new JTextField();
		txtqtnum.setBounds(143, 58, 90, 20);
		frmGeraoDeCombines.getContentPane().add(txtqtnum);
		txtqtnum.setColumns(10);
		
		btnNewButton = new JButton("Executar");
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String min = txtnummin.getText();
				int min_int = 0;
				try{
					min_int = Integer.parseInt(min);
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(frmGeraoDeCombines,"N�mero m�nimo inv�lido. Mensagem de erro: \n" +  ex.getMessage());
					return;
				}
				
				String max = txtnummax.getText();
				int max_int = 0;
				try{
					max_int = Integer.parseInt(max);
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(frmGeraoDeCombines,"N�mero m�ximo inv�lido. Mensagem de erro: \n" +  ex.getMessage());
					return;
				}
				
				String n = txtqtnum.getText();
				int qtnum_int = 0;
				try{
					qtnum_int = Integer.parseInt(n);
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(frmGeraoDeCombines,"Quantidade de n�meros inv�lida. Mensagem de erro: \n" +  ex.getMessage());
					return;
				}
				
				if(min_int > max_int){
					JOptionPane.showMessageDialog(frmGeraoDeCombines,"N�mero m�ximo menor que n�mero m�nimo!");
					return;
				}else if(max_int - min_int + 1 < qtnum_int){
					JOptionPane.showMessageDialog(frmGeraoDeCombines,"Quantidade de n�mero maior que intervalo!");
					return;
				}
				
				if(txt_path.getText().isEmpty()){
					JOptionPane.showMessageDialog(frmGeraoDeCombines,"Escolha o caminho para salvar o arquivo de combina��es!");
					return;
				}
				
				String seq_str = txt_seq.getText();
				int seq_int = 0;
				try{
					if(!txt_seq.getText().isEmpty()) seq_int = Integer.parseInt(seq_str);
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(frmGeraoDeCombines,"N�mero de sequ�ncia inv�lida. Mensagem de erro: \n" +  ex.getMessage());
					return;
				}
				
				
				if(seq_int > qtnum_int){
					JOptionPane.showMessageDialog(frmGeraoDeCombines,"N�mero de sequ�ncia n�o pode ser maior que a quantidade de n�meros na combina��o!");
					return;
				}else if(seq_int == 1){
					JOptionPane.showMessageDialog(frmGeraoDeCombines,"N�mero de sequ�ncia deve ser maior que 1!");
					return;
				}
				
				CalculaCombinacoes combinacoes = new CalculaCombinacoes(min_int,max_int,qtnum_int,seq_int,txt_path.getText());
				
				//JOptionPane.showMessageDialog(frmGeraoDeCombines,"Come�ou!" + " N� total de combina��es: " + combinacoes.getNumberOfCombinations());
				combinacoes.calcula();
				
				long total = combinacoes.getNumberOfCombinations();
				int afterFilter = combinacoes.getNumberOfCombinationsAfterFilter();
				long removed = total- afterFilter;
				
				JOptionPane.showMessageDialog(frmGeraoDeCombines,"Acabou!" + " \nN� total de combina��es: " + total + "\nN� de combina��es ap�s filtro: " + afterFilter + "\nN� de combina��es removidas ap�s filtro: " + removed);
				
			}
		});
		
		frmGeraoDeCombines.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(143, 145, 181, 62);
		frmGeraoDeCombines.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GeraNumeros.class.getResource("/javax/swing/plaf/metal/icons/dinheiro.jpg")));
		label.setBounds(243, 11, 201, 92);
		frmGeraoDeCombines.getContentPane().add(label);
		
		txt_path = new JTextField();
		txt_path.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_path.setColumns(10);
		txt_path.setBounds(143, 114, 181, 20);
		frmGeraoDeCombines.getContentPane().add(txt_path);
		
		JLabel lblCaminhoDeExportao = new JLabel("Salvar em");
		lblCaminhoDeExportao.setBounds(10, 117, 108, 14);
		frmGeraoDeCombines.getContentPane().add(lblCaminhoDeExportao);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				
				if(txt_path.getText() != "") 
					chooser.setCurrentDirectory(new java.io.File(txt_path.getText()));
				else	
					chooser.setCurrentDirectory(new java.io.File("."));
				
				chooser.setDialogTitle("Selecione a pasta de destino");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				 
					txt_path.setText(chooser.getSelectedFile().getPath());
					txt_path.setToolTipText(txt_path.getText());

				}
				
			}
		});
		
		btnSelecionar.setBounds(334, 115, 110, 23);
		frmGeraoDeCombines.getContentPane().add(btnSelecionar);
		
		JLabel lblSequnciaNoPermit = new JLabel("Sequ\u00EAncia n\u00E3o permit.");
		lblSequnciaNoPermit.setBounds(10, 89, 135, 14);
		frmGeraoDeCombines.getContentPane().add(lblSequnciaNoPermit);
		
		txt_seq = new JTextField();
		txt_seq.setColumns(10);
		txt_seq.setBounds(143, 86, 90, 20);
		frmGeraoDeCombines.getContentPane().add(txt_seq);
		
			
	}
}


