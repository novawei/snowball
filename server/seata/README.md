# SEATA SERVER

分布式事务，建议采用docker compose方式运行

## config example
https://github.com/seata/seata/blob/develop/script/config-center/config.txt

## docker compose example
```yaml
version: '3'
services:
  seata-server:
    hostname: seata-server
    image: snowball/seata-server:${SNOWBALL_VERSION}
    build:
      context: ./server/seata
    depends_on:
      - nacos-server
    command: /usr/local/bin/wait-for.sh -t 0 nacos-server:8848 -- /usr/local/bin/startup.sh
    environment:
      - SEATA_CONFIG_NAME=file:/root/seata-config/registry
    volumes:
      - ./server/seata/config:/root/seata-config
    ports:
      - 8091:8091
```
