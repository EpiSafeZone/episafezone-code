    package com.example.episafezone.models;

    import jakarta.persistence.*;

    @Entity(name="tutor")
    public class Tutor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;
        private String surname;
        private String email;
        private String password;
        @Column(columnDefinition = "TINYINT(1)")
        private Boolean notifications;

        public Tutor(String name, String surname, String email, String password, Boolean notifications) {
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.password = password;
            this.notifications = notifications;
        }

        public Tutor() {}

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public Boolean getNotifications() {
            return notifications;
        }

        public void setNotifications(Boolean notifications) {
            this.notifications = notifications;
        }

    }
