FROM java:8-jre

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms200m -Xmx200m"

ADD ./target/uac-service.jar /app/
CMD java -jar /app/uac-service.jar ${JAVA_OPTS}

EXPOSE 9001