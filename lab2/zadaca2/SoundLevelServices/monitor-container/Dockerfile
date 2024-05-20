FROM openjdk:17-slim

WORKDIR .

COPY ./SoundLevelMonitor.java .

COPY ./monitor-soundlevel.sh .

RUN chmod +x monitor-soundlevel.sh

RUN javac SoundLevelMonitor.java

CMD ["./monitor-soundlevel.sh"]

