package com.example.circling.game.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.circling.circling.entity.User;
import com.example.circling.circling.repository.UserRepository;
import com.example.circling.game.entity.Player;
import com.example.circling.game.entity.User_info;
import com.example.circling.game.form.ScoutForm;
import com.example.circling.game.repository.Family_nameRepository;
import com.example.circling.game.repository.PlayerRepository;
import com.example.circling.game.repository.User_infoRepository;
import com.example.circling.game.scout.Scout;
import com.example.circling.game.service.Transformation;
import com.example.circling.security.UserDetailsImpl;

@RequestMapping("/game/home/scout")
@Controller
public class ScoutController {

	private final UserRepository userRepository;
	private final PlayerRepository playerRepository;
	private final Family_nameRepository family_nameRepository;
	private final User_infoRepository user_infoRepository;

	public ScoutController(UserRepository userRepository, PlayerRepository playerRepository,
			Family_nameRepository family_nameRepository, User_infoRepository userinfoRepository) {
		this.userRepository = userRepository;
		this.playerRepository = playerRepository;
		this.family_nameRepository = family_nameRepository;
		this.user_infoRepository = userinfoRepository;
	}

	@GetMapping()
	public String scout(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model,
			Transformation transformation) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		System.out.println(user.getName() + "スカウト画面");
		ScoutForm scoutForm = new ScoutForm(family_nameRepository, user.getId(), "");
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("user_info", user_info);
		model.addAttribute("scoutForm", scoutForm);
		model.addAttribute("transformation", transformation);
		return "game/scout";
	}

	@Transactional
	@PostMapping("/result")
	public String scoutresult(@ModelAttribute @Validated ScoutForm scoutForm2,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model, Transformation transformation,
			Scout scout, @PageableDefault(size = 1000) Pageable pe) {

		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		System.out.println(user.getName() + "スカウト開始");
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		System.out.println(user.getName() + "スカウト本機能");
		Player player = scout.Scouta(scoutForm2.getName(), user, playerRepository, transformation);
		System.out.println(user.getName() + "スカウト残りの機能");
		user_info.setMoney(user_info.getMoney() - 1000);
		user_infoRepository.save(user_info);
		Page<Player> players = playerRepository.findByUser_id(user.getId(), pe);
		transformation.time(user_info, 1, user_infoRepository, players, playerRepository);
		model.addAttribute("player", player);
		model.addAttribute("scoutForm", scoutForm2);
		ScoutForm scoutForm = new ScoutForm(family_nameRepository, user.getId(), "");
		model.addAttribute("scoutForm", scoutForm);
		model.addAttribute("transformation", transformation);
		model.addAttribute("user_info", user_info);
		System.out.println(user.getName() + "スカウト機能完");
		return "game/scoutresult";
	}
}
