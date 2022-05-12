#!/usr/bin/env sh

# call original script, start seata server
# seata-server use jib-maven-plugin to build docker image
# https://github.com/seata/seata/blob/develop/server/pom.xml
java -Djava.security.egd=file:/dev/./urandom \
     -server \
     -Xss512k \
     -XX:+UnlockExperimentalVMOptions \
     -XX:+UseContainerSupport \
     -XX:SurvivorRatio=10 \
     -XX:MetaspaceSize=128m \
     -XX:MaxMetaspaceSize=256m \
     -XX:MaxDirectMemorySize=1024m \
     -XX:-OmitStackTraceInFastThrow \
     -XX:-UseAdaptiveSizePolicy \
     -XX:+HeapDumpOnOutOfMemoryError \
     -XX:HeapDumpPath=/var/log/seata_heapdump.hprof \
     -XX:+DisableExplicitGC \
     -XX:+CMSParallelRemarkEnabled \
     -XX:+UseCMSInitiatingOccupancyOnly \
     -XX:CMSInitiatingOccupancyFraction=75 \
     -Xloggc:/var/log/seata_gc.log \
     -verbose:gc \
     -Dio.netty.leakDetectionLevel=advanced \
     -Dlogback.color.disable-for-bat=true \
     -cp /seata-server/resources:/seata-server/classes:/seata-server/libs/* \
     io.seata.server.Server