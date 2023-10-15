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

    private String email;

    private String userName;

    private String password;

    private String nickName;

    private String profileImageUrl;

    @Convert(converter = StringListConverter.class)
    private List<String> imgPath = new ArrayList<>();

    public User(User user) {
        this.email = user.getEmail();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.nickName = user.getNickName();
    }

    public void profileUpload(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void modifyProfileUrl(String profileImageUrl){
        this.profileImageUrl = profileImageUrl;
    }

    public void imageListUpload(List<String> profileImageUrl) {
        this.imgPath = profileImageUrl;
    }
}
