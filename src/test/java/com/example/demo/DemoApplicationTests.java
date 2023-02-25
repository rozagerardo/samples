package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.model.Bar;
import com.example.demo.model.Foo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

class DemoApplicationTests {

	private EntityManagerFactory emf;
	private EntityManager em;

	@BeforeEach
	public void setup() {
		emf = Persistence.createEntityManagerFactory("nativequery-issue");
		em = emf.createEntityManager();
	}

	@Test
	public void persistFooThenRetrieveTheDetails() {
		Bar bar = new Bar();
		bar.setName("bar1");

		Foo foo = createFooWithRelevantDetails(bar);
		persist(foo);
		clearThePersistenceContext();
		List<Foo> foos = getFoosFromTable();
		clearThePersistenceContext();
		System.out.println("=====from REGULAR query result=====");
		System.out.println(foos.get(0).getName());
		System.out.println(foos.get(0).getBar().getName());
		checkAssertionsWith(foos);
		clearThePersistenceContext();

		List<Foo> foosFromNativeQuery = getFoosFromTableWithNativeQuery();
		clearThePersistenceContext();
		System.out.println("=====from NATIVE query result=====");
		System.out.println(foosFromNativeQuery.get(0).getName());
		System.out.println(foosFromNativeQuery.get(0).getBar().getName());
		checkAssertionsWith(foosFromNativeQuery);
	}

	@AfterEach
	public void destroy() {
		if (em != null) {
			em.close();
		}
		if (emf != null) {
			emf.close();
		}
	}

	private void clearThePersistenceContext() {
		em.clear();
	}

	private void checkAssertionsWith(List<Foo> foos) {
		assertEquals(1, foos.size());
		Foo foo = foos.get(0);
		assertEquals("bar1", foo.getBar().getName());
	}

	private List<Foo> getFoosFromTable() {
		String selectQuery = "SELECT foo FROM Foo foo";
		TypedQuery<Foo> selectFromFooTypedQuery = em.createQuery(selectQuery, Foo.class);
		List<Foo> foos = selectFromFooTypedQuery.getResultList();
		return foos;
	}

	@SuppressWarnings("unchecked")
	private List<Foo> getFoosFromTableWithNativeQuery() {
		String selectQuery = "SELECT * FROM Foo foo";
		Query selectFromFooTypedQuery = em.createNativeQuery(selectQuery, Foo.class);
		List<Foo> foos = selectFromFooTypedQuery.getResultList();
		return foos;
	}

	private void persist(Foo foo) {
		em.getTransaction().begin();
		em.persist(foo.getBar());
		em.persist(foo);
		em.getTransaction().commit();
	}

	private Foo createFooWithRelevantDetails(Bar bar) {
		Foo foo = new Foo();
		foo.setName("FooName");
		foo.setBar(bar);
		return foo;
	}
}
