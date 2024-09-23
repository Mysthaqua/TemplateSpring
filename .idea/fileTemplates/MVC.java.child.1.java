import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ${PACKAGE_NAME}.dto.${NAME}DtoRequest;
import ${PACKAGE_NAME}.dto.${NAME}DtoResponse;
import ${PACKAGE_NAME}.service.${NAME}Service;

import java.util.List;

@RestController
@RequestMapping("/api/${NAME.replaceAll("([a-z])([A-Z])", "$1-$2").toLowerCase()}")
public class ${NAME}RestController {
    private final ${NAME}Service service;

    public ${NAME}RestController(${NAME}Service service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<${NAME}DtoResponse>> getAll(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(name == null ? service.findAllDto() : service.findAllDtoByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<${NAME}DtoResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findDtoById(id));
    }

    @PostMapping
    public ResponseEntity<${NAME}DtoResponse> create(@Validated @RequestBody ${NAME}DtoRequest ${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<${NAME}DtoResponse> update(@PathVariable("id") Long id, @Validated @RequestBody ${NAME}DtoRequest ${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoRequest) {
        return ResponseEntity.ok(service.update(id, ${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}