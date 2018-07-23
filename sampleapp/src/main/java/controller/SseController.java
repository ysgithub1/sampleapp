package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import component.AsyncHelper;
import component.Console;

import java.io.IOException;

@Controller
@RequestMapping("/sse")
public class SseController {

    @Autowired
    AsyncHelper asyncHelper;

    @RequestMapping(method = RequestMethod.GET)
    public SseEmitter sse(@RequestParam(defaultValue = "1") long eventNumber, @RequestParam(defaultValue = "0") long intervalSec) throws IOException {
        Console.println("Start get.");

        SseEmitter emitter = new SseEmitter();
        asyncHelper.sse(emitter, eventNumber, intervalSec);

        Console.println("End get.");
        return emitter;
    }

}