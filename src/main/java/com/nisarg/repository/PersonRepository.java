package com.nisarg.repository;

import com.nisarg.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  @Transactional
  @Modifying
  @Query("update Person p set p.name = :pName, p.email = :pEmail, p.gender = :pGender, p.age = :pAge where p.id = :personId")
  int update(@Param("personId") long personId, @Param("pName") String name,
      @Param("pEmail") String email, @Param("pGender") String gender, @Param("pAge") int age);

  Person findByName(String name);
}
