FROM openjdk:8

RUN mkdir /app

WORKDIR /app

RUN apt-get update \
    && apt-get install curl \
    && apt-get install -y libxrender1 libxtst6 libxi6

RUN curl -L -H "Accept: application/vnd.github.v3+json" -H "Authorization: token <token_id>" https://api.github.com/repos/onairam-97/ninja-gaiden/actions/artifacts/35388790/zip --output Ninja-Gaiden.zip

RUN unzip Ninja-Gaiden.zip

CMD ["java", "-jar", "Ninja_Gaiden-1.0-SNAPSHOT.jar"]
