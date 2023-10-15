package practice.Practice.domain.user.presentation;

import practice.Practice.domain.user.presentation.dto.request.LoginRequest;
import practice.Practice.domain.user.presentation.dto.request.SendEmailRequest;
import practice.Practice.domain.user.presentation.dto.request.SignupRequest;
import practice.Practice.domain.user.presentation.dto.response.MyPageResponse;
import practice.Practice.domain.user.service.CreateImgListService;
import practice.Practice.domain.user.service.EmailService;
import practice.Practice.domain.user.service.ProfileUploadService;
import practice.Practice.domain.user.service.UserService;
import practice.Practice.global.security.TokenResponse;
import practice.Practice.infra.service.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final EmailService sendEmailService;
    private final ProfileUploadService profileUploadService;
    private final CreateImgListService createImgListService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupRequest signupRequest) {
        userService.signUp(signupRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestHeader(name = "AUTHORIZATION_HEADER") String refreshToken) {
        return userService.reissue(refreshToken);
    }

    @GetMapping("/myPage")
    public MyPageResponse myPage() {
        return userService.myPage();
    }


    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestBody @Valid SendEmailRequest request) {
        try {
            sendEmailService.sendEmail(request);
            return ResponseEntity.ok("임시 비밀번호가 이메일로 발송되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/profile")
    public void upload(@RequestPart(required = false, value = "image") MultipartFile profileImage) throws IOException {
        profileUploadService.profileUpload(profileImage);
    }

    /*
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/school")
    public void createSchool(@RequestPart(value = "imageList", required = false) List<MultipartFile> multipartFiles) {
        List<String> imgPaths = s3Facade.upload(multipartFiles);
        createImgListService.imgListUpload(imgPaths);
    }*/
}
