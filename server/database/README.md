# DATABASE SERVER

数据库服务, 建议采用docker compose方式运行

## docker compose example
```yaml
  database-server:
    hostname: database-server
    image: mysql:${MYSQL_VERSION}
    restart: always
    environment:
      - TZ=Asia/Shanghai
      - MYSQL_ROOT_HOST=%
      - MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD}
    volumes:
      - ./server/database/conf.d:/etc/mysql/conf.d
      - ./server/database/initdb.d:/docker-entrypoint-initdb.d
      - ./server/database/.tmp/datadir:/var/lib/mysql
      - ./server/database/.tmp/logs:/var/log/mysql
    ports:
    - 3306:3306
```
