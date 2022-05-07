# rss22sSb1

1. Se positioner dans le dossier root (où existe le pom.xml) et lancer la commande : mvn spring-boot:run

2. Sur votre Navigateur : localhost:8080/accueil

3. Insérer 'Item' respectant le xsd du TP1 via Postman en choisissant :
          POST : http://localhost:8080/rss/insert
          Body
          raw

4. localhost:8080/items
          Lister les articles existants

5. localhost:8080/documentation
          Switcher vers la page de documentation
