package in.cimdata.tunifyapi.dto;

import in.cimdata.tunifyapi.document.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String id;
    private String email;
    private Role role;

    public enum Role{
        USER,ADMIN
    }
}
