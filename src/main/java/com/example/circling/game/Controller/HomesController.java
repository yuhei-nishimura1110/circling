package com.example.circling.game.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.circling.circling.entity.User;
import com.example.circling.circling.repository.UserRepository;
import com.example.circling.game.Repository.User_infoRepository;
import com.example.circling.game.Service.Transformation;
import com.example.circling.game.entity.User_info;
import com.example.circling.security.UserDetailsImpl;

@Controller
@RequestMapping("/game/home")
public class HomesController {
	private final UserRepository userRepository;
	private final User_infoRepository user_infoRepository;

	public HomesController(UserRepository userRepository, User_infoRepository user_infoRepository) {
		this.userRepository = userRepository;
		this.user_infoRepository = user_infoRepository;
	}

	@GetMapping()
	public String home(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model,
			Transformation transformation) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		model.addAttribute("user", user);
		User_info user_info = user_infoRepository.findByUser_id(userDetailsImpl.getUser().getId());
		model.addAttribute("user_info", user_info);
		model.addAttribute("transformtion", transformation);
		System.out.println(user.getName() + "ホーム画面");
		return "game/home";
	}

	@GetMapping("/user")
	public String user(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		model.addAttribute("user", user);
		System.out.println(user.getName() + "ユーザー画面");
		return "game/user";
	}


	@GetMapping("/information")
	public String information() {
		return "game/information";
	}

	@GetMapping("/prologue")
	public String prologue() {
		return "game/prologue";
	}

	@GetMapping("/explanation")
	public String explanation() {
		return "game/explanation";
	}

}
