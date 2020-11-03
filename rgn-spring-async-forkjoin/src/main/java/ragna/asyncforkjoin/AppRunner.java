package ragna.asyncforkjoin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {
  private final GitHubLookupService gitHubLookupService;

  @Override
  public void run(String... args) throws Exception {
    log.info("command line runner - start");

    final var start = System.currentTimeMillis();

    final var page1 = gitHubLookupService.findUser("ragnarokkrr");
    final var page2 = gitHubLookupService.findUser("PivotalSoftware");
    final var page3 = gitHubLookupService.findUser("CloudFoundry");
    final var page4 = gitHubLookupService.findUser("Spring-Projects");

    CompletableFuture.allOf(page1, page2, page3, page4).join();

    log.info("Elapsed time: {}", System.currentTimeMillis() - start);
    log.info("\tp1-> {}", page1.get());
    log.info("\tp2-> {}", page2.get());
    log.info("\tp3-> {}", page3.get());
    log.info("\tp4-> {}", page4.get());
  }
}
