package pl.biologicznieczynny.diycosmeticsdatabase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class SpringDataRESTConfig implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    public SpringDataRESTConfig(EntityManager entityManager) {this.entityManager = entityManager;}

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        List<Class> entityClasses = entityManager.getMetamodel()
                .getEntities()
                .stream()
                .map(e -> e.getJavaType())
                .collect(Collectors.toList());

        config.exposeIdsFor(entityClasses.toArray(new Class[0]));
    }
}
