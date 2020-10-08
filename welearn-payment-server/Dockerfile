FROM openjdk:8-jre-slim

# CONFIG_IP
ARG ENV_CIP='39.96.31.164'
# CONFIG_PORT
ARG ENV_CPORT='4624'
# EUREKA_IP
ARG ENV_EIP='47.110.73.199'
# EUREKA_PORT
ARG ENV_EPORT='9200'

# --------------------------------------

# SERVER_IP
ARG ENV_IP='47.110.73.199'
# SERVER_PORT
ARG ENV_PORT='9300'
# SERVER_TYPE
ARG ENV_SERVER='payment'
# JVM_XMS
ARG ENV_XMS='128'
# JVM_XMX
ARG ENV_XMX='256'

# --------------------------------------

ENV CONFIG_IP   ${ENV_CIP}
ENV CONFIG_PORT ${ENV_CPORT}
ENV EUREKA_IP   ${ENV_EIP}
ENV EUREKA_PORT ${ENV_EPORT}
ENV SERVER_IP   ${ENV_IP}
ENV SERVER_PORT ${ENV_PORT}
ENV SERVER_NAME welearn-${ENV_SERVER}-server
ENV VERSION     0.0.1-SNAPSHOT
ENV JVM_XMS     ${ENV_XMS}
ENV JVM_XMX     ${ENV_XMX}

# COPY JAR TO CONTAINER
COPY target/${SERVER_NAME}-${VERSION}.jar /usr/local/micro-service.jar

# RUN JAR WITH ARGS
ENTRYPOINT java -Xms${JVM_XMS}m -Xmx${JVM_XMX}m -Dspring.cloud.config.uri=http://${CONFIG_IP}:${CONFIG_PORT} -Dspring.cloud.config.profile=prd -Dfile.encoding=UTF8 -Duser.timezone=GMT+08 -Dspring.profiles.active=prd -Djava.security.egd=file:/dev/./urandom -jar /usr/local/micro-service.jar