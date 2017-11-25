FROM openjdk:8

ENV NODE_VERSION 8.9.1
ENV SBT_VERSION 0.13.15

# install Node.js
RUN curl -sL https://deb.nodesource.com/setup_8.x | bash - && \
    apt-get install -y nodejs

# install yarn
RUN npm install -g yarn

# install sbt
RUN curl -L -O https://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
    dpkg -i sbt-$SBT_VERSION.deb && \
    rm sbt-$SBT_VERSION.deb && \
    sbt sbtVersion

# compile app
RUN mkdir /root/src
COPY . /root/src
RUN cd /root/src && \
    sbt clean universal:packageZipTarball && \
    tar xvf target/universal/ksql-ui-*.tgz -C /root && \
    mv /root/ksql-ui-* /root/ksql-ui && \
    rm -rf /root/src

# delete cache
RUN rm -rf ~/.ivy2/cache && \
    yarn cache clean

EXPOSE 9000

CMD ["/root/ksql-ui/bin/ksql-ui", "-Dlogger.resource=logback-prod.xml"]
