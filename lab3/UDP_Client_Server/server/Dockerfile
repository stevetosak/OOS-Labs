FROM openjdk:17-slim

WORKDIR /app

COPY . /app

RUN javac -d ./build UdpServer.java

CMD ["java", "-cp","./build","UdpServer"]

