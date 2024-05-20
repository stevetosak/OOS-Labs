FROM openjdk:17-slim

WORKDIR /content/html

COPY ./content/html/ProductsManagement.java .

RUN javac ProductsManagement.java

CMD ["java", "ProductsManagement"]