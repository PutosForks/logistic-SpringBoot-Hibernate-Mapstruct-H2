# logistic-SpringBoot-Hibernate-Mapstruct-H2

Если необходимо запустить через postgress, то следующие найстроки вставить в \src\main\resources\application.properties

spring.jpa.database=POSTGRESQL
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=root
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true


запуск на порту 8080 
запросы для клиента:
/clients 
get - выведет список клиентов
post  - создаст клиента
/clients/ID  put  -обновление клиента по id
/clients/ID  delete удаление клиента ID.


json client :

{
  "id":0,
  "address":
  {
    "id":0,
    "address":"address"
  },
  "name":"Ginger",
  "phoneNumber":"+3-000-000"
}
