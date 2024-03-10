# Utilisez une image de base avec Java
FROM openjdk:11

# Définissez le répertoire de travail dans le conteneur
WORKDIR /app

# Copiez le fichier pom.xml dans le conteneur
COPY pom.xml .

# Exécutez la commande Maven pour télécharger les dépendances
RUN mvn dependency:go-offline -B

# Copiez le reste des fichiers du projet dans le conteneur
COPY src ./src

# Compilez le projet
RUN mvn package

# Définissez la commande par défaut pour exécuter l'application
CMD ["java", "-war", "target/CRUD-1.0-SNAPSHOT.war"]
