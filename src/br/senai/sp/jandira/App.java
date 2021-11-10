package br.senai.sp.jandira;

import br.senai.sp.jandira.ui.FrameCadastroAlunos;

public class App {

	public static void main(String[] args) {
		
		/*  AlunoRepository turma = new AlunoRepository(5);
		
		Aluno a = new Aluno();
		a.setNome("douglas");
		turma.gravar(a, 0);
		
		Aluno b = new Aluno();
		b.setNome("pedri");
		turma.gravar(b, 1);
		
		Aluno c = new Aluno();
		c.setNome("pietro");
		turma.gravar(c, 2);
		
		Aluno h = new Aluno();
		h.setNome("hulk");
		turma.gravar(h, 3);
		
		Aluno g = new Aluno();
		g.setNome("marcus");
		turma.gravar(g, 4);
		
		for (Aluno alunoAtual : turma.listarTodos()) {
			System.out.println(alunoAtual.getNome());
		} 
		
		*/		
		
		
		FrameCadastroAlunos tela = new FrameCadastroAlunos();
		tela.setVisible(true);
			
		}


	}


