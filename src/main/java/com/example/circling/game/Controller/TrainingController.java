package com.example.circling.game.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.circling.circling.entity.User;
import com.example.circling.circling.repository.UserRepository;
import com.example.circling.game.Forn.ChangeJob;
import com.example.circling.game.Forn.ItemForm;
import com.example.circling.game.Forn.PlayerNameEditForm;
import com.example.circling.game.Forn.Playertrainingform;
import com.example.circling.game.Repository.ItemRepository;
import com.example.circling.game.Repository.PartyRepository;
import com.example.circling.game.Repository.PlayerRepository;
import com.example.circling.game.Repository.User_infoRepository;
import com.example.circling.game.Service.Transformation;
import com.example.circling.game.entity.Item;
import com.example.circling.game.entity.Party;
import com.example.circling.game.entity.Player;
import com.example.circling.game.entity.User_info;
import com.example.circling.security.UserDetailsImpl;

@RequestMapping("/game/home/training")
@Controller
public class TrainingController {
	private final UserRepository userRepository;
	private final PlayerRepository playerRepository;
	private final PartyRepository partyRepository;
	private final ItemRepository itemRepository;
	private final User_infoRepository user_infoRepository;

	public TrainingController(UserRepository userRepository, PlayerRepository playerRepository,
			PartyRepository partyRepository, ItemRepository itemRepository, User_infoRepository user_infoRepository) {
		this.userRepository = userRepository;
		this.playerRepository = playerRepository;
		this.partyRepository = partyRepository;
		this.itemRepository = itemRepository;
		this.user_infoRepository = user_infoRepository;

	}

	@GetMapping()
	public String training(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model,
			Transformation transformation,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("user_info", user_info);
		Page<Player> playerPage = playerRepository.findByUser_id(user.getId(), pageable);
		model.addAttribute("playerPage", playerPage);
		model.addAttribute("transformation", transformation);
		System.out.println(user.getName() + "成長・転職画面");
		return "game/training/training";
	}

	@GetMapping("/{id}")
	public String show(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable(name = "id") Integer id,
			Model model, Transformation transformation) {
		Player player = playerRepository.getReferenceById(id);
		model.addAttribute("player", player);
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("user_info", user_info);
		model.addAttribute("transformation", transformation);
		System.out.println(user.getName() + "詳細画面");
		return "game/training/show";
	}

