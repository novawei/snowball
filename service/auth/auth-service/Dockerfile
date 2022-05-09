FROM java:8-jre

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms200m -Xmx200m"

ADD ./target/auth-service.jar /app/
CMD java -jar /app/auth-service.jar ${JAVA_OPTS}

EXPOSE 9002