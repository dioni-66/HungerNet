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
import com.myspring.hungernet.Model.entity.Menu;
import com.myspring.hungernet.Model.pojo.MenuPojo;
import com.myspring.hungernet.Model.pojo.RestaurantPojo;
import com.myspring.hungernet.request.CreateMenuRequestDTO;
import com.myspring.hungernet.request.DeleteMenuRequestDTO;
import com.myspring.hungernet.request.FindAllMenusRequestDTO;
import com.myspring.hungernet.request.UpdateMenuRequestDTO;
import com.myspring.hungernet.service.MenuService;

@RestController
@RequestMapping(path = "/hungernet")
public class MenuController {
	@Autowired
	private MenuService menuService;

	@PostMapping(path = "/addMenu")
	// add new user to hungernetdb.user
	public ResponseEntity<String> addNewMenu(@RequestParam String userLoggedIn,
			@RequestBody CreateMenuRequestDTO menuDto) {
		return menuService.addNewMenu(userLoggedIn, menuDto);
	}

	@DeleteMapping(path = "/deleteMenu")
	public ResponseEntity<String> deleteByUserId(@RequestParam String userLoggedIn,
			@RequestBody DeleteMenuRequestDTO menuDto) {
		return menuService.deleteMenuById(userLoggedIn, menuDto);
	}

	@GetMapping(path = "/findAllMenus")
	public ResponseEntity<List<Menu>> findAllMenus(@RequestParam String userLoggedIn,
			@RequestBody FindAllMenusRequestDTO menuDto) {
		return menuService.findAllMenus(userLoggedIn, menuDto);
	}

	@PutMapping(path = "/updateMenu")
	public ResponseEntity<String> updateMenuById(@RequestParam String userLoggedIn,
			@RequestBody UpdateMenuRequestDTO menuDto) {
		return menuService.updateMenuById(userLoggedIn, menuDto);
	}
}
