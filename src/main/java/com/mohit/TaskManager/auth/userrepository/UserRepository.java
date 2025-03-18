package com.mohit.TaskManager.auth.userrepository;

import com.mohit.TaskManager.auth.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String > {
}