	@GetMapping("/{id}/nameEdit")
	public String nameEdit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PathVariable(name = "id") Integer id, Model model, Transformation transformation) {
		Player player = playerRepository.getReferenceById(id);
		model.addAttribute("player", player);
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("user_info", user_info);
		PlayerNameEditForm playerNameEditForm = new PlayerNameEditForm(player.getName());
		model.addAttribute("playerNameEditForm", playerNameEditForm);
		model.addAttribute("transformation", transformation);
		System.out.println(user.getName() + "名前編集画面");
		return "game/training/nameEdit";
	}

	@PostMapping("/{id}/nameEdited")
	public String nameEdited(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@ModelAttribute @Validated PlayerNameEditForm playerNameEditForm,
			@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes,
			Transformation transformation) {
		Player player = playerRepository.getReferenceById(id);
		model.addAttribute("player", player);
		player.setName(playerNameEditForm.getName());
		playerRepository.save(player);
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		System.out.println(user.getName() + "名前を編集");
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("user_info", user_info);
		model.addAttribute("playerNameEditForm", playerNameEditForm);
		model.addAttribute("transformation", transformation);
		redirectAttributes.addFlashAttribute("successMessage", "冒険者の名前の変更に成功しました。");
		return "game/training/nameEdit";
	}

	@GetMapping("/{id}/changejob")
	public String changeJob(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PathVariable(name = "id") Integer id, Model model, Transformation transformation) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		System.out.println(user.getName() + "転職画面");
		Player player = playerRepository.getReferenceById(id);
		model.addAttribute("player", player);
		ChangeJob changeJob = new ChangeJob(player.getJob());
		model.addAttribute("changeJob", changeJob);

		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("user_info", user_info);
		model.addAttribute("transformation", transformation);
		return "game/training/changeJob";

	}

	@PostMapping("/{id}/changejobed")
	public String changeJob(@ModelAttribute @Validated ChangeJob changeJob, @PathVariable(name = "id") Integer id,
			Model model, Transformation transformation, RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PageableDefault(size = 1000) Pageable pe) {
		Player player = playerRepository.getReferenceById(id);
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		System.out.println(user.getName() + "転職開始");
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("player", player);
		model.addAttribute("transformation", transformation);

		player.setJob(changeJob.getJob());
		transformation.changeJob(player, changeJob.getJob());
		playerRepository.save(player);
		model.addAttribute("changeJob", changeJob);
		user_info.setMoney(user_info.getMoney() - 1000);
		Page<Player> players = playerRepository.findByUser_id(user.getId(), pe);
		transformation.time(user_info, 5, user_infoRepository, players, playerRepository);
		user_infoRepository.save(user_info);
		model.addAttribute("user_info", user_info);
		System.out.println(user.getName() + "転職完");
		redirectAttributes.addFlashAttribute("successMessage", "冒険者の転職に成功しました。");

		return "game/training/changeJob";
	}

	@GetMapping("/{id}/playertraining")
	public String playertraining(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PathVariable(name = "id") Integer id, Model model, Transformation transformation) {
		Player player = playerRepository.getReferenceById(id);
		model.addAttribute("player", player);
		Playertrainingform playertrainingform = new Playertrainingform(player.getJob());
		model.addAttribute("playertrainingform", playertrainingform);
		model.addAttribute("transformation", transformation);
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		System.out.println(user.getName() + "訓練画面");
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("user_info", user_info);
		return "game/training/playertraining";
	}

	@PostMapping("/{id}/playertraininged")
	public String plaertraininga(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PathVariable(name = "id") Integer id, Model model, Transformation transformation,
			@ModelAttribute @Validated Playertrainingform playertrainingform, RedirectAttributes redirectAttributes,
			@PageableDefault(size = 1000) Pageable pe) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Party party = partyRepository.findByUser_idAndCount(user.getId(), 7);
		Player player = playerRepository.getReferenceById(id);
		System.out.println(user.getName() + "訓練開始");
		model.addAttribute("player", player);
		transformation.playertraining(player, playertrainingform.getTraining(), redirectAttributes, party);
		playerRepository.save(player);
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		user_info.setMoney(user_info.getMoney() - 500);
		Page<Player> players = playerRepository.findByUser_id(user.getId(), pe);
		transformation.time(user_info, 2, user_infoRepository, players, playerRepository);
		user_infoRepository.save(user_info);
		model.addAttribute("transformation", transformation);
		model.addAttribute("user_info", user_info);
		System.out.println(user.getName() + "訓練完");
		return "redirect:/home/training/{id}/playertraining";
	}

	@GetMapping("/{id}/item")
	public String Items(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable(name = "id") Integer id,
			Model model, Transformation transformation,
			Pageable pageable) {
		Player player = playerRepository.getReferenceById(id);
		model.addAttribute("player", player);
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		System.out.println(user.getName() + "アイテム装着");
		Item nowitem1 = itemRepository.findByPlayer_idAndItemkindNot(player.getId(), 10);
		Item nowitem2 = itemRepository.findByPlayer_idAndItemkind(player.getId(), 10);
		model.addAttribute("nowitem1", nowitem1);
		model.addAttribute("nowitem2", nowitem2);
		Page<Item> itemPage1 = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
				null, transformation.jobitem(player.getJob()),
				pageable);
		Page<Item> itemPage2 = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
				null, transformation.jobarmor(player.getJob()),
				pageable);
		model.addAttribute("itemPage1", itemPage1);
		model.addAttribute("itemPage2", itemPage2);
		int item1 = 0;
		int item2 = 0;
		if (player.getJob() == 12) {
			item1 = 1;
			item2 = 2;
		} else if (player.getJob() > 12) {
			item1 = 2;
			item2 = 2;
		} else if (player.getJob() < 12) {
			item1 = 1;
			item2 = 1;
		}
		model.addAttribute("item1", item1);
		model.addAttribute("item2", item2);
		model.addAttribute("transformation", transformation);
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("user_info", user_info);
		ItemForm itemForm = new ItemForm(null, null, null, null);
		return "game/training/item";
	}

	@PostMapping("/{id}/item/ed")
	public String Itemsed(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PathVariable(name = "id") Integer id,
			Model model, Transformation transformation,
			Pageable pageable, @ModelAttribute @Validated ItemForm itemForm) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		System.out.println(user.getName() + "アイテム装着開始");
		Player player = playerRepository.getReferenceById(id);
		model.addAttribute("player", player);
		Item nowitem1 = itemRepository.findByPlayer_idAndItemkindNot(player.getId(), 10);
		Item nowitem2 = itemRepository.findByPlayer_idAndItemkind(player.getId(), 10);
		System.out.println(user.getName() + "アイテム外す");
		if (nowitem1 != null) {
			nowitem1.setPlayer(null);
			itemRepository.save(nowitem1);
		}
		if (nowitem2 != null) {
			nowitem2.setPlayer(null);
			itemRepository.save(nowitem2);
		}
		System.out.println(user.getName() + "名前をセット");
		if (itemForm.getPlayer_id() != null) {
			Item item = itemRepository.getReferenceById(itemForm.getPlayer_id());
			item.setPlayer(player);
			itemRepository.save(item);
		}
		if (itemForm.getUser_id() != null) {
			Item itemarmor = itemRepository.getReferenceById(itemForm.getUser_id());
			itemarmor.setPlayer(player);
			itemRepository.save(itemarmor);
		}
		System.out.println(user.getName() + "鍛冶師たちの育成");
		if (player.getJob() != null) {
			Page<Item> itemPage1 = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					null, transformation.jobitem(player.getJob()),
					pageable);
			model.addAttribute("itemPage1", itemPage1);

			Page<Item> itemPage2 = itemRepository.findByUser_idAndPlayer_idAndItemkind(user.getId(),
					null, transformation.jobarmor(player.getJob()),
					pageable);

			model.addAttribute("itemPage2", itemPage2);
		}
		System.out.println(user.getName() + "現在のアイテムの再セット");
		nowitem1 = itemRepository.findByPlayer_idAndItemkindNot(player.getId(), 10);
		nowitem2 = itemRepository.findByPlayer_idAndItemkind(player.getId(), 10);

		model.addAttribute("nowitem1", nowitem1);
		model.addAttribute("nowitem2", nowitem2);
		int item1 = 0;
		int item2 = 0;
		if (player.getJob() == 12) {
			item1 = 1;
			item2 = 2;
		} else if (player.getJob() > 12) {
			item1 = 2;
			item2 = 2;
		} else if (player.getJob() < 12) {
			item1 = 1;
			item2 = 1;
		}
		model.addAttribute("item1", item1);
		model.addAttribute("item2", item2);
		model.addAttribute("transformation", transformation);
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("user_info", user_info);
		ItemForm itemForm2 = new ItemForm(null, null, null, null);
		System.out.println(user.getName() + "アイテム装着完");
		return "game/training/item";
	}

	@GetMapping("/{id}/delete")
	public String delete(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PathVariable(name = "id") Integer id,
			Model model, Transformation transformation) {
		Player player = playerRepository.getReferenceById(id);
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		model.addAttribute("user_info", user_info);
		model.addAttribute("transformation", transformation);
		model.addAttribute("player", player);
		System.out.println(user.getName() + "プレイヤーの削除");
		return "game/training/drop";
	}

	@PostMapping("/{id}/deleted")
	public String deleted(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PathVariable(name = "id") Integer id,
			RedirectAttributes redirectAttributes) {
		Player player = playerRepository.getReferenceById(id);
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		System.out.println(user.getName() + "プレイヤーの削除開始");
		User_info user_info = user_infoRepository.findByUser_id(user.getId());
		playerRepository.delete(player);
		user_info.setMoney(user_info.getMoney() - 5000);
		user_infoRepository.save(user_info);
		redirectAttributes.addFlashAttribute("successMessage", "戦闘員を退職金を支払って解雇しました。");
		System.out.println(user.getName() + "プレイヤーの削除完");
		return "redirect:/home/training";
	}

}
