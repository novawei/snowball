FROM java:8-jre

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms200m -Xmx200m"

ADD ./target/gateway.jar /app/
CMD java -jar /app/gateway.jar ${JAVA_OPTS}

EXPOSE 6001