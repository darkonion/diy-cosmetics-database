package pl.biologicznieczynny.diycosmeticsdatabase.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationBean {

    private String message;

    @Override
    public String toString() {
        return String.format("Success [message=%s]", message);
    }
}
