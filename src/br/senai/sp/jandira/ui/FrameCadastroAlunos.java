package br.senai.sp.jandira.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import br.senai.sp.jandira.model.Aluno;
import br.senai.sp.jandira.model.Periodo;
import br.senai.sp.jandira.repository.AlunoRepository;

public class FrameCadastroAlunos extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtNome;
	private JTextField textFieldM;
	private JTextField textFieldN;
	private int posicao;



	public FrameCadastroAlunos() {
		setTitle("Cadastro Alunos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matr\u00EDcula:");
		lblMatricula.setBounds(10, 23, 78, 25);
		contentPane.add(lblMatricula);
		
		textFieldM = new JTextField();
		textFieldM.setBounds(117, 25, 98, 25);
		contentPane.add(textFieldM);
		textFieldM.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 72, 78, 25);
		contentPane.add(lblNome);
		
		textFieldN = new JTextField();
		textFieldN.setColumns(10);
		textFieldN.setBounds(117, 74, 164, 25);
		contentPane.add(textFieldN);
		
		JLabel lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setBounds(10, 125, 78, 25);
		contentPane.add(lblPeriodo);
		
		JComboBox comboBox = new JComboBox();
		DefaultComboBoxModel <String> comboModelPeriodo = new DefaultComboBoxModel <String> ();
		
		// Carregar o comboModel com as descrições dos Perídos
		for (Periodo descricao : Periodo.values() ) {
			comboModelPeriodo.addElement(descricao.getDescricao());		
			}

		
		comboBox.setModel(comboModelPeriodo);
		comboBox.setBounds(117, 128, 130, 22);
		contentPane.add(comboBox);
		
		JLabel lblLista = new JLabel("Lista Alunos:");
		lblLista.setBounds(310, 28, 98, 20);
		contentPane.add(lblLista);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(310, 206, 98, -132);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(310, 72, 98, 157);
		contentPane.add(scrollPane_1);
		
		JList listAlunos = new JList();
		scrollPane_1.setViewportView(listAlunos);
		
		DefaultListModel <String> modelAlunos = new DefaultListModel();
		listAlunos.setModel(modelAlunos);
		
		JButton btnSalvar = new JButton("Salvar Aluno");
		btnSalvar.setBounds(35, 183, 180, 46);
		contentPane.add(btnSalvar);
		
		AlunoRepository turma = new AlunoRepository(3);
	
		
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Aluno aluno = new Aluno();
				aluno.setNome(textFieldN.getText());
				aluno.setMatricula(textFieldM.getText());
				
				turma.gravar(aluno, posicao);
				
				posicao++;
				
				modelAlunos.addElement(aluno.getNome());
			
			
				
			}
		});
		
	}
}
