package com.example.circling.game.battle;

import java.util.ArrayList;

import com.example.circling.circling.entity.User;
import com.example.circling.game.entity.Item;
import com.example.circling.game.entity.Party;
import com.example.circling.game.entity.Player;
import com.example.circling.game.form.BattleForm;
import com.example.circling.game.repository.ItemRepository;
import com.example.circling.game.repository.PlayerRepository;
import com.example.circling.game.service.Transformation;

public class Main_battle {
	public int m(Player player) {
		int x = 0;
		int y = 0;
		x += player.getHp() + player.getCp();
		y += player.getCp() + player.getFp();
		y /= 2;
		return x + y;
	}

	public int monsterattack(Party party, int h1, int h2, int h3, int h4, int h5, int h6, int h7,
			int h8) {
		int x = 0;
		if (party.getPlayer1() != null && h1 > 0) {
			x += m(party.getPlayer1());
		}
		if (party.getPlayer2() != null && h2 > 0) {
			x += m(party.getPlayer2());
		}
		if (party.getPlayer3() != null && h3 > 0) {
			x += m(party.getPlayer3());
		}
		if (party.getPlayer4() != null && h4 > 0) {
			x += m(party.getPlayer4());
		}
		if (party.getPlayer5() != null && h5 > 0) {
			x += m(party.getPlayer5());
		}
		if (party.getPlayer6() != null && h6 > 0) {
			x += m(party.getPlayer6());
		}
		if (party.getPlayer7() != null && h7 > 0) {
			x += m(party.getPlayer7());
		}
		if (party.getPlayer8() != null && h8 > 0) {
			x += m(party.getPlayer8());
		}
		return x;

	}

	public int Jobitem(int x) {
		switch (x) {
		case 1, 2, 3, 4, 5, 6, 7, 8, 9 -> {
			return x;
		}
		case 11, 12 -> {
			return 8;
		}
		}
		return 0;
	}

	public void ifattackint(ArrayList<Integer> monsterHp, int attackint) {
		int random = new java.util.Random().nextInt(8);
		if (monsterHp.get(random) > 0) {
			monsterHp.set(random, monsterHp.get(random) - attackint);
		} else {
			random++;
			if (random > 7) {
				random -= 8;
			}
			if (monsterHp.get(random) > 0) {
				monsterHp.set(random, monsterHp.get(random) - attackint);
			} else {
				random++;
				if (random > 7) {
					random -= 8;
				}
				if (monsterHp.get(random) > 0) {
					monsterHp.set(random, monsterHp.get(random) - attackint);
				} else {
					random++;
					if (random > 7) {
						random -= 8;
					}
					if (monsterHp.get(random) > 0) {
						monsterHp.set(random, monsterHp.get(random) - attackint);
					} else {
						random++;
						if (random > 7) {
							random -= 8;
						}
						if (monsterHp.get(random) > 0) {
							monsterHp.set(random, monsterHp.get(random) - attackint);
						} else {
							random++;
							if (random > 7) {
								random -= 8;
							}
							if (monsterHp.get(random) > 0) {
								monsterHp.set(random, monsterHp.get(random) - attackint);
							} else {
								random++;
								if (random > 7) {
									random -= 8;
								}
								if (monsterHp.get(random) > 0) {
									monsterHp.set(random, monsterHp.get(random) - attackint);
								} else {
									random++;
									if (random > 7) {
										random -= 8;
									}
									if (monsterHp.get(random) > 0) {
										monsterHp.set(random, monsterHp.get(random) - attackint);
									} else {

									}
								}
							}
						}
					}
				}
			}
		}
	}

