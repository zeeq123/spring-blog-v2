package shop.mtcoding.blog.board;


import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void updateById_test() {
        // given
        int id = 1;
        String title = "제목 수정";
        String content = "내용 수정";

        // when
        boardRepository.updateById(id, title, content);

        // then
        em.flush(); // 실제 코드에서는 필요없음. 트랜잭션이 종료되기 때문
        System.out.println("update : " + boardRepository.findById(id));
    }

    @Test
    public void deleteById_test() {
        // given
        int id = 1;

        // when
        boardRepository.deleteById(id); // delete query 발동함

        // then
        System.out.println(boardRepository.findAll().size());
    }

    @Test
    public void findAllV2_test() {
        List<Board> boardList = boardRepository.findAllV2();
        System.out.println(boardList);
    }

    @Test
    public void randomquery_test(){
        int[] ids = {1,2};

        // select u from User u where u.id in (:id1, :id2)
        String q = "select u from User u where u.id in (";
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length-1){
                q = q + ":id)";
            }else {
                q = q + ":id,";
            }
        }
        System.out.println(q);
    }

    @Test
    public void findAll_custom_test() {
        List<Board> boardList = boardRepository.findAll();

        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        for (int i : userIds){
            System.out.println(i);
        }

        // select * from user_tb where id in (3,2,1) - distinct
    }

    @Test
    public void findAll_lazyloading_test() {
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername()); // lazy loading
        });
    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardRepository.findAll();

        // then
    }


    @Test
    public void findById_test(){
        int id = 1;

        boardRepository.findById(id);
    }
}
