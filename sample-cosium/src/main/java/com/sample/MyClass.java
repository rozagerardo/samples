package com.sample;

import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class MyClass implements ApplicationRunner {

  private static final Logger LOG = LoggerFactory.getLogger(MyClass.class);

  @Autowired EntityManager entityManager;

  public static void main(final String... args) {
    SpringApplication.run(MyClass.class, args);
  }

  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {

    LOG.info("actual change HERE");

    LOG.info("Existing statement");
  }
}
