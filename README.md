# logistic-SpringBoot-Hibernate-Mapstruct-H2

Если необходимо запустить через postgress, то следующие найстроки вставить в \src\main\resources\application.properties

spring.jpa.database=POSTGRESQL \n
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=root
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true


# postman/json 
in src\test\resources\json
#
#
#
