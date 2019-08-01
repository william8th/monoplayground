#/bin/bash

echo "Running postgres on port $1"

docker run -it --rm -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgresPass -e POSTGRES_DB=mydb -p $1:5432 postgres:10.5

