package authorization;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import authorization.users.TacoUser;
import authorization.users.UserRepository;

@EnableJpaRepositories(basePackages = {"authorization.users"})
@SpringBootApplication
public class AuthServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthServerApplication.class, args);
  }

  @Bean
  public ApplicationRunner dataLoader(
          UserRepository repo, PasswordEncoder encoder) {
    return args -> {
      repo.save(
          new TacoUser("habuma", encoder.encode("password"), "ROLE_ADMIN"));
      repo.save(
          new TacoUser("tacochef", encoder.encode("password"), "ROLE_ADMIN"));
    };
  }

}
