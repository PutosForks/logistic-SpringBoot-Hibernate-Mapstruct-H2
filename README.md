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


#postman/json 
in src\test\resources\json
#
#
#
"get /clients - вывод всех клиетов сохраненных в базе"+ "\n"
                +"get /clients/5 - вывод всех клиета с ID 5 "+ "\n"
                +"post /clients/ - сохранение переданного Json в объект и в базу" +"\n"
                +"put /clients/ID - обновление переданого в Json обекта по ID" +"\n"
                +"delete /clients/ID - удаление клиента по ID"+
                "\n"+
                "all entitys - Client\n" +
                "     Address\n" +
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
