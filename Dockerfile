FROM maven:3.8.5-openjdk-17 as build

WORKDIR /workspace
ADD . /workspace
ENV WORKSPACE=/workspace
RUN mvn clean package -U


FROM azul/zulu-openjdk-alpine:17-jre-latest

WORKDIR /app
COPY --from=build /workspace/target/surge-dashboard.jar /app/
COPY --from=build /workspace/start.sh /app/
RUN chmod +x /app/start.sh
CMD ["/bin/sh", "./start.sh" ]