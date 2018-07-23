package component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
public class AsyncHelper {
//    @Autowired
//    private TaskExecutor taskExecutor;
    @Autowired
    private AsyncTest asyncTest;
    @Async // コメントアウトすると、メインスレッドで実行されるようになります。非同期処理はなされなくなりますが、コンパイルエラーにはなりません。
    public Future<String> test() throws InterruptedException {
  

        // 非同期処理を並列実行することによって、処理時間が短縮されることを分かりやすくするための sleep です。
        Thread.sleep(1000L);
        String test ="test";
        return new AsyncResult<String>(test);
    }
	
    @Async() // 非同期実行するメソッドに@Asyncを付与する
    public void asyncProcessing(Model model, long waitSec, DeferredResult<String> deferredResult) {
        Console.println("Start Async processing.");

        if (waitSec == 999) {
            // 例外はスローではなく、setErrorResultメソッドを使用する
            // throw new IllegalStateException("Special parameter for confirm error.");
            deferredResult.setErrorResult(new IllegalStateException("Special parameter for confirm error."));
            return;
        }
        
        sleep(waitSec);

        model.addAttribute("completedTime", LocalDateTime.now());

        deferredResult.setResult("complete"); // 処理結果はDeferredResult#setResultメソッドを呼び出して設定する。

        Console.println("End Async processing.");
    }

    private void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }
    
    @Async
    public void streaming(ResponseBodyEmitter emitter, long eventNumber, long intervalSec) throws IOException {
        Console.println("Start Async streaming processing.");

        for (long i = 1; i <= eventNumber; i++) {
            sleep(intervalSec);
            emitter.send("msg" + i + "\r\n");
        }

        emitter.complete();

        Console.println("End Async streaming processing.");
    }
    @Async
    public void sse(SseEmitter emitter, long eventNumber, long intervalSec) throws IOException {
        Console.println("Start Async sse processing.");

        for (long i = 1; i <= eventNumber; i++) {
            sleep(intervalSec);
            emitter.send("msg sse" + i);
        }

        emitter.complete();

        Console.println("End Async sse processing.");
    }

    
    
}