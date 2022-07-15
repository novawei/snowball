FROM mysql:5.7.38

ENV TZ=Asia/Shanghai MYSQL_ROOT_HOST=%

ADD ./startup.sh /usr/local/bin/startup.sh
ADD ./wait-for.sh /usr/local/bin/wait-for.sh
RUN chmod +x /usr/local/bin/startup.sh /usr/local/bin/wait-for.sh

ENTRYPOINT ["/usr/bin/env"]
CMD ["/usr/local/bin/startup.sh"]
