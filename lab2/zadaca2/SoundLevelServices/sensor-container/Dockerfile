FROM openjdk:17-slim

WORKDIR .

COPY ./SoundLevelSensor.java .

COPY ./sensor-soundlevel.sh .

RUN chmod +x sensor-soundlevel.sh

RUN javac SoundLevelSensor.java

CMD ["./sensor-soundlevel.sh"]



