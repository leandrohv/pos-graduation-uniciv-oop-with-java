package posjavamavenhibernate;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() {
		//HibernateUtil.getEntityManager();
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setIdade(45);
		pessoa.setLogin("teste");
		pessoa.setNome("Roberval");
		pessoa.setSobrenome("Da Costa");
		pessoa.setEmail("rob.costa@gmail.com");
		pessoa.setSenha("abc123");
		
		daoGeneric.salvar(pessoa);
		
	}
	
	@Test
	public void testePesquisar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(1L);
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.buscar(1L, UsuarioPessoa.class);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
//		UsuarioPessoa pessoa = new UsuarioPessoa();
//		pessoa.setId(1L);
//		pessoa = daoGeneric.pesquisar(pessoa);
		
		UsuarioPessoa pessoa = daoGeneric.buscar(2L, UsuarioPessoa.class);
		
		pessoa.setIdade(99);
		pessoa.setNome("Nome Atualizado Hibernate");
		
		pessoa = daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.buscar(1L, UsuarioPessoa.class);
		
		daoGeneric.deletarPorId(pessoa);
		
		System.out.println("Removido com sucesso!");
		
	}
	
	@Test
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-------------------");
		}
		
	}
}

