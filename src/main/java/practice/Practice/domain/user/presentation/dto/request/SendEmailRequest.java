package practice.Practice.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SendEmailRequest {

    private String address;

    private String title;

    private String messege;

}
