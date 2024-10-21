package com.example.episafezone.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
public class DataBaseService {
    private final JdbcTemplate jdbcTemplate;

    public DataBaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void resetDatabase() {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=0;");
        jdbcTemplate.execute("TRUNCATE TABLE crisis;");
        jdbcTemplate.execute("TRUNCATE TABLE has_manifestation;");
        jdbcTemplate.execute("TRUNCATE TABLE manifestation;");
        jdbcTemplate.execute("TRUNCATE TABLE medication;");
        jdbcTemplate.execute("TRUNCATE TABLE patient;");
        jdbcTemplate.execute("TRUNCATE TABLE shared_with;");
        jdbcTemplate.execute("TRUNCATE TABLE tutor;");
        jdbcTemplate.execute("TRUNCATE TABLE tutor_of;");
        jdbcTemplate.execute("TRUNCATE TABLE remainder;");

        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1;");

        populateDatabase();
    }

    public void populateDatabase() {
        jdbcTemplate.update("INSERT INTO `tutor` VALUES (1,'Mario','Garcia','MG','a@a','1',1),(2,'Luisa','Garcia','Lu','b@b','luisa',1),(3,'Antonio','Pardo','Antopato','Antopato@gmail.com','Antopato',0);\n");
        jdbcTemplate.update("INSERT INTO `patient` VALUES (1,'Pepe','Garcia',150,50,NULL,NULL,NULL),(2,'Cesar','Gimeno',143,42,NULL,NULL,NULL);");
        jdbcTemplate.update("INSERT INTO `manifestation` VALUES (1,'tonico-clonica','Convulsiones'),(2,'ausencias','El paciente se que en blanco mirando un punto fijo y no responde');");
        jdbcTemplate.update("INSERT INTO `has_manifestation` VALUES (1,1,1),(2,1,2),(3,2,1),(4,2,2);");
        jdbcTemplate.update("INSERT INTO `medication` VALUES (1,'ibuprofeno',400,'mg',_binary '\\0',1),(2,'paracetamol',500,'mg',_binary '\\0',1);");
        jdbcTemplate.update("INSERT INTO `shared_with` VALUES (1,3,1,2),(2,1,2,1);");
        jdbcTemplate.update("INSERT INTO `tutor_of` VALUES (1,1,1,_binary '\u0001'),(2,2,1,_binary '\\0'),(3,3,2,_binary '\u0001');");

    }
}
