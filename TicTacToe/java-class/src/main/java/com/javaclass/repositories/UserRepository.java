package com.javaclass.repositories;
import com.javaclass.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    Optional<User> findByName(String user);

}
