package com.eshop.project.ui.controllers;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eshop.project.api.entities.Role;
import com.eshop.project.api.entities.User;
import com.eshop.project.api.models.response.UserResponse;
import com.eshop.project.api.repository.UserRepository;
import com.eshop.project.api.services.RoleService;
import com.eshop.project.api.services.UserService;
import com.eshop.project.api.shared.utils.Utils;

@Controller
@RequestMapping("/admin")
public class UserViewController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Utils utils;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/users")
	public String getUsers(Model model) {
		List<UserResponse> userResponseList = null;

		List<User> users = userService.getUsers();
		if (users != null && !users.isEmpty()) {
			Type listType = new TypeToken<List<UserResponse>>() {
			}.getType();
			userResponseList = new ModelMapper().map(users, listType);

		}
		model.addAttribute("users", userResponseList);
		return "users.html";

	}

	@GetMapping("/users/create")
	public String showCreateUserForm(Model model) {
		User user = new User();
		List<Role> roles = roleService.findAllRoles();
		user.setActive(true);
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		return "create_user.html";

	}

	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		user.setUserId(utils.generateRandomUserId(30));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		redirectAttributes.addFlashAttribute("message", "USER HAS BEEN SAVED SUCCESSULLY");
		return "redirect:/admin/users";
	}

	@GetMapping("/users/update/redirect")
	public String redirectUpdate(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "USER HAS BEEN UPDATED SUCCESSFULLY");
		return "redirect:/admin/users";
	}

	@GetMapping("/users/delete/redirect")
	public String redirectDelete(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "USER HAS BEEN DELETED SUCCESSFULLY");
		return "redirect:/admin/users";
	}

}
