package com.example.circling.game.Scout;

import org.springframework.stereotype.Service;

import com.example.circling.circling.entity.User;
import com.example.circling.game.Repository.PlayerRepository;
import com.example.circling.game.Service.Transformation;
import com.example.circling.game.entity.Item;
import com.example.circling.game.entity.Player;
import com.example.circling.game.enumset.Item_Strong;
import com.example.circling.game.enumset.Job;

@Service
public class Scout {

	public int incaroamt_status() {
		int random_Status = new java.util.Random().nextInt(255) + 1;
		int count = 1;
		while (random_Status % 2 == 0) {
			count++;
			random_Status /= 2;
		}
		return count;
	}

	public int max_invariant_status(int a) {
		int x = new java.util.Random().nextInt(10);
		switch (a) {
		case 1 -> {
			switch (x) {
			case 0 -> {
				return 2;
			}
			case 1, 2, 3, 4 -> {
				return 3;
			}
			default -> {
				return 4;
			}
			}
		}
		case 2 -> {
			switch (x) {
			case 0, 1 -> {
				return 3;
			}
			case 2, 3, 4, 5 -> {
				return 4;
			}
			default -> {
				return 5;
			}
			}
		}
		case 3 -> {
			switch (x) {
			case 0, 1, 2 -> {
				return 4;
			}
			case 3, 4, 5 -> {
				return 6;
			}
			default -> {
				return 5;
			}
			}
		}
		case 4 -> {
			switch (x) {
			case 0, 1 -> {
				return 7;
			}
			case 3, 4, 5 -> {
				return 6;
			}
			default -> {
				return 5;
			}
			}
		}
		case 5 -> {
			switch (x) {
			case 0 -> {
				return 8;
			}
			case 4, 5 -> {
				return 7;
			}
			default -> {
				return 6;
			}
			}
		}
		case 6 -> {
			switch (x) {
			case 0 -> {
				return 8;
			}

			default -> {
				return 7;
			}
			}
		}
		case 7 -> {
			return 8;
		}
		case 8 -> {
			return 8;
		}
		}
		return 4;
	}

