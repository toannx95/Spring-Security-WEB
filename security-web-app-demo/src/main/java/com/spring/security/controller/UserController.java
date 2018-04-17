package com.spring.security.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.security.dto.UserDTO;
import com.spring.security.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "", "login" }, method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public String home() {
		return "welcome";
	}

	@RequestMapping(value = "403", method = RequestMethod.GET)
	public String error() {
		return "403";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView();
		UserDTO userDTO = new UserDTO();

		mav.addObject("user", userDTO);
		mav.setViewName("register");
		return mav;
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ModelAndView createNewUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();

		UserDTO userExists = userService.findByUserName(userDTO.getUserName());
		if (!Objects.isNull(userExists)) {
			mav.addObject("existsUserMsg", "Username is already registered!");
			mav.setViewName("register");
			bindingResult.reject("userName");
		}

		if (bindingResult.hasErrors()) {
			mav.setViewName("register");
		} else {
			userService.create(userDTO);
			mav.addObject("successMsg", "User has been registered successfully!");
			mav.addObject("user", new UserDTO());
			mav.setViewName("register");
		}
		return mav;
	}

}
