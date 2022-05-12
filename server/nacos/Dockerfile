FROM nacos/nacos-server:v2.0.3

ADD ./startup.sh ./startup.sh
ADD ./wait-for.sh ./wait-for.sh
RUN chmod +x ./startup.sh ./wait-for.sh

ENTRYPOINT ["/usr/bin/env"]
CMD ["./startup.sh"]