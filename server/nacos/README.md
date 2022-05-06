# NACOS SERVER

服务发现和配置中心，建议采用docker compose方式运行

## docker compose example
```yaml
services:
  nacos-server:
    image: nacos/nacos-server:${NACOS_VERSION}
    environment:
    - PREFER_HOST_MODE=hostname
    - MODE=standalone
    volumes:
    - ./logs/nacos-standalone-logs/:/home/nacos/logs
    - .//init.d/custom.properties:/home/nacos/init.d/custom.properties
    ports:
    - 8848:8848
    - 9848:9848
```
