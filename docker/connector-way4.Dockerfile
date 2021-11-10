FROM openjdk:11.0.13-oraclelinux8

EXPOSE 8085

ADD connector-way4/build/libs/connector-way4-0.0.1-SNAPSHOT.jar connector-way4.jar
