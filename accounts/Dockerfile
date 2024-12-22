#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# MAINTAINER instruction is deprecated in favor of using label
# MAINTAINER afe.com
#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="afe"

# Add the application's target jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]

# Go to the this dockerfile path and open the terminal and type:
#    docker build . -t alifurkan159/accounts:a1
# This command builds a Docker image using the Dockerfile in the current directory (.)
# and tags the image with the name alifurkan159/accounts and version a1.
# You need to see alifurkan159/accounts image with bottom command
#   docker images

#    docker run -p 8080:8080 alifurkan159/accounts:a1
# This command runs a Docker container from the alifurkan159/accounts:a1 image and maps port
# 8080(left) on the host (external access point) to port 8080(right) in the container (internal application port),
# enabling communication between the host and the containerized application.

#    docker run -d -p 8080:8080 alifurkan159/accounts:a1
# This command runs a Docker container in the background (-d=detached) and maps port 8080 on the host to port 8080 in the container

#    docker ps
# This command lists all running Docker containers, including those created from the alifurkan159/accounts:a1 image

#    docker ps -a
# This command lists all Docker containers, including stopped containers

#    docker image push docker.io/alifurkan159/accounts:a1
# This command pushes the alifurkan159/accounts:a1 image to the Docker Hub

#    docker pull alifurkan159/accounts:a1
# This command pulls the alifurkan159/accounts:a1 image from the Docker Hub

#    docker image rm [image-id or image-name]
# This command removes the entered image

#    docker container start [container-id or container-name]
# This command starts the stopped container

#    docker container stop [container-id or container-name]
# This command stops the running container

#    docker container kill [container-id or container-name]
# This command kills the running container

#    docker container restart [container-id or container-name]
# This command restarts one or more containers

#    docker container rm [container-id or container-name]
# This command removes one or more containers

#     docker rmi [image-id or image-name]
# Removes one or more images

#     docker container logs <container_name>
# Displays the logs of a specific container

#     docker container logs -f <container_name>
# Displays the logs of a specific container in a live mode

#     docker container prune
# Removes all stopped containers

#     docker container stats
# Displays resource usage statistics for all containers

#     docker image prune
# Removes all unused images

#     docker exec -it <container_name> bash
# Opens a bash shell in the running container
