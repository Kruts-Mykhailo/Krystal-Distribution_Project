CREATE
DATABASE landside;
GRANT ALL
ON landside.* TO 'app_user'@'%';
GRANT SHOW
DATABASES ON *.* TO 'app_user'@'%';
FLUSH
PRIVILEGES;

CREATE
    DATABASE waterside;
GRANT ALL
    ON waterside.* TO 'app_user'@'%';
GRANT SHOW
    DATABASES ON *.* TO 'app_user'@'%';
FLUSH
    PRIVILEGES;

CREATE
    DATABASE warehouse;
GRANT ALL
    ON warehouse.* TO 'app_user'@'%';
GRANT SHOW
    DATABASES ON *.* TO 'app_user'@'%';
FLUSH
    PRIVILEGES;


CREATE
    DATABASE invoicing;
GRANT ALL
    ON invoicing.* TO 'app_user'@'%';
GRANT SHOW
    DATABASES ON *.* TO 'app_user'@'%';
FLUSH
    PRIVILEGES;