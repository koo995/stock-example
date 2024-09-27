package test.stock_my_example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import test.stock_my_example.domain.Stock;

/**
 * 실제로 사용할때는 dataSource을 분리할 것을 권장.
 * 안그러면 커넥션 풀이 부족해져서 다른 서비스에도 영향을 끼친다.
 * 실무에서는 dataSource을 분리해서 사용할 것을 추천.
 * 여기서 편의성을 위해 Stock엔티티를 사용한다. 실무에서는 별도의 JDBC사용해야함.
 *
 * 명령어를 모두 작성했다면 실제 로직 전후로 락 획득 해제를 해줘야해서 facade 클래스를 추가해준다.
 */
public interface LockRepository extends JpaRepository<Stock, Long> {
    @Query(value = "select get_lock(:key, 3000)", nativeQuery = true)
    void getLock(String key);

    @Query(value = "select release_lock(:key)", nativeQuery = true)
    void releaseLock(String key);
}
