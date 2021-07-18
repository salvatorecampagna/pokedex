FROM azul/zulu-openjdk:11
VOLUME /tmp
RUN adduser --system --group app
USER app
COPY build/libs/pokedex-1.0.0.jar app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]