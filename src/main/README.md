<h1>Projet Cinéma en Java avec Spring Boot 🎥 </h1>

--------------------
<h3>Description</h3>

Dans le cadre de ma formation de concepteur développeur d'applications,
nous avons dû réaliser un TP Projet Cinéma. Ce projet consiste en la création d'une
application backend pour la gestion d'un cinéma. L'application est développée en Java avec
le framework Spring et implémente une API REST. Le but est de gérer les films,
les séances, les salles, et les tickets dans un système de cinéma.

_________________________________________

<h3>Fonctionnalités Générales</h3>

Le projet suit le modèle UML fourni, avec les classes Film, Réalisateur, Acteur, Séance, Salle, et Ticket, chacune avec
ses attributs et
relations spécifiques.

Créations des entités et leurs relations ainsi que leur CRUD.

Selon le modèle UML, la base de données est conçue pour gérer les informations relatives aux films, séances,
salles, réalisateurs, acteurs, et tickets.

<h4>1 - Film - Séance</h4>
Un film peut avoir plusieurs séances, mais chaque séance est associée à un seul film.

<h4>2 - Salle - Séance</h4>
Une salle peut accueillir plusieurs séances, mais chaque séance se déroule dans une seule salle.

<h4>3 - Réalisateur - Film</h4>
Un réalisateur peut avoir dirigé plusieurs films, mais un film peut aussi n'avoir qu'un seul réalisateur.

<h4>4 - Séance - Ticket</h4>
Une séance peut avoir plusieurs tickets vendus, mais chaque ticket est associé à une seule séance.

<h4>5 - Acteur - Film</h4>
Un film peut avoir plusieurs acteurs et un acteur peut jouer dans plusieurs films.

<h4>Le rôle du controller</h4>

Dans ce projet le controller gère les requêtes HTTP entrantes, délègue les opérations métier aux services, et renvoie
les réponses appropriées. Le controller est responsable de l'interprétation des données des différents requêtes.

<h4>Le rôle du service</h4>

<h4>Le rôle du repository</h4>


_______________________________

<h3>Technologies Utilisées</h3>
<ul><li>Java</li>
<li>Spring</li>
<li>API REST</li></ul>
