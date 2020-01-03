## Un site communautaire autour de l’escalade

### Technologies

- Application web réalisé en Java/JEE 
- Serveur Apache Tomcat 8.5
- Base de données PostgreSQL 12.1.1
- ApacheMaven


### Déploiement

1.	Importer le projet dans Eclipse IDE for Enterprise Java Développers à partir du lien GitHub suivant : https://github.com/ElenaGebel/escalade-project
2.	Créer la structure de la base de données en utilisant le fichier bdd/ocescalade_bdd
3.	Injecter les données en utilisant le fichier bdd/jeu_de_donnee

test user login: test@gmail.com  
mot de pass: Escalade3!

test user2 login: test2@gmail.com 
mot de pass: Escalade3!

admin login: admin@gmail.com  
mot de pass: Escalade3!

4.	Changer les paramètres de connexion dans le fichier escalade/escalade-webapp/src/main/webapp/META-INF/context.xml

