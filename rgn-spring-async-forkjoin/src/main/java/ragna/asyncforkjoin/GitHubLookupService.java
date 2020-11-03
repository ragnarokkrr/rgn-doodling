package ragna.asyncforkjoin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class GitHubLookupService {
  private final RestTemplate restTemplate;

  @Async
  public CompletableFuture<User> findUser(String username) throws InterruptedException {
    log.info("Looking up `{}`", username);
    final var url = String.format("https://api.github.com/users/%s", username);
    final var user = restTemplate.getForObject(url, User.class);
    log.info("Waiting 3s `{}`", username);
    TimeUnit.SECONDS.sleep(3);
    return CompletableFuture.completedFuture(user);
  }
}
