FROM ubuntu:latest
RUN apt-get -y update
RUN apt-get -y upgrade
RUN apt-get install -y curl;
# install mongosh
RUN curl https://downloads.mongodb.com/compass/mongodb-mongosh_1.0.0_amd64.deb -o mongosh.deb && \
    dpkg --install mongosh.deb
COPY source-connector.json sink-connector.json sink-connector-cdc.json initialize-setup.sh /
RUN chmod +x initialize-setup.sh

RUN rm mongosh.deb