Muy importante tener MySQL instalado, Como tambien Spring Boot y Java(JDK 11)

# Archivo application.properties para Spring Boot

```
spring.application.name=bitacora
spring.datasource.url="tu localhot"
spring.datasource.username="Nombre usurio"
spring.datasource.password="Tu contaseña"
```
# Configuraciones de JPA e Hibernate

```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

```


# Script SQL

Toma este archivo y ejecuta ya sea en consola o MySQL workbench

```sql
CREATE DATABASE railway;
USE railway;

SHOW TABLES;

INSERT INTO departments (department_name, description, created_at, updated_at)
VALUES 
  ('Desarrollo', 'Departamento de desarrollo de software', NOW(), NOW()),
  ('Marketing', 'Departamento de marketing', NOW(), NOW());

INSERT INTO users (username, password, email, full_name, department_id, role, created_at, updated_at)
VALUES 
  ('admin', '$2b$12$.gAcN8PLcCdRgf2iFMD5tuFF/5Jz7FP34oCOpJg7H0OXsGMVgpJz2', 'admin1@company.com', 'Admin User', 1, 'ADMIN', NOW(), NOW()),
  ('owner', '$2b$12$p4P4AFVgIF/d7dMKXp9Vn./qPTxQ/QUo5yzyMQSo9QXCBvToZW30q', 'owner1@company.com', 'Project Owner', 1, 'PROJECTOWNER', NOW(), NOW()),
  ('user1', '$2b$12$Si7k8bRejkcW0oy.mGZydOEXO7Qxt9IVIn8abCpzxIETPOToLvEza', 'user1@company.com', 'Regular User', 1, 'USER', NOW(), NOW());

INSERT INTO projects (project_name, department_id, description, start_date, end_date, created_at, updated_at)
VALUES 
  ('Proyecto Alpha', 1, 'Desarrollo de aplicación Alpha', '2024-01-01', '2024-12-31', NOW(), NOW()),
  ('Proyecto Beta', 2, 'Lanzamiento de campaña Beta', '2024-02-01', '2024-08-31', NOW(), NOW());

INSERT INTO priorities (priority_level, description, created_at, updated_at)
VALUES 
  ('Alta', 'Prioridad alta para actividades críticas', NOW(), NOW()),
  ('Media', 'Prioridad media para actividades estándar', NOW(), NOW()),
  ('Baja', 'Prioridad baja para actividades no urgentes', NOW(), NOW());

INSERT INTO activitystatuses (status_name, description, created_at, updated_at)
VALUES 
  ('Pendiente', 'Actividad pendiente por realizar', NOW(), NOW()),
  ('En Progreso', 'Actividad en progreso', NOW(), NOW()),
  ('Completada', 'Actividad completada', NOW(), NOW());

INSERT INTO activitytypes (type_name, description, created_at, updated_at)
VALUES 
  ('Desarrollo', 'Actividades relacionadas con desarrollo de software', NOW(), NOW()),
  ('Testing', 'Actividades de pruebas de calidad', NOW(), NOW()),
  ('Revisión', 'Actividades de revisión de proyectos', NOW(), NOW());

INSERT INTO activities (activity_name, description, project_id, priority_id, created_by_user_id, activity_status_id, activity_type_id, created_at, updated_at)
VALUES 
  ('Desarrollo Módulo X', 'Implementación del módulo X', 1, 1, 1, 1, 1, NOW(), NOW()),
  ('Revisión del Módulo X', 'Revisión del código del módulo X', 1, 2, 1, 2, 3, NOW(), NOW()),
  ('Pruebas Módulo Y', 'Pruebas del módulo Y', 1, 1, 1, 1, 2, NOW(), NOW()),
  ('Campaña Publicitaria Z', 'Lanzamiento de la campaña Z', 2, 3, 2, 1, 3, NOW(), NOW());

INSERT INTO activity_status_changes (activity_id, changed_at, changed_by_user_id, status_id, change_comment)
VALUES 
  (1, NOW(), 1, 2, 'Cambio de estado a En Progreso'),
  (2, NOW(), 1, 3, 'Revisión completada');

UPDATE users 
SET password = '$2b$12$.gAcN8PLcCdRgf2iFMD5tuFF/5Jz7FP34oCOpJg7H0OXsGMVgpJz2' 
WHERE username = 'admin';

UPDATE users 
SET password = '$2b$12$p4P4AFVgIF/d7dMKXp9Vn./qPTxQ/QUo5yzyMQSo9QXCBvToZW30q' 
WHERE username = 'owner';

UPDATE users 
SET password = '$2b$12$Si7k8bRejkcW0oy.mGZydOEXO7Qxt9IVIn8abCpzxIETPOToLvEza' 
WHERE username = 'user1';
