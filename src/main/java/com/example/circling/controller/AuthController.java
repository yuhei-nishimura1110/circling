package com.example.circling.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.circling.entity.User;
import com.example.circling.entity.VerificationToken;
import com.example.circling.event.SignupEventPublisher;
import com.example.circling.form.SignupForm;
import com.example.circling.service.ConnectService;
import com.example.circling.service.UserService;
import com.example.circling.service.VerificationTokenService;

@Controller
public class AuthController {
	private final UserService userService;
	private final SignupEventPublisher signupEventPublisher;
	private final VerificationTokenService verificationTokenService;
	private final ConnectService connectService;

	public AuthController(UserService userService, SignupEventPublisher signupEventPublisher,
			VerificationTokenService verificationTokenService, ConnectService connectService) {
		this.userService = userService;
		this.signupEventPublisher = signupEventPublisher;
		this.verificationTokenService = verificationTokenService;
		this.connectService = connectService;
	}

	@GetMapping("/login")
	public String login() {
		return "circling/auth/login";
	}

	@GetMapping("/signup/{id}")
	public String signup(@PathVariable(name = "id") String id, Model model) {
		model.addAttribute("signupForm", new SignupForm());
		model.addAttribute("id", id);
		return "circling/auth/signup";
	}

	@PostMapping("/signup/{id}")
	public String signup(@PathVariable(name = "id") String id,
			@ModelAttribute @Validated SignupForm signupForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest) {
		// メールアドレスが登録済みであれば、BindingResultオブジェクトにエラー内容を追加する
		if (userService.isEmailRegistered(signupForm.getEmail())) {
			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
			bindingResult.addError(fieldError);
		}

		// パスワードとパスワード（確認用）の入力値が一致しなければ、BindingResultオブジェクトにエラー内容を追加する
		if (!userService.isSamePassword(signupForm.getPassword(), signupForm.getPasswordConfirmation())) {
			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "password", "パスワードが一致しません。");
			bindingResult.addError(fieldError);
		}

		if (bindingResult.hasErrors()) {
			return "circling/auth/signup";
		}

		User createdUser = userService.create(signupForm, id);
		String requestUrl = new String(httpServletRequest.getRequestURL());
		signupEventPublisher.publishSignupEvent(createdUser, requestUrl);
		redirectAttributes.addFlashAttribute("successMessage",
				"ご入力いただいたメールアドレスに認証メールを送信しました。メールに記載されているリンクをクリックし、会員登録を完了してください。");
		connectService.signup(createdUser);
		return "redirect:/";
	}

	@GetMapping("/signup/{id}/verify")
	public String verify(@PathVariable(name = "id") String id, @RequestParam(name = "token") String token,
			Model model) {
		VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
		System.out.println("aaa");
		if (verificationToken != null) {
			User user = verificationToken.getUser();
			userService.enableUser(user);
			System.out.println("bbb");
			String successMessage = "会員登録が完了しました。";
			model.addAttribute("successMessage", successMessage);
		} else {
			String errorMessage = "トークンが無効です。";
			model.addAttribute("errorMessage", errorMessage);
		}

		return "circling/auth/verify";
	}

	@GetMapping("/logout")
	public String logout() {
		return "circling/top/top";
	}
}
