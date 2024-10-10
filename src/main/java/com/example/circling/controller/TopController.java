package com.example.circling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.circling.repository.BoardRepository;
import com.example.circling.repository.ChatRepository;
import com.example.circling.repository.ChattableRepository;
import com.example.circling.repository.UserRepository;

@Controller
public class TopController {
	private final ChatRepository chatRepository;
	private final ChattableRepository chattableRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	public TopController(ChatRepository chatRepository,ChattableRepository chattableRepository,
			UserRepository userRepository,BoardRepository boardRepository) {
		this.chatRepository =chatRepository;
		this.chattableRepository =chattableRepository;
		this.userRepository =userRepository;
		this.boardRepository= boardRepository;
	}
	@GetMapping("/")
	public String top() {
		return "top/top";
	}
	@GetMapping("/information")
	public String information() {
		return "top/information";
	}
	@GetMapping("/portfolio")
	public String portfolio() {
		return "top/portfolio";
	}
}
