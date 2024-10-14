package com.example.circling.service;

import org.springframework.stereotype.Service;

import com.example.circling.entity.Party;
import com.example.circling.entity.Player;

@Service
public class ItemScout {

	public int itemscouta(Integer integer, Party party, Transformation transformation) {
		switch (integer) {
		case 1, 2, 5, 6, 7, 9 -> {
			int b = new java.util.Random().nextInt(64);

			double a = nullchekernbl(party.getPlayer1(), transformation)
					+ nullchekernbl(party.getPlayer2(), transformation)
					+ nullchekernbl(party.getPlayer3(), transformation)
					+ nullchekernbl(party.getPlayer4(), transformation)
					+ nullchekernbl(party.getPlayer5(), transformation)
					+ nullchekernbl(party.getPlayer6(), transformation)
					+ nullchekernbl(party.getPlayer7(), transformation)
					+ nullchekernbl(party.getPlayer8(), transformation);
			if (a > b && a > 60) {
				return 5;
			} else if (a > 60 || (a > b && a > 32)) {
				return 4;
			} else if (a > 32 || (a > b && a > 16)) {
				return 3;
			} else if (a > 16 || a > b) {
				return 2;
			} else {
				return 1;
			}
		}
		case 3 -> {
			int b = new java.util.Random().nextInt(64);

			double a = nullchekerarr(party.getPlayer1(), transformation)
					+ nullchekerarr(party.getPlayer2(), transformation)
					+ nullchekerarr(party.getPlayer3(), transformation)
					+ nullchekerarr(party.getPlayer4(), transformation)
					+ nullchekerarr(party.getPlayer5(), transformation)
					+ nullchekerarr(party.getPlayer6(), transformation)
					+ nullchekerarr(party.getPlayer7(), transformation)
					+ nullchekerarr(party.getPlayer8(), transformation);
			if (a > b && a > 60) {
				return 5;
			} else if (a > 60 || (a > b && a > 32)) {
				return 4;
			} else if (a > 32 || (a > b && a > 16)) {
				return 3;
			} else if (a > 16 || a > b) {
				return 2;
			} else {
				return 1;
			}
		}
		case 4 -> {
			int b = new java.util.Random().nextInt(64);

			double a = nullchekerkat(party.getPlayer1(), transformation)
					+ nullchekerkat(party.getPlayer2(), transformation)
					+ nullchekerkat(party.getPlayer3(), transformation)
					+ nullchekerkat(party.getPlayer4(), transformation)
					+ nullchekerkat(party.getPlayer5(), transformation)
					+ nullchekerkat(party.getPlayer6(), transformation)
					+ nullchekerkat(party.getPlayer7(), transformation)
					+ nullchekerkat(party.getPlayer8(), transformation);
			if (a > b && a > 60) {
				return 5;
			} else if (a > 60 || (a > b && a > 45)) {
				return 4;
			} else if (a > 45 || (a > b && a > 32)) {
				return 3;
			} else if (a > 32 || a > b) {
				return 2;
			} else {
				return 1;
			}
		}
		case 10 -> {
			int b = new java.util.Random().nextInt(64);

			double a = nullchekerarm(party.getPlayer1(), transformation)
					+ nullchekerarm(party.getPlayer2(), transformation)
					+ nullchekerarm(party.getPlayer3(), transformation)
					+ nullchekerarm(party.getPlayer4(), transformation)
					+ nullchekerarm(party.getPlayer5(), transformation)
					+ nullchekerarm(party.getPlayer6(), transformation)
					+ nullchekerarm(party.getPlayer7(), transformation)
					+ nullchekerarm(party.getPlayer8(), transformation);
			if (a > b && a > 60) {
				return 5;
			} else if (a > 60 || (a > b && a > 32)) {
				return 4;
			} else if (a > 32 || (a > b && a > 16)) {
				return 3;
			} else if (a > 16 || a > b) {
				return 2;
			} else {
				return 1;
			}
		}
		case 8 -> {
			int b = new java.util.Random().nextInt(64);

			double a = nullchekercan(party.getPlayer1(), transformation)
					+ nullchekercan(party.getPlayer2(), transformation)
					+ nullchekercan(party.getPlayer3(), transformation)
					+ nullchekercan(party.getPlayer4(), transformation)
					+ nullchekercan(party.getPlayer5(), transformation)
					+ nullchekercan(party.getPlayer6(), transformation)
					+ nullchekercan(party.getPlayer7(), transformation)
					+ nullchekercan(party.getPlayer8(), transformation);
			if (a > b && a > 60) {
				return 5;
			} else if (a > 60 || (a > b && a > 32)) {
				return 4;
			} else if (a > 32 || (a > b && a > 16)) {
				return 3;
			} else if (a > 16 || a > b) {
				return 2;
			} else {
				return 1;
			}
		}
		}
		return 1;
	}

	public double nullchekernbl(Player player, Transformation transformation) {
		if (player == null) {
			return 0;
		} else {
			return Math.cbrt(player.getMuscle() * player.getTechnique() * player.getSpirit())
					* transformation.ex(player.getJob_blacksmith());
		}

	}

	public double nullchekerarr(Player player, Transformation transformation) {
		if (player != null) {

			return Math.sqrt(
					Math.sqrt(player.getMuscle() * player.getTechnique() * player.getSpirit() * player.getSpirit()))
					* transformation.ex(player.getJob_arrow_master());
		} else {
			return 0;
		}

	}

	public double nullchekerarm(Player player, Transformation transformation) {
		if (player != null) {

			return Math.cbrt(player.getMuscle() * player.getTechnique() * player.getSpirit())
					* transformation.ex(player.getJob_armor_blacksmith());
		} else {
			return 0;
		}

	}

	public double nullchekercan(Player player, Transformation transformation) {
		if (player != null) {

			return Math.cbrt(player.getMagic() * player.getTechnique())
					* transformation.ex(player.getJob_cane_maker());
		} else {
			return 0;
		}

	}

	public double nullchekerkat(Player player, Transformation transformation) {
		if (player != null) {

			return Math.sqrt(
					Math.sqrt(player.getMuscle() * player.getTechnique() * player.getSpirit() * player.getMagic()))
					* transformation.ex(player.getJob_arrow_master());
		} else {
			return 0;
		}

	}
}
