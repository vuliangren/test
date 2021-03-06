FROM java:8

ENV SERVER_NAME tt

ENV SERVER_PORT 8080

ENV JAVA_OPS -server -Xms128m -Xmx512m -XX:CompressedClassSpaceSize=128m -XX:MetaspaceSize=200m -XX:MaxMetaspaceSize=200m

WORKDIR /usr/local

COPY target/*.jar /usr/local/${SERVER_NAME}.jar

CMD java ${JAVA_OPS} -Dspring.profiles.active=dev -Djava.security.egd=file:/dev/./urandom -jar ${SERVER_NAME}.jar