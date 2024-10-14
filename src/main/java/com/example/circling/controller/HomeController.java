package com.example.circling.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.circling.entity.User;
import com.example.circling.form.UserEditEmailForm;
import com.example.circling.form.UserEditNameForm;
import com.example.circling.form.UserEditPasswordForm;
import com.example.circling.repository.BoardRepository;
import com.example.circling.repository.ChatRepository;
import com.example.circling.repository.ChattableRepository;
import com.example.circling.repository.UserRepository;
import com.example.circling.security.UserDetailsImpl;
import com.example.circling.service.ChatService;
import com.example.circling.service.UserService;

@Controller
public class HomeController {
	private final ChatRepository chatRepository;
	private final ChattableRepository chattableRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final UserService userService;
	private final ChatService chatService;
	private final PasswordEncoder passwordEncoder;

	public HomeController(ChatRepository chatRepository, ChattableRepository chattableRepository,
			UserRepository userRepository, BoardRepository boardRepository, ChatService chatService,
			UserService userService, PasswordEncoder passwordEncoder) {
		this.chatRepository = chatRepository;
		this.chattableRepository = chattableRepository;
		this.userRepository = userRepository;
		this.boardRepository = boardRepository;
		this.chatService = chatService;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/home")
	public String home() {
		return "circling/home/home";
	}

	@GetMapping("/settings")
	public String settings(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		model.addAttribute("user", user);
		return "circling/home/settings";
	}

	@GetMapping("/settings/name")
	public String settingsname(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		model.addAttribute("userEditNameForm", new UserEditNameForm(user.getName()));
		model.addAttribute("user", user);
		System.out.println("a");
		return "circling/home/name";
	}

	@GetMapping("/settings/email")
	public String settingsemail(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		model.addAttribute("user", user);
		UserEditEmailForm userEditEmailForm = new UserEditEmailForm(user.getEmail());
		model.addAttribute("userEditEmailForm", userEditEmailForm);
		return "circling/home/email";
	}

	@GetMapping("/settings/password")
	public String settingspassword(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		model.addAttribute("user", user);
		UserEditPasswordForm userEditPasswordForm = new UserEditPasswordForm(null, null, null);
		model.addAttribute("userEditPasswordForm", userEditPasswordForm);
		return "circling/home/password";
	}

	@PostMapping("/settings/name")
	public String settingsnames(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@ModelAttribute @Validated UserEditNameForm userEditNameForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
			model.addAttribute("user", user);
			return "circling/home/name";
		}
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		user.setName(userEditNameForm.getName());
		userRepository.save(user);
		model.addAttribute("user", user);
		return "circling/home/settings";
	}

	@PostMapping("/settings/email")
	public String settingsemails(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@ModelAttribute @Validated UserEditEmailForm userEditEmailForm,
			BindingResult bindingResult, Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		if (user.getEmail().equals(userEditEmailForm.getEmail())) {

		} else {
			if (userService.isEmailRegistered(userEditEmailForm.getEmail())) {
				FieldError fieldError = new FieldError(bindingResult.getObjectName(), "password", "すでに登録済みのメールアドレスです。");
				bindingResult.addError(fieldError);
			}
			if (bindingResult.hasErrors()) {
				model.addAttribute("user", user);
				return "circling/home/email";
			}
		}
		user.setEmail(userEditEmailForm.getEmail());
		userRepository.save(user);
		model.addAttribute("user", user);
		return "circling/home/settings";
	}

	@PostMapping("/settings/password")
	public String settingspasswords(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@ModelAttribute @Validated UserEditPasswordForm userEditPasswordForm,
			BindingResult bindingResult, Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		if (passwordEncoder.matches(passwordEncoder.encode(userEditPasswordForm.getNowpassword()),
				user.getPassword())) {
			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "nowpassword", "現在のパスワードが一致しません。");
			bindingResult.addError(fieldError);
		}
		if (!userService.isSamePassword(userEditPasswordForm.getPassword(),
				userEditPasswordForm.getPasswordConfirmation())) {
			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "password", "確認用のパスワードと一致しません。");
			bindingResult.addError(fieldError);
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			return "circling/home/password";
		}
		userService.passwordupdate(user, userEditPasswordForm);
		model.addAttribute("user", user);
		return "circling/home/settings";
	}

}
