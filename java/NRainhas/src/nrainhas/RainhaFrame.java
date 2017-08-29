package nrainhas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class RainhaFrame extends javax.swing.JFrame {

	// Pane de exibicao do resultado
	private JEditorPane AreadeImpressaoDeResultado;
	//Numero de rainhas
	private JLabel jLabel2;
	//Numero de solucoes
	private JLabel numSolucao;
	// Botao que inicia a solucao
	private JButton BotaoIniciar;
	// Opcao no menu de salvar resultado
	private JScrollPane barraDeRolagem;
	/// Area de selecao do numero de rainhas
	private JTextField InsereNdeRainhas;
	// Engloba JButton1, Monitoramento e InsereNdeRainhas
	private JLabel jLabel1;
	// Botao que inicia a solucao pelo menu
	private String texto;
	// Objeto rainha
	protected NRainhas rainha;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				RainhaFrame inst = new RainhaFrame();
				inst.setTitle("Problema de N Rainhas");
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setSize(900, 900);
			}
		});
	}
	
	// Construtor Padrao
	public RainhaFrame() {
		super();
		initGUI();
	}
	
	// GUI - General User interface 
	// Geracao da GUI que exibe o frame
	private void initGUI() {
		try {
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1, BorderLayout.NORTH);
					jLabel1.setText(" ");
					jLabel1.setPreferredSize(new java.awt.Dimension(213, 371));
				{
					InsereNdeRainhas = new JTextField();
					jLabel1.add(InsereNdeRainhas);
					InsereNdeRainhas.setText("");
					InsereNdeRainhas.setBounds(6, 73, 119, 21);
				}
				{
					BotaoIniciar = new JButton();
					jLabel1.add(BotaoIniciar);
					BotaoIniciar.setText("Go");
					BotaoIniciar.setBounds(131, 73, 71, 22);
					BotaoIniciar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton1ActionPerformed(evt);
						}
					});
				}
				{
					jLabel2 = new JLabel();
					jLabel1.add(jLabel2);
					jLabel2.setText("Numero de rainhas:");
					jLabel2.setBounds(6, 50, 195, 20);
				}
				{
					numSolucao = new JLabel();
					jLabel1.add(numSolucao);
					numSolucao.setText(" ");
					numSolucao.setBounds(6, 23, 207, 21);
					numSolucao.setBorder(BorderFactory.createTitledBorder(""));
					numSolucao.setFont(new java.awt.Font("Tahoma",0,10));
				}

			}
			{
				AreadeImpressaoDeResultado = new JEditorPane();
				getContentPane().add(AreadeImpressaoDeResultado, BorderLayout.CENTER);
				AreadeImpressaoDeResultado.setText(" ");
				AreadeImpressaoDeResultado.setPreferredSize(new java.awt.Dimension(300, 476));
				AreadeImpressaoDeResultado.setBorder(BorderFactory.createTitledBorder(""));
				AreadeImpressaoDeResultado.setFont(new java.awt.Font("MS Sans Serif", 0, 12));
			}
			{
				barraDeRolagem = new JScrollPane(AreadeImpressaoDeResultado);
				getContentPane().add(barraDeRolagem, BorderLayout.SOUTH);
				barraDeRolagem.setPreferredSize(new java.awt.Dimension(308, 493));
			}

			pack();
			this.setSize(531, 577);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Busca da solucao 
	private void jMenu1ActionPerformed(ActionEvent evt) {
		System.out.println("jMenu1.actionPerformed, event="+evt);
		//TODO add your code for jMenu1.actionPerformed
		this.rainha = new NRainhas();

		String numerodeRainhas ="";

		JOptionPane.showMessageDialog(null,"N rainhas \n Programa que soluciona o problema" +
				" de N rainhas"," N Rainhas",JOptionPane.PLAIN_MESSAGE);
		numerodeRainhas = JOptionPane.showInputDialog("Entre com o numero de Rainhas para" +
		" encontrar a solucao");

		int numR = Integer.parseInt(numerodeRainhas);
		rainha.N = numR;
		rainha.solucionar();

		System.out.println(texto);
		AreadeImpressaoDeResultado.setText(texto);
	}
		
	private void IniciarActionPerformed(ActionEvent evt) {
		//TODO add your code for Iniciar.actionPerformed
		this.rainha = new NRainhas();
		AreadeImpressaoDeResultado.setText(texto);
		AreadeImpressaoDeResultado.setEditable(false);
	}
	
	// Acao do botao de inicio da busca da solucao 
	private void jButton1ActionPerformed(ActionEvent evt) {
		//TODO add your code for jButton1.actionPerformed
		String NumerodeRainhas = InsereNdeRainhas.getText();
		int Num = Integer.parseInt(NumerodeRainhas);

		for (int i=0;i <100000;i++){}
		this.rainha = new NRainhas();
		rainha.N = Num;
		rainha.solucionar();
		numSolucao.setText("Para " + rainha.N + " há " + rainha.contador + " solucoes!");
		texto = rainha.print;
		AreadeImpressaoDeResultado.setText(texto);
		AreadeImpressaoDeResultado.setEditable(false);
	}
}