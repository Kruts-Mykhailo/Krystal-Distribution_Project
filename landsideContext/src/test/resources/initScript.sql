CREATE
    DATABASE landside;
GRANT ALL
    ON landside.* TO 'root'@'%';
GRANT SHOW
    DATABASES ON *.* TO 'root'@'%';
FLUSH
    PRIVILEGES;
