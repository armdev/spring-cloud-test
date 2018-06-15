FROM openjdk
COPY target/collector.jar /opt/collector.jar
ENTRYPOINT ["java","-Xmx256m", "-Xss512m", "-jar","/opt/collector.jar"]
EXPOSE 2020