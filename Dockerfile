FROM openjdk:14
COPY . /myapp/
WORKDIR /myapp/
RUN javac -cp src/ src/Compiler.java -d dst/
