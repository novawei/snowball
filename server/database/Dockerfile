FROM mysql:5.7.38

ENV TZ=Asia/Shanghai MYSQL_ROOT_HOST=%

ADD ./startup.sh ./startup.sh
ADD ./wait-for.sh ./wait-for.sh
RUN chmod +x ./startup.sh ./wait-for.sh

ENTRYPOINT ["/usr/bin/env"]
CMD ["./startup.sh"]
