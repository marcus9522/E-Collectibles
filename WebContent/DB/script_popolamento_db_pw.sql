DROP DATABASE IF EXISTS  associazione_prog_pw;
CREATE DATABASE associazione_prog_pw;
USE associazione_prog_pw;

CREATE TABLE utente (
email varchar(40) NOT NULL,
password varchar(20) NOT NULL,
ruolo enum('amministratore','normale'),
cod_fiscale char(16),
nome varchar(20) NOT NULL,
cognome varchar(20) NOT NULL,
data_nascita date NOT NULL,
tel1 varchar(15),
tel2 varchar(15),
cap char(5) NOT NULL,
via varchar(30) NOT NULL,
civico smallint unsigned NOT NULL,
citta varchar(25) NOT NULL,
provincia varchar(25) NOT NULL,
nazione varchar(25) NOT NULL,
PRIMARY KEY (email)
);

CREATE TABLE prodotto (
codice int NOT NULL,
nome varchar(30) NOT NULL,
prezzo float(6, 2) NOT NULL,
categoria varchar(20) NOT NULL,
descrizione text(300) NOT NULL,
quantita integer NOT NULL,
offerta bool NOT NULL,
foto text,
PRIMARY KEY (codice)
);

CREATE TABLE lista_personale (
email varchar(40) NOT NULL,
cod_prod int NOT NULL,
PRIMARY KEY (email,cod_prod),
FOREIGN KEY (email) REFERENCES utente(email) ON DELETE CASCADE ,
FOREIGN KEY (cod_prod) REFERENCES prodotto(codice) ON UPDATE CASCADE
);

