#Use the official OpenJdk 17 image from Docker Hub
From openjdk:17
# Set working directory inside the container
WORKDIR /app
#Copy the complied java application JAR file into the container
Copy ./target/Blog-App-0.0.1-SNAPSHOT.jar /app
#Expose the port the Spring Boot application will run on
EXPOSE 8480
# Command to run the application
CMD ["java","-jar","Blog-App-0.0.1-SNAPSHOT.jar"]