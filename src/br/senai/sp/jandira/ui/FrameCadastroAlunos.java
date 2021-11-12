package br.senai.sp.jandira.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import br.senai.sp.jandira.model.Aluno;
import br.senai.sp.jandira.model.Periodo;
import br.senai.sp.jandira.repository.AlunoRepository;

public class FrameCadastroAlunos extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMatricula;
	private JTextField textFieldNome;
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
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.setBounds(117, 25, 98, 25);
		contentPane.add(textFieldMatricula);
		textFieldMatricula.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 72, 78, 25);
		contentPane.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(117, 74, 164, 25);
		contentPane.add(textFieldNome);
		
		JLabel lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setBounds(10, 125, 78, 25);
		contentPane.add(lblPeriodo);
		
		JComboBox comboPeriodo = new JComboBox();
		DefaultComboBoxModel <String> comboModelPeriodo = new DefaultComboBoxModel <String> ();
		
		// Carregar o comboModel com as descrições dos Períodos
		for (Periodo descricao : Periodo.values() ) {
			comboModelPeriodo.addElement(descricao.getDescricao());		
			}

		
		comboPeriodo.setModel(comboModelPeriodo);
		comboPeriodo.setBounds(117, 128, 130, 22);
		contentPane.add(comboPeriodo);
		
		JLabel lblLista = new JLabel("Lista Alunos:");
		lblLista.setBounds(310, 28, 98, 20);
		contentPane.add(lblLista);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(310, 72, 98, 149);
		contentPane.add(scrollPane_1);
		
		JList listAlunos = new JList();
		scrollPane_1.setViewportView(listAlunos);
		
		DefaultListModel <String> modelAlunos = new DefaultListModel();
		listAlunos.setModel(modelAlunos);
		
		JButton btnSalvar = new JButton("Salvar Aluno");
		btnSalvar.setBounds(35, 160, 180, 46);
		contentPane.add(btnSalvar);
		
		JButton btnMostrarAluno = new JButton("Dados Aluno");
		btnMostrarAluno.setBounds(35, 217, 180, 23);
		contentPane.add(btnMostrarAluno);
		btnMostrarAluno.setEnabled(false);
		
		AlunoRepository turma = new AlunoRepository(3);
	
		
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Aluno aluno = new Aluno();
				aluno.setNome(textFieldNome.getText());
				aluno.setMatricula(textFieldMatricula.getText());
				
				// Definir o horario do aluno
				aluno.setPeriodo(definirPeriodo(comboPeriodo.getSelectedIndex()));
				
				turma.gravar(aluno, posicao);
				
				posicao++;
				
				modelAlunos.addElement(aluno.getNome());
				
				if (posicao == turma.getTamanho()) {
					btnSalvar.setEnabled(false);
					JOptionPane.showMessageDialog(null, "A turma já encheu!!", "Uepaaaaaa", JOptionPane.WARNING_MESSAGE);
					btnMostrarAluno.setEnabled(true);
				}
				
			}
		});
		
		btnMostrarAluno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for (Aluno aluno : turma.listarTodos()) {
					System.out.println(aluno.getMatricula());
					System.out.println(aluno.getNome());
					System.out.println(aluno.getPeriodo().getDescricao());
					System.out.println("-------------------");
					
					
				}
			}
		});
		
		listAlunos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				Aluno aluno = turma.listarAluno(listAlunos.getSelectedIndex());
				textFieldNome.setText(aluno.getNome());
				textFieldMatricula.setText(aluno.getMatricula());
				
				comboPeriodo.setSelectedIndex(aluno.getPeriodo().ordinal());
			
			}
		});
	
	
		}
		private Periodo definirPeriodo(int periodoSelecionado) {
					
					if (periodoSelecionado == 0) {
						return Periodo.MANHA;
					} else if (periodoSelecionado == 1) {
						return Periodo.TARDE;
					} else if (periodoSelecionado == 2) {
						return Periodo.NOITE;
					} else if (periodoSelecionado == 3) {
						return Periodo.SABADO;
					} else {
						return Periodo.ONLINE;
					}
	}
}
