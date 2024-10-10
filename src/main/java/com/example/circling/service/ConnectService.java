package com.example.circling.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.circling.entity.Board;
import com.example.circling.entity.Chat;
import com.example.circling.entity.Chattable;
import com.example.circling.entity.User;
import com.example.circling.repository.BoardRepository;
import com.example.circling.repository.ChatRepository;
import com.example.circling.repository.ChattableRepository;
import com.example.circling.repository.UserRepository;

@Service
public class ConnectService {
	private final ChatRepository chatRepository;
	private final ChattableRepository chattableRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;

	public ConnectService(ChatRepository chatRepository,ChattableRepository chattableRepository,
			UserRepository userRepository,BoardRepository boardRepository) {
		this.chatRepository =chatRepository;
		this.chattableRepository =chattableRepository;
		this.userRepository =userRepository;
		this.boardRepository= boardRepository;
	}
	@Transactional
	public void connect(User user,User userd) { 
		Board borad =new Board();
		borad.setTime(LocalDateTime.now());
		boardRepository.save(borad);
		Chattable chattable=new Chattable();
		Chattable chattabled = new Chattable();
		chattable.setBoard(borad);
		chattable.setUser(user);
		chattable.setTime(LocalDateTime.now());
		chattableRepository.save(chattable);
		chattabled.setBoard(borad);
		chattabled.setUser(userd);
		chattabled.setTime(LocalDateTime.now());
		chattableRepository.save(chattabled);		
	}
	
	@Transactional
	public void signup(User user) {
		Board board=new Board();
		board.setTime(LocalDateTime.now());
		boardRepository.save(board);
		Chattable chattable =new Chattable();
		chattable.setBoard(board);
		chattable.setUser(user);
		chattable.setTime(LocalDateTime.now());
		chattableRepository.save(chattable);
		User kuser =userRepository.getReferenceById(1);
		Chattable kchattable=new Chattable();
		kchattable.setBoard(board);
		kchattable.setUser(kuser);
		kchattable.setTime(LocalDateTime.now());
		chattableRepository.save(kchattable);
		Chat chat=new Chat();
		chat.setBoard(board);
		chat.setChat("何かお困りごとがありましたら、気軽にご連絡ください");
		chat.setTime(LocalDateTime.now());
		chat.setUser(kuser);
		chatRepository.save(chat);
		Board board2=new Board();
		board2.setTime(LocalDateTime.now());
		board2.setName("CirClIngNote");
		boardRepository.save(board2);
		Chattable chattable2=new Chattable();
		chattable2.setBoard(board2);
		chattable2.setUser(user);
		chattable2.setTime(LocalDateTime.now());
		chattableRepository.save(chattable2);
	}
	@Transactional
	public void join(Board board,User user) {
		Chattable chattable =new Chattable();
		chattable.setBoard(board);
		chattable.setTime(LocalDateTime.now());
		chattable.setUser(user);
		chattableRepository.save(chattable);
	}
}
