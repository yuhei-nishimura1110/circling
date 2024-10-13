package com.example.circling.game.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.circling.game.entity.Party;
import com.example.circling.game.entity.Player;
import com.example.circling.game.entity.User_info;
import com.example.circling.game.enumset.Experience;
import com.example.circling.game.repository.PlayerRepository;
import com.example.circling.game.repository.User_infoRepository;

@Service
public class Transformation {

	public void Initial_Fluctuation_Status(Player player) {
		player.setHp(player.getStamina() * player.getStamina() * player.getMuscle());
		player.setMp(player.getMagic() * player.getMagic() * player.getTechnique());
		player.setFp(player.getEating() * player.getEating() * player.getQuick());
		player.setCp(player.getSpirit() * player.getSpirit() * player.getKnowledge());
	}

	public void Add_Fluctuation_Status(Player player, int id, int addPoint) {
		switch (id) {
		case 1 -> {
			int hpPoint = 0;
			if (player.getStamina() * player.getStamina() * player.getMuscle() < player.getHp() + addPoint) {
				hpPoint = player.getStamina() * player.getStamina() * player.getMuscle();
			} else
				hpPoint = player.getHp() + addPoint;

			player.setHp(hpPoint);
		}
		case 2 -> {
			int mpPoint = 0;
			if (player.getMagic() * player.getMagic() * player.getTechnique() < player.getMp() + addPoint) {
				mpPoint = player.getMagic() * player.getMagic() * player.getTechnique();
			} else
				mpPoint = player.getMp() + addPoint;
			player.setMp(mpPoint);
		}
		case 3 -> {
			int fpPoint;
			if (player.getEating() * player.getEating() * player.getQuick() < player.getFp() + addPoint) {
				fpPoint = player.getEating() * player.getEating() * player.getQuick();
			} else
				fpPoint = player.getFp() + addPoint;
			player.setFp(fpPoint);
		}
		case 4 -> {
			int cpPoint;
			if (player.getSpirit() * player.getSpirit() * player.getKnowledge() < player.getCp() + addPoint) {
				cpPoint = player.getSpirit() * player.getSpirit() * player.getKnowledge();
			} else
				cpPoint = player.getCp() + addPoint;
			player.setCp(cpPoint);
		}
		}

	}

	public String Fluctuation_Status(int fluctuation_status) {
		switch (fluctuation_status) {
		case 1 -> {
			return "G";
		}
		case 2 -> {
			return "F";
		}
		case 3 -> {
			return "E";
		}
		case 4 -> {
			return "D";
		}
		case 5 -> {
			return "C";
		}
		case 6 -> {
			return "B";
		}
		case 7 -> {
			return "A";
		}
		case 8 -> {
			return "S";
		}
		}
		return "G";
	}

	public String Job(int job) {
		switch (job) {
		case 1 -> {
			return "剣使い";
		}
		case 2 -> {
			return "槍使い";
		}
		case 3 -> {
			return "弓使い";
		}
		case 4 -> {
			return "刀使い";
		}
		case 5 -> {
			return "斧使い";
		}
		case 6 -> {
			return "槌使い";
		}
		case 7 -> {
			return "棍棒使い";
		}
		case 8 -> {
			return "黒魔術師";
		}
		case 9 -> {
			return "盾使い";
		}
		case 10 -> {
			return "魔法盾使い";
		}
		case 11 -> {
			return "白魔術師";
		}
		case 12 -> {
			return "参謀";
		}
		case 13 -> {
			return "鍛冶師";
		}
		case 14 -> {
			return "矢師";
		}
		case 15 -> {
			return "刀鍛冶師";
		}
		case 16 -> {
			return "防具鍛冶師";
		}
		case 17 -> {
			return "杖職人";
		}
		case 18 -> {
			return "教授";
		}

		}
		return "無職";
	}

