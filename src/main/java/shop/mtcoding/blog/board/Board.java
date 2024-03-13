package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@ToString
@NoArgsConstructor
@Data
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;

    @CreationTimestamp // pc -> db (날짜 주입)
    private Timestamp createdAt;

    public String getTime(){
        return MyDateUtil.timestampFormat(createdAt);
    }

    public Board(String title, String content, String username){
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public void update(BoardRequest.UpdateDTO reqDTO){
        this.title = reqDTO.getTitle();
        this.content = reqDTO.getContent();
        this.username = reqDTO.getUsername();
    }
}
