import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import ${PACKAGE_NAME}.entity.${NAME};

@Builder
public record ${NAME}DtoRequest(@NotBlank(message = "You must specify a name") String name) {
    public ${NAME} toEntity() {
        return ${NAME}.builder()
                .name(name)
                .build();
    }

    public static ${NAME}DtoRequest empty() {
        return new ${NAME}DtoRequest("");
    }
}