	public String jobskil(Player player, int job) {
		int ex = 0;
		switch (job) {
		case 1 -> {
			ex = player.getJob_sworder();
		}
		case 2 -> {
			ex = player.getJob_spearer();
		}
		case 3 -> {
			ex = player.getJob_archer();
		}
		case 4 -> {
			ex = player.getJob_katanaer();
		}
		case 5 -> {
			ex = player.getJob_axer();
		}
		case 6 -> {
			ex = player.getJob_melleter();
		}
		case 7 -> {
			ex = player.getJob_cluber();
		}
		case 8 -> {
			ex = player.getJob_black_magician();
		}
		case 9 -> {
			ex = player.getJob_shielder();
		}
		case 10 -> {
			ex = player.getJob_magician();
		}
		case 11 -> {
			ex = player.getJob_white_magician();
		}
		case 12 -> {
			ex = player.getJob_commander();
		}
		case 13 -> {
			ex = player.getJob_blacksmith();
		}
		case 14 -> {
			ex = player.getJob_white_magician();
		}
		case 15 -> {
			ex = player.getJob_katana_blacksmith();
		}
		case 16 -> {
			ex = player.getJob_armor_blacksmith();
		}
		case 17 -> {
			ex = player.getJob_cane_maker();
		}
		case 18 -> {
			ex = player.getJob_teacher();
		}
		}
		if (ex < 10) {
			return Experience.FRESH.getName();
		} else if (ex < 30) {
			return Experience.TRAINED.getName();
		} else if (ex < 60) {
			return Experience.REGULAR.getName();
		} else if (ex < 100) {
			return Experience.SEASONED.getName();
		} else {
			return Experience.VETERANS.getName();
		}

	}

	public int max_hp(Player player) {
		return player.getStamina() * player.getStamina() * player.getMuscle();
	}

	public int max_mp(Player player) {
		return player.getMagic() * player.getMagic() * player.getTechnique();
	}

	public int max_fp(Player player) {
		return player.getEating() * player.getEating() * player.getQuick();
	}

	public int max_cp(Player player) {
		return player.getSpirit() * player.getSpirit() * player.getKnowledge();
	}

	public void jobskilint(Player player, int job) {
		int ex = 0;
		switch (job) {
		case 1 -> {
			ex = player.getJob_sworder();
		}
		case 2 -> {
			ex = player.getJob_spearer();
		}
		case 3 -> {
			ex = player.getJob_archer();
		}
		case 4 -> {
			ex = player.getJob_katanaer();
		}
		case 5 -> {
			ex = player.getJob_axer();
		}
		case 6 -> {
			ex = player.getJob_melleter();
		}
		case 7 -> {
			ex = player.getJob_cluber();
		}
		case 8 -> {
			ex = player.getJob_black_magician();
		}
		case 9 -> {
			ex = player.getJob_shielder();
		}
		case 10 -> {
			ex = player.getJob_magician();
		}
		case 11 -> {
			ex = player.getJob_white_magician();
		}
		case 12 -> {
			ex = player.getJob_commander();
		}
		case 13 -> {
			ex = player.getJob_blacksmith();
		}
		case 14 -> {
			ex = player.getJob_white_magician();
		}
		case 15 -> {
			ex = player.getJob_katana_blacksmith();
		}
		case 16 -> {
			ex = player.getJob_armor_blacksmith();
		}
		case 17 -> {
			ex = player.getJob_cane_maker();
		}
		case 18 -> {
			ex = player.getJob_teacher();
		}
		}
		if (ex < 10) {

		} else if (ex < 19) {
			ex = 10;
		} else {
			ex -= 10;
		}
		switch (job) {
		case 1 -> {
			player.setJob_sworder(ex);
		}
		case 2 -> {
			player.setJob_spearer(ex);
		}
		case 3 -> {
			player.setJob_archer(ex);
		}
		case 4 -> {
			player.setJob_katanaer(ex);
		}
		case 5 -> {
			player.setJob_axer(ex);
		}
		case 6 -> {
			player.setJob_melleter(ex);
		}
		case 7 -> {
			player.setJob_cluber(ex);
		}
		case 8 -> {
			player.setJob_black_magician(ex);
		}
		case 9 -> {
			player.setJob_shielder(ex);
		}
		case 10 -> {
			player.setJob_magician(ex);
		}
		case 11 -> {
			player.setJob_white_magician(ex);
		}
		case 12 -> {
			player.setJob_commander(ex);
		}
		case 13 -> {
			player.setJob_blacksmith(ex);
		}
		case 14 -> {
			player.setJob_white_magician(ex);
		}
		case 15 -> {
			player.setJob_katana_blacksmith(ex);
		}
		case 16 -> {
			player.setJob_armor_blacksmith(ex);
		}
		case 17 -> {
			player.setJob_cane_maker(ex);
		}
		case 18 -> {
			player.setJob_teacher(ex);
		}
		}

	}

