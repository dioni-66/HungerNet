package com.myspring.hungernet.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.myspring.hungernet.Model.entity.User;
import com.myspring.hungernet.Model.pojo.UserPojo;
import com.myspring.hungernet.request.CreateUserRequestDTO;
import com.myspring.hungernet.request.DeleteUserRequestDTO;
import com.myspring.hungernet.request.UpdateUserRequestDTO;
import com.myspring.hungernet.service.UserService;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/hungernet") // This means URL's start with /demo (after Application path)
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "")
	public String home() {
		return ("<h1>Welcome Dion</h1>");
	}

	@PostMapping(path = "/add")
	// add new user to hungernetdb.user
	public ResponseEntity<String> addNewUser(@RequestParam String userLoggedIn, @RequestBody CreateUserRequestDTO userDto) {
		return userService.addNewUser(userLoggedIn, userDto);
	}

	@DeleteMapping(path = "/delete")
	public ResponseEntity<String> deleteByUserId(@RequestParam String userLoggedIn,
			@RequestBody DeleteUserRequestDTO userDto) {
		return userService.deleteUserById(userLoggedIn, userDto);
	}

	@GetMapping(path = "/findAll")
	public ResponseEntity<List<User>> findAll(@RequestParam String userLoggedIn) {
		return userService.findAll(userLoggedIn);
	}

	@PutMapping(path = "/update")
	public ResponseEntity<String> updateUserById(@RequestParam String userLoggedIn,
			@RequestBody UpdateUserRequestDTO userDto) {
		return userService.updateUserById(userLoggedIn, userDto);
	}

	@GetMapping(path = "/findExceptAdmin")
	public ResponseEntity<List<User>> findExceptAdmin() {
		List<User> user = userService.findExceptAdmin();
		return ResponseEntity.ok(user);
	}
}
