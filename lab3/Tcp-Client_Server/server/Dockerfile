FROM openjdk:17-slim

WORKDIR /app

COPY . /app/

RUN javac -d ./build ./TcpServer.java

RUN javac -d ./build ./TcpServerThread.java

CMD [ "java", "-cp", "./build","TcpServer" ]
