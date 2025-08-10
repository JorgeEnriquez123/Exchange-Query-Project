package pe.com.exchange.utils.exceptions.handler;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorMessage {
    private int code;
    private String message;
}
