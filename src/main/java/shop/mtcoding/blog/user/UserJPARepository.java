package shop.mtcoding.blog.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 자동 컴퍼넌트 스캔이 된다. (JPARepository)
public interface UserJPARepository extends JpaRepository<User, Integer> {

    // 복잡한 query 는 queryRepository 사용
//    @Query("select u from User u where u.username = :username and u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
