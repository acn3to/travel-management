# Project Setup Guide

## Prerequisites

Ensure you have the following installed:

- **Java 17** or higher
- **Maven** (for building the project)
- **PostgreSQL** (or your preferred database)
- **Git** (for version control)

## Download the Project

1. **Clone the Repository**

Open your terminal and run:

```bash
git clone git@github.com:acn3to/travel-management.git
```

2. **Navigate to the Project Directory**

```bash
cd travel-management
```

## Configuration

1. **Set Up the Database**

- Create a PostgreSQL database.
- Update the database connection settings in `src/main/resources/application.properties` or `application.yml`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdatabase
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
```

Replace `localhost`, `5432`, `yourdatabase`, `yourusername`, and `yourpassword` with your database details.

2. **Build the Project**

Run:
```bash
mvn clean install
```

## Run the Project

1. **Start the Application**

Run the Spring Boot application using:

```bash
mvn spring-boot:run
```

Alternatively, you can run it from your IDE.

2. **Access the API**

The API will be available at:

```
http://localhost:8080
```

Modify the port if you have configured a different one.

## Postman Collection

You can access and import the API collection into Postman using the following link:

[Postman Collection](https://documenter.getpostman.com/view/17449015/2sA3s4mAov)


## Troubleshooting

- **Database Connection Issues**: Ensure PostgreSQL is running and credentials are correct.
- **Build Errors**: Review error messages for missing dependencies or configuration issues.

## Additional Information

- Refer to the project's API documentation for details on endpoints.
- For specific issues, check the issue tracker or contact project maintainers.

