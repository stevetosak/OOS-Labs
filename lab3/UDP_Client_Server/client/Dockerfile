FROM openjdk:17-slim

WORKDIR /app

COPY . /app

RUN javac -d ./build UdpClient.java

CMD ["java", "-cp","./build","UdpClient", "udp_server"]

