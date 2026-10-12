FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY . .

RUN javac -cp "lib/mysql-connector-j-26.7.0.jar" *.java

CMD ["sh", "-c", "java -cp \".:lib/mysql-connector-j-26.7.0.jar\" TrackingServer"]