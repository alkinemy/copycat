package al.copycat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class CopycatApplication {

	private static final String PID_FILE_NAME = "copycat.pid";

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(CopycatApplication.class);
		application.addListeners(new ApplicationPidFileWriter(PID_FILE_NAME));
		application.run(args);
	}

}
