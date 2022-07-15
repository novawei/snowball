FROM openjdk:8u212-jre-alpine

ENV JAR_NAME=auth-service TZ=Asia/Shanghai JAVA_OPTS="-Xms200m -Xmx200m"

ADD ./startup.sh /usr/local/bin/startup.sh
ADD ./wait-for.sh /usr/local/bin/wait-for.sh
RUN chmod +x /usr/local/bin/startup.sh /usr/local/bin/wait-for.sh

ADD ./target/${JAR_NAME}.jar /app/

ENTRYPOINT ["/usr/bin/env"]
CMD ["/usr/local/bin/startup.sh"]

EXPOSE 9003