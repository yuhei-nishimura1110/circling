package com.example.circling.circling.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.circling.circling.entity.Board;
import com.example.circling.circling.entity.Chat;
import com.example.circling.circling.entity.Chattable;
import com.example.circling.circling.entity.User;
import com.example.circling.circling.form.ChatForm;
import com.example.circling.circling.form.ComeForm;
import com.example.circling.circling.form.GrupeNameEdit;
import com.example.circling.circling.repository.BoardRepository;
import com.example.circling.circling.repository.ChatRepository;
import com.example.circling.circling.repository.ChattableRepository;
import com.example.circling.circling.repository.UserRepository;
import com.example.circling.circling.service.ChatService;
import com.example.circling.circling.service.ConnectService;
import com.example.circling.security.UserDetailsImpl;

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
		this.connectService = connectService;
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

		List<Chat> chat = chatRepository.findByBoardOrderByIdAsc(board);
		model.addAttribute("chat", chat);
		if (chattable.getBoard().getName() == null) {
			chattable = chattableRepository.findByBoardAndUserNot(board, user);
		}
		model.addAttribute("chattable", chattable);
		ChatForm chatForm = new ChatForm();
		model.addAttribute("chatForm", chatForm);

		return "circling/chat/chat";
	}

	@PostMapping("/chat/{id}")
	public String chats(@ModelAttribute @Validated ChatForm chatedForm,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Model model, @PathVariable(name = "id") Integer id) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Board board = boardRepository.getReferenceById(id);
		Chattable chattable = chattableRepository.findByBoardAndUser(board, user);
		List<Chattable> chattabletime = chattableRepository.findByBoard(board);
		for (Chattable i : chattabletime) {
			i.setDtime(LocalDateTime.now());
		}
		chattable.setTime(LocalDateTime.now());
		chattableRepository.save(chattable);
		chatService.Create(chatedForm, user, board);
		model.addAttribute("user", user);
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

		List<Chat> chat = chatRepository.findByBoardOrderByIdAsc(board);
		model.addAttribute("chat", chat);

		model.addAttribute("chattable", chattable);
		ChatForm chatForm = new ChatForm();
		model.addAttribute("chatForm", chatForm);

		return "circling/chat/chat";
	}

	@GetMapping("/chat/{table}/user/{id}")
	public String user(Model model, @PathVariable(name = "id") Integer id, @PathVariable(name = "table") Integer table,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User myuser = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Board boards = boardRepository.getReferenceById(table);
		model.addAttribute("boards", boards);
		User youruser = userRepository.getReferenceById(id);
		if (chattableRepository.findByBoardAndUser(boardRepository.getReferenceById(table), youruser) == null) {
			return "redirect:/chat/{table}";
		}
		model.addAttribute("youruser", youruser);
		List<Chattable> chattableList = chattableRepository.findByUserOrderByDtimeDesc(myuser);
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
				model.addAttribute("board", i.getId());
			}
		}
		model.addAttribute("x", x);
		return "circling/chat/user";
	}

	@PostMapping("/chat/{table}/user/{id}/ed")
	public String usered(Model model, @PathVariable(name = "id") Integer id,
			@PathVariable(name = "table") Integer table,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User myuser = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Board boards = boardRepository.getReferenceById(table);
		model.addAttribute("boards", boards);
		User youruser = userRepository.getReferenceById(id);
		model.addAttribute("youruser", youruser);
		connectService.connect(myuser, youruser);
		List<Chattable> chattableList = chattableRepository.findByUserOrderByDtimeDesc(myuser);
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
				model.addAttribute("board", i.getId());
			}
		}
		model.addAttribute("x", x);
		return "circling/chat/user";
	}

	@GetMapping("/chat/{id}/list")
	public String list(Model model, @PathVariable(name = "id") Integer id,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Board board = boardRepository.getReferenceById(id);
		List<Chattable> chattableList = chattableRepository.findByBoard(board);
		model.addAttribute("chattableList", chattableList);
		model.addAttribute("user", user);
		model.addAttribute("board", board);
		return "chat/list";
	}

	@GetMapping("/chat/{id}/come")
	public String come(Model model, @PathVariable(name = "id") Integer id,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Board board = boardRepository.getReferenceById(id);
		model.addAttribute("board", board);
		List<Chattable> chattableListboard = chattableRepository.findByBoard(board);
		List<Chattable> chattableListfriend = chattableRepository.findByUserOrderByDtimeDesc(user);
		List<Chattable> chattableListselect = new ArrayList<>();
		boolean x = false;
		for (Chattable i : chattableListfriend) {
			if (i.getBoard().getName() == null) {
				x = true;
				for (Chattable k : chattableListboard) {
					if (chattableRepository.findByBoardAndUserNot(i.getBoard(), user).getUser() == k.getUser()) {
						x = false;
					}
				}
				if (x) {
					chattableListselect.add(chattableRepository.findByBoardAndUserNot(i.getBoard(), user));
				}
			}
		}
		chattableListselect.size();
		model.addAttribute("chattableListselect", chattableListselect);
		model.addAttribute("comeForm", new ComeForm());
		return "circling/chat/come";
	}

	@PostMapping("/chat/{id}/come/ed")
	public String come(@ModelAttribute @Validated ComeForm comeForm, Model model, @PathVariable(name = "id") Integer id,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		model.addAttribute("user", user);
		Board board = boardRepository.getReferenceById(id);
		model.addAttribute("board", board);
		for (Integer i : comeForm.getMenber()) {
			connectService.join(board, userRepository.getReferenceById(i));
		}
		List<Chattable> chattableList = chattableRepository.findByBoard(board);
		model.addAttribute("chattableList", chattableList);
		return "circling/chat/list";
	}

	@GetMapping("/chat/{id}/settings")
	public String settings(Model model, @PathVariable(name = "id") Integer id,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Board board = boardRepository.getReferenceById(id);
		model.addAttribute("board", board);
		model.addAttribute("grupeNameEdit", new GrupeNameEdit(board.getName()));
		return "circling/chat/settings";
	}

	@PostMapping("/chat/{id}/settings/ed")
	public String settingsed(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@ModelAttribute @Validated GrupeNameEdit grupeNameEdit, Model model, @PathVariable(name = "id") Integer id,
			BindingResult bindingResult) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		model.addAttribute("user", user);
		Board board = boardRepository.getReferenceById(id);
		if (bindingResult.hasErrors()) {
			model.addAttribute("board", board);
			return "circling/chat/settings";
		}

		board.setName(grupeNameEdit.getName());
		boardRepository.save(board);
		model.addAttribute("board", board);

		List<Chattable> chattableList = chattableRepository.findByBoard(board);
		model.addAttribute("chattableList", chattableList);
		return "circling/chat/list";
	}
}