	public void changeJob(Player player, int job) {
		for (int i = 1; i < 19; i++) {
			if (i == job) {

			} else {
				jobskilint(player, i);
			}
		}
	}

	public void playertraining(Player player, int a, RedirectAttributes redirectAttributes, Party party) {
		addjobskilint(player, player.getJob());
		int x = new java.util.Random().nextInt(10);

		switch (a) {
		case 1 -> {
			if ((x > 3) && (player.getMax_muscle() - player.getMuscle() > 0)) {
				redirectAttributes.addFlashAttribute("successMessage", "筋力の訓練をし、成長しました。");
				player.setMuscle(player.getMuscle() + 1);
			} else {
				redirectAttributes.addFlashAttribute("successMessage", "筋力の訓練をしました。");
			}
		}
		case 2 -> {
			if ((x > 3) && (player.getMax_quick() - player.getQuick() > 0)) {
				redirectAttributes.addFlashAttribute("successMessage", "俊敏の訓練をし、成長しました。");
				player.setQuick(player.getQuick() + 1);
			} else {
				redirectAttributes.addFlashAttribute("successMessage", "俊敏の訓練をしました。");
			}
		}
		case 3 -> {
			if ((x > 3) && (player.getMax_technique() - player.getTechnique() > 0)) {
				redirectAttributes.addFlashAttribute("successMessage", "技術の訓練をし、成長しました。");
				player.setTechnique(player.getTechnique() + 1);
			} else {
				redirectAttributes.addFlashAttribute("successMessage", "技術の訓練をしました。");
			}
		}
		case 4 -> {
			if ((x > 3) && (player.getMax_magic() - player.getMagic() > 0)) {
				redirectAttributes.addFlashAttribute("successMessage", "魔術の訓練をし、成長しました。");
				player.setMagic(player.getMagic() + 1);
			} else {
				redirectAttributes.addFlashAttribute("successMessage", "魔術の訓練をしました。");
			}
		}
		case 5 -> {
			if ((x > 3) && (player.getMax_spirit() - player.getSpirit() > 0)) {
				redirectAttributes.addFlashAttribute("successMessage", "精神の訓練をし、成長しました。");
				player.setSpirit(player.getSpirit() + 1);
			} else {
				redirectAttributes.addFlashAttribute("successMessage", "精神の訓練をしました。");
			}
		}
		case 6 -> {
			int c = new java.util.Random().nextInt(64);
			double b = nullcheckerteacher(party.getPlayer1()) + nullcheckerteacher(party.getPlayer2())
					+ nullcheckerteacher(party.getPlayer3())
					+ nullcheckerteacher(party.getPlayer4()) + nullcheckerteacher(party.getPlayer5())
					+ nullcheckerteacher(party.getPlayer6())
					+ nullcheckerteacher(party.getPlayer7()) + nullcheckerteacher(party.getPlayer8());
			if ((c < b) && (player.getMax_knowledge() - player.getKnowledge() > 0)) {
				redirectAttributes.addFlashAttribute("successMessage", "体力の訓練をし、成長しました。");
			} else {
				redirectAttributes.addFlashAttribute("successMessage", "学力の訓練をしました。");
			}
		}

		//		{
		//			if ((x > 3) && (player.getMax_knowledge() - player.getKnowledge() > 0)) {
		//				redirectAttributes.addFlashAttribute("successMessage", "学力の訓練をし、成長しました。");
		//				player.setKnowledge(player.getKnowledge() + 1);
		//			} else {
		//				redirectAttributes.addFlashAttribute("successMessage", "学力の訓練をしました。");
		//			}
		//		}
		//		
		case 7 -> {
			if ((x > 3) && (player.getMax_stamina() - player.getStamina() > 0)) {
				redirectAttributes.addFlashAttribute("successMessage", "体力の訓練をし、成長しました。");
				player.setStamina(player.getStamina() + 1);
			} else {
				redirectAttributes.addFlashAttribute("successMessage", "体力の訓練をしました。");
			}
		}
		case 8 -> {
			if ((x > 3) && (player.getMax_eating() - player.getEating() > 0)) {
				redirectAttributes.addFlashAttribute("successMessage", "食力の訓練をし、成長しました。");
				player.setEating(player.getEating() + 1);
			} else {
				redirectAttributes.addFlashAttribute("successMessage", "食力の訓練をしました。");
			}
		}
		}

	}

