import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${PACKAGE_NAME}.dto.${NAME}DtoRequest;
import ${PACKAGE_NAME}.dto.${NAME}DtoResponse;
import ${PACKAGE_NAME}.entity.${NAME};
import ${PACKAGE_NAME}.repository.${NAME}Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ${NAME}Service {
    private final ${NAME}Repository repository;

    public ${NAME}Service(${NAME}Repository repository) {
        this.repository = repository;
    }

    public List<${NAME}> findAll() {
        return repository.findAll();
    }

    public List<${NAME}DtoResponse> findAllDto() {
        return findAll().stream().map(${NAME}::toDtoResponse).toList();
    }

    public ${NAME} findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("Test not found with id %s", id)));
    }

    public ${NAME}DtoResponse findDtoById(Long id) {
        return findById(id).toDtoResponse();
    }

    public List<${NAME}> findAllByName(String name) {
        return repository.findAllByName(name);
    }

    public List<${NAME}DtoResponse> findAllDtoByName(String name) {
        return findAllByName(name).stream().map(${NAME}::toDtoResponse).toList();
    }

    @Transactional
    public ${NAME}DtoResponse create(${NAME}DtoRequest ${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoRequest) {
        return repository.save(${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoRequest.toEntity()).toDtoResponse();
    }

    @Transactional
    public ${NAME}DtoResponse update(Long id, ${NAME}DtoRequest ${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoRequest) {
        ${NAME} ${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)} = findById(id);
        ${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}.setName(${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoRequest.name());
        return repository.save(${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}).toDtoResponse();
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}