package com.example.circling.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.circling.entity.Board;
import com.example.circling.entity.Chat;
import com.example.circling.entity.Chattable;
import com.example.circling.entity.User;
import com.example.circling.form.ChatForm;
import com.example.circling.repository.BoardRepository;
import com.example.circling.repository.ChatRepository;
import com.example.circling.repository.ChattableRepository;
import com.example.circling.repository.UserRepository;
import com.example.circling.security.UserDetailsImpl;
import com.example.circling.service.ChatService;
import com.example.circling.service.ConnectService;

@Controller
public class ChatController {
	private final ChatRepository chatRepository;
	private final ChattableRepository chattableRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final ChatService chatService;
	private final ConnectService connectService;

	public ChatController(ChatRepository chatRepository, ChattableRepository chattableRepository,
			UserRepository userRepository, BoardRepository boardRepository, ChatService chatService,
			ConnectService connectService) {
		this.chatRepository = chatRepository;
		this.chattableRepository = chattableRepository;
		this.userRepository = userRepository;
		this.boardRepository = boardRepository;
		this.chatService = chatService;
		this.connectService =connectService;
	}

	@GetMapping("/chat/{id}")
	public String chat(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Model model, @PathVariable(name = "id") Integer id) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Board board = boardRepository.getReferenceById(id);
		Chattable chattable = chattableRepository.findByBoardAndUser(board, user);
		chattable.setTime(LocalDateTime.now());
		chattableRepository.save(chattable);
		model.addAttribute("user", user);
		List<Chattable> chattableList = chattableRepository.findByUserOrderByTimeDesc(user);
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


		List<Chat> chat = chatRepository.findByBoardOrderByIdAsc(board);
		model.addAttribute("chat", chat);
		if(chattable.getBoard().getName()==null) {
			chattable=chattableRepository.findByBoardAndUserNot(board, user);
		}
		model.addAttribute("chattable", chattable);
		ChatForm chatForm = new ChatForm();
		model.addAttribute("chatForm", chatForm);

		return "chat/chat";
	}

	@PostMapping("/chat/{id}")
	public String chats(@ModelAttribute @Validated ChatForm chatedForm,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Model model, @PathVariable(name = "id") Integer id) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Board board = boardRepository.getReferenceById(id);
		Chattable chattable = chattableRepository.findByBoardAndUser(board, user);
		chattable.setTime(LocalDateTime.now());
		chattableRepository.save(chattable);
		chatService.Create(chatedForm, user, board);
		model.addAttribute("user", user);
		List<Chattable> chattableList = chattableRepository.findByUserOrderByTimeDesc(user);
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

		List<Chat> chat = chatRepository.findByBoardOrderByIdAsc(board);
		model.addAttribute("chat", chat);

		model.addAttribute("chattable", chattable);
		ChatForm chatForm = new ChatForm();
		model.addAttribute("chatForm", chatForm);

		return "chat/chat";
	}

	@GetMapping("/chat/{table}/user/{id}")
	public String user(Model model, @PathVariable(name = "id") Integer id, @PathVariable(name = "table") Integer table,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User myuser = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Board boards=boardRepository.getReferenceById(table);
		model.addAttribute("boards",boards);
		User youruser = userRepository.getReferenceById(id);
		if (chattableRepository.findByBoardAndUser(boardRepository.getReferenceById(table), youruser) == null) {
			return "redirect:/chat/{table}";
		}
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
		return "chat/user";
	}
	@PostMapping("/chat/{table}/user/{id}/ed")
	public String usered(Model model, @PathVariable(name = "id") Integer id, @PathVariable(name = "table") Integer table,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User myuser = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Board boards=boardRepository.getReferenceById(table);
		model.addAttribute("boards",boards);
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
		return "chat/user";
	}
	@GetMapping("/chat/{id}/list")
	public String list(Model model, @PathVariable(name = "id") Integer id,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Board board =boardRepository.getReferenceById(id);
		List<Chattable> chattableList=chattableRepository.findByBoard(board);
		model.addAttribute("chattableList", chattableList);
		model.addAttribute("user",user);
		model.addAttribute("board",board);
		return "chat/list";
	}
}
