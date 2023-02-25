package com.example.demo;

import java.util.List;

import com.example.demo.model.Bar;
import com.example.demo.model.Foo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class Exec {

	public static void main(String[] args) {
		// setup
		EntityManagerFactory emf;
		EntityManager em;

		emf = Persistence.createEntityManagerFactory("nativequery-issue");
		em = emf.createEntityManager();

		// instantiate
		Bar bar = new Bar();
		bar.setName("bar1");
		Foo foo = new Foo();
		foo.setName("FooName");
		foo.setBar(bar);

		// persist
		em.getTransaction().begin();
		em.persist(foo.getBar());
		em.persist(foo);
		em.getTransaction().commit();
		em.clear();

		String selectQuery = "SELECT foo FROM Foo foo";
		TypedQuery<Foo> selectFromFooTypedQuery = em.createQuery(selectQuery, Foo.class);
		List<Foo> foos = selectFromFooTypedQuery.getResultList();
		em.clear();

		System.out.println("=====from REGULAR query result=====");
		System.out.println(foos.get(0).getName());
		System.out.println(foos.get(0).getBar().getName());
		em.clear();

		String selectNativeQuery = "SELECT * FROM Foo foo";
		Query selectFromFooNativeQuery = em.createNativeQuery(selectNativeQuery, Foo.class);
		List<Foo> foosFromNativeQuery = selectFromFooNativeQuery.getResultList();
		em.clear();
		System.out.println("=====from NATIVE query result=====");
		System.out.println(foosFromNativeQuery.get(0).getName());
		System.out.println(foosFromNativeQuery.get(0).getBar().getName());

		em.close();
	}
}
