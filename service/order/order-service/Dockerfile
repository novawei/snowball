FROM java:8-jre

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms200m -Xmx200m"

ADD ./target/order-service.jar /app/
CMD java -jar /app/order-service.jar ${JAVA_OPTS}

EXPOSE 9003