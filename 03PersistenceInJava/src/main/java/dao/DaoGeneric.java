package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import posjavamavenhibernate.HibernateUtil;

public class DaoGeneric<E> {

	private static final String UNCHECKED = "unchecked";
	private EntityManager entityManager = HibernateUtil.getEntityManager();

	public void salvar(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
	}

	public E updateMerge(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entidadeSalva = entityManager.merge(entidade);
		transaction.commit();

		return entidadeSalva;
	}

	@SuppressWarnings(UNCHECKED)
	public E pesquisar(E entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);

		E e = (E) entityManager.find(entidade.getClass(), id);

		return e;
	}

	public E buscar(Long id, Class<E> entidade) {
		@SuppressWarnings(UNCHECKED)
		E e = (E) entityManager.find(entidade, id);

		return e;
	}

	public void deletarPorId(E entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager
				.createNativeQuery(
						"DELETE FROM " + entidade.getClass().getSimpleName().toLowerCase() + " where id = " + id)
				.executeUpdate();
		transaction.commit();
	}
	
	public List<E> listar(Class<E> entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		@SuppressWarnings(UNCHECKED)
		List<E> lista = entityManager.createQuery("FROM " + entidade.getName()).getResultList();
		
		transaction.commit();
		
		return lista;
	}

}
