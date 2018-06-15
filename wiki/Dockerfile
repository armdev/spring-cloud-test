#FROM 10.1.12.70:5000/java
#FROM airhacks/java
FROM openjdk
COPY target/wiki.jar /opt/wiki.jar
ENTRYPOINT ["java","-Xmx256m", "-Xss512m", "-jar","/opt/wiki.jar"]
EXPOSE 2018