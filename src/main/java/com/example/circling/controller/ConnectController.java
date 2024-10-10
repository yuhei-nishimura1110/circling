package com.example.circling.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.circling.entity.Board;
import com.example.circling.entity.Chattable;
import com.example.circling.entity.User;
import com.example.circling.repository.BoardRepository;
import com.example.circling.repository.ChatRepository;
import com.example.circling.repository.ChattableRepository;
import com.example.circling.repository.UserRepository;
import com.example.circling.security.UserDetailsImpl;
import com.example.circling.service.ChatService;
import com.example.circling.service.ConnectService;

@Controller
public class ConnectController {
	private final ChatRepository chatRepository;
	private final ChattableRepository chattableRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final ChatService chatService;
	private final ConnectService connectService;

	public ConnectController(ChatRepository chatRepository, ChattableRepository chattableRepository,
			UserRepository userRepository, BoardRepository boardRepository, ChatService chatService,
			ConnectService connectService) {
		this.chatRepository = chatRepository;
		this.chattableRepository = chattableRepository;
		this.userRepository = userRepository;
		this.boardRepository = boardRepository;
		this.chatService = chatService;
		this.connectService =connectService;
	}
	@GetMapping("/connect/qrcode")
	public String a(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,Model model) {
		User user=userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		model.addAttribute("user",user.getId());
		return "connect/qrcode";
	}
	
	@GetMapping("/connect/{id}")
	public String b(@PathVariable(name = "id") Integer id,@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Model model) {
		User myuser = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		User youruser = userRepository.getReferenceById(id);
		model.addAttribute("youruser", youruser);
		List<Chattable> chattableList = chattableRepository.findByUserOrderByTimeDesc(myuser);
		List<Board> boardList = new ArrayList<Board>();
		for (Chattable i : chattableList) {
			if (i.getBoard().getName() == null) {
				boardList.add(i.getBoard());
			}
		}
		boolean x = false;
		for (Board i : boardList) {
			if (chattableRepository.findByBoardAndUser(i, youruser) != null) {
				x = true;
				model.addAttribute("board",i.getId());
			}
		}
		model.addAttribute("x",x);
		return "connect/user";
	}
	@PostMapping("/connect/{id}/ed")
	public String d(Model model, @PathVariable(name = "id") Integer id,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User myuser = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		User youruser = userRepository.getReferenceById(id);
		model.addAttribute("youruser", youruser);
		connectService.connect(myuser, youruser);
		List<Chattable> chattableList = chattableRepository.findByUserOrderByTimeDesc(myuser);
		List<Board> boardList = new ArrayList<Board>();
		for (Chattable i : chattableList) {
			if (i.getBoard().getName() == null) {
				boardList.add(i.getBoard());
			}
		}
		boolean x = false;
		for (Board i : boardList) {
			if (chattableRepository.findByBoardAndUser(i, youruser) != null) {
				x = true;
				model.addAttribute("board",i.getId());
			}
		}
		model.addAttribute("x",x);
		return "connect/user";
	}
	@GetMapping("/connect/camera")
	public String c() {
		return "connect/camera";
	}
}
