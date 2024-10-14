package com.example.circling.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.circling.entity.Party;
import com.example.circling.entity.Player;
import com.example.circling.entity.User;
import com.example.circling.entity.User_info;
import com.example.circling.form.PartyForm;
import com.example.circling.repository.PartyRepository;
import com.example.circling.repository.PlayerRepository;
import com.example.circling.repository.UserRepository;
import com.example.circling.repository.User_infoRepository;
import com.example.circling.security.UserDetailsImpl;
import com.example.circling.service.Transformation;

@Controller
@RequestMapping("/game/home/organization")
public class OrganizationController {
	private final PlayerRepository playerRepository;
	private final UserRepository userRepository;
	private final PartyRepository partyRepository;
	private final User_infoRepository user_infoRepository;

	public OrganizationController(PlayerRepository playerRepository, UserRepository userRepository,
			PartyRepository partyRepository, User_infoRepository user_infoRepository) {
		this.playerRepository = playerRepository;
		this.userRepository = userRepository;
		this.partyRepository = partyRepository;
		this.user_infoRepository = user_infoRepository;

	}

	@GetMapping("/{id}")
	public String index(@PageableDefault(size = 1000) Pageable pageable, Model model,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Transformation transformation, @PathVariable(name = "id") Integer id) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		System.out.println(user.getName() + "編成画面");
		model.addAttribute("user_info", user_info);
		model.addAttribute("id", id);
		switch (id) {
		case 1 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJobLessThan(user.getId(), 13, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		case 2 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJob(user.getId(), 13, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		case 3 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJob(user.getId(), 14, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		case 4 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJob(user.getId(), 15, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		case 5 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJob(user.getId(), 16, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		case 6 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJob(user.getId(), 17, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		case 7 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJob(user.getId(), 18, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		}

		Party party = partyRepository.findByUser_idAndCount(user.getId(), id);
		model.addAttribute("party", party);
		PartyForm partyForm = new PartyForm(null, null, null, null, null, null, null, null);
		model.addAttribute("transformation", transformation);
		return "game/organization";
	}

	@PostMapping("/{id}/ed")
	public String inded(@PageableDefault(size = 100) Pageable pageable, Model model,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Transformation transformation, @ModelAttribute @Validated PartyForm partyForm1,
			RedirectAttributes redirectAttributes, @PathVariable(name = "id") Integer id) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		System.out.println(user.getName() + "編成処理");
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("user_info", user_info);
		switch (id) {
		case 1 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJobLessThan(user.getId(), 13, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		case 2 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJob(user.getId(), 13, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		case 3 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJob(user.getId(), 14, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		case 4 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJob(user.getId(), 15, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		case 5 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJob(user.getId(), 16, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		case 6 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJob(user.getId(), 17, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		case 7 -> {
			Page<Player> playerPage = playerRepository.findByUser_idAndJob(user.getId(), 18, pageable);
			model.addAttribute("playerPage", playerPage);
		}
		}
		System.out.println(user.getName() + "編成処理２");
		Party party = partyRepository.findByUser_idAndCount(user.getId(), id);
		model.addAttribute("party", party);
		model.addAttribute("transformation", transformation);
		if (transformation.party(partyForm1.getPlayer_id1(), partyForm1.getPlayer_id2(), partyForm1.getPlayer_id3(),
				partyForm1.getPlayer_id4(), partyForm1.getPlayer_id5(), partyForm1.getPlayer_id6(),
				partyForm1.getPlayer_id7(), partyForm1.getPlayer_id8())) {
			if (partyForm1.getPlayer_id1() != null) {
				party.setPlayer1(playerRepository.getReferenceById(partyForm1.getPlayer_id1()));
			} else {
				party.setPlayer1(null);
			}
			if (partyForm1.getPlayer_id2() != null) {
				party.setPlayer2(playerRepository.getReferenceById(partyForm1.getPlayer_id2()));
			} else {
				party.setPlayer2(null);
			}
			if (partyForm1.getPlayer_id3() != null) {
				party.setPlayer3(playerRepository.getReferenceById(partyForm1.getPlayer_id3()));
			} else {
				party.setPlayer3(null);
			}
			if (partyForm1.getPlayer_id4() != null) {
				party.setPlayer4(playerRepository.getReferenceById(partyForm1.getPlayer_id4()));
			} else {
				party.setPlayer4(null);
			}
			if (partyForm1.getPlayer_id5() != null) {
				party.setPlayer5(playerRepository.getReferenceById(partyForm1.getPlayer_id5()));
			} else {
				party.setPlayer5(null);
			}
			if (partyForm1.getPlayer_id6() != null) {
				party.setPlayer6(playerRepository.getReferenceById(partyForm1.getPlayer_id6()));
			} else {
				party.setPlayer6(null);
			}
			if (partyForm1.getPlayer_id7() != null) {
				party.setPlayer7(playerRepository.getReferenceById(partyForm1.getPlayer_id7()));
			} else {
				party.setPlayer7(null);
			}
			if (partyForm1.getPlayer_id8() != null) {
				party.setPlayer8(playerRepository.getReferenceById(partyForm1.getPlayer_id8()));
			} else {
				party.setPlayer8(null);
			}

			partyRepository.save(party);

		} else {
			redirectAttributes.addFlashAttribute("successMessage", "冒険者の重複が無いように編成してください");
			System.out.println(user.getName() + "編成処理完重複");
			return "redirect:/home/organization/{id}";
		}
		PartyForm partyForm = new PartyForm(null, null, null, null, null, null, null, null);
		System.out.println(user.getName() + "編成処理完");
		return "game/organization";
	}
}
