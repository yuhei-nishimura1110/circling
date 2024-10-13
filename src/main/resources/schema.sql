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

