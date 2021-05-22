package com.test.repository;

import com.test.entities.UserAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<UserAccounts, Long> {


}
