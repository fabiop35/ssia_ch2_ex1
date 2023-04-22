INSERT IGNORE INTO `spring`.`authorities`
 VALUES (NULL, 'john', 'write');

INSERT IGNORE INTO `spring`.`authorities`
VALUES (NULL, 'bill', 'read');

INSERT IGNORE INTO `spring`.`login_users` 
VALUES (NULL, 'john', '12345', '1');

INSERT IGNORE INTO `spring`.`login_users` 
VALUES (NULL, 'bill', '123', '1');
