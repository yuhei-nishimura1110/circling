package com.example.circling.game.controller;

import java.util.ArrayList;

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

import com.example.circling.circling.entity.User;
import com.example.circling.circling.repository.UserRepository;
import com.example.circling.game.battle.Main_battle;
import com.example.circling.game.entity.Item;
import com.example.circling.game.entity.Party;
import com.example.circling.game.entity.Player;
import com.example.circling.game.entity.User_info;
import com.example.circling.game.form.BattleForm;
import com.example.circling.game.repository.ItemRepository;
import com.example.circling.game.repository.PartyRepository;
import com.example.circling.game.repository.PlayerRepository;
import com.example.circling.game.repository.User_infoRepository;
import com.example.circling.game.service.Transformation;
import com.example.circling.security.UserDetailsImpl;

@RequestMapping("/game/home/battle")
@Controller
public class BattleController {
	private final UserRepository userRepository;
	private final PartyRepository partyRepository;
	private final ItemRepository itemRepository;
	private final User_infoRepository user_infoRepository;
	private final PlayerRepository playerRepository;

	public BattleController(UserRepository userRepository, PartyRepository partyRepository,
			ItemRepository itemRepository, User_infoRepository user_infoRepository, PlayerRepository playerRepository) {
		this.userRepository = userRepository;
		this.partyRepository = partyRepository;
		this.itemRepository = itemRepository;
		this.user_infoRepository = user_infoRepository;
		this.playerRepository = playerRepository;
	}