	public Player Scouta(String name, User user, PlayerRepository playerRepository, Transformation transformation) {
		Player player = new Player();
		player.setName(name);

		//User user=userRepository.getReferenceById(scoutForm.getUser_id());
		//User users = new User();
		player.setUser(user);

		player.setMuscle(incaroamt_status());
		player.setQuick(incaroamt_status());
		player.setTechnique(incaroamt_status());
		player.setMagic(incaroamt_status());
		player.setSpirit(incaroamt_status());
		player.setKnowledge(incaroamt_status());
		player.setStamina(incaroamt_status());
		player.setEating(incaroamt_status());

		playerRepository.save(player);

		player.setMax_muscle(max_invariant_status(player.getMuscle()));
		player.setMax_quick(max_invariant_status(player.getQuick()));
		player.setMax_technique(max_invariant_status(player.getTechnique()));
		player.setMax_magic(max_invariant_status(player.getMagic()));
		player.setMax_spirit(max_invariant_status(player.getSpirit()));
		player.setMax_stamina(max_invariant_status(player.getStamina()));
		player.setMax_knowledge(8);
		player.setMax_eating(max_invariant_status(player.getEating()));

		int result = new java.util.Random().nextInt(100) + 1;
		result = new java.util.Random().nextInt(result);
		int probability = new java.util.Random().nextInt(10);
		int probability1 = new java.util.Random().nextInt(10);
		int probability2 = new java.util.Random().nextInt(2);

		player.setJob_sworder(0);
		player.setJob_spearer(0);
		player.setJob_archer(0);
		player.setJob_katanaer(0);
		player.setJob_axer(0);
		player.setJob_melleter(0);
		player.setJob_cluber(0);
		player.setJob_black_magician(0);
		player.setJob_shielder(0);
		player.setJob_magician(0);
		player.setJob_white_magician(0);
		player.setJob_commander(0);
		player.setJob_blacksmith(0);
		player.setJob_arrow_master(0);
		player.setJob_katana_blacksmith(0);
		player.setJob_armor_blacksmith(0);
		player.setJob_cane_maker(0);
		player.setJob_teacher(0);

		switch (probability) {
		case 0 -> {
			player.setJob_sworder(result);
			player.setJob(Job.SWORDER.getId());

			Item item = new Item();
			item.setItemkind(com.example.circling.game.enumset.Item.SWORD.getId());
			item.setItemstrong(Item_Strong.type_1918.getId());
		}
		case 1 -> {
			player.setJob_archer(result);
			player.setJob(Job.ARCHER.getId());

			Item item = new Item();
			item.setItemkind(com.example.circling.game.enumset.Item.ARCH.getId());
			item.setItemstrong(Item_Strong.type_1918.getId());
		}
		case 2 -> {
			player.setJob_black_magician(result);
			player.setJob(Job.BLACK_MAGICIAN.getId());

			Item item = new Item();
			item.setItemkind(com.example.circling.game.enumset.Item.CANE.getId());
			item.setItemstrong(Item_Strong.type_1918.getId());
		}
		case 3 -> {
			player.setJob_shielder(result);
			player.setJob(Job.SHIELDER.getId());

			Item item = new Item();
			item.setItemkind(com.example.circling.game.enumset.Item.SHIELD.getId());
			item.setItemstrong(Item_Strong.type_1918.getId());
		}
		case 4 -> {
			player.setJob_magician(result);
			player.setJob(Job.MAGICIAN.getId());

			Item item = new Item();
			item.setItemkind(com.example.circling.game.enumset.Item.CANE.getId());
			item.setItemstrong(Item_Strong.type_1918.getId());
		}
		case 5 -> {
			player.setJob_white_magician(result);
			player.setJob(Job.WHITE_MAGICIAN.getId());

			Item item = new Item();
			item.setItemkind(com.example.circling.game.enumset.Item.CANE.getId());
			item.setItemstrong(Item_Strong.type_1918.getId());
		}
		case 6 -> {
			switch (probability2) {
			case 0 -> {
				player.setJob_spearer(result);
				player.setJob(Job.SPEARER.getId());

				Item item = new Item();
				item.setItemkind(com.example.circling.game.enumset.Item.SPEAR.getId());
				item.setItemstrong(Item_Strong.type_1918.getId());

			}
			case 1 -> {
				player.setJob_axer(result);
				player.setJob(Job.AXER.getId());

				Item item = new Item();
				item.setItemkind(com.example.circling.game.enumset.Item.AX.getId());
				item.setItemstrong(Item_Strong.type_1918.getId());
			}
			}
		}
		case 7 -> {
			switch (probability2) {
			case 0 -> {
				player.setJob_melleter(result);
				player.setJob(Job.MELLETER.getId());

				Item item = new Item();
				item.setItemkind(com.example.circling.game.enumset.Item.MELLET.getId());
				item.setItemstrong(Item_Strong.type_1918.getId());
			}
			case 1 -> {
				player.setJob_cluber(result);
				player.setJob(Job.CLUBER.getId());

				Item item = new Item();
				item.setItemkind(com.example.circling.game.enumset.Item.CLUB.getId());
				item.setItemstrong(Item_Strong.type_1918.getId());
			}
			}
		}
		case 8 -> {
			switch (probability2) {
			case 0 -> {
				player.setJob_blacksmith(result);
				player.setJob(Job.BLACKSMITH.getId());
			}
			case 1 -> {
				player.setJob_arrow_master(result);
				player.setJob(Job.ARROW_MASTER.getId());
			}
			}
		}
		case 9 -> {
			switch (probability1) {
			case 0 -> {
				player.setJob_katana_blacksmith(result);
				player.setJob(Job.KATANA_BLACKSMITH.getId());
			}
			case 1, 2, 3 -> {
				player.setJob_teacher(result);
				player.setJob(Job.TEACHER.getId());
			}
			default -> {
				player.setJob_armor_blacksmith(result);
				player.setJob(Job.ARMOR_BLACKSMITH.getId());
			}
			}
		}

		}
		transformation.Initial_Fluctuation_Status(player);

		playerRepository.save(player);
		return player;
	}
}
