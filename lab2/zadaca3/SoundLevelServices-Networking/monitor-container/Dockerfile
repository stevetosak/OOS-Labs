FROM openjdk:17-slim

WORKDIR .

RUN apt-get update && apt-get install -y netcat

EXPOSE 2000

COPY ./SoundLevelMonitor.java .

COPY ./monitor-soundlevel.sh .

RUN chmod +x monitor-soundlevel.sh

RUN javac SoundLevelMonitor.java

CMD ["./monitor-soundlevel.sh"]

