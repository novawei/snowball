FROM nacos/nacos-server:v2.0.3

ADD ./startup.sh /usr/local/bin/startup.sh
ADD ./wait-for.sh /usr/local/bin/wait-for.sh
RUN chmod +x /usr/local/bin/startup.sh /usr/local/bin/wait-for.sh

ENTRYPOINT ["/usr/bin/env"]
CMD ["/usr/local/bin/startup.sh"]