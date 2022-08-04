package com.demo.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.Model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

	User findOneByEmailIdIgnoreCaseAndPassword(String emailId, String password);

}
