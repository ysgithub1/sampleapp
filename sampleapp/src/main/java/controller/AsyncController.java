package controller;

import org.hibernate.engine.internal.SessionEventListenerManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import component.AsyncHelper;
import component.Console;
import component.GreetingMessageSender;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;



@Controller
@RequestMapping("/async")

public class AsyncController {
	@Autowired
	AsyncHelper asyncHelper;
	
    @RequestMapping(method = RequestMethod.GET)
    public Callable<String> get(@RequestParam(defaultValue = "0") long waitSec, Model model) {

        Console.println("Start get.");

        model.addAttribute("acceptedTime", LocalDateTime.now());

        // Callableのcallメソッドの中に非同期処理を実装する
        // Callableは関数型インターフェースなのでJava SE 8+ならラムダ式が使えます。
        Callable<String> asyncProcessing = () -> {

            Console.println("Start Async processing.");

            heavyProcessing(waitSec, model);

            Console.println("End Async processing.");

            return "complete";
        };

        Console.println("End get.");

        return asyncProcessing;
    }

    private void heavyProcessing(long waitSec, Model model) {

        if (waitSec == 999) {
            throw new IllegalStateException("Special parameter for confirm error.");
        }

        try {
            TimeUnit.SECONDS.sleep(waitSec);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }

        model.addAttribute("completedTime", LocalDateTime.now());

    }

    @ExceptionHandler(Exception.class)
    public String handleException() {
        return "error";
    }
    
    @RequestMapping(path = "deferred", method = RequestMethod.GET)
    public DeferredResult<String> getReferred(@RequestParam(defaultValue = "0") long waitSec, Model model) {

        Console.println("Start get.");

        model.addAttribute("acceptedTime", LocalDateTime.now());

        // 非同期処理を呼び出す。DeferredResultを非同期処理に引き渡すのがポイント。
        DeferredResult<String> deferredResult = new DeferredResult<>();
        asyncHelper.asyncProcessing(model, waitSec, deferredResult);

        Console.println("End get.");

        return deferredResult; // DeferredResultを返却する
    }
    
    @Autowired
    GreetingMessageSender greetingMessageSender;
    @RequestMapping(path = "greeting", method = RequestMethod.GET)
    public SseEmitter greetinb() throws IOException,InterruptedException {
		SseEmitter emitter =new SseEmitter();
		greetingMessageSender.send(emitter);
        return emitter; 
    }

}