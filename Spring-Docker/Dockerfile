# 使用官方的 OpenJDK 镜像作为基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 将构建好的 JAR 文件复制到镜像中
COPY target/Spring-Docker-1.0-SNAPSHOT.jar /app/Spring-Docker-1.0-SNAPSHOT.jar

# 暴露应用程序的端口
EXPOSE 8093

# 设置容器启动时运行的命令
ENTRYPOINT ["java", "-jar", "Spring-Docker-1.0-SNAPSHOT.jar"]
