import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ${PACKAGE_NAME}.entity.${NAME};

import java.util.List;

@Repository
public interface ${NAME}Repository extends JpaRepository<${NAME}, Long> {
    List<${NAME}> findAllByName(String name);
}