	@GetMapping("/menu")
	public String battlemenu(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model,
			Transformation transformation) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("user_info", user_info);
		model.addAttribute("transformation", transformation);
		System.out.println(user.getName() + "行き場所画面");
		return "game/battle/battlemenu";
	}

	@GetMapping("/menu{id}")
	public String battlemenu(@PathVariable(name = "id") Integer id, Model model,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Transformation transformation) {
		model.addAttribute("id", id);
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("user_info", user_info);
		model.addAttribute("transformation", transformation);
		System.out.println(user.getName() + "一覧画面");
		return "game/battle/kaisou";
	}

	@GetMapping("/moon")
	public String moon(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model,
			Transformation transformation) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		user_info.setMoney(user_info.getMoney() - 10000);
		user_infoRepository.save(user_info);
		model.addAttribute("user_info", user_info);
		model.addAttribute("transformation", transformation);
		System.out.println(user.getName() + "月画面");
		return "game/battle/moon";
	}

	@GetMapping("/{id}")
	public String battle(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model,
			@PathVariable(name = "id") Integer id, Transformation transformation, Main_battle main_battle,
			@PageableDefault(size = 1000) Pageable pe) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		System.out.println(user.getName() + "戦闘画面");
		Party friendparty = partyRepository.findByUser_idAndCount(user.getId(), 1);
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		Page<Player> player = playerRepository.findByUser_id(user.getId(), pe);
		switch (id) {
		case 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 -> {
			transformation.time(user_info, 1, user_infoRepository, player, playerRepository);
		}
		case 20, 21, 22, 23, 24, 25, 26, 27, 28, 29 -> {
			transformation.time(user_info, 60, user_infoRepository, player, playerRepository);
		}
		case 30, 31, 32, 33, 34, 35, 36, 37, 38, 39 -> {
			transformation.time(user_info, 15, user_infoRepository, player, playerRepository);
		}
		case 40, 41, 42, 43, 44, 45, 46, 47, 48, 49 -> {
			transformation.time(user_info, 60, user_infoRepository, player, playerRepository);
		}
		case 50, 51, 52, 53, 54, 55, 56, 57, 58, 59 -> {
			transformation.time(user_info, 60, user_infoRepository, player, playerRepository);
		}
		case 60, 61, 62, 63, 64, 65, 66, 67, 68, 69 -> {
			transformation.time(user_info, 60, user_infoRepository, player, playerRepository);
		}
		case 70, 71, 72, 73, 74, 75, 76, 77, 78, 79 -> {
			transformation.time(user_info, 75, user_infoRepository, player, playerRepository);
		}
		case 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 -> {
			transformation.time(user_info, 105, user_infoRepository, player, playerRepository);
		}
		case 90, 91, 92, 93, 94, 95, 96, 97, 98, 99 -> {
			transformation.time(user_info, 60, user_infoRepository, player, playerRepository);
		}
		case 100 -> {
			transformation.time(user_info, 105, user_infoRepository, player, playerRepository);
			user_info.setMoney(user_info.getMoney() - 10000);
		}

		}
		user_infoRepository.save(user_info);
		if (user_info.getMoney() <= 0) {
			if (friendparty.getPlayer1() != null) {
				friendparty.getPlayer1().setCp(0);
				playerRepository.save(friendparty.getPlayer1());
			}
			if (friendparty.getPlayer2() != null) {
				friendparty.getPlayer2().setCp(0);
				playerRepository.save(friendparty.getPlayer2());
			}
			if (friendparty.getPlayer3() != null) {
				friendparty.getPlayer3().setCp(0);
				playerRepository.save(friendparty.getPlayer3());
			}
			if (friendparty.getPlayer4() != null) {
				friendparty.getPlayer4().setCp(0);
				playerRepository.save(friendparty.getPlayer4());
			}
			if (friendparty.getPlayer5() != null) {
				friendparty.getPlayer5().setCp(0);
				playerRepository.save(friendparty.getPlayer5());
			}
			if (friendparty.getPlayer6() != null) {
				friendparty.getPlayer6().setCp(0);
				playerRepository.save(friendparty.getPlayer6());
			}
			if (friendparty.getPlayer7() != null) {
				friendparty.getPlayer7().setCp(0);
				playerRepository.save(friendparty.getPlayer7());
			}
			if (friendparty.getPlayer8() != null) {
				friendparty.getPlayer8().setCp(0);
				playerRepository.save(friendparty.getPlayer8());
			}
		}

		if (friendparty.getPlayer1() != null) {
			main_battle.cpfp(friendparty.getPlayer1(), playerRepository);
			playerRepository.save(friendparty.getPlayer1());
		}
		if (friendparty.getPlayer2() != null) {
			main_battle.cpfp(friendparty.getPlayer2(), playerRepository);
			playerRepository.save(friendparty.getPlayer2());
		}
		if (friendparty.getPlayer3() != null) {
			main_battle.cpfp(friendparty.getPlayer3(), playerRepository);
			playerRepository.save(friendparty.getPlayer3());
		}
		if (friendparty.getPlayer4() != null) {
			main_battle.cpfp(friendparty.getPlayer4(), playerRepository);
			playerRepository.save(friendparty.getPlayer4());
		}
		if (friendparty.getPlayer5() != null) {
			main_battle.cpfp(friendparty.getPlayer5(), playerRepository);
			playerRepository.save(friendparty.getPlayer5());
		}
		if (friendparty.getPlayer6() != null) {
			main_battle.cpfp(friendparty.getPlayer6(), playerRepository);
			playerRepository.save(friendparty.getPlayer6());
		}
		if (friendparty.getPlayer7() != null) {
			main_battle.cpfp(friendparty.getPlayer7(), playerRepository);
			playerRepository.save(friendparty.getPlayer7());
		}
		if (friendparty.getPlayer8() != null) {
			main_battle.cpfp(friendparty.getPlayer8(), playerRepository);
			playerRepository.save(friendparty.getPlayer8());
		}

		model.addAttribute("friendparty", friendparty);

		Party enemyparty = partyRepository.findByUser_idAndCount(2, id);
		model.addAttribute("enemyparty", enemyparty);
		int h1 = 0;
		int h2 = 0;
		int h3 = 0;
		int h4 = 0;
		int h5 = 0;
		int h6 = 0;
		int h7 = 0;
		int h8 = 0;
		if (enemyparty.getPlayer1() != null) {
			h1 = enemyparty.getPlayer1().getHp();
		}
		if (enemyparty.getPlayer2() != null) {
			h2 = enemyparty.getPlayer2().getHp();
		}
		if (enemyparty.getPlayer3() != null) {
			h3 = enemyparty.getPlayer3().getHp();
		}
		if (enemyparty.getPlayer4() != null) {
			h4 = enemyparty.getPlayer4().getHp();
		}
		if (enemyparty.getPlayer5() != null) {
			h5 = enemyparty.getPlayer5().getHp();
		}
		if (enemyparty.getPlayer6() != null) {
			h6 = enemyparty.getPlayer6().getHp();
		}
		if (enemyparty.getPlayer7() != null) {
			h7 = enemyparty.getPlayer7().getHp();
		}
		if (enemyparty.getPlayer8() != null) {
			h8 = enemyparty.getPlayer8().getHp();
		}
		int ides = 0;

		model.addAttribute("ides", ides);
		BattleForm battleForm = new BattleForm(0, 0, 0, 0, 0, 0, 0, 0, h1, h2, h3, h4, h5, h6, h7, h8);
		model.addAttribute("battleForm", battleForm);
		model.addAttribute("transformation", transformation);
		return "game/battle/battle";
	}

	@PostMapping("/{id}/ed")
	public String battled(@ModelAttribute @Validated BattleForm battle,
			@PathVariable(name = "id") Integer id,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model,
			Transformation transformation, Main_battle main_battle) {

		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Party friendparty = partyRepository.findByUser_idAndCount(user.getId(), 1);
		Party enemyparty = partyRepository.findByUser_idAndCount(2, id);
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		//処理
		System.out.println(user.getName() + "戦闘モンスター攻撃力");
		double x = main_battle.monsterattack(enemyparty, battle.getH1(), battle.getH2(),
				battle.getH3(),
				battle.getH4(), battle.getH5(), battle.getH6(), battle.getH7(),
				battle.getH8());
		x /= 10;
		System.out.println(user.getName() + "戦闘変数定義");
		//変数作成ゾーン
		ArrayList<Integer> distans = new ArrayList<Integer>();
		double gard = 1;
		double attack = 1;
		double suport = 1;
		ArrayList<String> message = new ArrayList<String>();
		for (int i = 0; i < 16; i++) {
			distans.add(0);
		}
		//定義ゾーン
		System.out.println(user.getName() + "戦闘主に長さなどの定義");
		if (friendparty.getPlayer1() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer1().getId(), main_battle.Jobitem(friendparty.getPlayer1().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			if (friendparty.getPlayer1().getJob() == 1) {
				if (battle.getPlayer1() == 0) {
					distans.set(0, 7);
				} else if (battle.getPlayer1() == 1) {
					distans.set(0, 4);
				} else if (battle.getPlayer1() == 2) {
					distans.set(0, 4);
				}
			} else if (friendparty.getPlayer1().getJob() == 2) {
				if (battle.getPlayer1() == 0) {
					distans.set(0, 7);
				} else if (battle.getPlayer1() == 1) {
					distans.set(0, 3);
				} else if (battle.getPlayer1() == 2) {
					distans.set(0, 3);
				}

			} else if (friendparty.getPlayer1().getJob() == 3) {
				if (battle.getPlayer1() == 0) {
					distans.set(0, 7);
				} else if (battle.getPlayer1() == 1 && friendparty.getPlayer1().getMp() >= 5) {
					distans.set(0, 5);
				} else if (battle.getPlayer1() == 2 && friendparty.getPlayer1().getMp() >= 5) {
					distans.set(0, 5);
				} else {
					distans.set(0, 5);
					message.add(friendparty.getPlayer1().getName() + "の攻撃がMP不足により失敗しました。");
				}
			} else if (friendparty.getPlayer1().getJob() == 4) {
				if (battle.getPlayer1() == 0) {
					distans.set(0, 7);
				} else if (battle.getPlayer1() == 1) {
					distans.set(0, 4);
				} else if (battle.getPlayer1() == 2) {
					distans.set(0, 4);
				}
			} else if (friendparty.getPlayer1().getJob() == 5) {
				if (battle.getPlayer1() == 0) {
					distans.set(0, 7);
				} else if (battle.getPlayer1() == 1) {
					distans.set(0, 4);
				} else if (battle.getPlayer1() == 2) {
					distans.set(0, 4);
				}
			} else if (friendparty.getPlayer1().getJob() == 6) {
				if (battle.getPlayer1() == 0) {
					distans.set(0, 7);
				} else if (battle.getPlayer1() == 1) {
					distans.set(0, 3);
				} else if (battle.getPlayer1() == 2) {
					distans.set(0, 3);
				}
			} else if (friendparty.getPlayer1().getJob() == 7) {
				if (battle.getPlayer1() == 0) {
					distans.set(0, 7);
				} else if (battle.getPlayer1() == 1) {
					distans.set(0, 4);
				} else if (battle.getPlayer1() == 2) {
					distans.set(0, 4);
				}
			} else if (friendparty.getPlayer1().getJob() == 8) {
				if (battle.getPlayer1() == 0) {
					distans.set(0, 7);
				} else if (battle.getPlayer1() == 1 && friendparty.getPlayer1().getMp() >= 5) {
					distans.set(0, 5);
				} else if (battle.getPlayer1() == 2 && friendparty.getPlayer1().getMp() >= 5) {
					distans.set(0, 5);
				} else {
					distans.set(0, 5);
					message.add(friendparty.getPlayer1().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer1().getJob() == 9) {
				if (battle.getPlayer1() == 0) {
					distans.set(0, 7);
				} else if (battle.getPlayer1() == 1) {
					distans.set(0, 2);

					gard *= (2560 - (friendparty.getPlayer1().getMuscle() * friendparty.getPlayer1().getSpirit()
							* friendparty.getPlayer1().getQuick() * itemst)) / 2560 * 0.95;
				} else if (battle.getPlayer1() == 2) {
					distans.set(0, 2);

					gard *= (2560 - (friendparty.getPlayer1().getMuscle() * friendparty.getPlayer1().getSpirit()
							* friendparty.getPlayer1().getQuick() * itemst)) / 2560 * 0.9;
				}
			} else if (friendparty.getPlayer1().getJob() == 10) {
				if (battle.getPlayer1() == 0) {
					distans.set(0, 7);
				} else if (battle.getPlayer1() == 1 && friendparty.getPlayer1().getMp() >= 5) {
					distans.set(0, 5);
					distans.set(8, 1);

					gard *= (2560 - (friendparty.getPlayer1().getTechnique() * friendparty.getPlayer1().getMagic()
							* friendparty.getPlayer1().getKnowledge() * itemst)) / 2560 * 0.96;
				} else if (battle.getPlayer1() == 2 && friendparty.getPlayer1().getMp() >= 5) {
					distans.set(0, 5);
					distans.set(8, 1);

					gard *= (2560 - (friendparty.getPlayer1().getTechnique() * friendparty.getPlayer1().getMagic()
							* friendparty.getPlayer1().getKnowledge() * itemst)) / 2560 * 0.9;
				} else {
					distans.set(0, 5);
					message.add(friendparty.getPlayer1().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer1().getJob() == 11) {
				distans.set(0, 7);
				if (battle.getPlayer1() == 0) {

				} else {
					if (friendparty.getPlayer1().getMp() < 3) {
						message.add(friendparty.getPlayer1().getName() + "がMP不足により攻撃に失敗しました。");
					}
				}
			} else if (friendparty.getPlayer1().getJob() == 12) {
				distans.set(0, 6);
				attack *= (friendparty.getPlayer1().getJob_sworder() + friendparty.getPlayer1().getJob_spearer()
						+ friendparty.getPlayer1().getJob_archer() + friendparty.getPlayer1().getJob_katanaer()
						+ friendparty.getPlayer1().getJob_axer() + friendparty.getPlayer1().getJob_melleter()
						+ friendparty.getPlayer1().getJob_cluber()
						+ friendparty.getPlayer1().getJob_black_magician())
						* friendparty.getPlayer1().getJob_commander() / 100;
				if (friendparty.getPlayer1().getJob_shielder() + friendparty.getPlayer1().getJob_magician() < 200) {
					gard *= (200 - friendparty.getPlayer1().getJob_shielder()
							- friendparty.getPlayer1().getJob_magician()) / 2
							/ (friendparty.getPlayer1().getJob_commander() + 1);
				} else {
					gard = 0;
				}
				suport *= friendparty.getPlayer1().getJob_commander()
						* friendparty.getPlayer1().getJob_white_magician()
						/ 100;
			}
		}
		if (friendparty.getPlayer2() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer2().getId(), main_battle.Jobitem(friendparty.getPlayer2().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			if (friendparty.getPlayer2().getJob() == 1) {
				if (battle.getPlayer2() == 0) {
					distans.set(1, 7);
				} else if (battle.getPlayer2() == 1) {
					distans.set(1, 4);
				} else if (battle.getPlayer2() == 2) {
					distans.set(1, 4);
				}
			} else if (friendparty.getPlayer2().getJob() == 2) {
				if (battle.getPlayer2() == 0) {
					distans.set(1, 7);
				} else if (battle.getPlayer2() == 1) {
					distans.set(1, 3);
				} else if (battle.getPlayer2() == 2) {
					distans.set(1, 3);
				}

			} else if (friendparty.getPlayer2().getJob() == 3) {
				if (battle.getPlayer2() == 0) {
					distans.set(1, 7);
				} else if (battle.getPlayer2() == 1 && friendparty.getPlayer2().getMp() >= 5) {
					distans.set(1, 5);
				} else if (battle.getPlayer2() == 2 && friendparty.getPlayer2().getMp() >= 5) {
					distans.set(1, 5);
				} else {
					distans.set(1, 5);
					message.add(friendparty.getPlayer2().getName() + "の攻撃がMP不足により失敗しました。");
				}
			} else if (friendparty.getPlayer2().getJob() == 4) {
				if (battle.getPlayer2() == 0) {
					distans.set(1, 7);
				} else if (battle.getPlayer2() == 1) {
					distans.set(1, 4);
				} else if (battle.getPlayer2() == 2) {
					distans.set(1, 4);
				}
			} else if (friendparty.getPlayer2().getJob() == 5) {
				if (battle.getPlayer2() == 0) {
					distans.set(1, 7);
				} else if (battle.getPlayer2() == 1) {
					distans.set(1, 4);
				} else if (battle.getPlayer2() == 2) {
					distans.set(1, 4);
				}
			} else if (friendparty.getPlayer2().getJob() == 6) {
				if (battle.getPlayer2() == 0) {
					distans.set(1, 7);
				} else if (battle.getPlayer2() == 1) {
					distans.set(1, 3);
				} else if (battle.getPlayer2() == 2) {
					distans.set(1, 3);
				}
			} else if (friendparty.getPlayer2().getJob() == 7) {
				if (battle.getPlayer2() == 0) {
					distans.set(1, 7);
				} else if (battle.getPlayer2() == 1) {
					distans.set(1, 4);
				} else if (battle.getPlayer2() == 2) {
					distans.set(1, 4);
				}
			} else if (friendparty.getPlayer2().getJob() == 8) {
				if (battle.getPlayer2() == 0) {
					distans.set(1, 7);
				} else if (battle.getPlayer2() == 1 && friendparty.getPlayer2().getMp() >= 5) {
					distans.set(1, 5);
				} else if (battle.getPlayer2() == 2 && friendparty.getPlayer2().getMp() >= 5) {
					distans.set(1, 5);
				} else {
					distans.set(1, 5);
					message.add(friendparty.getPlayer2().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer2().getJob() == 9) {
				if (battle.getPlayer2() == 0) {
					distans.set(1, 7);
				} else if (battle.getPlayer2() == 1) {
					distans.set(1, 2);

					gard *= (2560 - (friendparty.getPlayer2().getMuscle() * friendparty.getPlayer2().getSpirit()
							* friendparty.getPlayer2().getQuick() * itemst)) / 2560 * 0.95;
				} else if (battle.getPlayer2() == 2) {
					distans.set(1, 2);

					gard *= (2560 - (friendparty.getPlayer2().getMuscle() * friendparty.getPlayer2().getSpirit()
							* friendparty.getPlayer2().getQuick() * itemst)) / 2560 * 0.9;
				}
			} else if (friendparty.getPlayer2().getJob() == 10) {
				if (battle.getPlayer2() == 0) {
					distans.set(1, 7);
				} else if (battle.getPlayer2() == 1 && friendparty.getPlayer2().getMp() >= 5) {
					distans.set(1, 5);
					distans.set(9, 1);

					gard *= (2560 - (friendparty.getPlayer2().getTechnique() * friendparty.getPlayer2().getMagic()
							* friendparty.getPlayer2().getKnowledge() * itemst)) / 2560 * 0.96;
				} else if (battle.getPlayer2() == 2 && friendparty.getPlayer2().getMp() >= 5) {
					distans.set(1, 5);
					distans.set(9, 1);

					gard *= (2560 - (friendparty.getPlayer2().getTechnique() * friendparty.getPlayer2().getMagic()
							* friendparty.getPlayer2().getKnowledge() * itemst)) / 2560 * 0.9;
				} else {
					distans.set(1, 5);
					message.add(friendparty.getPlayer2().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer2().getJob() == 11) {
				distans.set(1, 7);
				if (battle.getPlayer2() == 0) {

				} else {
					if (friendparty.getPlayer2().getMp() < 3) {
						message.add(friendparty.getPlayer2().getName() + "がMP不足により攻撃に失敗しました。");
					}
				}
			} else if (friendparty.getPlayer2().getJob() == 12) {
				distans.set(1, 6);
				attack *= (friendparty.getPlayer2().getJob_sworder() + friendparty.getPlayer2().getJob_spearer()
						+ friendparty.getPlayer2().getJob_archer() + friendparty.getPlayer2().getJob_katanaer()
						+ friendparty.getPlayer2().getJob_axer() + friendparty.getPlayer2().getJob_melleter()
						+ friendparty.getPlayer2().getJob_cluber()
						+ friendparty.getPlayer2().getJob_black_magician())
						* friendparty.getPlayer2().getJob_commander() / 100;
				if (friendparty.getPlayer2().getJob_shielder() + friendparty.getPlayer2().getJob_magician() < 200) {
					gard *= (200 - friendparty.getPlayer2().getJob_shielder()
							- friendparty.getPlayer2().getJob_magician()) / 2
							/ (friendparty.getPlayer2().getJob_commander() + 1);
				} else {
					gard = 0;
				}
				suport *= friendparty.getPlayer2().getJob_commander()
						* friendparty.getPlayer2().getJob_white_magician()
						/ 100;
			}
		}

		if (friendparty.getPlayer3() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer3().getId(), main_battle.Jobitem(friendparty.getPlayer3().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			if (friendparty.getPlayer3().getJob() == 1) {
				if (battle.getPlayer3() == 0) {
					distans.set(2, 7);
				} else if (battle.getPlayer3() == 1) {
					distans.set(2, 4);
				} else if (battle.getPlayer3() == 2) {
					distans.set(2, 4);
				}
			} else if (friendparty.getPlayer3().getJob() == 2) {
				if (battle.getPlayer3() == 0) {
					distans.set(2, 7);
				} else if (battle.getPlayer3() == 1) {
					distans.set(2, 3);
				} else if (battle.getPlayer3() == 2) {
					distans.set(2, 3);
				}

			} else if (friendparty.getPlayer3().getJob() == 3) {
				if (battle.getPlayer3() == 0) {
					distans.set(2, 7);
				} else if (battle.getPlayer3() == 1 && friendparty.getPlayer3().getMp() >= 5) {
					distans.set(2, 5);
				} else if (battle.getPlayer3() == 2 && friendparty.getPlayer3().getMp() >= 5) {
					distans.set(2, 5);
				} else {
					distans.set(2, 5);
					message.add(friendparty.getPlayer3().getName() + "の攻撃がMP不足により失敗しました。");
				}
			} else if (friendparty.getPlayer3().getJob() == 4) {
				if (battle.getPlayer3() == 0) {
					distans.set(2, 7);
				} else if (battle.getPlayer3() == 1) {
					distans.set(2, 4);
				} else if (battle.getPlayer3() == 2) {
					distans.set(2, 4);
				}
			} else if (friendparty.getPlayer3().getJob() == 5) {
				if (battle.getPlayer3() == 0) {
					distans.set(2, 7);
				} else if (battle.getPlayer3() == 1) {
					distans.set(2, 4);
				} else if (battle.getPlayer3() == 2) {
					distans.set(2, 4);
				}
			} else if (friendparty.getPlayer3().getJob() == 6) {
				if (battle.getPlayer3() == 0) {
					distans.set(2, 7);
				} else if (battle.getPlayer3() == 1) {
					distans.set(2, 3);
				} else if (battle.getPlayer3() == 2) {
					distans.set(2, 3);
				}
			} else if (friendparty.getPlayer3().getJob() == 7) {
				if (battle.getPlayer3() == 0) {
					distans.set(2, 7);
				} else if (battle.getPlayer3() == 1) {
					distans.set(2, 4);
				} else if (battle.getPlayer3() == 2) {
					distans.set(2, 4);
				}
			} else if (friendparty.getPlayer3().getJob() == 8) {
				if (battle.getPlayer3() == 0) {
					distans.set(2, 7);
				} else if (battle.getPlayer3() == 1 && friendparty.getPlayer3().getMp() >= 5) {
					distans.set(2, 5);
				} else if (battle.getPlayer3() == 2 && friendparty.getPlayer3().getMp() >= 5) {
					distans.set(2, 5);
				} else {
					distans.set(2, 5);
					message.add(friendparty.getPlayer3().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer3().getJob() == 9) {
				if (battle.getPlayer3() == 0) {
					distans.set(2, 7);
				} else if (battle.getPlayer3() == 1) {
					distans.set(2, 2);

					gard *= (2560 - (friendparty.getPlayer3().getMuscle() * friendparty.getPlayer3().getSpirit()
							* friendparty.getPlayer3().getQuick() * itemst)) / 2560 * 0.95;
				} else if (battle.getPlayer3() == 2) {
					distans.set(2, 2);

					gard *= (2560 - (friendparty.getPlayer3().getMuscle() * friendparty.getPlayer3().getSpirit()
							* friendparty.getPlayer3().getQuick() * itemst)) / 2560 * 0.9;
				}
			} else if (friendparty.getPlayer3().getJob() == 10) {
				if (battle.getPlayer3() == 0) {
					distans.set(2, 7);
				} else if (battle.getPlayer3() == 1 && friendparty.getPlayer3().getMp() >= 5) {
					distans.set(2, 5);
					distans.set(10, 1);

					gard *= (2560 - (friendparty.getPlayer3().getTechnique() * friendparty.getPlayer3().getMagic()
							* friendparty.getPlayer3().getKnowledge() * itemst)) / 2560 * 0.96;
				} else if (battle.getPlayer3() == 2 && friendparty.getPlayer3().getMp() >= 5) {
					distans.set(2, 5);
					distans.set(10, 1);

					gard *= (2560 - (friendparty.getPlayer3().getTechnique() * friendparty.getPlayer3().getMagic()
							* friendparty.getPlayer3().getKnowledge() * itemst)) / 2560 * 0.9;
				} else {
					distans.set(2, 5);
					message.add(friendparty.getPlayer3().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer3().getJob() == 11) {
				distans.set(2, 7);
				if (battle.getPlayer3() == 0) {

				} else {
					if (friendparty.getPlayer3().getMp() < 3) {
						message.add(friendparty.getPlayer3().getName() + "がMP不足により攻撃に失敗しました。");
					}
				}
			} else if (friendparty.getPlayer3().getJob() == 12) {
				distans.set(2, 6);
				attack *= (friendparty.getPlayer3().getJob_sworder() + friendparty.getPlayer3().getJob_spearer()
						+ friendparty.getPlayer3().getJob_archer() + friendparty.getPlayer3().getJob_katanaer()
						+ friendparty.getPlayer3().getJob_axer() + friendparty.getPlayer3().getJob_melleter()
						+ friendparty.getPlayer3().getJob_cluber()
						+ friendparty.getPlayer3().getJob_black_magician())
						* friendparty.getPlayer3().getJob_commander() / 100;
				if (friendparty.getPlayer3().getJob_shielder() + friendparty.getPlayer3().getJob_magician() < 200) {
					gard *= (200 - friendparty.getPlayer3().getJob_shielder()
							- friendparty.getPlayer3().getJob_magician()) / 2
							/ (friendparty.getPlayer3().getJob_commander() + 1);
				} else {
					gard = 0;
				}
				suport *= friendparty.getPlayer3().getJob_commander()
						* friendparty.getPlayer3().getJob_white_magician()
						/ 100;
			}
		}
		if (friendparty.getPlayer4() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer4().getId(), main_battle.Jobitem(friendparty.getPlayer4().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			if (friendparty.getPlayer4().getJob() == 1) {
				if (battle.getPlayer4() == 0) {
					distans.set(3, 7);
				} else if (battle.getPlayer4() == 1) {
					distans.set(3, 4);
				} else if (battle.getPlayer4() == 2) {
					distans.set(3, 4);
				}
			} else if (friendparty.getPlayer4().getJob() == 2) {
				if (battle.getPlayer4() == 0) {
					distans.set(3, 7);
				} else if (battle.getPlayer4() == 1) {
					distans.set(3, 3);
				} else if (battle.getPlayer4() == 2) {
					distans.set(3, 3);
				}

			} else if (friendparty.getPlayer4().getJob() == 3) {
				if (battle.getPlayer4() == 0) {
					distans.set(3, 7);
				} else if (battle.getPlayer4() == 1 && friendparty.getPlayer4().getMp() >= 5) {
					distans.set(3, 5);
				} else if (battle.getPlayer4() == 2 && friendparty.getPlayer4().getMp() >= 5) {
					distans.set(3, 5);
				} else {
					distans.set(3, 5);
					message.add(friendparty.getPlayer4().getName() + "の攻撃がMP不足により失敗しました。");
				}
			} else if (friendparty.getPlayer4().getJob() == 4) {
				if (battle.getPlayer4() == 0) {
					distans.set(3, 7);
				} else if (battle.getPlayer4() == 1) {
					distans.set(3, 4);
				} else if (battle.getPlayer4() == 2) {
					distans.set(3, 4);
				}
			} else if (friendparty.getPlayer4().getJob() == 5) {
				if (battle.getPlayer4() == 0) {
					distans.set(3, 7);
				} else if (battle.getPlayer4() == 1) {
					distans.set(3, 4);
				} else if (battle.getPlayer4() == 2) {
					distans.set(3, 4);
				}
			} else if (friendparty.getPlayer4().getJob() == 6) {
				if (battle.getPlayer4() == 0) {
					distans.set(3, 7);
				} else if (battle.getPlayer4() == 1) {
					distans.set(3, 3);
				} else if (battle.getPlayer4() == 2) {
					distans.set(3, 3);
				}
			} else if (friendparty.getPlayer4().getJob() == 7) {
				if (battle.getPlayer4() == 0) {
					distans.set(3, 7);
				} else if (battle.getPlayer4() == 1) {
					distans.set(3, 4);
				} else if (battle.getPlayer4() == 2) {
					distans.set(3, 4);
				}
			} else if (friendparty.getPlayer4().getJob() == 8) {
				if (battle.getPlayer4() == 0) {
					distans.set(3, 7);
				} else if (battle.getPlayer4() == 1 && friendparty.getPlayer4().getMp() >= 5) {
					distans.set(3, 5);
				} else if (battle.getPlayer4() == 2 && friendparty.getPlayer4().getMp() >= 5) {
					distans.set(3, 5);
				} else {
					distans.set(3, 5);
					message.add(friendparty.getPlayer4().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer4().getJob() == 9) {
				if (battle.getPlayer4() == 0) {
					distans.set(3, 7);
				} else if (battle.getPlayer4() == 1) {
					distans.set(3, 2);

					gard *= (2560 - (friendparty.getPlayer4().getMuscle() * friendparty.getPlayer4().getSpirit()
							* friendparty.getPlayer4().getQuick() * itemst)) / 2560 * 0.95;
				} else if (battle.getPlayer4() == 2) {
					distans.set(3, 2);

					gard *= (2560 - (friendparty.getPlayer4().getMuscle() * friendparty.getPlayer4().getSpirit()
							* friendparty.getPlayer4().getQuick() * itemst)) / 2560 * 0.9;
				}
			} else if (friendparty.getPlayer4().getJob() == 10) {
				if (battle.getPlayer4() == 0) {
					distans.set(3, 7);
				} else if (battle.getPlayer4() == 1 && friendparty.getPlayer4().getMp() >= 5) {
					distans.set(3, 5);
					distans.set(11, 1);

					gard *= (2560 - (friendparty.getPlayer4().getTechnique() * friendparty.getPlayer4().getMagic()
							* friendparty.getPlayer4().getKnowledge() * itemst)) / 2560 * 0.96;
				} else if (battle.getPlayer4() == 2 && friendparty.getPlayer4().getMp() >= 5) {
					distans.set(3, 5);
					distans.set(11, 1);

					gard *= (2560 - (friendparty.getPlayer4().getTechnique() * friendparty.getPlayer4().getMagic()
							* friendparty.getPlayer4().getKnowledge() * itemst)) / 2560 * 0.9;
				} else {
					distans.set(3, 5);
					message.add(friendparty.getPlayer4().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer4().getJob() == 11) {
				distans.set(3, 7);
				if (battle.getPlayer4() == 0) {

				} else {
					if (friendparty.getPlayer4().getMp() < 3) {
						message.add(friendparty.getPlayer4().getName() + "がMP不足により攻撃に失敗しました。");
					}
				}
			} else if (friendparty.getPlayer4().getJob() == 12) {
				distans.set(3, 6);
				attack *= (friendparty.getPlayer4().getJob_sworder() + friendparty.getPlayer4().getJob_spearer()
						+ friendparty.getPlayer4().getJob_archer() + friendparty.getPlayer4().getJob_katanaer()
						+ friendparty.getPlayer4().getJob_axer() + friendparty.getPlayer4().getJob_melleter()
						+ friendparty.getPlayer4().getJob_cluber()
						+ friendparty.getPlayer4().getJob_black_magician())
						* friendparty.getPlayer4().getJob_commander() / 100;
				if (friendparty.getPlayer4().getJob_shielder() + friendparty.getPlayer4().getJob_magician() < 200) {
					gard *= (200 - friendparty.getPlayer4().getJob_shielder()
							- friendparty.getPlayer4().getJob_magician()) / 2
							/ (friendparty.getPlayer4().getJob_commander() + 1);
				} else {
					gard = 0;
				}
				suport *= friendparty.getPlayer4().getJob_commander()
						* friendparty.getPlayer4().getJob_white_magician()
						/ 100;
			}
		}

		if (friendparty.getPlayer5() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer5().getId(), main_battle.Jobitem(friendparty.getPlayer5().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			if (friendparty.getPlayer5().getJob() == 1) {
				if (battle.getPlayer5() == 0) {
					distans.set(4, 7);
				} else if (battle.getPlayer5() == 1) {
					distans.set(4, 4);
				} else if (battle.getPlayer5() == 2) {
					distans.set(4, 4);
				}
			} else if (friendparty.getPlayer5().getJob() == 2) {
				if (battle.getPlayer5() == 0) {
					distans.set(4, 7);
				} else if (battle.getPlayer5() == 1) {
					distans.set(4, 3);
				} else if (battle.getPlayer5() == 2) {
					distans.set(4, 3);
				}

			} else if (friendparty.getPlayer5().getJob() == 3) {
				if (battle.getPlayer5() == 0) {
					distans.set(4, 7);
				} else if (battle.getPlayer5() == 1 && friendparty.getPlayer5().getMp() >= 5) {
					distans.set(4, 5);
				} else if (battle.getPlayer5() == 2 && friendparty.getPlayer5().getMp() >= 5) {
					distans.set(4, 5);
				} else {
					distans.set(4, 5);
					message.add(friendparty.getPlayer5().getName() + "の攻撃がMP不足により失敗しました。");
				}
			} else if (friendparty.getPlayer5().getJob() == 4) {
				if (battle.getPlayer5() == 0) {
					distans.set(4, 7);
				} else if (battle.getPlayer5() == 1) {
					distans.set(4, 4);
				} else if (battle.getPlayer5() == 2) {
					distans.set(4, 4);
				}
			} else if (friendparty.getPlayer5().getJob() == 5) {
				if (battle.getPlayer5() == 0) {
					distans.set(4, 7);
				} else if (battle.getPlayer5() == 1) {
					distans.set(4, 4);
				} else if (battle.getPlayer5() == 2) {
					distans.set(4, 4);
				}
			} else if (friendparty.getPlayer5().getJob() == 6) {
				if (battle.getPlayer5() == 0) {
					distans.set(4, 7);
				} else if (battle.getPlayer5() == 1) {
					distans.set(4, 3);
				} else if (battle.getPlayer5() == 2) {
					distans.set(4, 3);
				}
			} else if (friendparty.getPlayer5().getJob() == 7) {
				if (battle.getPlayer5() == 0) {
					distans.set(4, 7);
				} else if (battle.getPlayer5() == 1) {
					distans.set(4, 4);
				} else if (battle.getPlayer5() == 2) {
					distans.set(4, 4);
				}
			} else if (friendparty.getPlayer5().getJob() == 8) {
				if (battle.getPlayer5() == 0) {
					distans.set(4, 7);
				} else if (battle.getPlayer5() == 1 && friendparty.getPlayer5().getMp() >= 5) {
					distans.set(4, 5);
				} else if (battle.getPlayer5() == 2 && friendparty.getPlayer5().getMp() >= 5) {
					distans.set(4, 5);
				} else {
					distans.set(4, 5);
					message.add(friendparty.getPlayer5().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer5().getJob() == 9) {
				if (battle.getPlayer5() == 0) {
					distans.set(4, 7);
				} else if (battle.getPlayer5() == 1) {
					distans.set(4, 2);

					gard *= (2560 - (friendparty.getPlayer5().getMuscle() * friendparty.getPlayer5().getSpirit()
							* friendparty.getPlayer5().getQuick() * itemst)) / 2560 * 0.95;
				} else if (battle.getPlayer5() == 2) {
					distans.set(4, 2);

					gard *= (2560 - (friendparty.getPlayer5().getMuscle() * friendparty.getPlayer5().getSpirit()
							* friendparty.getPlayer5().getQuick() * itemst)) / 2560 * 0.9;
				}
			} else if (friendparty.getPlayer5().getJob() == 10) {
				if (battle.getPlayer5() == 0) {
					distans.set(4, 7);
				} else if (battle.getPlayer5() == 1 && friendparty.getPlayer5().getMp() >= 5) {
					distans.set(4, 5);
					distans.set(12, 1);

					gard *= (2560 - (friendparty.getPlayer5().getTechnique() * friendparty.getPlayer5().getMagic()
							* friendparty.getPlayer5().getKnowledge() * itemst)) / 2560 * 0.96;
				} else if (battle.getPlayer5() == 2 && friendparty.getPlayer5().getMp() >= 5) {
					distans.set(4, 5);
					distans.set(12, 1);

					gard *= (2560 - (friendparty.getPlayer5().getTechnique() * friendparty.getPlayer5().getMagic()
							* friendparty.getPlayer5().getKnowledge() * itemst)) / 2560 * 0.9;
				} else {
					distans.set(4, 5);
					message.add(friendparty.getPlayer5().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer5().getJob() == 11) {
				distans.set(4, 7);
				if (battle.getPlayer5() == 0) {

				} else {
					if (friendparty.getPlayer5().getMp() < 3) {
						message.add(friendparty.getPlayer5().getName() + "がMP不足により攻撃に失敗しました。");
					}
				}
			} else if (friendparty.getPlayer5().getJob() == 12) {
				distans.set(4, 6);
				attack *= (friendparty.getPlayer5().getJob_sworder() + friendparty.getPlayer5().getJob_spearer()
						+ friendparty.getPlayer5().getJob_archer() + friendparty.getPlayer5().getJob_katanaer()
						+ friendparty.getPlayer5().getJob_axer() + friendparty.getPlayer5().getJob_melleter()
						+ friendparty.getPlayer5().getJob_cluber()
						+ friendparty.getPlayer5().getJob_black_magician())
						* friendparty.getPlayer5().getJob_commander() / 100;
				if (friendparty.getPlayer5().getJob_shielder() + friendparty.getPlayer5().getJob_magician() < 200) {
					gard *= (200 - friendparty.getPlayer5().getJob_shielder()
							- friendparty.getPlayer5().getJob_magician()) / 2
							/ (friendparty.getPlayer5().getJob_commander() + 1);
				} else {
					gard = 0;
				}
				suport *= friendparty.getPlayer5().getJob_commander()
						* friendparty.getPlayer5().getJob_white_magician()
						/ 100;
			}
		}

		if (friendparty.getPlayer6() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer6().getId(), main_battle.Jobitem(friendparty.getPlayer6().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			if (friendparty.getPlayer6().getJob() == 1) {
				if (battle.getPlayer6() == 0) {
					distans.set(5, 7);
				} else if (battle.getPlayer6() == 1) {
					distans.set(5, 4);
				} else if (battle.getPlayer6() == 2) {
					distans.set(5, 4);
				}
			} else if (friendparty.getPlayer6().getJob() == 2) {
				if (battle.getPlayer6() == 0) {
					distans.set(5, 7);
				} else if (battle.getPlayer6() == 1) {
					distans.set(5, 3);
				} else if (battle.getPlayer6() == 2) {
					distans.set(5, 3);
				}

			} else if (friendparty.getPlayer6().getJob() == 3) {
				if (battle.getPlayer6() == 0) {
					distans.set(5, 7);
				} else if (battle.getPlayer6() == 1 && friendparty.getPlayer6().getMp() >= 5) {
					distans.set(5, 5);
				} else if (battle.getPlayer6() == 2 && friendparty.getPlayer6().getMp() >= 5) {
					distans.set(5, 5);
				} else {
					distans.set(5, 5);
					message.add(friendparty.getPlayer6().getName() + "の攻撃がMP不足により失敗しました。");
				}
			} else if (friendparty.getPlayer6().getJob() == 4) {
				if (battle.getPlayer6() == 0) {
					distans.set(5, 7);
				} else if (battle.getPlayer6() == 1) {
					distans.set(5, 4);
				} else if (battle.getPlayer6() == 2) {
					distans.set(5, 4);
				}
			} else if (friendparty.getPlayer6().getJob() == 5) {
				if (battle.getPlayer6() == 0) {
					distans.set(5, 7);
				} else if (battle.getPlayer6() == 1) {
					distans.set(5, 4);
				} else if (battle.getPlayer6() == 2) {
					distans.set(5, 4);
				}
			} else if (friendparty.getPlayer6().getJob() == 6) {
				if (battle.getPlayer6() == 0) {
					distans.set(5, 7);
				} else if (battle.getPlayer6() == 1) {
					distans.set(5, 3);
				} else if (battle.getPlayer6() == 2) {
					distans.set(5, 3);
				}
			} else if (friendparty.getPlayer6().getJob() == 7) {
				if (battle.getPlayer6() == 0) {
					distans.set(5, 7);
				} else if (battle.getPlayer6() == 1) {
					distans.set(5, 4);
				} else if (battle.getPlayer6() == 2) {
					distans.set(5, 4);
				}
			} else if (friendparty.getPlayer6().getJob() == 8) {
				if (battle.getPlayer6() == 0) {
					distans.set(5, 7);
				} else if (battle.getPlayer6() == 1 && friendparty.getPlayer6().getMp() >= 5) {
					distans.set(5, 5);
				} else if (battle.getPlayer6() == 2 && friendparty.getPlayer6().getMp() >= 5) {
					distans.set(5, 5);
				} else {
					distans.set(5, 5);
					message.add(friendparty.getPlayer6().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer6().getJob() == 9) {
				if (battle.getPlayer6() == 0) {
					distans.set(5, 7);
				} else if (battle.getPlayer6() == 1) {
					distans.set(5, 2);

					gard *= (2560 - (friendparty.getPlayer6().getMuscle() * friendparty.getPlayer6().getSpirit()
							* friendparty.getPlayer6().getQuick() * itemst)) / 2560 * 0.95;
				} else if (battle.getPlayer6() == 2) {
					distans.set(5, 2);

					gard *= (2560 - (friendparty.getPlayer6().getMuscle() * friendparty.getPlayer6().getSpirit()
							* friendparty.getPlayer6().getQuick() * itemst)) / 2560 * 0.9;
				}
			} else if (friendparty.getPlayer6().getJob() == 10) {
				if (battle.getPlayer6() == 0) {
					distans.set(5, 7);
				} else if (battle.getPlayer6() == 1 && friendparty.getPlayer6().getMp() >= 5) {
					distans.set(5, 5);
					distans.set(13, 1);

					gard *= (2560 - (friendparty.getPlayer6().getTechnique() * friendparty.getPlayer6().getMagic()
							* friendparty.getPlayer6().getKnowledge() * itemst)) / 2560 * 0.96;
				} else if (battle.getPlayer6() == 2 && friendparty.getPlayer6().getMp() >= 5) {
					distans.set(5, 5);
					distans.set(13, 1);

					gard *= (2560 - (friendparty.getPlayer6().getTechnique() * friendparty.getPlayer6().getMagic()
							* friendparty.getPlayer6().getKnowledge() * itemst)) / 2560 * 0.9;
				} else {
					distans.set(5, 5);
					message.add(friendparty.getPlayer6().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer6().getJob() == 11) {
				distans.set(5, 7);
				if (battle.getPlayer6() == 0) {

				} else {
					if (friendparty.getPlayer6().getMp() < 3) {
						message.add(friendparty.getPlayer6().getName() + "がMP不足により攻撃に失敗しました。");
					}
				}
			} else if (friendparty.getPlayer6().getJob() == 12) {
				distans.set(5, 6);
				attack *= (friendparty.getPlayer6().getJob_sworder() + friendparty.getPlayer6().getJob_spearer()
						+ friendparty.getPlayer6().getJob_archer() + friendparty.getPlayer6().getJob_katanaer()
						+ friendparty.getPlayer6().getJob_axer() + friendparty.getPlayer6().getJob_melleter()
						+ friendparty.getPlayer6().getJob_cluber()
						+ friendparty.getPlayer6().getJob_black_magician())
						* friendparty.getPlayer6().getJob_commander() / 100;
				if (friendparty.getPlayer6().getJob_shielder() + friendparty.getPlayer6().getJob_magician() < 200) {
					gard *= (200 - friendparty.getPlayer6().getJob_shielder()
							- friendparty.getPlayer6().getJob_magician()) / 2
							/ (friendparty.getPlayer6().getJob_commander() + 1);
				} else {
					gard = 0;
				}
				suport *= friendparty.getPlayer6().getJob_commander()
						* friendparty.getPlayer6().getJob_white_magician()
						/ 100;
			}
		}

		if (friendparty.getPlayer7() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer7().getId(), main_battle.Jobitem(friendparty.getPlayer7().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			if (friendparty.getPlayer7().getJob() == 1) {
				if (battle.getPlayer7() == 0) {
					distans.set(6, 7);
				} else if (battle.getPlayer7() == 1) {
					distans.set(6, 4);
				} else if (battle.getPlayer7() == 2) {
					distans.set(6, 4);
				}
			} else if (friendparty.getPlayer7().getJob() == 2) {
				if (battle.getPlayer7() == 0) {
					distans.set(6, 7);
				} else if (battle.getPlayer7() == 1) {
					distans.set(6, 3);
				} else if (battle.getPlayer7() == 2) {
					distans.set(6, 3);
				}

			} else if (friendparty.getPlayer7().getJob() == 3) {
				if (battle.getPlayer7() == 0) {
					distans.set(6, 7);
				} else if (battle.getPlayer7() == 1 && friendparty.getPlayer7().getMp() >= 5) {
					distans.set(6, 5);
				} else if (battle.getPlayer7() == 2 && friendparty.getPlayer7().getMp() >= 5) {
					distans.set(6, 5);
				} else {
					distans.set(6, 5);
					message.add(friendparty.getPlayer7().getName() + "の攻撃がMP不足により失敗しました。");
				}
			} else if (friendparty.getPlayer7().getJob() == 4) {
				if (battle.getPlayer7() == 0) {
					distans.set(6, 7);
				} else if (battle.getPlayer7() == 1) {
					distans.set(6, 4);
				} else if (battle.getPlayer7() == 2) {
					distans.set(6, 4);
				}
			} else if (friendparty.getPlayer7().getJob() == 5) {
				if (battle.getPlayer7() == 0) {
					distans.set(6, 7);
				} else if (battle.getPlayer7() == 1) {
					distans.set(6, 4);
				} else if (battle.getPlayer7() == 2) {
					distans.set(6, 4);
				}
			} else if (friendparty.getPlayer7().getJob() == 6) {
				if (battle.getPlayer7() == 0) {
					distans.set(6, 7);
				} else if (battle.getPlayer7() == 1) {
					distans.set(6, 3);
				} else if (battle.getPlayer7() == 2) {
					distans.set(6, 3);
				}
			} else if (friendparty.getPlayer7().getJob() == 7) {
				if (battle.getPlayer7() == 0) {
					distans.set(6, 7);
				} else if (battle.getPlayer7() == 1) {
					distans.set(6, 4);
				} else if (battle.getPlayer7() == 2) {
					distans.set(6, 4);
				}
			} else if (friendparty.getPlayer7().getJob() == 8) {
				if (battle.getPlayer7() == 0) {
					distans.set(6, 7);
				} else if (battle.getPlayer7() == 1 && friendparty.getPlayer7().getMp() >= 5) {
					distans.set(6, 5);
				} else if (battle.getPlayer7() == 2 && friendparty.getPlayer7().getMp() >= 5) {
					distans.set(6, 5);
				} else {
					distans.set(6, 5);
					message.add(friendparty.getPlayer7().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer7().getJob() == 9) {
				if (battle.getPlayer7() == 0) {
					distans.set(6, 7);
				} else if (battle.getPlayer7() == 1) {
					distans.set(6, 2);

					gard *= (2560 - (friendparty.getPlayer7().getMuscle() * friendparty.getPlayer7().getSpirit()
							* friendparty.getPlayer7().getQuick() * itemst)) / 2560 * 0.95;
				} else if (battle.getPlayer7() == 2) {
					distans.set(6, 2);

					gard *= (2560 - (friendparty.getPlayer7().getMuscle() * friendparty.getPlayer7().getSpirit()
							* friendparty.getPlayer7().getQuick() * itemst)) / 2560 * 0.9;
				}
			} else if (friendparty.getPlayer7().getJob() == 10) {
				if (battle.getPlayer7() == 0) {
					distans.set(6, 7);
				} else if (battle.getPlayer7() == 1 && friendparty.getPlayer7().getMp() >= 5) {
					distans.set(6, 5);
					distans.set(14, 1);

					gard *= (2560 - (friendparty.getPlayer7().getTechnique() * friendparty.getPlayer7().getMagic()
							* friendparty.getPlayer7().getKnowledge() * itemst)) / 2560 * 0.96;
				} else if (battle.getPlayer7() == 2 && friendparty.getPlayer7().getMp() >= 5) {
					distans.set(6, 5);
					distans.set(14, 1);

					gard *= (2560 - (friendparty.getPlayer7().getTechnique() * friendparty.getPlayer7().getMagic()
							* friendparty.getPlayer7().getKnowledge() * itemst)) / 2560 * 0.9;
				} else {
					distans.set(6, 5);
					message.add(friendparty.getPlayer7().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer7().getJob() == 11) {
				distans.set(6, 7);
				if (battle.getPlayer7() == 0) {

				} else {
					if (friendparty.getPlayer7().getMp() < 3) {
						message.add(friendparty.getPlayer7().getName() + "がMP不足により攻撃に失敗しました。");
					}
				}
			} else if (friendparty.getPlayer7().getJob() == 12) {
				distans.set(6, 6);
				attack *= (friendparty.getPlayer7().getJob_sworder() + friendparty.getPlayer7().getJob_spearer()
						+ friendparty.getPlayer7().getJob_archer() + friendparty.getPlayer7().getJob_katanaer()
						+ friendparty.getPlayer7().getJob_axer() + friendparty.getPlayer7().getJob_melleter()
						+ friendparty.getPlayer7().getJob_cluber()
						+ friendparty.getPlayer7().getJob_black_magician())
						* friendparty.getPlayer7().getJob_commander() / 100;
				if (friendparty.getPlayer7().getJob_shielder() + friendparty.getPlayer7().getJob_magician() < 200) {
					gard *= (200 - friendparty.getPlayer7().getJob_shielder()
							- friendparty.getPlayer7().getJob_magician()) / 2
							/ (friendparty.getPlayer7().getJob_commander() + 1);
				} else {
					gard = 0;
				}
				suport *= friendparty.getPlayer7().getJob_commander()
						* friendparty.getPlayer7().getJob_white_magician()
						/ 100;
			}
		}

		if (friendparty.getPlayer8() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer8().getId(), main_battle.Jobitem(friendparty.getPlayer8().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			if (friendparty.getPlayer8().getJob() == 1) {
				if (battle.getPlayer8() == 0) {
					distans.set(7, 7);
				} else if (battle.getPlayer8() == 1) {
					distans.set(7, 4);
				} else if (battle.getPlayer8() == 2) {
					distans.set(7, 4);
				}
			} else if (friendparty.getPlayer8().getJob() == 2) {
				if (battle.getPlayer8() == 0) {
					distans.set(7, 7);
				} else if (battle.getPlayer8() == 1) {
					distans.set(7, 3);
				} else if (battle.getPlayer8() == 2) {
					distans.set(7, 3);
				}

			} else if (friendparty.getPlayer8().getJob() == 3) {
				if (battle.getPlayer8() == 0) {
					distans.set(7, 7);
				} else if (battle.getPlayer8() == 1 && friendparty.getPlayer8().getMp() >= 5) {
					distans.set(7, 5);
				} else if (battle.getPlayer8() == 2 && friendparty.getPlayer8().getMp() >= 5) {
					distans.set(7, 5);
				} else {
					distans.set(7, 5);
					message.add(friendparty.getPlayer8().getName() + "の攻撃がMP不足により失敗しました。");
				}
			} else if (friendparty.getPlayer8().getJob() == 4) {
				if (battle.getPlayer8() == 0) {
					distans.set(7, 7);
				} else if (battle.getPlayer8() == 1) {
					distans.set(7, 4);
				} else if (battle.getPlayer8() == 2) {
					distans.set(7, 4);
				}
			} else if (friendparty.getPlayer8().getJob() == 5) {
				if (battle.getPlayer8() == 0) {
					distans.set(7, 7);
				} else if (battle.getPlayer8() == 1) {
					distans.set(7, 4);
				} else if (battle.getPlayer8() == 2) {
					distans.set(7, 4);
				}
			} else if (friendparty.getPlayer8().getJob() == 6) {
				if (battle.getPlayer8() == 0) {
					distans.set(7, 7);
				} else if (battle.getPlayer8() == 1) {
					distans.set(7, 3);
				} else if (battle.getPlayer8() == 2) {
					distans.set(7, 3);
				}
			} else if (friendparty.getPlayer8().getJob() == 7) {
				if (battle.getPlayer8() == 0) {
					distans.set(7, 7);
				} else if (battle.getPlayer8() == 1) {
					distans.set(7, 4);
				} else if (battle.getPlayer8() == 2) {
					distans.set(7, 4);
				}
			} else if (friendparty.getPlayer8().getJob() == 8) {
				if (battle.getPlayer8() == 0) {
					distans.set(7, 7);
				} else if (battle.getPlayer8() == 1 && friendparty.getPlayer8().getMp() >= 5) {
					distans.set(7, 5);
				} else if (battle.getPlayer8() == 2 && friendparty.getPlayer8().getMp() >= 5) {
					distans.set(7, 5);
				} else {
					distans.set(7, 5);
					message.add(friendparty.getPlayer8().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer8().getJob() == 9) {
				if (battle.getPlayer8() == 0) {
					distans.set(7, 7);
				} else if (battle.getPlayer8() == 1) {
					distans.set(7, 2);

					gard *= (2560 - (friendparty.getPlayer8().getMuscle() * friendparty.getPlayer8().getSpirit()
							* friendparty.getPlayer8().getQuick() * itemst)) / 2560 * 0.95;
				} else if (battle.getPlayer8() == 2) {
					distans.set(7, 2);

					gard *= (2560 - (friendparty.getPlayer8().getMuscle() * friendparty.getPlayer8().getSpirit()
							* friendparty.getPlayer8().getQuick() * itemst)) / 2560 * 0.9;
				}
			} else if (friendparty.getPlayer8().getJob() == 10) {
				if (battle.getPlayer8() == 0) {
					distans.set(7, 7);
				} else if (battle.getPlayer8() == 1 && friendparty.getPlayer8().getMp() >= 5) {
					distans.set(7, 5);
					distans.set(15, 1);

					gard *= (2560 - (friendparty.getPlayer8().getTechnique() * friendparty.getPlayer8().getMagic()
							* friendparty.getPlayer8().getKnowledge() * itemst)) / 2560 * 0.96;
				} else if (battle.getPlayer8() == 2 && friendparty.getPlayer8().getMp() >= 5) {
					distans.set(7, 5);
					distans.set(15, 1);

					gard *= (2560 - (friendparty.getPlayer8().getTechnique() * friendparty.getPlayer8().getMagic()
							* friendparty.getPlayer8().getKnowledge() * itemst)) / 2560 * 0.9;
				} else {
					distans.set(0, 5);
					message.add(friendparty.getPlayer8().getName() + "がMP不足により攻撃に失敗しました。");
				}
			} else if (friendparty.getPlayer8().getJob() == 11) {
				distans.set(7, 7);
				if (battle.getPlayer8() == 0) {

				} else {
					if (friendparty.getPlayer8().getMp() < 3) {
						message.add(friendparty.getPlayer8().getName() + "がMP不足により攻撃に失敗しました。");
					}
				}
			} else if (friendparty.getPlayer8().getJob() == 12) {
				distans.set(7, 6);
				attack *= (friendparty.getPlayer8().getJob_sworder() + friendparty.getPlayer8().getJob_spearer()
						+ friendparty.getPlayer8().getJob_archer() + friendparty.getPlayer8().getJob_katanaer()
						+ friendparty.getPlayer8().getJob_axer() + friendparty.getPlayer8().getJob_melleter()
						+ friendparty.getPlayer8().getJob_cluber()
						+ friendparty.getPlayer8().getJob_black_magician())
						* friendparty.getPlayer8().getJob_commander() / 100;
				if (friendparty.getPlayer8().getJob_shielder() + friendparty.getPlayer8().getJob_magician() < 200) {
					gard *= (200 - friendparty.getPlayer8().getJob_shielder()
							- friendparty.getPlayer8().getJob_magician()) / 2
							/ (friendparty.getPlayer8().getJob_commander() + 1);
				} else {
					gard = 0;
				}
				suport *= friendparty.getPlayer8().getJob_commander()
						* friendparty.getPlayer8().getJob_white_magician()
						/ 100;
			}
		}
		System.out.println(user.getName() + "戦闘長さ計測");
		//処理
		ArrayList<Integer> distanscount = new ArrayList<Integer>();
		for (int i = 0; i < 7; i++) {
			distanscount.add(0);
		}
		for (int i = 0; i < 7; i++) {
			int count = 0;
			for (int j : distans) {
				if (j - 1 == i) {
					count++;
				}

			}
			distanscount.set(i, count);
		}
		ArrayList<Double> distanstrong = new ArrayList<Double>();
		for (int i = 0; i < 8; i++) {
			distanstrong.add(1.0);
		}
		if (distanscount.get(0) > 0) {
			distanstrong.set(1, 5.0 / distanscount.get(0));
			if (distanscount.get(1) > 0) {
				distanstrong.set(2, 3.0 / distanscount.get(1));
				if (distanscount.get(2) > 0) {
					distanstrong.set(3, 2.0 / distanscount.get(2));//////////////////////////
				} else {
					if (distanscount.get(3) > 0) {
						distanstrong.set(4, 2.0 / distanscount.get(3));////////////////////////////////////
					} else {
						if (distanscount.get(4) > 0) {
							distanstrong.set(5, 2.0 / distanscount.get(4));/////////////////////////
						} else {
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 2.0 / distanscount.get(5));/////////////////
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							}
						}
					}
				}
			} else {
				if (distanscount.get(2) > 0) {
					distanstrong.set(3, 3.0 / distanscount.get(2));
					if (distanscount.get(3) > 0) {
						distanstrong.set(4, 2.0 / distanscount.get(3));///////////////////////////////////////
					} else {
						if (distanscount.get(4) > 0) {
							distanstrong.set(5, 2.0 / distanscount.get(4));///////////////////////////
						} else {
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 2.0 / distanscount.get(5));
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							}
						}
					}
				} else {
					if (distanscount.get(3) > 0) {
						distanstrong.set(4, 3.0 / distanscount.get(3));
						if (distanscount.get(4) > 0) {
							distanstrong.set(5, 2.0 / distanscount.get(4));//////////////////////////
						} else {
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 2.0 / distanscount.get(5));
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							}
						}
					} else {
						if (distanscount.get(4) > 0) {
							distanstrong.set(5, 3.0 / distanscount.get(4));
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 2.0 / distanscount.get(5));
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							}
						} else {
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 3.0 / distanscount.get(5));
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 3.0 / distanscount.get(6));
								}
							}
						}
					}
				}
			}
		} else {
			if (distanscount.get(1) > 0) {
				distanstrong.set(2, 5.0 / distanscount.get(1));
				if (distanscount.get(2) > 0) {
					distanstrong.set(3, 3.0 / distanscount.get(2));
					if (distanscount.get(3) > 0) {
						distanstrong.set(4, 2.0 / distanscount.get(3));/////////////////////////////////////////////
					} else {
						if (distanscount.get(4) > 0) {
							distanstrong.set(5, 2.0 / distanscount.get(4));////////////////////////////
						} else {
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 2.0 / distanscount.get(5));
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							}
						}
					}
				} else {
					if (distanscount.get(3) > 0) {
						distanstrong.set(4, 3.0 / distanscount.get(3));
						if (distanscount.get(4) > 0) {
							distanstrong.set(5, 2.0 / distanscount.get(4));//////////////////////////////
						} else {
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 2.0 / distanscount.get(5));
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							}
						}
					} else {
						if (distanscount.get(4) > 0) {
							distanstrong.set(5, 3.0 / distanscount.get(4));
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 2.0 / distanscount.get(5));
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							}
						} else {
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 3.0 / distanscount.get(5));
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 3.0 / distanscount.get(6));
								}
							}
						}
					}
				}
			} else {
				if (distanscount.get(2) > 0) {
					distanstrong.set(3, 5.0 / distanscount.get(2));
					if (distanscount.get(3) > 0) {
						distanstrong.set(4, 3.0 / distanscount.get(3));
						if (distanscount.get(4) > 0) {
							distanstrong.set(5, 2.0 / distanscount.get(4));////////////////////////////
						} else {
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 2.0 / distanscount.get(5));
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							}
						}
					} else {
						if (distanscount.get(4) > 0) {
							distanstrong.set(5, 3.0 / distanscount.get(4));
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 2.0 / distanscount.get(5));
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							}
						} else {
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 3.0 / distanscount.get(5));
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 3.0 / distanscount.get(6));
								}
							}
						}
					}
				} else {
					if (distanscount.get(3) > 0) {
						distanstrong.set(4, 5.0 / distanscount.get(3));
						if (distanscount.get(4) > 0) {
							distanstrong.set(5, 3.0 / distanscount.get(4));
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 2.0 / distanscount.get(5));
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							}
						} else {
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 3.0 / distanscount.get(5));
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 3.0 / distanscount.get(6));
								}
							}
						}
					} else {
						if (distanscount.get(4) > 0) {
							distanstrong.set(5, 5.0 / distanscount.get(4));
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 3.0 / distanscount.get(5));
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 2.0 / distanscount.get(6));
								}
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 3.0 / distanscount.get(6));
								}
							}
						} else {
							if (distanscount.get(5) > 0) {
								distanstrong.set(6, 5.0 / distanscount.get(5));
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 3.0 / distanscount.get(6));
								}
							} else {
								if (distanscount.get(6) > 0) {
									distanstrong.set(7, 5.0 / distanscount.get(6));
								}
							}
						}
					}
				}
			}
		}

		x *= gard;
		ArrayList<Integer> monsterHp = new ArrayList<Integer>();
		monsterHp.add(battle.getH1());
		monsterHp.add(battle.getH2());
		monsterHp.add(battle.getH3());
		monsterHp.add(battle.getH4());
		monsterHp.add(battle.getH5());
		monsterHp.add(battle.getH6());
		monsterHp.add(battle.getH7());
		monsterHp.add(battle.getH8());
		//代入ゾーン
		System.out.println(user.getName() + "戦闘条件分岐処理");
		if (friendparty.getPlayer1() != null) {
			//message = main_battle.syori11(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
			//		monsterHp, suport, transformation);
			monsterHp = main_battle.syori12(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
					monsterHp, suport, transformation, playerRepository);
			playerRepository.save(friendparty.getPlayer1());
			main_battle.cpfp(friendparty.getPlayer1(), playerRepository);
			playerRepository.save(friendparty.getPlayer1());
		}
		if (friendparty.getPlayer2() != null) {
			//			message = main_battle.syori21(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
			//					monsterHp, suport, transformation);
			monsterHp = main_battle.syori22(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
					monsterHp, suport, transformation, playerRepository);
			playerRepository.save(friendparty.getPlayer2());
			main_battle.cpfp(friendparty.getPlayer2(), playerRepository);
			playerRepository.save(friendparty.getPlayer2());
		}
		if (friendparty.getPlayer3() != null) {
			//			message = main_battle.syori31(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
			//					monsterHp, suport, transformation);
			monsterHp = main_battle.syori32(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
					monsterHp, suport, transformation, playerRepository);
			playerRepository.save(friendparty.getPlayer3());
			main_battle.cpfp(friendparty.getPlayer3(), playerRepository);
			playerRepository.save(friendparty.getPlayer3());
		}
		if (friendparty.getPlayer4() != null) {
			//			message = main_battle.syori41(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
			//					monsterHp, suport, transformation);
			monsterHp = main_battle.syori42(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
					monsterHp, suport, transformation, playerRepository);
			playerRepository.save(friendparty.getPlayer4());
			main_battle.cpfp(friendparty.getPlayer4(), playerRepository);
			playerRepository.save(friendparty.getPlayer4());
		}
		if (friendparty.getPlayer5() != null) {
			//			message = main_battle.syori51(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
			//				monsterHp, suport, transformation);
			monsterHp = main_battle.syori52(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
					monsterHp, suport, transformation, playerRepository);
			playerRepository.save(friendparty.getPlayer5());
			main_battle.cpfp(friendparty.getPlayer5(), playerRepository);
			playerRepository.save(friendparty.getPlayer5());
		}
		if (friendparty.getPlayer6() != null) {
			//			message = main_battle.syori61(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
			//					monsterHp, suport, transformation);
			monsterHp = main_battle.syori62(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
					monsterHp, suport, transformation, playerRepository);
			playerRepository.save(friendparty.getPlayer6());
			main_battle.cpfp(friendparty.getPlayer6(), playerRepository);
			playerRepository.save(friendparty.getPlayer6());
		}
		if (friendparty.getPlayer7() != null) {
			//			message = main_battle.syori71(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
			//					monsterHp, suport, transformation);
			monsterHp = main_battle.syori72(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
					monsterHp, suport, transformation, playerRepository);
			playerRepository.save(friendparty.getPlayer7());
			main_battle.cpfp(friendparty.getPlayer7(), playerRepository);
			playerRepository.save(friendparty.getPlayer7());
		}
		if (friendparty.getPlayer8() != null) {
			//			message = main_battle.syori81(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
			//					monsterHp, suport, transformation);
			monsterHp = main_battle.syori82(friendparty, itemRepository, user, battle, distanstrong, x, message, attack,
					monsterHp, suport, transformation, playerRepository);
			playerRepository.save(friendparty.getPlayer8());
			main_battle.cpfp(friendparty.getPlayer8(), playerRepository);
			playerRepository.save(friendparty.getPlayer8());
		}
		System.out.println(user.getName() + "戦闘モンスター最終処理");
		//終始処理
		if (battle.getH1() > 0 && monsterHp.get(0) < 0) {
			message.add(enemyparty.getPlayer1().getName() + "は死亡した");
		}
		if (battle.getH2() > 0 && monsterHp.get(1) < 0) {
			message.add(enemyparty.getPlayer2().getName() + "は死亡した");
		}
		if (battle.getH3() > 0 && monsterHp.get(2) < 0) {
			message.add(enemyparty.getPlayer3().getName() + "は死亡した");
		}
		if (battle.getH4() > 0 && monsterHp.get(3) < 0) {
			message.add(enemyparty.getPlayer4().getName() + "は死亡した");
		}
		if (battle.getH5() > 0 && monsterHp.get(4) < 0) {
			message.add(enemyparty.getPlayer5().getName() + "は死亡した");
		}
		if (battle.getH6() > 0 && monsterHp.get(5) < 0) {
			message.add(enemyparty.getPlayer6().getName() + "は死亡した");
		}
		if (battle.getH7() > 0 && monsterHp.get(6) < 0) {
			message.add(enemyparty.getPlayer7().getName() + "は死亡した");
		}
		if (battle.getH8() > 0 && monsterHp.get(7) < 0) {
			message.add(enemyparty.getPlayer8().getName() + "は死亡した");
		}
		System.out.println(user.getName() + "戦闘プレイヤー最終処理");
		if (friendparty.getPlayer1() != null && friendparty.getPlayer1().getHp() <= 0) {
			message.add(friendparty.getPlayer1().getName() + "は死亡した。遺族に慰謝料を支払った");
			Player player = playerRepository.getReferenceById(friendparty.getPlayer1().getId());
			friendparty.setPlayer1(null);
			partyRepository.save(friendparty);
			user_info.setMoney(user_info.getMoney() - 10000);
			user_infoRepository.save(user_info);
			Item dropitem = itemRepository.findByPlayer_idAndItemkindNot(player.getId(), 10);
			Item dropsitem = itemRepository.findByPlayer_idAndItemkind(player.getId(), 10);
			if (dropitem != null) {
				itemRepository.delete(dropitem);
			}
			if (dropsitem != null) {
				itemRepository.delete(dropsitem);

			}
			playerRepository.delete(player);

		}
		if (friendparty.getPlayer2() != null && friendparty.getPlayer2().getHp() <= 0) {
			message.add(friendparty.getPlayer2().getName() + "は死亡した。遺族に慰謝料を支払った");
			Player player = playerRepository.getReferenceById(friendparty.getPlayer2().getId());
			friendparty.setPlayer2(null);
			partyRepository.save(friendparty);
			user_info.setMoney(user_info.getMoney() - 10000);
			user_infoRepository.save(user_info);
			Item dropitem = itemRepository.findByPlayer_idAndItemkindNot(player.getId(), 10);
			Item dropsitem = itemRepository.findByPlayer_idAndItemkind(player.getId(), 10);
			if (dropitem != null) {
				itemRepository.delete(dropitem);
			}
			if (dropsitem != null) {
				itemRepository.delete(dropsitem);
			}
			playerRepository.delete(player);

		}
		if (friendparty.getPlayer3() != null && friendparty.getPlayer3().getHp() <= 0) {
			message.add(friendparty.getPlayer3().getName() + "は死亡した。遺族に慰謝料を支払った");
			Player player = playerRepository.getReferenceById(friendparty.getPlayer3().getId());
			friendparty.setPlayer3(null);
			partyRepository.save(friendparty);
			user_info.setMoney(user_info.getMoney() - 10000);
			user_infoRepository.save(user_info);
			Item dropitem = itemRepository.findByPlayer_idAndItemkindNot(player.getId(), 10);
			Item dropsitem = itemRepository.findByPlayer_idAndItemkind(player.getId(), 10);
			if (dropitem != null) {
				itemRepository.delete(dropitem);
			}
			if (dropsitem != null) {
				itemRepository.delete(dropsitem);
			}
			playerRepository.delete(player);

		}
		if (friendparty.getPlayer4() != null && friendparty.getPlayer4().getHp() <= 0) {
			message.add(friendparty.getPlayer4().getName() + "は死亡した。遺族に慰謝料を支払った");
			Player player = playerRepository.getReferenceById(friendparty.getPlayer4().getId());
			friendparty.setPlayer4(null);
			partyRepository.save(friendparty);
			user_info.setMoney(user_info.getMoney() - 10000);
			user_infoRepository.save(user_info);
			Item dropitem = itemRepository.findByPlayer_idAndItemkindNot(player.getId(), 10);
			Item dropsitem = itemRepository.findByPlayer_idAndItemkind(player.getId(), 10);
			if (dropitem != null) {
				itemRepository.delete(dropitem);

			}
			if (dropsitem != null) {
				itemRepository.delete(dropsitem);

			}
			playerRepository.delete(player);

		}
		if (friendparty.getPlayer5() != null && friendparty.getPlayer5().getHp() <= 0) {
			message.add(friendparty.getPlayer5().getName() + "は死亡した。遺族に慰謝料を支払った");
			Player player = playerRepository.getReferenceById(friendparty.getPlayer5().getId());
			friendparty.setPlayer5(null);
			partyRepository.save(friendparty);
			user_info.setMoney(user_info.getMoney() - 10000);
			user_infoRepository.save(user_info);
			Item dropitem = itemRepository.findByPlayer_idAndItemkindNot(player.getId(), 10);
			Item dropsitem = itemRepository.findByPlayer_idAndItemkind(player.getId(), 10);
			if (dropitem != null) {
				itemRepository.delete(dropitem);

			}
			if (dropsitem != null) {
				itemRepository.delete(dropsitem);

			}
			playerRepository.delete(player);

		}
		if (friendparty.getPlayer6() != null && friendparty.getPlayer6().getHp() <= 0) {
			message.add(friendparty.getPlayer6().getName() + "は死亡した。遺族に慰謝料を支払った");
			Player player = playerRepository.getReferenceById(friendparty.getPlayer6().getId());
			friendparty.setPlayer6(null);
			partyRepository.save(friendparty);
			user_info.setMoney(user_info.getMoney() - 10000);
			user_infoRepository.save(user_info);
			Item dropitem = itemRepository.findByPlayer_idAndItemkindNot(player.getId(), 10);
			Item dropsitem = itemRepository.findByPlayer_idAndItemkind(player.getId(), 10);
			if (dropitem != null) {
				itemRepository.delete(dropitem);

			}
			if (dropsitem != null) {
				itemRepository.delete(dropsitem);
			}
			playerRepository.delete(player);

		}
		if (friendparty.getPlayer7() != null && friendparty.getPlayer7().getHp() <= 0) {
			message.add(friendparty.getPlayer7().getName() + "は死亡した。遺族に慰謝料を支払った");
			Player player = playerRepository.getReferenceById(friendparty.getPlayer7().getId());
			friendparty.setPlayer7(null);
			partyRepository.save(friendparty);
			user_info.setMoney(user_info.getMoney() - 10000);
			user_infoRepository.save(user_info);
			Item dropitem = itemRepository.findByPlayer_idAndItemkindNot(player.getId(), 10);
			Item dropsitem = itemRepository.findByPlayer_idAndItemkind(player.getId(), 10);
			if (dropitem != null) {
				itemRepository.delete(dropitem);

			}
			if (dropsitem != null) {
				itemRepository.delete(dropsitem);

			}
			playerRepository.delete(player);

		}
		if (friendparty.getPlayer8() != null && friendparty.getPlayer8().getHp() <= 0) {
			message.add(friendparty.getPlayer8().getName() + "は死亡した。遺族に慰謝料を支払った");
			Player player = playerRepository.getReferenceById(friendparty.getPlayer8().getId());
			friendparty.setPlayer8(null);
			partyRepository.save(friendparty);
			user_info.setMoney(user_info.getMoney() - 10000);
			user_infoRepository.save(user_info);
			Item dropitem = itemRepository.findByPlayer_idAndItemkindNot(player.getId(), 10);
			Item dropsitem = itemRepository.findByPlayer_idAndItemkind(player.getId(), 10);
			if (dropitem != null) {
				itemRepository.delete(dropitem);

			}
			if (dropsitem != null) {
				itemRepository.delete(dropsitem);

			}
			playerRepository.delete(player);

		}
		model.addAttribute("monsterHp", monsterHp);
		model.addAttribute("transformation", transformation);
		int ides = 0;
		model.addAttribute("enemyparty", enemyparty);
		System.out.println(user.getName() + "戦闘結果処理");
		model.addAttribute("friendparty", friendparty);
		if (friendparty.getPlayer1() == null && friendparty.getPlayer2() == null && friendparty.getPlayer3() == null
				&& friendparty.getPlayer4() == null && friendparty.getPlayer5() == null
				&& friendparty.getPlayer6() == null && friendparty.getPlayer7() == null
				&& friendparty.getPlayer8() == null) {
			message.add("全滅したため敗北となります。下のボタンを押してホームに帰還してください");
			ides = 1;
			model.addAttribute("ides", ides);
			model.addAttribute("message", message);
			BackForm backForm = new BackForm();
			return "game/battle/battle";
		}
		if (monsterHp.get(0) <= 0 && monsterHp.get(1) <= 0 && monsterHp.get(2) <= 0 && monsterHp.get(3) <= 0
				&& monsterHp.get(4) <= 0 && monsterHp.get(5) <= 0 && monsterHp.get(6) <= 0
				&& monsterHp.get(7) <= 0) {
			message.add("敵モンスターが全滅したため勝利です。下のボタンを押してホームに帰還してください");
			user_info.setMoney(user_info.getMoney() + id * 1000);
			user_infoRepository.save(user_info);
			message.add("勝利しため報酬がお金「" + id * 1000 + "」手に入りました。");
			ides = 1;
			model.addAttribute("ides", ides);
			model.addAttribute("message", message);
			return "game/battle/battle";
		}
		System.out.println(user.getName() + "次の戦闘へ続く");
		model.addAttribute("monsterHp", monsterHp);
		model.addAttribute("ides", ides);
		model.addAttribute("message", message);
		BattleForm battleForm = new BattleForm(0, 0, 0, 0, 0, 0, 0, 0, monsterHp.get(0), monsterHp.get(1),
				monsterHp.get(2), monsterHp.get(3), monsterHp.get(4), monsterHp.get(5), monsterHp.get(6),
				monsterHp.get(7));
		model.addAttribute("battleForm", battleForm);
		return "game/battle/battle";
	}

	@PostMapping("/back")
	public String home(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model,
			Transformation transformation) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		model.addAttribute("user", user);
		User_info user_info = user_infoRepository.findByUser_id(userDetailsImpl.getUser().getId());
		model.addAttribute("user_info", user_info);
		model.addAttribute("transformtion", transformation);
		System.out.println(user.getName() + "戦闘バック");
		return "game/home";
	}
}
