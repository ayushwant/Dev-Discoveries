docker build --tag=pgraphql:1.0 .
docker run -d -p 8080:8080 pgraphql:1.0

#8080:8080 = localhost-port:container-port