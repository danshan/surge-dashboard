FROM maven:3.8.5-openjdk-17 as build

WORKDIR /workspace
ADD . /workspace
ENV WORKSPACE=/workspace
RUN make -f CICD/Makefile all release


FROM azul/zulu-openjdk-alpine:17-jre-latest

WORKDIR /app
COPY --from=build /workspace/release/ /app/
RUN chmod +x /app/start.sh
CMD ["/bin/sh", "./start.sh" ]