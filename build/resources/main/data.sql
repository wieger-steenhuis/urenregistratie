INSERT INTO `trainer` (`id`, `first_name`, `last_name`, `phone_nr`, `e_mail`, `pass_word`) VALUES
  (1, 'Tessa', 'van Tuijl', '06222551', 'tessa@sx.nl', NULL),
  (2, 'erwin', 'van Erp', '06111145', 'erwin@sx.nl', NULL),
  (3, 'Judith', 'van SX', '06 - 25546518', 'judithvansx@sx.co.eu', NULL),
  (4, 'Henk', 'den Treenert', '06-12635265', 'h.dentreenert@sx.co.uk', NULL),
  (5, 'Hendigen', 'Harry', '06-13235421', 'hendigen.h@sxsports.com', NULL);

INSERT INTO `customer` (`id`, `first_name`, `last_name`, `phone_nr`, `e_mail`, `pin`) VALUES
  (1, 'mark', 'UDR13', '0625548221', 'udr13@gmail.com', '1111'),
  (2, 'Sjoerd', 'de Jong', '068877112', 'jannekeuytewaal@gmail.com', '77777'),
  (3, 'Felix', 'van Loenen', '06-11238652', 'felix@digitaleservice.com', '88751'),
  (4, 'Wieger', 'Steenhuis', '', 'wsteenhuis@gmail.com', ''),
  (5, 'Leandro', 'D''agostino', '651', 'a@b.c', '12358'),
  (6, 'Larisa', '', '069985742', 'larisa@programit.eu', '8888'),
  (7, 'Ger', 'Erades', '06-5246352', 'ger@programit.nl', '11111'),
  (8, 'Aaron', 'vd Akker', '', 'aaron@gmail.com', '');

INSERT INTO `subscription` (`id`, `customer_id`, `trainer_id`, `subscr_type`, `start_date`) VALUES
  (1, 5, 4, 'TWELVE', '2016-05-05');

INSERT INTO `sport_session` (`id`, `subscription_id`, `customer_id`, `trainer_id`, `date`) VALUES
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
