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
"get /clients - 
                all entitys - Client \n 
                     Address \n 
                "   Crew\n" +
                "    Flight\n" +
                "    Person\n" +
                "    Shift\n" +
                "    Vehicle\n" +
                "\n"+"\n"+"\n"+"\n"+
                "all Request"+
                "\n"+"\n"+"\n"+"\n"+
                "/clients"+"\n"+
                "/address"+"\n"+
                "/crew"+"\n"+
                "/flight"+"\n"+
                "/person"+"\n"+
                "/shift"+"\n"+
                "/vehicle"+"\n";
