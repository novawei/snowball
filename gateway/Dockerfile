FROM openjdk:8u212-jre-alpine

ENV JAR_NAME=gateway TZ=Asia/Shanghai JAVA_OPTS="-Xms200m -Xmx200m"

ADD ./startup.sh ./startup.sh
ADD ./wait-for.sh ./wait-for.sh
RUN chmod +x ./startup.sh ./wait-for.sh

ADD ./target/${JAR_NAME}.jar /app/

ENTRYPOINT ["/usr/bin/env"]
CMD ["./startup.sh"]

EXPOSE 6001