	public void addjobskilint(Player player, int job) {
		switch (job) {
		case 1 -> {
			player.setJob_sworder(player.getJob_sworder() + 5);
		}
		case 2 -> {
			player.setJob_spearer(player.getJob_spearer() + 5);
		}
		case 3 -> {
			player.setJob_archer(player.getJob_archer() + 5);
		}
		case 4 -> {
			player.setJob_katanaer(player.getJob_katanaer() + 5);
		}
		case 5 -> {
			player.setJob_axer(player.getJob_axer() + 5);
		}
		case 6 -> {
			player.setJob_melleter(player.getJob_melleter() + 5);
		}
		case 7 -> {
			player.setJob_cluber(player.getJob_cluber() + 5);
		}
		case 8 -> {
			player.setJob_black_magician(player.getJob_black_magician() + 5);
		}
		case 9 -> {
			player.setJob_shielder(player.getJob_shielder() + 5);
		}
		case 10 -> {
			player.setJob_magician(player.getJob_magician() + 5);
		}
		case 11 -> {
			player.setJob_white_magician(player.getJob_white_magician() + 5);
		}
		case 12 -> {
			player.setJob_commander(player.getJob_commander() + 5);
		}
		case 13 -> {
			player.setJob_blacksmith(player.getJob_blacksmith() + 5);
		}
		case 14 -> {
			player.setJob_white_magician(player.getJob_white_magician() + 5);
		}
		case 15 -> {
			player.setJob_katana_blacksmith(player.getJob_katana_blacksmith() + 5);
		}
		case 16 -> {
			player.setJob_armor_blacksmith(player.getJob_armor_blacksmith() + 5);
		}
		case 17 -> {
			player.setJob_cane_maker(player.getJob_cane_maker() + 5);
		}
		case 18 -> {
			player.setJob_teacher(player.getJob_teacher() + 5);
		}
		}
	}

	public Boolean party(Integer i1, Integer i2, Integer i3, Integer i4, Integer i5, Integer i6, Integer i7,
			Integer i8) {
		Integer[] i = new Integer[] { i1, i2, i3, i4, i5, i6, i7, i8 };
		for (int n = 0; n < 8; n++) {
			if (i[n] != null) {

				for (int m = 0; m < 8; m++) {
					if (n != m && i[m] != null) {

						if (i[n].equals(i[m])) {

							return false;
						} else {

						}
					}
				}
			}
		}
		return true;

	}

	public double nullcheckerteacher(Player player) {
		if (player == null) {
			return 0;
		} else {
			player.setJob_teacher(player.getJob_teacher() + 5);
			return player.getKnowledge() * ex(player.getJob_teacher());
		}
	}

	public double ex(int x) {
		if (x < 10) {
			return 0.2;
		} else if (x < 30) {
			return 0.4;
		} else if (x < 60) {
			return 0.6;
		} else if (x < 100) {
			return 0.8;
		} else
			return 1.0;
	}

	public String Item_kind(int x) {
		switch (x) {
		case 1 -> {
			return "剣";
		}
		case 2 -> {
			return "槍";
		}
		case 3 -> {
			return "弓";
		}
		case 4 -> {
			return "刀";
		}
		case 5 -> {
			return "斧";
		}
		case 6 -> {
			return "槌";
		}
		case 7 -> {
			return "棍棒";
		}
		case 8 -> {
			return "杖";
		}
		case 9 -> {
			return "盾";
		}
		case 10 -> {
			return "防具";
		}
		}
		return "素手";
	}

