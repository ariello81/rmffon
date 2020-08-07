package pl.ryzykowski.rmffon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RmffonSongsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmffonSongsApplication.class, args);
	}

}
