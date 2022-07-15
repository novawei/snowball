# NACOS SERVER

服务发现和配置中心，建议采用docker compose方式运行

## docker compose example
```yaml
version: '3'
services:
  nacos-server:
    hostname: nacos-server
    image: snowball/nacos-server:${SNOWBALL_VERSION}
    build:
      context: ./server/nacos
    depends_on:
      - database-server
    command: /usr/local/bin/wait-for.sh -t 0 database-server:3306 -- /usr/local/bin/startup.sh
    restart: always
    environment:
      - JVM_XMS=200m
      - JVM_XMX=200m
      - JVM_XMN=100m
      - JVM_MS=64m
      - JVM_MMS=128m
      - PREFER_HOST_MODE=hostname
      - MODE=standalone
      - SPRING_DATASOURCE_PLATFORM=mysql
      - MYSQL_SERVICE_HOST=database-server
      - MYSQL_SERVICE_DB_NAME=nacos_server
      - MYSQL_SERVICE_PORT=3306
      - MYSQL_SERVICE_USER=root
      - MYSQL_SERVICE_PASSWORD=${MYSQL_ROOT_PASSWORD}
      - MYSQL_SERVICE_DB_PARAM=characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false
    volumes:
      - ./server/nacos/.tmp/logs/:/home/nacos/logs
      - ./server/nacos/.tmp/init.d/custom.properties:/home/nacos/init.d/custom.properties
    ports:
      - 8848:8848
      - 9848:9848
      - 9555:9555
```