	public String Item_strong(int x) {
		switch (x) {
		case 1 -> {
			return "1936年式";
		}
		case 2 -> {
			return "1938年式";
		}
		case 3 -> {
			return "1940年式";
		}
		case 4 -> {
			return "1942年式";
		}
		case 5 -> {
			return "1944年式";
		}

		}
		return "1918年式";
	}

	public int jobitem(Integer integer) {
		switch (integer) {
		case 1 -> {
			return 1;
		}
		case 2 -> {
			return 2;
		}
		case 3 -> {
			return 3;
		}
		case 4 -> {
			return 4;
		}
		case 5 -> {
			return 5;
		}
		case 6 -> {
			return 6;
		}
		case 7 -> {
			return 7;
		}
		case 8, 10, 11 -> {
			return 8;
		}
		case 9 -> {
			return 9;
		}
		default -> {
			return 11;
		}

		}
	}

	public int jobarmor(Integer integer) {
		if (integer <= 12) {
			return 10;
		}
		return 11;
	}

	public void blacksmith(Player player, PlayerRepository playerRepository) {
		if (player != null) {
			player.setJob_blacksmith(player.getJob_blacksmith() + 5);
			playerRepository.save(player);
		}

	}

	public void arrow(Player player, PlayerRepository playerRepository) {
		if (player != null) {
			player.setJob_arrow_master(player.getJob_arrow_master() + 5);
			playerRepository.save(player);
		}

	}

	public void katana(Player player, PlayerRepository playerRepository) {
		if (player != null) {
			player.setJob_katana_blacksmith(player.getJob_katana_blacksmith() + 5);
			playerRepository.save(player);
		}

	}

	public void armor(Player player, PlayerRepository playerRepository) {
		if (player != null) {
			player.setJob_armor_blacksmith(player.getJob_armor_blacksmith() + 5);
			playerRepository.save(player);
		}

	}

	public void cane(Player player, PlayerRepository playerRepository) {
		if (player != null) {
			player.setJob_cane_maker(player.getJob_cane_maker() + 5);
			playerRepository.save(player);
		}

	}

	public String time(int x) {
		int y = x / 15;
		int z = (x % 15) + 6;
		String a = "";
		switch (y) {
		case 0 -> {
			a = "月曜日";
		}
		case 1 -> {
			a = "火曜日";
		}
		case 2 -> {
			a = "水曜日";
		}
		case 3 -> {
			a = "木曜日";
		}
		case 4 -> {
			a = "金曜日";
		}
		case 5 -> {
			a = "土曜日";
		}
		case 6 -> {
			a = "日曜日";
		}
		}
		return a + z + ":00";

	}

	public void time(User_info user_info, int add, User_infoRepository user_infoRepository, Page<Player> player,
			PlayerRepository playerRepository) {
		if (user_info.getTime() + add >= 105) {
			user_info.setTime(user_info.getTime() + add - 105);
			long x = player.getTotalElements() * 250;
			int y = (int) x;
			user_info.setMoney(user_info.getMoney() - y);
		} else {
			user_info.setTime(user_info.getTime() + add);
		}
		for (Player i : player) {
			if (user_info.getMoney() > 0) {
				Add_Fluctuation_Status(i, 3, add);
			}
			Add_Fluctuation_Status(i, 1, add);
			Add_Fluctuation_Status(i, 2, add);
			Add_Fluctuation_Status(i, 4, add);
		}

	}

	public int H(Player player) {
		return player.getStamina() * player.getStamina() * player.getMuscle();
	}

	public int M(Player player) {
		return player.getMagic() * player.getMagic() * player.getTechnique();
	}

	public int F(Player player) {
		return player.getEating() * player.getEating() * player.getQuick();
	}

	public int C(Player player) {
		return player.getSpirit() * player.getSpirit() * player.getKnowledge();
	}

}
