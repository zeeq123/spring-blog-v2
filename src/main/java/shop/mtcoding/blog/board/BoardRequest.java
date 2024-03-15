package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardRequest {

    @Data
    public static class UpdateDTO{
        private String title;
        private String content;
    }

    @Data
    public static class SaveDTO{
        private String title;
        private String content;

        // DTO를 클라이언트로 부터 받아서, PC에 전달하기 위해 사용
        // insert 할때에만 만든다 (toEntity)
        public Board toEntity(User user){
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(user)
                    .build();

        }
    }
}
