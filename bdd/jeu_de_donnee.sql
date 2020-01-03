INSERT INTO user_account (pseudo, password, email, role, date) VALUES ('test user','$2a$10$Z9I/Mf1jLr938S.6ayZwMOPNsP0xluNlslJ6rxfvckMn9qFnbOb5q', 'test@gmail.com', 'user', '2019-12-07 09:48:34');
INSERT INTO user_account (pseudo, password, email, role, date) VALUES ('test user2','$2a$10$Z9I/Mf1jLr938S.6ayZwMOPNsP0xluNlslJ6rxfvckMn9qFnbOb5q', 'test2@gmail.com', 'user', '2019-12-07 09:48:34');
INSERT INTO user_account (pseudo, password, email, role, date) VALUES ('admin user','$2a$10$Z9I/Mf1jLr938S.6ayZwMOPNsP0xluNlslJ6rxfvckMn9qFnbOb5q', 'admin@gmail.com', 'admin', '2019-12-07 09:48:34');

INSERT INTO quotation (name) VALUES ('3a');
INSERT INTO quotation (name) VALUES ('3b');
INSERT INTO quotation (name) VALUES ('3c');
INSERT INTO quotation (name) VALUES ('4a');
INSERT INTO quotation (name) VALUES ('4b');
INSERT INTO quotation (name) VALUES ('4c');

INSERT INTO quotation (name) VALUES ('5a');
INSERT INTO quotation (name) VALUES ('5b');
INSERT INTO quotation (name) VALUES ('5c');
INSERT INTO quotation (name) VALUES ('6a');
INSERT INTO quotation (name) VALUES ('6b');
INSERT INTO quotation (name) VALUES ('6c');

INSERT INTO quotation (name) VALUES ('7a');
INSERT INTO quotation (name) VALUES ('7b');
INSERT INTO quotation (name) VALUES ('7c');
INSERT INTO quotation (name) VALUES ('8a');
INSERT INTO quotation (name) VALUES ('8b');
INSERT INTO quotation (name) VALUES ('8c');

INSERT INTO quotation (name) VALUES ('9a');
INSERT INTO quotation (name) VALUES ('9b');
INSERT INTO quotation (name) VALUES ('9c');


INSERT INTO topo (user_id, name, description, image, date_publication) VALUES ('1', 'Saint Pancrasse', 'À quelques encablures de Saint-Hilaire-du-Touvet, lieu de la désormais célébrissime Coupe Icare et grande fête planétaire du parapente, à une demi-heure de «l’encuvettée» (ça n’existe pas mais on voit l’idée) agglomération grenobloise, dans un écrin de verdure propice à la décontraction, vous attend le site d’escalade du Luisset.', 'image/topo/topo41-b3df62ac-1d28-408b-bb35-deac96a17520.jpg', '2019-12-16 15:40:24' );
INSERT INTO topo (user_id, name, description, image, date_publication) VALUES ('1', 'Valdu Di Saltu', 'Valdu di Saltu, c’est LE site de bloc récent et en plein développement dans le sud de la Corse, quelque part entre Ajaccio et Propriano. C’est déjà le plus gros spot de bloc de l’île et il se caractérise par… son très gros potentiel.', 'image/topo/topo41-b3df62ac-1d28-408b-bb35-deac96a17520.jpg', '2019-12-16 15:40:24' );
INSERT INTO topo (user_id, name, description, image, date_publication) VALUES ('1', 'Chamiers', 'Bien qu ayant été découvert des années auparavant pour ses falaises, le site de Charmiers restait vierge d’ouvertures du côté des blocs. Le hasard fit bien les choses lorsque, à partir de 2015, Philippe Ribière se domicilia juste au-dessus…', 'image/topo/topo41-b3df62ac-1d28-408b-bb35-deac96a17520.jpg', '2019-12-16 15:40:24' );


INSERT INTO user_topo_reservation (user_id, topo_id, reserved) VALUES ('1', '1', 'FALSE');
INSERT INTO user_topo_reservation (user_id, topo_id, reserved) VALUES ('1', '2', 'FALSE');
INSERT INTO user_topo_reservation (user_id, topo_id, reserved) VALUES ('1', '3', 'FALSE');


INSERT INTO spot (user_id, name, description, image, topo_id) VALUES ('1', 'Arudy', 'La vallée d’Ossau est l’une des trois grandes vallées de montagne de la région béarnaise. Elle s’étire du nord au sud depuis Pau jusqu’au col du Pourtalet, à la frontière espagnole, sous le regard bienveillant du pic du Midi d’Ossau (2884 mètres), à la silhouette bicéphale si caractéristique. ', 'image/spot/spot23-75e0eaa8-72d3-4b55-b04a-821b9714ef77.jpg', '1' );
INSERT INTO spot (user_id, name, description, image, topo_id) VALUES ('1', 'Badami', 'Depuis quelques années, Badamii est devenu une destination prisée des grimpeurs. On peut en effet jouir des joies de la grimpe sur du grés rouge, tout en savourant le calme, la tranquillité et le dépaysement qu’offrent les lieux.', 'image/spot/spot23-75e0eaa8-72d3-4b55-b04a-821b9714ef77.jpg', '2' );
INSERT INTO spot (user_id, name, description, image, topo_id) VALUES ('1', 'Gorges du Tarn', 'En pénétrant dans les Gorges du Tarn, vous entrez dans le royaume des grandes envolées interminables sur des murs de dolomie calcaire démesurés, parsemés de trous de toutes dimensions et toutes formes.', 'image/spot/spot23-75e0eaa8-72d3-4b55-b04a-821b9714ef77.jpg', '3' );


INSERT INTO secteur (spot_id, name, description, user_id) VALUES ('1', 'Arudy', 'Arudy', '1' );
INSERT INTO secteur (spot_id, name, description, user_id) VALUES ('2', 'Badami', 'Badami', '1' );
