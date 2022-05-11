# SEATA SERVER

分布式事务，建议采用docker compose方式运行

## config example
https://github.com/seata/seata/blob/develop/script/config-center/config.txt

## docker compose example
```yaml
services:
  seata-server:
    hostname: seata-server
    image: seataio/seata-server:${SEATA_VERSION}
    depends_on:
      - nacos-server
    environment:
      - SEATA_CONFIG_NAME=file:/root/seata-config/registry
    volumes:
      - ./server/seata/config:/root/seata-config
    ports:
      - 8091:8091
```
