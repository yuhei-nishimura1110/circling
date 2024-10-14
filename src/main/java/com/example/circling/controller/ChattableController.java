package com.example.circling.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.circling.entity.Board;
import com.example.circling.entity.Chat;
import com.example.circling.entity.Chattable;
import com.example.circling.entity.User;
import com.example.circling.form.TableNameForm;
import com.example.circling.repository.BoardRepository;
import com.example.circling.repository.ChatRepository;
import com.example.circling.repository.ChattableRepository;
import com.example.circling.repository.UserRepository;
import com.example.circling.security.UserDetailsImpl;
import com.example.circling.service.ConnectService;

@Controller
public class ChattableController {
	private final ChatRepository chatRepository;
	private final ChattableRepository chattableRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final ConnectService connectService;

	public ChattableController(ChatRepository chatRepository, ChattableRepository chattableRepository,
			UserRepository userRepository, BoardRepository boardRepository, ConnectService connectService) {
		this.chatRepository = chatRepository;
		this.chattableRepository = chattableRepository;
		this.userRepository = userRepository;
		this.boardRepository = boardRepository;
		this.connectService = connectService;
	}

	@GetMapping("/chat")
	public String chat(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		List<Chattable> chattableList = chattableRepository.findByUserOrderByDtimeDesc(user);
		Map<Board, String> chattableMap = new LinkedHashMap<>();
		Map<Board, Chat> lastchatMap = new LinkedHashMap<>();
		for (Chattable i : chattableList) {
			if (i.getBoard().getName() == null) {
				chattableMap.put(i.getBoard(),
						chattableRepository.findByBoardAndUserNot(i.getBoard(), user).getUser().getName());
			} else {
				chattableMap.put(i.getBoard(), i.getBoard().getName());
			}
			lastchatMap.put(i.getBoard(), chatRepository.findTopByBoardOrderByIdAsc(i.getBoard()));
		}
		model.addAttribute("chattableList", chattableList);
		model.addAttribute("chattableMap", chattableMap);
		model.addAttribute("lastchatMap", lastchatMap);
		return "circling/chattable/chattable";
	}

	@GetMapping("/chat/grope/create")
	public String gropecreate(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		model.addAttribute("tableNameForm", new TableNameForm());
		List<Chattable> chattable = chattableRepository.findByUserOrderByDtimeDesc(user);
		List<Chattable> chattableList = new ArrayList<>();
		for (Chattable i : chattable) {
			if (i.getBoard().getName() == null) {
				chattableList.add(chattableRepository.findByBoardAndUserNot(i.getBoard(), user));
			}
		}
		model.addAttribute("chattableList", chattableList);
		return "circling/chattable/gropecreate";
	}

	@PostMapping("/chat/grope/create")
	public String gropecreated(@ModelAttribute @Validated TableNameForm tableNameForm,
			BindingResult bindingResult, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		if (bindingResult.hasErrors()) {
			List<Chattable> chattable = chattableRepository.findByUserOrderByDtimeDesc(user);
			List<Chattable> chattableList = new ArrayList<>();
			for (Chattable i : chattable) {
				if (i.getBoard().getName() == null) {
					chattableList.add(chattableRepository.findByBoardAndUserNot(i.getBoard(), user));
				}
			}
			model.addAttribute("chattableList", chattableList);
			return "circling/chattable/gropecreate";
		}

		Board board = new Board();
		board.setName(tableNameForm.getName());
		board.setTime(LocalDateTime.now());
		boardRepository.save(board);
		connectService.join(board, user);
		for (Integer i : tableNameForm.getNumber()) {
			connectService.join(board, userRepository.getReferenceById(i));
		}
		return "redirect:/chat";
	}
}
