FROM openjdk
COPY target/wiki.jar /opt/wiki.jar
ENTRYPOINT ["java","-Xmx256m", "-Xss512m", "-jar","/opt/wiki.jar"]
EXPOSE 2018