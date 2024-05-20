FROM openjdk:17-slim

WORKDIR /app

COPY . /app/

RUN javac -d ./build ./TcpClient.java

CMD ["java", "-cp","./build","TcpClient", "tcp_server"]