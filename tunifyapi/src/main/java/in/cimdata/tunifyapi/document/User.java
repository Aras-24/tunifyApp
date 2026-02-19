package in.cimdata.tunifyapi.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;


@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;
    private String password;
    private Role role = Role.USER;
    public enum Role {
        USER, ADMIN
    }
}
