package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    // TODO: 글목록 조회 API 필요
    // TODO: 글 상세보기 API 필요
    // TODO: 글조회 API 필요
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BoardRequest.UpdateDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글수정(id, sessionUser.getId(), reqDTO);
        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id){
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id, sessionUser.getId());
        return "redirect:/";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글쓰기(reqDTO, sessionUser);

        return "redirect:/";
    }


}
