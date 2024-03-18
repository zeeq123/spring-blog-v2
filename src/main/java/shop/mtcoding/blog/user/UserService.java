package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;

import java.util.Optional;

@RequiredArgsConstructor
@Service // IOC 등록
public class UserService {

    private final UserJPARepository userJPARepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO reqDTO){
        // 1. 유효성 검사 (컨트롤러 책임)

        // 2. 유저네임 중복검사 (서비스 체크) - DB연결이 필요함
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());
        if (userOP.isPresent()){
            throw new Exception400("중복된 유저네임입니다.");
        }

        userJPARepository.save(reqDTO.toEntity());
    }

}
