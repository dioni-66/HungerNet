package com.myspring.hungernet.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myspring.hungernet.Model.entity.User;
import com.myspring.hungernet.request.CreateUserRequestDTO;
import com.myspring.hungernet.request.DeleteUserRequestDTO;
import com.myspring.hungernet.request.UpdateUserRequestDTO;

@Service
@Transactional
public class UserService {

	@PersistenceContext
	private EntityManager entityManager;

	public ResponseEntity<String> addNewUser(String userLoggedIn, CreateUserRequestDTO userDto) {
		if (userLoggedIn.equals("Admin")) {
			User user = new User();
			user.setName(userDto.getName());
			user.setRole(userDto.getRole());
			user.setEmail(userDto.getEmail());
			entityManager.persist(user);
			return new ResponseEntity<>("User added!", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Unauthorized access!", HttpStatus.NOT_ACCEPTABLE);
	}

	public ResponseEntity<String> deleteUserById(String userLoggedIn, DeleteUserRequestDTO userDto) {
		User user = entityManager.find(User.class, userDto.getId());
		if (userLoggedIn.equals("Admin")) {
			if (user != null) {
				entityManager.remove(user);
				return new ResponseEntity<>("User deleted.", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>("Unauthorized", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<List<User>> findAll(String userLoggedin) {
		if (userLoggedin.equals("Admin")) {
			TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
			List<User> user = query.getResultList();
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				return new ResponseEntity("No user found", HttpStatus.NOT_FOUND);
			}

		}
		return new ResponseEntity("Unauthorized", HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<String> updateUserById(String userLoggedin, UpdateUserRequestDTO userDto) {
		User user = entityManager.find(User.class, userDto.getId());
		if (userLoggedin.equals("Admin")) {
			if (user != null) {
				user.setName(userDto.getName());
				user.setRole(userDto.getRole());
				user.setEmail(userDto.getEmail());
				return new ResponseEntity<>("User modified", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>("Unauthorized", HttpStatus.NOT_FOUND);
		}
	}

	public List<User> findExceptAdmin() {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.role IS NOT 'Admin'",
				User.class);
		List<User> users = query.getResultList();
		return users;
	}

}
