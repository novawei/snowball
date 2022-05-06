# DATABASE SERVER

数据库服务, 建议采用docker compose方式运行

## docker compose example
```yaml
services:
  database-server:
    image: mysql:${MYSQL_VERSION}
    environment:
    volumes:
    ports:
    - 3306:3306
```
