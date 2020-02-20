FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=muzixdb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/muzixdb
ADD ./target/muzixmanager-0.0.1-SNAPSHOT.jar /usr/src/muzixmanager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","muzixmanager-0.0.1-SNAPSHOT.jar"]
