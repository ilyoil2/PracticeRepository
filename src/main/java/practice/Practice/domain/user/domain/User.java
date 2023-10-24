package practice.Practice.domain.user.domain;

import practice.Practice.infra.StringListConverter;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", length = 4, nullable = false)
    private String username;

    @Column(name = "password", length = 2000, nullable = false)
    private String password;

    @Column(name = "account_id", length = 20, unique = true, nullable = false)
    private String accountId;

    private String profileImageUrl;

    @Convert(converter = StringListConverter.class)
    private List<String> imgList = new ArrayList<>();

    public User(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.accountId = user.getAccountId();
    }

    public void profileUpload(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void modifyProfileUrl(String profileImageUrl){
        this.profileImageUrl = profileImageUrl;
    }

    public void imageListUpload(List<String> profileImageUrl) {
        this.imgList = profileImageUrl;
    }
}
