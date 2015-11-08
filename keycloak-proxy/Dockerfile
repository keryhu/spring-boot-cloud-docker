FROM java:8
MAINTAINER justinndavis@gmail.com

COPY keycloak /keycloak
RUN mkdir /keycloak/logs

EXPOSE 8080


CMD ["/keycloak/run.sh"]
