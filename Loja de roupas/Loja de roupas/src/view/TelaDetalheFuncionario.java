package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controle.Validador;
import modelo.Funcionario;

/**
 * Respons�vel por mostrar a tela referente ao funcionario de forma mais detalhada
 * @author Leo
 * @version 1.0 (Out 2021)
 */
public class TelaDetalheFuncionario implements ActionListener {
	
	private JFrame janela;
	private JLabel labelNome = new JLabel("Nome: ");
	private JTextField valorNome;
	private JLabel labelFunc = new JLabel("Fun��o: ");
	private JTextField valorFunc;
	private JLabel labelData = new JLabel("Data de contrata��o: ");
	private JTextField valorData;
	private JLabel labelGen = new JLabel("G�nero: ");
	private JTextField valorGen;
	private JLabel labelCPF = new JLabel("CPF: ");
	private JTextField valorCPF;
	private JLabel labelTelefone = new JLabel("Telefone: ");
	private JTextField valorTelefone;
	private JLabel labelEnd = new JLabel("Endere�o: ");
	private JTextField valorEnd;
	private JButton botaoExcluir = new JButton("Excluir");
	private JButton botaoSalvar = new JButton("Salvar");
	private String s;
	
	int posicao;
	int opcao;
	
	/**
	 * Faz a escolha de mostrar as informa��es do funcionario de forma mais detalhada ou cadastrar um novo funcionario com base nos dois parametros
	 * @param op
	 * @param pos
	 */
	public void inserirEditar(int op, int pos) {
		if (op == 1) s = "Cadastro do Funcion�rio";
		if (op == 2) s = "Detalhe do Funcion�rio";
		janela = new JFrame(s);
		Funcionario f = new Funcionario();
		
		opcao = op;
		posicao = pos;
		
		if (op == 1) { //Sem dados
			valorNome = new JTextField(25);
			valorFunc = new JTextField(25);
			valorData = new JTextField(25);
			valorGen = new JTextField(25);
			valorCPF = new JTextField(25);
			valorTelefone = new JTextField(25);
			valorEnd = new JTextField(25);
		}
		
		else if (op == 2) { //Com dados
			valorNome = new JTextField(f.getNome(pos), 25);
			valorFunc = new JTextField(f.getFuncao(pos), 25);
			valorData = new JTextField(f.getDataContratacao(pos), 25);
			valorGen = new JTextField(f.getGenero(pos), 25);
			valorCPF = new JTextField(f.getCPF(pos), 25);
			valorTelefone = new JTextField(f.getTelefone(pos), 25);
			valorEnd = new JTextField(f.getEnd(pos), 25);
			
			botaoExcluir.setBounds(65, 420, 115, 30);
			janela.add(botaoExcluir);
		}
		
		labelNome.setBounds(30, 20, 150, 25);
		valorNome.setBounds(180, 20, 180, 25);
		labelFunc.setBounds(30, 50, 150, 25);
		valorFunc.setBounds(180, 50, 180, 25);
		labelData.setBounds(30, 80, 150, 25);
		valorData.setBounds(180, 80, 180, 25);		
		labelGen.setBounds(30, 110, 150, 25);
		valorGen.setBounds(180, 110, 180, 25);
		labelCPF.setBounds(30, 140, 150, 25);
		valorCPF.setBounds(180, 140, 180, 25);
		labelTelefone.setBounds(30, 170, 150, 25);
		valorTelefone.setBounds(180, 170, 180, 25);
		labelEnd.setBounds(30, 200, 150, 25);
		valorEnd.setBounds(180, 200, 180, 25);
		
		botaoSalvar.setBounds(190, 420, 115, 30);
		
		janela.add(labelNome); janela.add(labelGen);
		janela.add(valorNome); janela.add(valorGen);
		janela.add(labelCPF); janela.add(labelEnd);
		janela.add(valorCPF); janela.add(valorEnd);
		janela.add(labelTelefone); janela.add(labelData);
		janela.add(valorTelefone); janela.add(valorData);
		janela.add(labelFunc);
		janela.add(valorFunc); 
		
		janela.add(botaoSalvar);
		
		janela.setLayout(null);
		
		janela.setSize(400, 500);
		janela.setVisible(true);
		
		botaoSalvar.addActionListener(this);
		botaoExcluir.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		Funcionario f = new Funcionario();
		Validador v = new Validador();
		
		if(src == botaoSalvar) {
			if(opcao == 1) { //Novo
				if(v.validaCPF(valorCPF.getText()) && v.validaData(valorData.getText()) && v.validaTelefone(valorTelefone.getText())) {
					Funcionario.funcionarioN.add(valorNome.getText());
					Funcionario.funcionarioF.add(valorFunc.getText());
					Funcionario.funcionarioD.add(valorData.getText());
					Funcionario.funcionarioG.add(valorGen.getText());
					Funcionario.funcionarioC.add(valorCPF.getText());
					Funcionario.funcionarioT.add(valorTelefone.getText());
					Funcionario.funcionarioE.add(valorEnd.getText());
				} else {
					JOptionPane.showMessageDialog(null,"ERRO AO SALVAR OS DADOS!\n "
							+ "Pode ter ocorrido um dos dois erros a seguir:  \n"
							+ "1. Nem todos os campos foram preenchidos \n"
							+ "2. CPF ou Telefone n�o cont�m apenas n�meros \n"
							+ "3. Data Inv�lida", null, 
							JOptionPane.ERROR_MESSAGE);
				}
				janela.dispose();
			}
			if(opcao == 2) { //Editar
				f.editNome(f.getNome(posicao), valorNome.getText());
				f.editFuncao(f.getFuncao(posicao), valorFunc.getText());
				f.editDataContratacao(f.getDataContratacao(posicao), valorData.getText());
				f.editGenero(f.getGenero(posicao), valorGen.getText());
				f.editCPF(f.getCPF(posicao), valorCPF.getText());
				f.editTelefone(f.getTelefone(posicao), valorTelefone.getText());
				f.editEnd(f.getEnd(posicao), valorEnd.getText());
			}
			janela.dispose();
		}
		
		if(src == botaoExcluir) {
			f.delFuncionario(f.getCPF(posicao));
			janela.dispose();
		}
		
	}

}
