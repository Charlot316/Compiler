FROM openjdk:14
COPY ./lab1/ /myapp/
WORKDIR /myapp/
RUN javac -cp src/ src/Compiler.java -d dst/
