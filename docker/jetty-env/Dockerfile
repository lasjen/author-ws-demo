FROM jetty

#COPY source/ojdbc7.jar /var/lib/jetty/lib/ext/
#COPY source/ucp.jar /var/lib/jetty/lib/ext/
COPY source/ojdbc7.jar /usr/local/jetty/lib/ext/
COPY source/ucp.jar /usr/local/jetty/lib/ext/

COPY source/jetty-plus.xml /usr/local/jetty/etc/.

RUN echo "--module=plus" >> /usr/local/jetty/start.ini

CMD ["java","-jar","/usr/local/jetty/start.jar"]
