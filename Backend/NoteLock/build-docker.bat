start mvnw clean package -DskipTests
mkdir notelock-be-docker
mkdir notelock-be-docker\target

copy Dockerfile notelock-be-docker\
copy src\main\resources\application.properties notelock-be-docker\
copy target\NoteLock-0.0.1-SNAPSHOT.jar notelock-be-docker\target
copy .env notelock-be-docker\
