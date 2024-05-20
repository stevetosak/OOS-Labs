FROM openjdk:17-slim

WORKDIR .

EXPOSE 2000

RUN apt-get update && apt-get install -y netcat

COPY ./SoundLevelSensor.java .

COPY ./sensor-soundlevel.sh .

RUN chmod +x sensor-soundlevel.sh

RUN javac SoundLevelSensor.java

CMD ["./sensor-soundlevel.sh"]



