package com.example.circling.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.circling.entity.Board;
import com.example.circling.entity.Chat;
import com.example.circling.entity.Chattable;
import com.example.circling.entity.Party;
import com.example.circling.entity.User;
import com.example.circling.entity.User_info;
import com.example.circling.repository.BoardRepository;
import com.example.circling.repository.ChatRepository;
import com.example.circling.repository.ChattableRepository;
import com.example.circling.repository.PartyRepository;
import com.example.circling.repository.UserRepository;
import com.example.circling.repository.User_infoRepository;

@Service
public class ConnectService {
	private final ChatRepository chatRepository;
	private final ChattableRepository chattableRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final User_infoRepository user_infoRepository;
	private final PartyRepository partyRepository;

	public ConnectService(ChatRepository chatRepository, ChattableRepository chattableRepository,
			UserRepository userRepository, BoardRepository boardRepository,
			User_infoRepository user_infoRepository, PartyRepository partyRepository) {
		this.chatRepository = chatRepository;
		this.chattableRepository = chattableRepository;
		this.userRepository = userRepository;
		this.boardRepository = boardRepository;
		this.user_infoRepository = user_infoRepository;
		this.partyRepository = partyRepository;
	}

	
	@Transactional
	public void connect(User user, User userd) {
		Board borad = new Board();
		borad.setTime(LocalDateTime.now());
		boardRepository.save(borad);
		Chattable chattable = new Chattable();
		Chattable chattabled = new Chattable();
		chattable.setBoard(borad);
		chattable.setUser(user);
		chattable.setTime(LocalDateTime.now());
		chattable.setDtime(LocalDateTime.now());
		chattableRepository.save(chattable);
		chattabled.setBoard(borad);
		chattabled.setUser(userd);
		chattabled.setTime(LocalDateTime.now());
		chattabled.setDtime(LocalDateTime.now());
		chattableRepository.save(chattabled);
	}

	@Transactional
	public void signup(User user) {
		Board board = new Board();
		board.setTime(LocalDateTime.now());
		boardRepository.save(board);
		Chattable chattable = new Chattable();
		chattable.setBoard(board);
		chattable.setUser(user);
		chattable.setTime(LocalDateTime.now());
		chattable.setDtime(LocalDateTime.now());
		chattableRepository.save(chattable);
		User kuser = userRepository.getReferenceById(1);
		Chattable kchattable = new Chattable();
		kchattable.setBoard(board);
		kchattable.setUser(kuser);
		kchattable.setTime(LocalDateTime.now());
		kchattable.setDtime(LocalDateTime.now());
		chattableRepository.save(kchattable);
		Chat chat = new Chat();
		chat.setBoard(board);
		chat.setChat("何かお困りごとがありましたら、気軽にご連絡ください");
		chat.setTime(LocalDateTime.now());
		chat.setUser(kuser);
		chatRepository.save(chat);
		Board board2 = new Board();
		board2.setTime(LocalDateTime.now());
		board2.setName("CirClIngNote");
		boardRepository.save(board2);
		Chattable chattable2 = new Chattable();
		chattable2.setBoard(board2);
		chattable2.setUser(user);
		chattable2.setTime(LocalDateTime.now());
		chattable2.setDtime(LocalDateTime.now());
		chattableRepository.save(chattable2);
		User_info user_info = new User_info();
		user_info.setTime(0);
		user_info.setUser(user);
		user_info.setMoney(1000000);
		user_infoRepository.save(user_info);
		Party party1 = new Party();
		party1.setUser(user);
		party1.setCount(1);
		partyRepository.save(party1);
		Party party2 = new Party();
		party2.setUser(user);
		party2.setCount(2);
		partyRepository.save(party2);
		Party party3 = new Party();
		party3.setUser(user);
		party3.setCount(3);
		partyRepository.save(party3);
		Party party4 = new Party();
		party4.setUser(user);
		party4.setCount(4);
		partyRepository.save(party4);
		Party party5 = new Party();
		party5.setUser(user);
		party5.setCount(5);
		partyRepository.save(party5);
		Party party6 = new Party();
		party6.setUser(user);
		party6.setCount(6);
		partyRepository.save(party6);
		Party party7 = new Party();
		party7.setUser(user);
		party7.setCount(7);
		partyRepository.save(party7);

	}

	@Transactional
	public void join(Board board, User user) {
		Chattable chattable = new Chattable();
		chattable.setBoard(board);
		chattable.setTime(LocalDateTime.now());
		chattable.setUser(user);
		chattable.setDtime(LocalDateTime.now());
		chattableRepository.save(chattable);
	}
}
