FROM openjdk:11.0.13-oraclelinux8

EXPOSE 8090

ADD brain/build/libs/brain-0.0.1-SNAPSHOT.jar brain.jar
