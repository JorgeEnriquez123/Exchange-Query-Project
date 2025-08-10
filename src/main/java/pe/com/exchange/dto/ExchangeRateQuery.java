package pe.com.exchange.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExchangeRateQuery {
    private String fecha;
    private double sunat;
    private double compra;
    private double venta;
}
