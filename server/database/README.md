# DATABASE SERVER

数据库服务, 建议采用docker compose方式运行

## docker compose example
```yaml
version: '3'
services:
  database-server:
    hostname: database-server
    image: snowball/database-server:${SNOWBALL_VERSION}
    build:
      context: ./server/database
    restart: always
    environment:
      - MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD}
    volumes:
      - ./server/database/conf.d:/etc/mysql/conf.d
      - ./server/database/initdb.d:/docker-entrypoint-initdb.d
      - ./server/database/.tmp/datadir:/var/lib/mysql
      - ./server/database/.tmp/logs:/var/log/mysql
    ports:
    - 3306:3306
```
