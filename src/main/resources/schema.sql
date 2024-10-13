CREATE TABLE IF NOT EXISTS roles(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS users(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	role_id INT NOT NULL,
	FOREIGN KEY (role_id) REFERENCES roles (id),
	enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS verification_tokens (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     user_id INT NOT NULL UNIQUE,
     token VARCHAR(255) NOT NULL,        
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     FOREIGN KEY (user_id) REFERENCES users (id) 
 );
CREATE TABLE IF NOT EXISTS board(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),
	time DATETIME NOT NULL
);
CREATE TABLE IF NOT EXISTS chattable(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	board_id INT NOT NULL,
	FOREIGN KEY (board_id) REFERENCES board (id),
	user_id INT NOT NULL,
	time DATETIME NOT NULL,
	ktime DATETIME NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS chat(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	board_id INT NOT NULL,
	FOREIGN KEY (board_id) REFERENCES board (id),
	user_id INT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users (id),
	chat VARCHAR(255) NOT NULL,
	time DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS user_info(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT,
	FOREIGN KEY (user_id) REFERENCES users (id),
	time INT,
	money INT
);



CREATE TABLE IF NOT EXISTS player(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT,
	FOREIGN KEY (user_id) REFERENCES users (id),
	name VARCHAR(15),
	hp INT,
	mp INT,
	fp INT,
	cp INT,
	muscle INT,
	quick INT,
	technique INT,
	magic INT,
	spirit INT,
	knowledge INT,
	stamina INT,
	eating INT,
	max_muscle INT,
	max_quick INT,
	max_technique INT,
	max_magic INT,
	max_spirit INT,
	max_knowledge INT,
	max_stamina INT,
	max_eating INT,
	job INT,
	job_sworder INT,
	job_spearer INT,
	job_archer INT,
	job_katanaer INT,
	job_axer INT,
	job_melleter INT,
	job_cluber INT,
	job_black_magician INT,
	job_shielder INT,
	job_magician INT,
	job_white_magician INT,
	job_commander INT,
	job_blacksmith INT,
	job_arrow_master INT,
	job_katana_blacksmith INT,
	job_armor_blacksmith INT,
	job_cane_maker INT,
	job_teacher INT
	
);



CREATE TABLE IF NOT EXISTS party(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT,
	FOREIGN KEY (user_id) REFERENCES users (id),
	count INT NOT NULL,
	player_id1 INT,
	FOREIGN KEY (player_id1) REFERENCES player (id),
	player_id2 INT,
	FOREIGN KEY (player_id2) REFERENCES player (id),
	player_id3 INT,
	FOREIGN KEY (player_id3) REFERENCES player (id),
	player_id4 INT,
	FOREIGN KEY (player_id4) REFERENCES player (id),
	player_id5 INT,
	FOREIGN KEY (player_id5) REFERENCES player (id),
	player_id6 INT,
	FOREIGN KEY (player_id6) REFERENCES player (id),
	player_id7 INT,
	FOREIGN KEY (player_id7) REFERENCES player (id),
	player_id8 INT,
	FOREIGN KEY (player_id8) REFERENCES player (id)
);

CREATE TABLE IF NOT EXISTS item(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT,
	FOREIGN KEY (user_id) REFERENCES users (id),
	player_id INT,
	FOREIGN KEY (player_id) REFERENCES player (id),
	item_kind INT NOT NULL,
	item_strong INT NOT NULL
);

CREATE TABLE IF NOT EXISTS family_name(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(15)
);

