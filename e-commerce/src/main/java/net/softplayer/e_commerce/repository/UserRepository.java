package net.softplayer.e_commerce.repository;

import org.springframework.data.repository.CrudRepository;
import net.softplayer.e_commerce.entity.User;


public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
