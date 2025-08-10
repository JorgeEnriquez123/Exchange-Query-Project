package pe.com.exchange.proxy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRate {
    private String fecha;
    private double sunat;
    private double compra;
    private double venta;
}
