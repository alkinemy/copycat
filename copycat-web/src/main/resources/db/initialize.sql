CREATE DATABASE IF NOT EXISTS copycat DEFAULT CHARACTER SET utf8;

CREATE USER 'copycat'@'%' IDENTIFIED BY 'copycat';
GRANT ALL PRIVILEGES ON copycat.* TO 'copycat'@'%';
FLUSH PRIVILEGES;