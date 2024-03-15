package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.board.Board;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    @Transactional
    public User updateById(int id, String password, String email){
        User user = findById(id);
        user.setPassword(password);
        user.setEmail(email);

        return findById(id);
    }

    public User findById(int id){
        // id, title, content, user_id(이질감), created_at
        User user = em.find(User.class, id);
        return user;
    }

    @Transactional
    public User save(User user){
        em.persist(user);
        return user;
    }

    public User findByUsernameAndPassword(String username, String password){
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        return (User) query.getSingleResult();

    }
}
