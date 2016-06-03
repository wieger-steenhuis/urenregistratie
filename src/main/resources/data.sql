INSERT INTO `trainer` (`id`, `first_name`, `last_name`,username, `phone_nr`, `e_mail`, `password`,`enabled`) VALUES
  (1, 'Tessa', 'van Tuijl','tess', '06222551', 'tessa@sx.nl', '11111',1),
  (2, 'erwin', 'van Erp', 'er','06111145', 'erwin@sx.nl', '22222',1),
  (3, 'Judith', 'van SX', 'ju','06 - 25546518', 'judithvansx@sx.co.eu', 'test',1),
  (4, 'Henk', 'den Treenert','henk', '06-12635265', 'h.dentreenert@sx.co.uk', 'test',1),
  (5, 'Hendigen', 'Harry','har', '06-13235421', 'hendigen.h@sxsports.com', 'test',1);

INSERT INTO `customer` (`id`, `first_name`, `last_name`, `phone_nr`, `e_mail`, `pin`) VALUES
  (1, 'mark', 'UDR13','0625548221', 'udr013@gmail.com', '11111'),
  (2, 'Sjoerd', 'de Jong', '068877112', 'jannekeuytewaal@gmail.com', '77777'),
  (3, 'Felix', 'van Loenen', '06-11238652', 'felix@digitaleservice.com', '88751'),
  (4, 'Wieger', 'Steenhuis', '', 'wsteenhuis@gmail.com', ''),
  (5, 'Leandro', 'D''agostino', '651', 'a@b.c', '12358'),
  (6, 'Larisa', '', '069985742','larisa@programit.eu', '8888'),
  (7, 'Ger', 'Erades', '06-5246352', 'ger@programit.nl', '11111'),
  (8, 'Aaron', 'vd Akker', '', 'aaron@gmail.com', '');

INSERT INTO `subscription` (`id`, `customer_id`, `trainer_id`, `subscr_type`, `start_date`) VALUES
  (1, 5, 4, 'TWELVE', '2016-05-05');

INSERT INTO user_roles (username, role)
VALUES ('tess', 'TRAINER');
INSERT INTO user_roles (username, role)
VALUES ('ju', 'ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('hans', 'ADMIN');

INSERT INTO `sport_session` (`id`, `subscription_id`, `customer_id`, `trainer_id`, `date_time`) VALUES
  (1, 1, 5, 4, NULL),
  (2, 1, 5, 4, NULL),
  (3, 1, 5, 4, NULL),
  (4, 1, 5, 4, NULL),
  (5, 1, 5, 4, NULL),
  (6, 1, 5, 4, NULL),
  (7, 1, 5, 4, NULL),
  (8, 1, 5, 4, NULL),
  (9, 1, 5, 4, NULL),
  (10, 1, 5, 4, NULL),
  (11, 1, 5, 4, NULL),
  (12, 1, 5, 4, NULL);

