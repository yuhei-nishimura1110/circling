package com.example.circling.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.circling.entity.Board;
import com.example.circling.entity.Chat;
import com.example.circling.entity.User;
import com.example.circling.form.ChatForm;
import com.example.circling.repository.BoardRepository;
import com.example.circling.repository.ChatRepository;
import com.example.circling.repository.ChattableRepository;
import com.example.circling.repository.UserRepository;

@Service
public class ChatService {
	private final ChatRepository chatRepository;
	private final ChattableRepository chattableRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;

	public ChatService(ChatRepository chatRepository, ChattableRepository chattableRepository,
			UserRepository userRepository, BoardRepository boardRepository) {
		this.chatRepository = chatRepository;
		this.chattableRepository = chattableRepository;
		this.userRepository = userRepository;
		this.boardRepository = boardRepository;
	}

	@Transactional
	public void Create(ChatForm chatForm, User user, Board board) {
		Chat chat = new Chat();
		chat.setUser(user);
		chat.setBoard(board);
		chat.setChat(chatForm.getChat());
		chat.setTime(LocalDateTime.now());
		board.setTime(LocalDateTime.now());
		chatRepository.save(chat);
		boardRepository.save(board);

	}
}
