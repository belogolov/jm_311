package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final UserService userService;

	@Autowired
	public AdminController(UserService userService) {

		this.userService = userService;
	}

	@GetMapping
	public String printUsers(ModelMap model) {
		model.addAttribute("users", userService.listUsers());
		model.addAttribute("title", "All users");
		return "listOfUsers";
	}

	@GetMapping(value = "/add")
	public String newUser(ModelMap model) {
		model.addAttribute("user", new User());
		model.addAttribute("title", "New user");
		return "addUser";
	}

	@PostMapping(value = "/add")
	public String addUser(User user) {
		userService.add(user);
		return "redirect:/admin";
	}

	@GetMapping(value = "/delete")
	public String deleteUser(@RequestParam("id") long id) {
		userService.delete(id);
		return "redirect:/admin";
	}

	@GetMapping(value = "/edit")
	public String editUser(@RequestParam("id") long id, ModelMap model) {
		User userById = userService.getUserById(id);
		model.addAttribute("user", userById);
		model.addAttribute("allRoles", userService.listRoles());
		model.addAttribute("title", "Edit user");
		return "editUser";
	}

	@PostMapping(value = "/edit")
	public String updateUser(@ModelAttribute("user")User user,
							 @RequestParam(value = "newRoles", required = false) String[] roles) {
		userService.update(user);
		return "redirect:/admin";
	}
}