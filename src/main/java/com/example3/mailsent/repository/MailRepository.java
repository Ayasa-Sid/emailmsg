package com.example3.mailsent.repository;

import com.example3.mailsent.entity.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<MailEntity,String> {
    @Query("select u from MailEntity u where u.emailFrom = ?1")
    public
    List<MailEntity> getDetailByEmail(String id);
}
