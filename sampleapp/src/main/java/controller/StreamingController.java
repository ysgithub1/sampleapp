package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import component.AsyncHelper;
import component.Console;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/streaming")
public class StreamingController {

    @Autowired
    AsyncHelper asyncHelper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseBodyEmitter streaming(@RequestParam(defaultValue = "1") long eventNumber, @RequestParam(defaultValue = "0") long intervalSec) throws IOException {
        Console.println("Start get.");

        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        asyncHelper.streaming(emitter, eventNumber, intervalSec);

        Console.println("End get.");
        return emitter;
    }
    @RequestMapping(path = "direct", method = RequestMethod.GET)
    public StreamingResponseBody directStreaming(@RequestParam(defaultValue = "1") long eventNumber, @RequestParam(defaultValue = "0") long intervalSec) throws IOException {
        Console.println("Start get.");

        // StreamingResponseBodyのwriteToメソッドの中に非同期処理を実装する
        // StreamingResponseBodyは関数型インターフェースなのでJava SE 8+ならラムダ式が使えます。
        StreamingResponseBody responseBody = outputStream -> {
            Console.println("Start Async processing.");

            if (intervalSec == 999) {
                throw new IllegalStateException("Special parameter for confirm error.");
            }

            for (long i = 1; i <= eventNumber; i++) {
                try {
                    TimeUnit.SECONDS.sleep(intervalSec);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
                outputStream.write(("msg" + i + "\r\n").getBytes());
                outputStream.flush();
            }

            Console.println("End Async processing.");
        };

        Console.println("End get.");
        return responseBody;
    }

}