CREATE TABLE recensione (
valutazione smallint unsigned NOT NULL,
testo text(300) NOT NULL,
email varchar(40) NOT NULL,
cod_prod int NOT NULL,
PRIMARY KEY (email,cod_prod),
FOREIGN KEY (email) REFERENCES utente(email) ON DELETE CASCADE ON UPDATE CASCADE ,
FOREIGN KEY (cod_prod) REFERENCES prodotto(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ordine (
email varchar(40) NOT NULL,
cod_ordine int NOT NULL ,
importo_tot float(7, 2) NOT NULL,
data_ord date NOT NULL,
mod_pagamento enum('PayPal', 'Carta di credito', 'Bonifico bancario', 'Contrassegno') NOT NULL,
stato enum('In elaborazione', 'In preparazione', 'In spedizione', 'Spedito', 'In transito', 'In consegna', 'Consegnato', 'In giacenza') NOT NULL,
corriere enum('BRT', 'GLS', 'SDA', 'TNT', 'DHL', 'UPS', 'Poste Italiane') NOT NULL,
tracciamento varchar(20) ,
data_partenza date ,
PRIMARY KEY (email, cod_ordine),
FOREIGN KEY (email) REFERENCES utente(email) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE composto_da (
email varchar(40) NOT NULL,
cod_ordine int NOT NULL,
cod_prodotto int NOT NULL,
quantita smallint unsigned NOT NULL DEFAULT 1,
FOREIGN KEY (email, cod_ordine) REFERENCES ordine(email, cod_ordine) ON DELETE CASCADE,
FOREIGN KEY (cod_prodotto) REFERENCES prodotto(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

/*prodotto*/
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('1', 'carta pokemon', '4', 'Cartedagioco', 'Carta da gioco dei Pokemon ', '10', '1', 'http://media.pokemoncentral.it/wiki/1/17/Cardback.jpg');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('2', 'carta yu-gi-oh', '6', 'Cartedagioco', 'Carta da gioco di YU-GI-OH', '8', '1', 'http://vignette3.wikia.nocookie.net/yugioh/images/2/2f/200px-Back-EN.png/revision/latest?cb=20120324174524&path-prefix=it');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('3', 'carta Magic', '5', 'Cartedagioco', 'Carta da gioco Magic', '3', '0', 'http://www.everyeye.it/public/covers/15062011/Magic---l-adunanza_cover.jpg');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('4', 'deck yu-gi-oh', '12', 'Cartedagioco', 'Deck completo YU-GI-OH', '2', '0', 'http://www.yugioh-card.com/it/products/images/sdhs.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('5', 'deck pokemon', '11', 'Cartedagioco', 'Deck completo POKEMON', '5', '1', 'http://cdn.bulbagarden.net/upload/b/be/TCGO_BW_Basic_Blue.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('6', 'deck magic', '9', 'Cartedagioco', 'Deck completo MAGIC', '3', '0', 'http://media.wizards.com/images/magic/daily/arcana/1asldfuy9_EN_MTGM15_IntrPk_03_01.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('7', 'action thor', '20', 'Actionfigure', 'Action figure di Thor',  '4', '0', 'http://www.sideshowtoy.com/wp-content/uploads/2014/09/3003781-product-silo.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('8', 'action superman', '15', 'Actionfigure', 'Action figure di Superman', '15', '1', 'http://vignette2.wikia.nocookie.net/villainsfanon/images/2/2d/DCCOCT120316-Justice-League-Superman-Figure_3.png/revision/latest?cb=20150823160805');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('9', 'action batman', '18', 'Actionfigure', 'Action figure di Batman',  '8', '1', 'https://d7ijt0gdneggg.cloudfront.net/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/b/a/batman_1-4_scale_figure.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('10', 'action predator', '15', 'Actionfigure', 'Action figure di Predator', '18', '0', 'http://i230.photobucket.com/albums/ee110/DreamWolfz/Renders/Predator.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('11', 'action hellboy', '10', 'Actionfigure', 'Action figure di Hellboy', '25', '0', 'http://www.pngmart.com/files/2/Hellboy-PNG-Photos.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('12', 'action spiderman', '14', 'Actionfigure', 'Action figure di SpiderMan', '22', '1', 'http://www.pngall.com/wp-content/uploads/2016/05/Spider-Man-PNG.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('13', 'fumetto captain america', '10', 'Fumetti', 'Fumetto di Captain America', '16', '1', 'http://imgc.allpostersimages.com/images/P-488-488-90/92/9233/HV43500Z/posters/fumetti-marvel-retro-rivista-di-fumetti-capitan-america-n-100-copertina-con-pantera-nera-thor-namor.jpg');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('14', 'fumetto daredevil', '14', 'Fumetti', 'Fumetto di Daredevil', '16', '1', 'http://m2.paperblog.com/i/82/820754/photoshop-dissacrare-copertine-di-fumetti-L-ZZOWBe.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('15', 'fumetto avengers', '15', 'Fumetti', 'Fumetto degli Avengers', '12', '1', 'http://i678.photobucket.com/albums/vv147/joe7_2009/joe7_2009009/752_zps78884a6c.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('16', 'fumetto hulk', '18', 'Fumetti', 'Fumetto di HULK', '12', '0', 'http://hulkblog.screenweek.it/files/2008/04/hulk176.jpg');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('17', 'fumetto spiderman', '14', 'Fumetti', 'Fumetto di Spiderman', '12', '0', 'http://imgc.allpostersimages.com/images/P-473-488-90/92/9234/1L23500Z/posters/fumetti-marvel-retro-libro-di-fumetti-amazing-fantasy-n-15-copertina-presentazione-di-spider-man-in-inglese.jpg');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('18', 'fumetto diabolik', '9', 'Fumetti', 'Fumetto di Diabolik', '22', '0', 'http://i44.tinypic.com/24ex5k5.jpg');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('19', 'maglia spiderman', '20', 'Maglie', 'Maglia di Spiderman', '10', '1', 'http://s3.amazonaws.com/content.newsok.com/newsok/images/blogs/misc/spidey_youth1.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('20', 'maglia hulk', '18', 'Maglie', 'Maglia di Hulk', '10', '1', 'https://d7ijt0gdneggg.cloudfront.net/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/m/a/marvel_-_hulk_02.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('21', 'maglia avengers', '18', 'Maglie', 'Maglia Avengers', '10', '1', 'http://www.8tees.com/media/catalog/product/cache/6/image/9df78eab33525d08d6e5fb8d27136e95/x/h/xhmvts-1332-grey.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('22', 'maglia thor', '18', 'Maglie', 'Maglia di Thor', '10', '0', 'http://cdn4.teehunter.com/wp-content/uploads/2013/10/MF1227_large.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('23', 'maglia captain america', '18', 'Maglie', 'Maglia Captain America', '10', '0', 'http://www.garbattic.com/wp-content/uploads/2015/08/captain-america.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('24', 'maglia star wars', '18', 'Maglie', 'Maglia Star-wars', '10', '0', 'http://6fja9beiwb2o4svf.zippykid.netdna-cdn.com/wp-content/uploads/2015/05/Uniqlo-Star-Wars-T-Shirt-Singapore-Aya-Asahina.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('25', 'portachiavi spiderman', '4', 'Gadget', 'Portachiavi Spiderman', '40', '1', 'http://www.europartysud.com/media/catalog/product/cache/1/image/1200x1200/9df78eab33525d08d6e5fb8d27136e95/c/s/cs-gb38428.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('26', 'usb hulk', '5', 'Gadget', 'Penna Usb hulk', '30', '1', 'http://www.tribe-tech.com/wp-content/uploads/2014/02/Marvel-Hulk-USB-Flash-Drive.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('27', 'usb star-wars', '8', 'Gadget', 'Penna Usb Star-Wars', '20', '1', 'http://www.tribe-tech.com/wp-content/uploads/2015/12/Starwars-BB-8-USB-Flash-Drive.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('28', 'orologio captain', '15', 'Gadget', 'Orologio Captain America', '12', '0', 'https://c1-zingpopculture.eb-cdn.com.au/merchandising/images/packshots/0d457153c1294eef8d760cfbc60c7b6c_Large.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('29', 'elmo thor', '12', 'Gadget', 'Elmo di thor', '14', '0', 'https://d7ijt0gdneggg.cloudfront.net/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/t/h/thor-helmet-mask.png');
INSERT INTO `associazione_prog_pw`.`prodotto` (`codice`, `nome`, `prezzo`, `categoria`, `descrizione`,  `quantita`, `offerta`, `foto`) VALUES ('30', 'usb ironman', '9', 'Gadget', 'Penna Usb Iron-Man', '7', '0', 'http://www.tribe-tech.com/wp-content/uploads/2014/09/Marvel-Ironman-Metallic-USB-Flash-Drive.png');

/*utente*/
INSERT INTO `associazione_prog_pw`.`utente` (`email`, `password`, `ruolo`, `cod_fiscale`, `nome`, `cognome`, `data_nascita`, `tel1`, `tel2`, `cap`, `via`, `civico`, `citta`, `provincia`, `nazione`) VALUES ('root', 'root', 'amministratore', '0123456789123456', 'root', 'root', '2016-09-10', '0', '0', '0', 'root', '0', 'root', 'root', 'root');
INSERT INTO `associazione_prog_pw`.`utente` (`email`, `password`, `ruolo`, `cod_fiscale`, `nome`, `cognome`, `data_nascita`, `tel1`, `tel2`, `cap`, `via`, `civico`, `citta`, `provincia`, `nazione`) VALUES ('user', 'user', 'normale', '987654320987654', 'Mario', 'rossi', '1980-10-10', '123456', '123456', '84100', 'roma', '20', 'Salerno', 'Sa', 'Italia');