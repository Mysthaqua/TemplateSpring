import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ${PACKAGE_NAME}.dto.${NAME}DtoResponse;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ${NAME} extends AbstractEntity {
    @Column(nullable = false)
    @NotBlank(message = "You must specify a name")
    private String name;
    
    public ${NAME}DtoResponse toDtoResponse() {
        return ${NAME}DtoResponse.builder()
                .id(getId())
                .name(getName())
                .build();
    }
}