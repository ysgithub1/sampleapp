package component;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
@Component
public class AsyncTest {
	@Async
	public void run() throws IOException,InterruptedException{

		TimeUnit.SECONDS.sleep(10);
		System.out.println( Thread.currentThread()+"非同期処理完了");

	}

}
