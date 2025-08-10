package pe.com.exchange.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pe.com.exchange.model.entity.UserExchangeQuery;

import java.util.Optional;

@ApplicationScoped
public class UserExchangeQueryRepository implements PanacheRepository<UserExchangeQuery> {
    public Optional<UserExchangeQuery> findByDni(String dni) {
        return Optional.ofNullable(find("dni", dni).firstResult());
    }
}