	//1-1
	public ArrayList<String> syori11(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation) {
		System.out.println("来てます");
		if (friendparty.getPlayer1() != null) {
			System.out.println("来てます");
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer1().getId(), Jobitem(friendparty.getPlayer1().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer1().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer1().getJob() == 1) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer1().getMuscle()
							* friendparty.getPlayer1().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer1().getMuscle()
							* friendparty.getPlayer1().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer1().getJob() == 2) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer1().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer1().getMuscle())))
								* friendparty.getPlayer1().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer1().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer1().getMuscle())))
								* friendparty.getPlayer1().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer1().getJob() == 3) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer1().getSpirit())
							* (Math.sqrt(friendparty.getPlayer1().getMuscle()))
							* friendparty.getPlayer1().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer1().getSpirit())
							* (Math.sqrt(friendparty.getPlayer1().getMuscle()))
							* friendparty.getPlayer1().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer1().getJob() == 4) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1 && friendparty.getPlayer1().getMp() >= 5) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer1().getSpirit())
							* (Math.sqrt(friendparty.getPlayer1().getMuscle()))
							* friendparty.getPlayer1().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer1() == 2 && friendparty.getPlayer1().getMp() >= 5) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer1().getSpirit())
							* (Math.sqrt(friendparty.getPlayer1().getMuscle()))
							* friendparty.getPlayer1().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer1().getJob() == 5) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer1().getMuscle()
							* friendparty.getPlayer1().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer1().getMuscle()
							* friendparty.getPlayer1().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer1().getJob() == 6) {
				System.out.println("来てますyo");
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer1().getMuscle() * friendparty.getPlayer1().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer1().getMuscle() * friendparty.getPlayer1().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer1().getJob() == 7) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer1().getMuscle())
							* friendparty.getPlayer1().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer1().getMuscle())
							* friendparty.getPlayer1().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer1().getJob() == 8) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1 && friendparty.getPlayer1().getMp() >= 5) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 5);
					double attackdouble = friendparty.getPlayer1().getTechnique()
							* friendparty.getPlayer2().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer1() == 2 && friendparty.getPlayer1().getMp() >= 5) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 5);
					double attackdouble = friendparty.getPlayer1().getTechnique()
							* friendparty.getPlayer2().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer1().getJob() == 9) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer1().getJob() == 10) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1 && friendparty.getPlayer1().getMp() >= 5) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 5);

				} else if (battle.getPlayer1() == 2 && friendparty.getPlayer1().getMp() >= 5) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 5);
				} else {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer1().getJob() == 11) {
				double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer1().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer1() == 0) {
					if (friendparty.getPlayer1().getMp() < 3) {

					} else if (battle.getPlayer1() == 21) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);
					} else if (battle.getPlayer1() == 22) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);
					} else if (battle.getPlayer1() == 31) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer1() == 32) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer1() == 41) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 1, 15);

					} else if (battle.getPlayer1() == 42) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 2, 15);

					} else if (battle.getPlayer1() == 51) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer1() == 52) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer1() == 61) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer1() == 62) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer1() == 71) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer1() == 72) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					} else if (battle.getPlayer1() == 81) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer1() == 82) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer1().getJob() == 12) {
				double l = friendparty.getPlayer1().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer1().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		return message;

	}

	//1-2
	public ArrayList<Integer> syori12(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation,
			PlayerRepository playerRepository) {
		if (friendparty.getPlayer1() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer1().getId(), Jobitem(friendparty.getPlayer1().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer1().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer1().getJob() == 1) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer1().getMuscle()
							* friendparty.getPlayer1().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer1().getMuscle()
							* friendparty.getPlayer1().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer1().getJob() == 2) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer1().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer1().getMuscle())))
								* friendparty.getPlayer1().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer1().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer1().getMuscle())))
								* friendparty.getPlayer1().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer1().getJob() == 3) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer1().getSpirit())
							* (Math.sqrt(friendparty.getPlayer1().getMuscle()))
							* friendparty.getPlayer1().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer1().getSpirit())
							* (Math.sqrt(friendparty.getPlayer1().getMuscle()))
							* friendparty.getPlayer1().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer1().getJob() == 4) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1 && friendparty.getPlayer1().getMp() >= 5) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer1().getSpirit())
							* (Math.sqrt(friendparty.getPlayer1().getMuscle()))
							* friendparty.getPlayer1().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer1() == 2 && friendparty.getPlayer1().getMp() >= 5) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer1().getSpirit())
							* (Math.sqrt(friendparty.getPlayer1().getMuscle()))
							* friendparty.getPlayer1().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer1().getJob() == 5) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer1().getMuscle()
							* friendparty.getPlayer1().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer1().getMuscle()
							* friendparty.getPlayer1().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer1().getJob() == 6) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer1().getMuscle() * friendparty.getPlayer1().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer1().getMuscle() * friendparty.getPlayer1().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer1().getJob() == 7) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer1().getMuscle())
							* friendparty.getPlayer1().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer1().getMuscle())
							* friendparty.getPlayer1().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer1().getJob() == 8) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1 && friendparty.getPlayer1().getMp() >= 5) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 5);
					double attackdouble = friendparty.getPlayer1().getTechnique()
							* friendparty.getPlayer2().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer1() == 2 && friendparty.getPlayer1().getMp() >= 5) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 5);
					double attackdouble = friendparty.getPlayer1().getTechnique()
							* friendparty.getPlayer2().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer1().getJob() == 9) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 2) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer1().getJob() == 10) {
				if (battle.getPlayer1() == 0) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer1() == 1 && friendparty.getPlayer1().getMp() >= 5) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 5);

				} else if (battle.getPlayer1() == 2 && friendparty.getPlayer1().getMp() >= 5) {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 5);
				} else {
					double l = friendparty.getPlayer1().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer1().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer1().getJob() == 11) {
				double l = friendparty.getPlayer1().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer1().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer1() == 0) {
					if (friendparty.getPlayer1().getMp() < 3) {

					} else if (battle.getPlayer1() == 21) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);
					} else if (battle.getPlayer1() == 22) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);
					} else if (battle.getPlayer1() == 31) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer1() == 32) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer1() == 41) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 1, 15);

					} else if (battle.getPlayer1() == 42) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 2, 15);

					} else if (battle.getPlayer1() == 51) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer1() == 52) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer1() == 61) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer1() == 62) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer1() == 71) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer1() == 72) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					} else if (battle.getPlayer1() == 81) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer1() == 82) {
						friendparty.getPlayer1().setMp(friendparty.getPlayer1().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer1().getJob() == 12) {
				double l = friendparty.getPlayer1().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer1().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer1().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		if (friendparty.getPlayer1() != null) {
			playerRepository.save(friendparty.getPlayer1());
		}
		if (friendparty.getPlayer2() != null) {
			playerRepository.save(friendparty.getPlayer2());
		}
		if (friendparty.getPlayer3() != null) {
			playerRepository.save(friendparty.getPlayer3());
		}
		if (friendparty.getPlayer4() != null) {
			playerRepository.save(friendparty.getPlayer4());
		}
		if (friendparty.getPlayer5() != null) {
			playerRepository.save(friendparty.getPlayer5());
		}
		if (friendparty.getPlayer6() != null) {
			playerRepository.save(friendparty.getPlayer6());
		}
		if (friendparty.getPlayer7() != null) {
			playerRepository.save(friendparty.getPlayer7());
		}
		if (friendparty.getPlayer8() != null) {
			playerRepository.save(friendparty.getPlayer8());
		}
		return monsterHp;
	}

	//2-1
	public ArrayList<String> syori21(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation) {
		if (friendparty.getPlayer2() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer2().getId(), Jobitem(friendparty.getPlayer2().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer2().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer2().getJob() == 1) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer2().getMuscle()
							* friendparty.getPlayer2().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer2().getMuscle()
							* friendparty.getPlayer2().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer2().getJob() == 2) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer2().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer2().getMuscle())))
								* friendparty.getPlayer2().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer2().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer2().getMuscle())))
								* friendparty.getPlayer2().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer2().getJob() == 3) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer2().getSpirit())
							* (Math.sqrt(friendparty.getPlayer2().getMuscle()))
							* friendparty.getPlayer2().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer2().getSpirit())
							* (Math.sqrt(friendparty.getPlayer2().getMuscle()))
							* friendparty.getPlayer2().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer2().getJob() == 4) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1 && friendparty.getPlayer2().getMp() >= 5) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer2().getSpirit())
							* (Math.sqrt(friendparty.getPlayer2().getMuscle()))
							* friendparty.getPlayer2().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer2() == 2 && friendparty.getPlayer2().getMp() >= 5) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer2().getSpirit())
							* (Math.sqrt(friendparty.getPlayer2().getMuscle()))
							* friendparty.getPlayer2().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer2().getJob() == 5) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer2().getMuscle()
							* friendparty.getPlayer2().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer2().getMuscle()
							* friendparty.getPlayer2().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer2().getJob() == 6) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer2().getMuscle() * friendparty.getPlayer2().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer2().getMuscle() * friendparty.getPlayer2().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer2().getJob() == 7) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer2().getMuscle())
							* friendparty.getPlayer2().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer2().getMuscle())
							* friendparty.getPlayer2().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer2().getJob() == 8) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1 && friendparty.getPlayer2().getMp() >= 5) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 5);
					double attackdouble = friendparty.getPlayer2().getTechnique()
							* friendparty.getPlayer2().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer2() == 2 && friendparty.getPlayer2().getMp() >= 5) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 5);
					double attackdouble = friendparty.getPlayer2().getTechnique()
							* friendparty.getPlayer2().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer2().getJob() == 9) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer2().getJob() == 10) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1 && friendparty.getPlayer2().getMp() >= 5) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 5);

				} else if (battle.getPlayer2() == 2 && friendparty.getPlayer2().getMp() >= 5) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 5);
				} else {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer2().getJob() == 11) {
				double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer2().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer2() == 0) {
					if (friendparty.getPlayer2().getMp() < 3) {

					} else if (battle.getPlayer2() == 11) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer2() == 12) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer2() == 31) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer2() == 32) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer2() == 41) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 1, 15);

					} else if (battle.getPlayer2() == 42) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 2, 15);

					} else if (battle.getPlayer2() == 51) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer2() == 52) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer2() == 61) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer2() == 62) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer2() == 71) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer2() == 72) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					} else if (battle.getPlayer2() == 81) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer2() == 82) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer2().getJob() == 12) {
				double l = friendparty.getPlayer2().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer2().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		return message;
	}

	//2-2
	public ArrayList<Integer> syori22(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation,
			PlayerRepository playerRepository) {
		if (friendparty.getPlayer2() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer2().getId(), Jobitem(friendparty.getPlayer2().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer2().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer2().getJob() == 1) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer2().getMuscle()
							* friendparty.getPlayer2().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer2().getMuscle()
							* friendparty.getPlayer2().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer2().getJob() == 2) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer2().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer2().getMuscle())))
								* friendparty.getPlayer2().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer2().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer2().getMuscle())))
								* friendparty.getPlayer2().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer2().getJob() == 3) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer2().getSpirit())
							* (Math.sqrt(friendparty.getPlayer2().getMuscle()))
							* friendparty.getPlayer2().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer2().getSpirit())
							* (Math.sqrt(friendparty.getPlayer2().getMuscle()))
							* friendparty.getPlayer2().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer2().getJob() == 4) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1 && friendparty.getPlayer2().getMp() >= 5) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer2().getSpirit())
							* (Math.sqrt(friendparty.getPlayer2().getMuscle()))
							* friendparty.getPlayer2().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer2() == 2 && friendparty.getPlayer2().getMp() >= 5) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer2().getSpirit())
							* (Math.sqrt(friendparty.getPlayer2().getMuscle()))
							* friendparty.getPlayer2().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer2().getJob() == 5) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer2().getMuscle()
							* friendparty.getPlayer2().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer2().getMuscle()
							* friendparty.getPlayer2().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer2().getJob() == 6) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer2().getMuscle() * friendparty.getPlayer2().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer2().getMuscle() * friendparty.getPlayer2().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer1().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer2().getJob() == 7) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer2().getMuscle())
							* friendparty.getPlayer2().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer2().getMuscle())
							* friendparty.getPlayer2().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer2().getJob() == 8) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1 && friendparty.getPlayer2().getMp() >= 5) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 5);
					double attackdouble = friendparty.getPlayer2().getTechnique()
							* friendparty.getPlayer2().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer2() == 2 && friendparty.getPlayer2().getMp() >= 5) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 5);
					double attackdouble = friendparty.getPlayer2().getTechnique()
							* friendparty.getPlayer2().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer2().getJob() == 9) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 2) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer2().getJob() == 10) {
				if (battle.getPlayer2() == 0) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer2() == 1 && friendparty.getPlayer2().getMp() >= 5) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 5);

				} else if (battle.getPlayer2() == 2 && friendparty.getPlayer2().getMp() >= 5) {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 5);
				} else {
					double l = friendparty.getPlayer2().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer2().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer2().getJob() == 11) {
				double l = friendparty.getPlayer2().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer2().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer2() == 0) {
					if (friendparty.getPlayer2().getMp() < 3) {

					} else if (battle.getPlayer2() == 11) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer2() == 12) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer2() == 31) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer2() == 32) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer2() == 41) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 1, 15);

					} else if (battle.getPlayer2() == 42) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 2, 15);

					} else if (battle.getPlayer2() == 51) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer2() == 52) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer2() == 61) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer2() == 62) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer2() == 71) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer2() == 72) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					} else if (battle.getPlayer2() == 81) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer2() == 82) {
						friendparty.getPlayer2().setMp(friendparty.getPlayer2().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer2().getJob() == 12) {
				double l = friendparty.getPlayer2().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer2().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer2().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		if (friendparty.getPlayer1() != null) {
			playerRepository.save(friendparty.getPlayer1());
		}
		if (friendparty.getPlayer2() != null) {
			playerRepository.save(friendparty.getPlayer2());
		}
		if (friendparty.getPlayer3() != null) {
			playerRepository.save(friendparty.getPlayer3());
		}
		if (friendparty.getPlayer4() != null) {
			playerRepository.save(friendparty.getPlayer4());
		}
		if (friendparty.getPlayer5() != null) {
			playerRepository.save(friendparty.getPlayer5());
		}
		if (friendparty.getPlayer6() != null) {
			playerRepository.save(friendparty.getPlayer6());
		}
		if (friendparty.getPlayer7() != null) {
			playerRepository.save(friendparty.getPlayer7());
		}
		if (friendparty.getPlayer8() != null) {
			playerRepository.save(friendparty.getPlayer8());
		}
		return monsterHp;
	}

	//3-1
	public ArrayList<String> syori31(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation) {
		if (friendparty.getPlayer3() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer3().getId(), Jobitem(friendparty.getPlayer3().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer3().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer3().getJob() == 1) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer3().getMuscle()
							* friendparty.getPlayer3().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer3().getMuscle()
							* friendparty.getPlayer3().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer3().getJob() == 2) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer3().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer3().getMuscle())))
								* friendparty.getPlayer3().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer3().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer3().getMuscle())))
								* friendparty.getPlayer3().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer3().getJob() == 3) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer3().getSpirit())
							* (Math.sqrt(friendparty.getPlayer3().getMuscle()))
							* friendparty.getPlayer3().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer3().getSpirit())
							* (Math.sqrt(friendparty.getPlayer3().getMuscle()))
							* friendparty.getPlayer3().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer3().getJob() == 4) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1 && friendparty.getPlayer3().getMp() >= 5) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer3().getSpirit())
							* (Math.sqrt(friendparty.getPlayer3().getMuscle()))
							* friendparty.getPlayer3().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer3() == 2 && friendparty.getPlayer3().getMp() >= 5) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer3().getSpirit())
							* (Math.sqrt(friendparty.getPlayer3().getMuscle()))
							* friendparty.getPlayer3().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer3().getJob() == 5) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer3().getMuscle()
							* friendparty.getPlayer3().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer3().getMuscle()
							* friendparty.getPlayer3().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer3().getJob() == 6) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer3().getMuscle() * friendparty.getPlayer3().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer3().getMuscle() * friendparty.getPlayer3().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer3().getJob() == 7) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer3().getMuscle())
							* friendparty.getPlayer3().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer3().getMuscle())
							* friendparty.getPlayer3().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer3().getJob() == 8) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1 && friendparty.getPlayer3().getMp() >= 5) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 5);
					double attackdouble = friendparty.getPlayer3().getTechnique()
							* friendparty.getPlayer3().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer3() == 2 && friendparty.getPlayer3().getMp() >= 5) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 5);
					double attackdouble = friendparty.getPlayer3().getTechnique()
							* friendparty.getPlayer3().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer3().getJob() == 9) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer3().getJob() == 10) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1 && friendparty.getPlayer3().getMp() >= 5) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 5);

				} else if (battle.getPlayer3() == 2 && friendparty.getPlayer3().getMp() >= 5) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 5);
				} else {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer3().getJob() == 11) {
				double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer3().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer3() == 0) {
					if (friendparty.getPlayer3().getMp() < 3) {

					} else if (battle.getPlayer3() == 11) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer3() == 12) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer3() == 21) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);
					} else if (battle.getPlayer3() == 22) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);

					} else if (battle.getPlayer3() == 41) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 1, 15);

					} else if (battle.getPlayer3() == 42) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 2, 15);

					} else if (battle.getPlayer3() == 51) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer3() == 52) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer3() == 61) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer3() == 62) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer3() == 71) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer3() == 72) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					} else if (battle.getPlayer3() == 81) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer3() == 82) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer3().getJob() == 12) {
				double l = friendparty.getPlayer3().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer3().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		return message;
	}

	//3-2
	public ArrayList<Integer> syori32(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation,
			PlayerRepository playerRepository) {
		if (friendparty.getPlayer3() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer3().getId(), Jobitem(friendparty.getPlayer3().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer3().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer3().getJob() == 1) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer3().getMuscle()
							* friendparty.getPlayer3().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer3().getMuscle()
							* friendparty.getPlayer3().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer3().getJob() == 2) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer3().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer3().getMuscle())))
								* friendparty.getPlayer3().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer3().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer3().getMuscle())))
								* friendparty.getPlayer3().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer3().getJob() == 3) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer3().getSpirit())
							* (Math.sqrt(friendparty.getPlayer3().getMuscle()))
							* friendparty.getPlayer3().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer3().getSpirit())
							* (Math.sqrt(friendparty.getPlayer3().getMuscle()))
							* friendparty.getPlayer3().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer3().getJob() == 4) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1 && friendparty.getPlayer3().getMp() >= 5) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer3().getSpirit())
							* (Math.sqrt(friendparty.getPlayer3().getMuscle()))
							* friendparty.getPlayer3().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer3() == 2 && friendparty.getPlayer3().getMp() >= 5) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer3().getSpirit())
							* (Math.sqrt(friendparty.getPlayer3().getMuscle()))
							* friendparty.getPlayer3().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer3().getJob() == 5) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer3().getMuscle()
							* friendparty.getPlayer3().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer3().getMuscle()
							* friendparty.getPlayer3().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer3().getJob() == 6) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer3().getMuscle() * friendparty.getPlayer3().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer3().getMuscle() * friendparty.getPlayer3().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer1().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer3().getJob() == 7) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer3().getMuscle())
							* friendparty.getPlayer3().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer3().getMuscle())
							* friendparty.getPlayer3().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer3().getJob() == 8) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1 && friendparty.getPlayer3().getMp() >= 5) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 5);
					double attackdouble = friendparty.getPlayer3().getTechnique()
							* friendparty.getPlayer3().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer3() == 2 && friendparty.getPlayer3().getMp() >= 5) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 5);
					double attackdouble = friendparty.getPlayer3().getTechnique()
							* friendparty.getPlayer3().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer3().getJob() == 9) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 2) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer3().getJob() == 10) {
				if (battle.getPlayer3() == 0) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer3() == 1 && friendparty.getPlayer3().getMp() >= 5) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 5);

				} else if (battle.getPlayer3() == 2 && friendparty.getPlayer3().getMp() >= 5) {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 5);
				} else {
					double l = friendparty.getPlayer3().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer3().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer3().getJob() == 11) {
				double l = friendparty.getPlayer3().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer3().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer3() == 0) {
					if (friendparty.getPlayer3().getMp() < 3) {

					} else if (battle.getPlayer3() == 11) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer3() == 12) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer3() == 21) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);

					} else if (battle.getPlayer3() == 22) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);

					} else if (battle.getPlayer3() == 41) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 1, 15);

					} else if (battle.getPlayer3() == 42) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 2, 15);

					} else if (battle.getPlayer3() == 51) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer3() == 52) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer3() == 61) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer3() == 62) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer3() == 71) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer3() == 72) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					} else if (battle.getPlayer3() == 81) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer3() == 82) {
						friendparty.getPlayer3().setMp(friendparty.getPlayer3().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer3().getJob() == 12) {
				double l = friendparty.getPlayer3().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer3().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer3().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		if (friendparty.getPlayer1() != null) {
			playerRepository.save(friendparty.getPlayer1());
		}
		if (friendparty.getPlayer2() != null) {
			playerRepository.save(friendparty.getPlayer2());
		}
		if (friendparty.getPlayer3() != null) {
			playerRepository.save(friendparty.getPlayer3());
		}
		if (friendparty.getPlayer4() != null) {
			playerRepository.save(friendparty.getPlayer4());
		}
		if (friendparty.getPlayer5() != null) {
			playerRepository.save(friendparty.getPlayer5());
		}
		if (friendparty.getPlayer6() != null) {
			playerRepository.save(friendparty.getPlayer6());
		}
		if (friendparty.getPlayer7() != null) {
			playerRepository.save(friendparty.getPlayer7());
		}
		if (friendparty.getPlayer8() != null) {
			playerRepository.save(friendparty.getPlayer8());
		}
		return monsterHp;

	}

	//4-1
	public ArrayList<String> syori41(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation) {
		if (friendparty.getPlayer4() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer4().getId(), Jobitem(friendparty.getPlayer4().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer4().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer4().getJob() == 1) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer4().getMuscle()
							* friendparty.getPlayer4().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer4() == 2) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer4().getMuscle()
							* friendparty.getPlayer4().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer4().getJob() == 2) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer4().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer4().getMuscle())))
								* friendparty.getPlayer4().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer4() == 2) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer4().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer4().getMuscle())))
								* friendparty.getPlayer4().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer4().getJob() == 3) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer4().getSpirit())
							* (Math.sqrt(friendparty.getPlayer4().getMuscle()))
							* friendparty.getPlayer4().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer4() == 2) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer4().getSpirit())
							* (Math.sqrt(friendparty.getPlayer4().getMuscle()))
							* friendparty.getPlayer4().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer4().getJob() == 4) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1 && friendparty.getPlayer4().getMp() >= 5) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer4().getSpirit())
							* (Math.sqrt(friendparty.getPlayer4().getMuscle()))
							* friendparty.getPlayer4().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer4() == 2 && friendparty.getPlayer4().getMp() >= 5) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer4().getSpirit())
							* (Math.sqrt(friendparty.getPlayer4().getMuscle()))
							* friendparty.getPlayer4().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer4().getJob() == 5) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer4().getMuscle()
							* friendparty.getPlayer4().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer4() == 2) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer4().getMuscle()
							* friendparty.getPlayer4().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer4().getJob() == 6) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer4().getMuscle() * friendparty.getPlayer4().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer4() == 2) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer4().getMuscle() * friendparty.getPlayer4().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer4().getJob() == 7) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer4().getMuscle())
							* friendparty.getPlayer4().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer4() == 2) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer4().getMuscle())
							* friendparty.getPlayer4().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer4().getJob() == 8) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1 && friendparty.getPlayer4().getMp() >= 5) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 5);
					double attackdouble = friendparty.getPlayer4().getTechnique()
							* friendparty.getPlayer4().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer4() == 2 && friendparty.getPlayer4().getMp() >= 5) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 5);
					double attackdouble = friendparty.getPlayer4().getTechnique()
							* friendparty.getPlayer4().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer4().getJob() == 9) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 2) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer4().getJob() == 10) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1 && friendparty.getPlayer4().getMp() >= 5) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 5);

				} else if (battle.getPlayer4() == 2 && friendparty.getPlayer4().getMp() >= 5) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 5);
				} else {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer4().getJob() == 11) {
				double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer4().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer4() == 0) {
					if (friendparty.getPlayer4().getMp() < 3) {

					} else if (battle.getPlayer4() == 11) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer4() == 12) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer4() == 21) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);
					} else if (battle.getPlayer4() == 22) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);

					} else if (battle.getPlayer4() == 31) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer4() == 32) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer4() == 51) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer4() == 52) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer4() == 61) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer4() == 62) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer4() == 71) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer4() == 72) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					} else if (battle.getPlayer4() == 81) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer4() == 82) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer4().getJob() == 12) {
				double l = friendparty.getPlayer4().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer4().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		return message;
	}

	//4-2
	public ArrayList<Integer> syori42(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation,
			PlayerRepository playerRepository) {
		if (friendparty.getPlayer4() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer4().getId(), Jobitem(friendparty.getPlayer4().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer4().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer4().getJob() == 1) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer4().getMuscle()
							* friendparty.getPlayer4().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (battle.getPlayer4() == 2) {
				double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer4().setHp(k);
				if (distanstrong.get(4) * x > 0) {
					message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
				}
				double attackdouble = friendparty.getPlayer4().getMuscle()
						* friendparty.getPlayer4().getMax_technique() * itemst * 1.5 * attack;
				int attackint = (int) attackdouble;

				ifattackint(monsterHp, attackint);

			} else if (friendparty.getPlayer4().getJob() == 2) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer4().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer4().getMuscle())))
								* friendparty.getPlayer4().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer4() == 2) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer4().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer4().getMuscle())))
								* friendparty.getPlayer4().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer4().getJob() == 3) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer4().getSpirit())
							* (Math.sqrt(friendparty.getPlayer4().getMuscle()))
							* friendparty.getPlayer4().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer4() == 2) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer4().getSpirit())
							* (Math.sqrt(friendparty.getPlayer4().getMuscle()))
							* friendparty.getPlayer4().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer4().getJob() == 4) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1 && friendparty.getPlayer4().getMp() >= 5) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer4().getSpirit())
							* (Math.sqrt(friendparty.getPlayer4().getMuscle()))
							* friendparty.getPlayer4().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer4() == 2 && friendparty.getPlayer4().getMp() >= 5) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer4().getSpirit())
							* (Math.sqrt(friendparty.getPlayer4().getMuscle()))
							* friendparty.getPlayer4().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer4().getJob() == 5) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer4().getMuscle()
							* friendparty.getPlayer4().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer4() == 2) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer4().getMuscle()
							* friendparty.getPlayer4().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer4().getJob() == 6) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer4().getMuscle() * friendparty.getPlayer4().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer4() == 2) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer4().getMuscle() * friendparty.getPlayer4().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer1().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer4().getJob() == 7) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer4().getMuscle())
							* friendparty.getPlayer4().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer4() == 2) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer4().getMuscle())
							* friendparty.getPlayer4().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer4().getJob() == 8) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1 && friendparty.getPlayer4().getMp() >= 5) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 5);
					double attackdouble = friendparty.getPlayer4().getTechnique()
							* friendparty.getPlayer4().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer4() == 2 && friendparty.getPlayer4().getMp() >= 5) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 5);
					double attackdouble = friendparty.getPlayer4().getTechnique()
							* friendparty.getPlayer4().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer4().getJob() == 9) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 2) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer4().getJob() == 10) {
				if (battle.getPlayer4() == 0) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer4() == 1 && friendparty.getPlayer4().getMp() >= 5) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 5);

				} else if (battle.getPlayer4() == 2 && friendparty.getPlayer4().getMp() >= 5) {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 5);
				} else {
					double l = friendparty.getPlayer4().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer4().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer4().getJob() == 11) {
				double l = friendparty.getPlayer4().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer4().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer4() == 0) {
					if (friendparty.getPlayer4().getMp() < 3) {

					} else if (battle.getPlayer4() == 11) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer4() == 12) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer4() == 21) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);

					} else if (battle.getPlayer4() == 22) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);

					} else if (battle.getPlayer4() == 31) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer4() == 32) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer4() == 51) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer4() == 52) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer4() == 61) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer4() == 62) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer4() == 71) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer4() == 72) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					} else if (battle.getPlayer4() == 81) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer4() == 82) {
						friendparty.getPlayer4().setMp(friendparty.getPlayer4().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer4().getJob() == 12) {
				double l = friendparty.getPlayer4().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer4().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer4().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		if (friendparty.getPlayer1() != null) {
			playerRepository.save(friendparty.getPlayer1());
		}
		if (friendparty.getPlayer2() != null) {
			playerRepository.save(friendparty.getPlayer2());
		}
		if (friendparty.getPlayer3() != null) {
			playerRepository.save(friendparty.getPlayer3());
		}
		if (friendparty.getPlayer4() != null) {
			playerRepository.save(friendparty.getPlayer4());
		}
		if (friendparty.getPlayer5() != null) {
			playerRepository.save(friendparty.getPlayer5());
		}
		if (friendparty.getPlayer6() != null) {
			playerRepository.save(friendparty.getPlayer6());
		}
		if (friendparty.getPlayer7() != null) {
			playerRepository.save(friendparty.getPlayer7());
		}
		if (friendparty.getPlayer8() != null) {
			playerRepository.save(friendparty.getPlayer8());
		}
		return monsterHp;
	}

	//5-1
	public ArrayList<String> syori51(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation) {
		if (friendparty.getPlayer5() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer5().getId(), Jobitem(friendparty.getPlayer5().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer5().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer5().getJob() == 1) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer5().getMuscle()
							* friendparty.getPlayer5().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer5().getMuscle()
							* friendparty.getPlayer5().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer5().getJob() == 2) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer5().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer5().getMuscle())))
								* friendparty.getPlayer5().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer5().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer5().getMuscle())))
								* friendparty.getPlayer5().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer5().getJob() == 3) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer5().getSpirit())
							* (Math.sqrt(friendparty.getPlayer5().getMuscle()))
							* friendparty.getPlayer5().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer5().getSpirit())
							* (Math.sqrt(friendparty.getPlayer5().getMuscle()))
							* friendparty.getPlayer5().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer5().getJob() == 4) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1 && friendparty.getPlayer5().getMp() >= 5) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer5().getSpirit())
							* (Math.sqrt(friendparty.getPlayer5().getMuscle()))
							* friendparty.getPlayer5().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer5() == 2 && friendparty.getPlayer5().getMp() >= 5) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer5().getSpirit())
							* (Math.sqrt(friendparty.getPlayer5().getMuscle()))
							* friendparty.getPlayer5().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer5().getJob() == 5) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer5().getMuscle()
							* friendparty.getPlayer5().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer5().getMuscle()
							* friendparty.getPlayer5().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer5().getJob() == 6) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer5().getMuscle() * friendparty.getPlayer5().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer5().getMuscle() * friendparty.getPlayer5().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer5().getJob() == 7) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer5().getMuscle())
							* friendparty.getPlayer5().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer5().getMuscle())
							* friendparty.getPlayer5().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer5().getJob() == 8) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1 && friendparty.getPlayer5().getMp() >= 5) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 5);
					double attackdouble = friendparty.getPlayer5().getTechnique()
							* friendparty.getPlayer5().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer5() == 2 && friendparty.getPlayer5().getMp() >= 5) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 5);
					double attackdouble = friendparty.getPlayer5().getTechnique()
							* friendparty.getPlayer5().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer5().getJob() == 9) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer5().getJob() == 10) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1 && friendparty.getPlayer5().getMp() >= 5) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 5);

				} else if (battle.getPlayer5() == 2 && friendparty.getPlayer5().getMp() >= 5) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 5);
				} else {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer5().getJob() == 11) {
				double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer5().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer5() == 0) {
					if (friendparty.getPlayer5().getMp() < 3) {

					} else if (battle.getPlayer5() == 11) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer5() == 12) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer5() == 21) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);
					} else if (battle.getPlayer5() == 22) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);

					} else if (battle.getPlayer5() == 31) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer5() == 32) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer5() == 41) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer5() == 42) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer5() == 61) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer5() == 62) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer5() == 71) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer5() == 72) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					} else if (battle.getPlayer5() == 81) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer5() == 82) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer5().getJob() == 12) {
				double l = friendparty.getPlayer5().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer5().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		return message;
	}

	//5-2
	public ArrayList<Integer> syori52(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation,
			PlayerRepository playerRepository) {
		if (friendparty.getPlayer5() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer5().getId(), Jobitem(friendparty.getPlayer5().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer5().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer5().getJob() == 1) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer5().getMuscle()
							* friendparty.getPlayer5().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer5().getMuscle()
							* friendparty.getPlayer5().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer5().getJob() == 2) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer5().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer5().getMuscle())))
								* friendparty.getPlayer5().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer5().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer5().getMuscle())))
								* friendparty.getPlayer5().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer5().getJob() == 3) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer5().getSpirit())
							* (Math.sqrt(friendparty.getPlayer5().getMuscle()))
							* friendparty.getPlayer5().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer5().getSpirit())
							* (Math.sqrt(friendparty.getPlayer5().getMuscle()))
							* friendparty.getPlayer5().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer5().getJob() == 4) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1 && friendparty.getPlayer5().getMp() >= 5) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer5().getSpirit())
							* (Math.sqrt(friendparty.getPlayer5().getMuscle()))
							* friendparty.getPlayer5().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer5() == 2 && friendparty.getPlayer5().getMp() >= 5) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer5().getSpirit())
							* (Math.sqrt(friendparty.getPlayer5().getMuscle()))
							* friendparty.getPlayer5().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer5().getJob() == 5) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer5().getMuscle()
							* friendparty.getPlayer5().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer5().getMuscle()
							* friendparty.getPlayer5().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer5().getJob() == 6) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer5().getMuscle() * friendparty.getPlayer5().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer5().getMuscle() * friendparty.getPlayer5().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer1().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer5().getJob() == 7) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer5().getMuscle())
							* friendparty.getPlayer5().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer5().getMuscle())
							* friendparty.getPlayer5().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer5().getJob() == 8) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1 && friendparty.getPlayer5().getMp() >= 5) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 5);
					double attackdouble = friendparty.getPlayer5().getTechnique()
							* friendparty.getPlayer5().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer5() == 2 && friendparty.getPlayer5().getMp() >= 5) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 5);
					double attackdouble = friendparty.getPlayer5().getTechnique()
							* friendparty.getPlayer5().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer5().getJob() == 9) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 2) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer5().getJob() == 10) {
				if (battle.getPlayer5() == 0) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer5() == 1 && friendparty.getPlayer5().getMp() >= 5) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 5);

				} else if (battle.getPlayer5() == 2 && friendparty.getPlayer5().getMp() >= 5) {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 5);
				} else {
					double l = friendparty.getPlayer5().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer5().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer5().getJob() == 11) {
				double l = friendparty.getPlayer5().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer5().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer5() == 0) {
					if (friendparty.getPlayer5().getMp() < 3) {

					} else if (battle.getPlayer5() == 11) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer5() == 12) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer5() == 21) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);

					} else if (battle.getPlayer5() == 22) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);

					} else if (battle.getPlayer5() == 31) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer5() == 32) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer5() == 41) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 1, 15);

					} else if (battle.getPlayer5() == 42) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 2, 15);

					} else if (battle.getPlayer5() == 61) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer5() == 62) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer5() == 71) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer5() == 72) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					} else if (battle.getPlayer5() == 81) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer5() == 82) {
						friendparty.getPlayer5().setMp(friendparty.getPlayer5().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer5().getJob() == 12) {
				double l = friendparty.getPlayer5().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer5().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer5().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}
		if (friendparty.getPlayer1() != null) {
			playerRepository.save(friendparty.getPlayer1());
		}
		if (friendparty.getPlayer2() != null) {
			playerRepository.save(friendparty.getPlayer2());
		}
		if (friendparty.getPlayer3() != null) {
			playerRepository.save(friendparty.getPlayer3());
		}
		if (friendparty.getPlayer4() != null) {
			playerRepository.save(friendparty.getPlayer4());
		}
		if (friendparty.getPlayer5() != null) {
			playerRepository.save(friendparty.getPlayer5());
		}
		if (friendparty.getPlayer6() != null) {
			playerRepository.save(friendparty.getPlayer6());
		}
		if (friendparty.getPlayer7() != null) {
			playerRepository.save(friendparty.getPlayer7());
		}
		if (friendparty.getPlayer8() != null) {
			playerRepository.save(friendparty.getPlayer8());
		}
		return monsterHp;
	}

	//6-1
	public ArrayList<String> syori61(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation) {
		if (friendparty.getPlayer6() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer6().getId(), Jobitem(friendparty.getPlayer6().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer6().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer6().getJob() == 1) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer6().getMuscle()
							* friendparty.getPlayer6().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer6().getMuscle()
							* friendparty.getPlayer6().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer6().getJob() == 2) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer6().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer6().getMuscle())))
								* friendparty.getPlayer6().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer6().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer6().getMuscle())))
								* friendparty.getPlayer6().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer6().getJob() == 3) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer6().getSpirit())
							* (Math.sqrt(friendparty.getPlayer6().getMuscle()))
							* friendparty.getPlayer6().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer6().getSpirit())
							* (Math.sqrt(friendparty.getPlayer6().getMuscle()))
							* friendparty.getPlayer6().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer6().getJob() == 4) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1 && friendparty.getPlayer6().getMp() >= 5) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer6().getSpirit())
							* (Math.sqrt(friendparty.getPlayer6().getMuscle()))
							* friendparty.getPlayer6().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer6() == 2 && friendparty.getPlayer6().getMp() >= 5) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer6().getSpirit())
							* (Math.sqrt(friendparty.getPlayer6().getMuscle()))
							* friendparty.getPlayer6().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer6().getJob() == 5) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer6().getMuscle()
							* friendparty.getPlayer6().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer6().getMuscle()
							* friendparty.getPlayer6().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer6().getJob() == 6) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer6().getMuscle() * friendparty.getPlayer6().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer6().getMuscle() * friendparty.getPlayer6().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer6().getJob() == 7) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer6().getMuscle())
							* friendparty.getPlayer6().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer6().getMuscle())
							* friendparty.getPlayer6().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer6().getJob() == 8) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1 && friendparty.getPlayer6().getMp() >= 5) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 5);
					double attackdouble = friendparty.getPlayer6().getTechnique()
							* friendparty.getPlayer6().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer6() == 2 && friendparty.getPlayer6().getMp() >= 5) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 5);
					double attackdouble = friendparty.getPlayer6().getTechnique()
							* friendparty.getPlayer6().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer6().getJob() == 9) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer6().getJob() == 10) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1 && friendparty.getPlayer6().getMp() >= 5) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 5);

				} else if (battle.getPlayer6() == 2 && friendparty.getPlayer6().getMp() >= 5) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 5);
				} else {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer6().getJob() == 11) {
				double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer6().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer6() == 0) {
					if (friendparty.getPlayer6().getMp() < 3) {

					} else if (battle.getPlayer6() == 11) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer6() == 12) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer6() == 21) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);
					} else if (battle.getPlayer6() == 22) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);

					} else if (battle.getPlayer6() == 31) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer6() == 32) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer6() == 41) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer6() == 42) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer6() == 51) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer6() == 52) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer6() == 71) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer6() == 72) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					} else if (battle.getPlayer6() == 81) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer6() == 82) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer6().getJob() == 12) {
				double l = friendparty.getPlayer6().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer6().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		return message;
	}

	//6-2
	public ArrayList<Integer> syori62(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation,
			PlayerRepository playerRepository) {
		if (friendparty.getPlayer6() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer6().getId(), Jobitem(friendparty.getPlayer6().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer6().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer6().getJob() == 1) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer6().getMuscle()
							* friendparty.getPlayer6().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer6().getMuscle()
							* friendparty.getPlayer6().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer6().getJob() == 2) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer6().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer6().getMuscle())))
								* friendparty.getPlayer6().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer6().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer6().getMuscle())))
								* friendparty.getPlayer6().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer6().getJob() == 3) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer6().getSpirit())
							* (Math.sqrt(friendparty.getPlayer6().getMuscle()))
							* friendparty.getPlayer6().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer6().getSpirit())
							* (Math.sqrt(friendparty.getPlayer6().getMuscle()))
							* friendparty.getPlayer6().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer6().getJob() == 4) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1 && friendparty.getPlayer6().getMp() >= 5) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer6().getSpirit())
							* (Math.sqrt(friendparty.getPlayer6().getMuscle()))
							* friendparty.getPlayer6().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer6() == 2 && friendparty.getPlayer6().getMp() >= 5) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer6().getSpirit())
							* (Math.sqrt(friendparty.getPlayer6().getMuscle()))
							* friendparty.getPlayer6().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer6().getJob() == 5) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer6().getMuscle()
							* friendparty.getPlayer6().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer6().getMuscle()
							* friendparty.getPlayer6().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer6().getJob() == 6) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer6().getMuscle() * friendparty.getPlayer6().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer6().getMuscle() * friendparty.getPlayer6().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer1().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer6().getJob() == 7) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer6().getMuscle())
							* friendparty.getPlayer6().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer6().getMuscle())
							* friendparty.getPlayer6().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer6().getJob() == 8) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1 && friendparty.getPlayer6().getMp() >= 5) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 5);
					double attackdouble = friendparty.getPlayer6().getTechnique()
							* friendparty.getPlayer6().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer6() == 2 && friendparty.getPlayer6().getMp() >= 5) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 5);
					double attackdouble = friendparty.getPlayer6().getTechnique()
							* friendparty.getPlayer6().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer6().getJob() == 9) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 2) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer6().getJob() == 10) {
				if (battle.getPlayer6() == 0) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer6() == 1 && friendparty.getPlayer6().getMp() >= 5) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 5);

				} else if (battle.getPlayer6() == 2 && friendparty.getPlayer6().getMp() >= 5) {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 5);
				} else {
					double l = friendparty.getPlayer6().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer6().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer6().getJob() == 11) {
				double l = friendparty.getPlayer6().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer6().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer6() == 0) {
					if (friendparty.getPlayer6().getMp() < 3) {

					} else if (battle.getPlayer6() == 11) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer6() == 12) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer6() == 21) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);

					} else if (battle.getPlayer6() == 22) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);

					} else if (battle.getPlayer6() == 31) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer6() == 32) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer6() == 41) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 1, 15);

					} else if (battle.getPlayer6() == 42) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 2, 15);

					} else if (battle.getPlayer6() == 51) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer6() == 52) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer6() == 71) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer6() == 72) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					} else if (battle.getPlayer6() == 81) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer6() == 82) {
						friendparty.getPlayer6().setMp(friendparty.getPlayer6().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer6().getJob() == 12) {
				double l = friendparty.getPlayer6().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer6().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer6().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		if (friendparty.getPlayer1() != null) {
			playerRepository.save(friendparty.getPlayer1());
		}
		if (friendparty.getPlayer2() != null) {
			playerRepository.save(friendparty.getPlayer2());
		}
		if (friendparty.getPlayer3() != null) {
			playerRepository.save(friendparty.getPlayer3());
		}
		if (friendparty.getPlayer4() != null) {
			playerRepository.save(friendparty.getPlayer4());
		}
		if (friendparty.getPlayer5() != null) {
			playerRepository.save(friendparty.getPlayer5());
		}
		if (friendparty.getPlayer6() != null) {
			playerRepository.save(friendparty.getPlayer6());
		}
		if (friendparty.getPlayer7() != null) {
			playerRepository.save(friendparty.getPlayer7());
		}
		if (friendparty.getPlayer8() != null) {
			playerRepository.save(friendparty.getPlayer8());
		}
		return monsterHp;
	}

	//7-1
	public ArrayList<String> syori71(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation) {
		if (friendparty.getPlayer7() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer7().getId(), Jobitem(friendparty.getPlayer7().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer7().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer7().getJob() == 1) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer7().getMuscle()
							* friendparty.getPlayer7().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer7().getMuscle()
							* friendparty.getPlayer7().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer7().getJob() == 2) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer7().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer7().getMuscle())))
								* friendparty.getPlayer7().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer7().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer7().getMuscle())))
								* friendparty.getPlayer7().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer7().getJob() == 3) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer7().getSpirit())
							* (Math.sqrt(friendparty.getPlayer7().getMuscle()))
							* friendparty.getPlayer7().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer7().getSpirit())
							* (Math.sqrt(friendparty.getPlayer7().getMuscle()))
							* friendparty.getPlayer7().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer7().getJob() == 4) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1 && friendparty.getPlayer7().getMp() >= 5) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer7().getSpirit())
							* (Math.sqrt(friendparty.getPlayer7().getMuscle()))
							* friendparty.getPlayer7().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer7() == 2 && friendparty.getPlayer7().getMp() >= 5) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer7().getSpirit())
							* (Math.sqrt(friendparty.getPlayer7().getMuscle()))
							* friendparty.getPlayer7().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer7().getJob() == 5) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer7().getMuscle()
							* friendparty.getPlayer7().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer7().getMuscle()
							* friendparty.getPlayer7().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer7().getJob() == 6) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer7().getMuscle() * friendparty.getPlayer7().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer7().getMuscle() * friendparty.getPlayer7().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer7().getJob() == 7) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer7().getMuscle())
							* friendparty.getPlayer7().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer7().getMuscle())
							* friendparty.getPlayer7().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer7().getJob() == 8) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1 && friendparty.getPlayer7().getMp() >= 5) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 5);
					double attackdouble = friendparty.getPlayer7().getTechnique()
							* friendparty.getPlayer7().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer7() == 2 && friendparty.getPlayer7().getMp() >= 5) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 5);
					double attackdouble = friendparty.getPlayer7().getTechnique()
							* friendparty.getPlayer7().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer7().getJob() == 9) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer7().getJob() == 10) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1 && friendparty.getPlayer7().getMp() >= 5) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 5);

				} else if (battle.getPlayer7() == 2 && friendparty.getPlayer7().getMp() >= 5) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 5);
				} else {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer7().getJob() == 11) {
				double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer7().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer7() == 0) {
					if (friendparty.getPlayer7().getMp() < 3) {

					} else if (battle.getPlayer7() == 11) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer7() == 12) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer7() == 21) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);
					} else if (battle.getPlayer7() == 22) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);

					} else if (battle.getPlayer7() == 31) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer7() == 32) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer7() == 41) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer7() == 42) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					} else if (battle.getPlayer7() == 51) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer7() == 52) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer7() == 61) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer7() == 62) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer7() == 81) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer7() == 82) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer7().getJob() == 12) {
				double l = friendparty.getPlayer7().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer7().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		return message;
	}

	//7-2
	public ArrayList<Integer> syori72(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation,
			PlayerRepository playerRepository) {
		if (friendparty.getPlayer7() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer7().getId(), Jobitem(friendparty.getPlayer7().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer7().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer7().getJob() == 1) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer7().getMuscle()
							* friendparty.getPlayer7().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer7().getMuscle()
							* friendparty.getPlayer7().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer7().getJob() == 2) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer7().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer7().getMuscle())))
								* friendparty.getPlayer7().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer7().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer7().getMuscle())))
								* friendparty.getPlayer7().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer7().getJob() == 3) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer7().getSpirit())
							* (Math.sqrt(friendparty.getPlayer7().getMuscle()))
							* friendparty.getPlayer7().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer7().getSpirit())
							* (Math.sqrt(friendparty.getPlayer7().getMuscle()))
							* friendparty.getPlayer7().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer7().getJob() == 4) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1 && friendparty.getPlayer7().getMp() >= 5) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer7().getSpirit())
							* (Math.sqrt(friendparty.getPlayer7().getMuscle()))
							* friendparty.getPlayer7().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer7() == 2 && friendparty.getPlayer7().getMp() >= 5) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer7().getSpirit())
							* (Math.sqrt(friendparty.getPlayer7().getMuscle()))
							* friendparty.getPlayer7().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer7().getJob() == 5) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer7().getMuscle()
							* friendparty.getPlayer7().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer7().getMuscle()
							* friendparty.getPlayer7().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer7().getJob() == 6) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer7().getMuscle() * friendparty.getPlayer7().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer7().getMuscle() * friendparty.getPlayer7().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer1().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null && (battle.getPlayer8() == 1 || battle.getPlayer8() == 2)
							&& (friendparty.getPlayer8().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer7().getJob() == 7) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer7().getMuscle())
							* friendparty.getPlayer7().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer7().getMuscle())
							* friendparty.getPlayer7().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer8() != null) {
						friendparty.getPlayer8().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer7().getJob() == 8) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1 && friendparty.getPlayer7().getMp() >= 5) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 5);
					double attackdouble = friendparty.getPlayer7().getTechnique()
							* friendparty.getPlayer7().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer7() == 2 && friendparty.getPlayer7().getMp() >= 5) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 5);
					double attackdouble = friendparty.getPlayer7().getTechnique()
							* friendparty.getPlayer7().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer7().getJob() == 9) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 2) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer7().getJob() == 10) {
				if (battle.getPlayer7() == 0) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer7() == 1 && friendparty.getPlayer7().getMp() >= 5) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 5);

				} else if (battle.getPlayer7() == 2 && friendparty.getPlayer7().getMp() >= 5) {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 5);
				} else {
					double l = friendparty.getPlayer7().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer7().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer7().getJob() == 11) {
				double l = friendparty.getPlayer7().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer7().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer7() == 0) {
					if (friendparty.getPlayer7().getMp() < 3) {

					} else if (battle.getPlayer7() == 11) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer7() == 12) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer7() == 21) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);

					} else if (battle.getPlayer7() == 22) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);

					} else if (battle.getPlayer7() == 31) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer7() == 32) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer7() == 41) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 1, 15);

					} else if (battle.getPlayer7() == 42) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 2, 15);

					} else if (battle.getPlayer7() == 51) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer7() == 52) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer7() == 61) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer7() == 62) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer7() == 81) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer7() == 82) {
						friendparty.getPlayer7().setMp(friendparty.getPlayer7().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer7().getJob() == 12) {
				double l = friendparty.getPlayer7().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer7().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer7().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		if (friendparty.getPlayer1() != null) {
			playerRepository.save(friendparty.getPlayer1());
		}
		if (friendparty.getPlayer2() != null) {
			playerRepository.save(friendparty.getPlayer2());
		}
		if (friendparty.getPlayer3() != null) {
			playerRepository.save(friendparty.getPlayer3());
		}
		if (friendparty.getPlayer4() != null) {
			playerRepository.save(friendparty.getPlayer4());
		}
		if (friendparty.getPlayer5() != null) {
			playerRepository.save(friendparty.getPlayer5());
		}
		if (friendparty.getPlayer6() != null) {
			playerRepository.save(friendparty.getPlayer6());
		}
		if (friendparty.getPlayer7() != null) {
			playerRepository.save(friendparty.getPlayer7());
		}
		if (friendparty.getPlayer8() != null) {
			playerRepository.save(friendparty.getPlayer8());
		}
		return monsterHp;
	}

	//8-1
	public ArrayList<String> syori81(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation) {
		if (friendparty.getPlayer8() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer8().getId(), Jobitem(friendparty.getPlayer8().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer8().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer8().getJob() == 1) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer8().getMuscle()
							* friendparty.getPlayer8().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer8() == 2) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer8().getMuscle()
							* friendparty.getPlayer8().getMax_technique() * itemst * 1.5 * attack;
					int attackint = (int) attackdouble;

					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer8().getJob() == 2) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer8().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer8().getMuscle())))
								* friendparty.getPlayer8().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer8() == 2) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer8().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer8().getMuscle())))
								* friendparty.getPlayer8().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer8().getJob() == 3) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer8().getSpirit())
							* (Math.sqrt(friendparty.getPlayer8().getMuscle()))
							* friendparty.getPlayer8().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer8() == 2) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer8().getSpirit())
							* (Math.sqrt(friendparty.getPlayer8().getMuscle()))
							* friendparty.getPlayer8().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer8().getJob() == 4) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1 && friendparty.getPlayer8().getMp() >= 5) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer8().getSpirit())
							* (Math.sqrt(friendparty.getPlayer8().getMuscle()))
							* friendparty.getPlayer8().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer8() == 2 && friendparty.getPlayer8().getMp() >= 5) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer8().getSpirit())
							* (Math.sqrt(friendparty.getPlayer8().getMuscle()))
							* friendparty.getPlayer8().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer8().getJob() == 5) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer8().getMuscle()
							* friendparty.getPlayer8().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer8() == 2) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer8().getMuscle()
							* friendparty.getPlayer8().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer8().getJob() == 6) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer8().getMuscle() * friendparty.getPlayer8().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer8() == 2) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer8().getMuscle() * friendparty.getPlayer8().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer8().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer8().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer8().getJob() == 7) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer8().getMuscle())
							* friendparty.getPlayer8().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer8() == 2) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer8().getMuscle())
							* friendparty.getPlayer8().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer8().getJob() == 8) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1 && friendparty.getPlayer8().getMp() >= 5) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 5);
					double attackdouble = friendparty.getPlayer8().getTechnique()
							* friendparty.getPlayer8().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer8() == 2 && friendparty.getPlayer8().getMp() >= 5) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 5);
					double attackdouble = friendparty.getPlayer8().getTechnique()
							* friendparty.getPlayer8().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer8().getJob() == 9) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 2) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer8().getJob() == 10) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1 && friendparty.getPlayer8().getMp() >= 5) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 5);

				} else if (battle.getPlayer8() == 2 && friendparty.getPlayer8().getMp() >= 5) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 5);
				} else {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer8().getJob() == 11) {
				double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer8().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer8() == 0) {
					if (friendparty.getPlayer8().getMp() < 3) {

					} else if (battle.getPlayer8() == 11) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer8() == 12) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer8() == 21) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);
					} else if (battle.getPlayer8() == 22) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);

					} else if (battle.getPlayer8() == 31) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer8() == 32) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer8() == 41) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 1, 15);

					} else if (battle.getPlayer8() == 42) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer8(), 2, 15);

					} else if (battle.getPlayer8() == 51) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer8() == 52) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer8() == 61) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer8() == 62) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer8() == 71) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer8() == 72) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer8().getJob() == 12) {
				double l = friendparty.getPlayer8().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer8().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		return message;
	}

	//8-2
	public ArrayList<Integer> syori82(Party friendparty, ItemRepository itemRepository, User user, BattleForm battle,
			ArrayList<Double> distanstrong, double x, ArrayList<String> message, double attack,
			ArrayList<Integer> monsterHp, double suport, Transformation transformation,
			PlayerRepository playerRepository) {
		if (friendparty.getPlayer8() != null) {
			Item item = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer8().getId(), Jobitem(friendparty.getPlayer8().getJob()));
			int itemst = 0;
			if (item != null) {
				itemst = item.getItemstrong();
			} else {
				itemst = 0;
			}
			Item items = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					friendparty.getPlayer8().getId(), 10);
			double itemarr = 1;
			if (items != null) {
				switch (items.getItemstrong()) {
				case 1 -> {
					itemarr = 1;
				}
				case 2 -> {
					itemarr = 0.9;
				}
				case 3 -> {
					itemarr = 0.8;
				}
				case 4 -> {
					itemarr = 0.7;
				}
				case 5 -> {
					itemarr = 0.5;
				}
				default -> {
					itemarr = 1.5;
				}
				}
			} else {
				itemarr = 2;
			}
			if (friendparty.getPlayer8().getJob() == 1) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer8().getMuscle()
							* friendparty.getPlayer8().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (battle.getPlayer8() == 2) {
				double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer8().setHp(k);
				if (distanstrong.get(4) * x > 0) {
					message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
				}
				double attackdouble = friendparty.getPlayer8().getMuscle()
						* friendparty.getPlayer8().getMax_technique() * itemst * 1.5 * attack;
				int attackint = (int) attackdouble;

				ifattackint(monsterHp, attackint);

			} else if (friendparty.getPlayer8().getJob() == 2) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer8().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer8().getMuscle())))
								* friendparty.getPlayer8().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer8() == 2) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					int randoma = new java.util.Random().nextInt(41) + 80;
					for (int i : monsterHp) {
						int randomb = new java.util.Random().nextInt(41) + 80;
						double attackdouble = itemst * 0.8 * attack * randoma * randomb / 10000
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer8().getTechnique())))
								* (Math.sqrt(Math.sqrt(friendparty.getPlayer8().getMuscle())))
								* friendparty.getPlayer8().getQuick();
						int attackint = (int) attackdouble;
						monsterHp.set(s, i - attackint);
						s++;
					}
				}

			} else if (friendparty.getPlayer8().getJob() == 3) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer8().getSpirit())
							* (Math.sqrt(friendparty.getPlayer8().getMuscle()))
							* friendparty.getPlayer8().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer8() == 2) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer8().getSpirit())
							* (Math.sqrt(friendparty.getPlayer8().getMuscle()))
							* friendparty.getPlayer8().getMax_technique() * itemst * attack * 1.5;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer8().getJob() == 4) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1 && friendparty.getPlayer8().getMp() >= 5) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer8().getSpirit())
							* (Math.sqrt(friendparty.getPlayer8().getMuscle()))
							* friendparty.getPlayer8().getMax_technique() * itemst * attack;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else if (battle.getPlayer8() == 2 && friendparty.getPlayer8().getMp() >= 5) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 5);
					double attackdouble = Math.sqrt(friendparty.getPlayer8().getSpirit())
							* (Math.sqrt(friendparty.getPlayer8().getMuscle()))
							* friendparty.getPlayer8().getMax_technique() * itemst * attack * 2;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				} else {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer8().getJob() == 5) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}

				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer8().getMuscle()
							* friendparty.getPlayer8().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer8() == 2) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}

					int randoma = new java.util.Random().nextInt(8);
					int nibai = 1;
					if (randoma == 0) {
						nibai = 2;
					}
					double attackdouble = friendparty.getPlayer8().getMuscle()
							* friendparty.getPlayer8().getEating()
							* attack * nibai * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);
				}
			} else if (friendparty.getPlayer8().getJob() == 6) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = friendparty.getPlayer8().getMuscle() * friendparty.getPlayer8().getQuick()
							* attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
				} else if (battle.getPlayer8() == 2) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(3) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(3) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = friendparty.getPlayer8().getMuscle() * friendparty.getPlayer8().getQuick()
							* suport * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						if (i < attackint) {
							monsterHp.set(s, 0);
						}
						s++;
					}
					if (friendparty.getPlayer1() != null && (battle.getPlayer1() == 1 || battle.getPlayer1() == 2)
							&& (friendparty.getPlayer1().getJob() < 7 || friendparty.getPlayer1().getJob() == 9)) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null && (battle.getPlayer2() == 1 || battle.getPlayer2() == 2)
							&& (friendparty.getPlayer2().getJob() < 7 || friendparty.getPlayer2().getJob() == 9)) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null && (battle.getPlayer3() == 1 || battle.getPlayer3() == 2)
							&& (friendparty.getPlayer3().getJob() < 7 || friendparty.getPlayer3().getJob() == 9)) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null && (battle.getPlayer4() == 1 || battle.getPlayer4() == 2)
							&& (friendparty.getPlayer4().getJob() < 7 || friendparty.getPlayer4().getJob() == 9)) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer8().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null && (battle.getPlayer5() == 1 || battle.getPlayer5() == 2)
							&& (friendparty.getPlayer5().getJob() < 7 || friendparty.getPlayer5().getJob() == 9)) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null && (battle.getPlayer6() == 1 || battle.getPlayer6() == 2)
							&& (friendparty.getPlayer6().getJob() < 7 || friendparty.getPlayer6().getJob() == 9)) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null && (battle.getPlayer7() == 1 || battle.getPlayer7() == 2)
							&& (friendparty.getPlayer7().getJob() < 7 || friendparty.getPlayer7().getJob() == 9)) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は槌使いの連係攻撃の巻き添えを受けた");
					}
				}
			} else if (friendparty.getPlayer8().getJob() == 7) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					double attackdouble = Math.sqrt(friendparty.getPlayer8().getMuscle())
							* friendparty.getPlayer8().getMuscle() * attack * itemst;
					int s = 0;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
				} else if (battle.getPlayer8() == 2) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(4) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(4) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					int s = 0;
					double attackdouble = Math.sqrt(friendparty.getPlayer8().getMuscle())
							* friendparty.getPlayer8().getMuscle() * attack * itemst;
					int attackint = (int) attackdouble;
					for (int i : monsterHp) {
						monsterHp.set(s, i - attackint);
						s++;
					}
					if (friendparty.getPlayer1() != null) {
						friendparty.getPlayer1().setHp(friendparty.getPlayer1().getHp() - attackint);
						message.add(friendparty.getPlayer1().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer2() != null) {
						friendparty.getPlayer2().setHp(friendparty.getPlayer2().getHp() - attackint);
						message.add(friendparty.getPlayer2().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer3() != null) {
						friendparty.getPlayer3().setHp(friendparty.getPlayer3().getHp() - attackint);
						message.add(friendparty.getPlayer3().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer4() != null) {
						friendparty.getPlayer4().setHp(friendparty.getPlayer4().getHp() - attackint);
						message.add(friendparty.getPlayer4().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer5() != null) {
						friendparty.getPlayer5().setHp(friendparty.getPlayer5().getHp() - attackint);
						message.add(friendparty.getPlayer5().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer6() != null) {
						friendparty.getPlayer6().setHp(friendparty.getPlayer6().getHp() - attackint);
						message.add(friendparty.getPlayer6().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}
					if (friendparty.getPlayer7() != null) {
						friendparty.getPlayer7().setHp(friendparty.getPlayer7().getHp() - attackint);
						message.add(friendparty.getPlayer7().getName() + "は棍棒使いの攻撃の巻き添えを受けた");
					}

				}
			} else if (friendparty.getPlayer8().getJob() == 8) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1 && friendparty.getPlayer8().getMp() >= 5) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 5);
					double attackdouble = friendparty.getPlayer8().getTechnique()
							* friendparty.getPlayer8().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else if (battle.getPlayer8() == 2 && friendparty.getPlayer8().getMp() >= 5) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 5);
					double attackdouble = friendparty.getPlayer8().getTechnique()
							* friendparty.getPlayer8().getMagic()
							* attack * itemst;
					int attackint = (int) attackdouble;
					ifattackint(monsterHp, attackint);

				} else {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer8().getJob() == 9) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 2) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(2) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(2) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer8().getJob() == 10) {
				if (battle.getPlayer8() == 0) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(7) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				} else if (battle.getPlayer8() == 1 && friendparty.getPlayer8().getMp() >= 5) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 5);

				} else if (battle.getPlayer8() == 2 && friendparty.getPlayer8().getMp() >= 5) {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
					friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 5);
				} else {
					double l = friendparty.getPlayer8().getHp() - (distanstrong.get(5) * x) * itemarr;
					int k = (int) l;
					friendparty.getPlayer8().setHp(k);
					if (distanstrong.get(5) * x > 0) {
						message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
					}
				}
			} else if (friendparty.getPlayer8().getJob() == 11) {
				double l = friendparty.getPlayer8().getHp() - (distanstrong.get(7) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer8().setHp(k);
				if (distanstrong.get(7) * x > 0) {
					message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
				}

				if (battle.getPlayer8() == 0) {
					if (friendparty.getPlayer8().getMp() < 3) {

					} else if (battle.getPlayer8() == 11) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 1, 15);
					} else if (battle.getPlayer8() == 12) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer1(), 2, 15);
					} else if (battle.getPlayer8() == 21) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 1, 15);

					} else if (battle.getPlayer8() == 22) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer2(), 2, 15);

					} else if (battle.getPlayer8() == 31) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 1, 15);

					} else if (battle.getPlayer8() == 32) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer3(), 2, 15);

					} else if (battle.getPlayer8() == 41) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 1, 15);

					} else if (battle.getPlayer8() == 42) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer4(), 2, 15);

					} else if (battle.getPlayer8() == 51) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 1, 15);

					} else if (battle.getPlayer8() == 52) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer5(), 2, 15);

					} else if (battle.getPlayer8() == 61) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 1, 15);

					} else if (battle.getPlayer8() == 62) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer6(), 2, 15);

					} else if (battle.getPlayer8() == 71) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 1, 15);

					} else if (battle.getPlayer8() == 72) {
						friendparty.getPlayer8().setMp(friendparty.getPlayer8().getMp() - 3);
						transformation.Add_Fluctuation_Status(friendparty.getPlayer7(), 2, 15);

					}
				}
			} else if (friendparty.getPlayer8().getJob() == 12) {
				double l = friendparty.getPlayer8().getHp() - (distanstrong.get(6) * x) * itemarr;
				int k = (int) l;
				friendparty.getPlayer8().setHp(k);
				if (distanstrong.get(6) * x > 0) {
					message.add(friendparty.getPlayer8().getName() + "はモンスターたちの攻撃を受けた");
				}
			}
		}

		if (friendparty.getPlayer1() != null) {
			playerRepository.save(friendparty.getPlayer1());
		}
		if (friendparty.getPlayer2() != null) {
			playerRepository.save(friendparty.getPlayer2());
		}
		if (friendparty.getPlayer3() != null) {
			playerRepository.save(friendparty.getPlayer3());
		}
		if (friendparty.getPlayer4() != null) {
			playerRepository.save(friendparty.getPlayer4());
		}
		if (friendparty.getPlayer5() != null) {
			playerRepository.save(friendparty.getPlayer5());
		}
		if (friendparty.getPlayer6() != null) {
			playerRepository.save(friendparty.getPlayer6());
		}
		if (friendparty.getPlayer7() != null) {
			playerRepository.save(friendparty.getPlayer7());
		}
		if (friendparty.getPlayer8() != null) {
			playerRepository.save(friendparty.getPlayer8());
		}
		return monsterHp;
	}

	public void cpfp(Player player, PlayerRepository playerRepository) {
		if (player.getCp() <= 0) {
			player.setMp(player.getMp() / 2);
			player.setHp(player.getHp() / 2);
		}
		if (player.getFp() <= 0) {
			player.setMp(player.getMp() / 2);
			player.setHp(player.getHp() / 2);
		}
		playerRepository.save(player);
	}

}
