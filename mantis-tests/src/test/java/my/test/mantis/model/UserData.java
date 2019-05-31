package my.test.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

        @Id
        @Column(name = "id")
        public int id = 0;

        @Column(name = "username")
        public String username;

        @Column(name = "email")
        public String email;

        @Column(name = "password")
        public String password;


        public int getId() {
                return id;
        }

        public String getEmail() {
                return email;
        }

        @Override
        public String toString() {
                return "UserData{" +
                        "username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        '}';
        }

        public String getUsername() {
                return username;
        }

        public String getPassword() {
                return password;
        }

        public UserData withId(int id) {
                this.id = id;
                return this;
        }

        public UserData withEmail(String email) {

                this.email = email;
                return this;
        }

        public UserData withUsername(String username) {
                this.username = username;
                return this;
        }

        public UserData withPassword(String password) {
                this.password = password;
                return this;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                UserData userData = (UserData) o;
                return id == userData.id &&
                        Objects.equals(username, userData.username) &&
                        Objects.equals(password, userData.password);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, username, password);
        }
}
