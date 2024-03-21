package shop.mtcoding.blog.board;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BoardResponse {

    @Data
    public static class DetailDTO{
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username;
        private boolean isOwner;
        private List<ReplyDTO> replies = new ArrayList<>();

        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
            this.isOwner = false;
            if (sessionUser != null){
                if (sessionUser.getId() == userId) isOwner = true;
            }

            this.replies = board.getReplies().stream().map(reply -> new ReplyDTO(reply, sessionUser)).toList();
        }

        @Data
        public class ReplyDTO{
            private int id;
            private String comment;
            private int userId; // 댓글 작성자 ID
            private String username; // 댓글 작성자 이름
            private boolean isOwner;

            public ReplyDTO(Reply reply, User sessionUser) {
                this.id = reply.getId();
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername(); // Lazy loading 발동
                this.isOwner = false;
                if (sessionUser != null){
                    if (sessionUser.getId() == userId) isOwner = true;
                }
            }
        }
    }

    @Data
    public static class MainDTO{
        private int id;
        private String title;

        public MainDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }

    @AllArgsConstructor
    @Data
    public static class CountDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private Long replyCount;
    }
}