package theuxzn16.com.github.diabetiq.dto.response;

public record TokenResponseDTO(
        String accessToken,
        String tokenType,
        long expiresIn,
        String refreshToken,
        long refreshExpiresIn
) {
}
