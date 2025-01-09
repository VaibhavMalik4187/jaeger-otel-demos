package com.demo.java_agent;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.instrumentation.annotations.WithSpan;



@RestController
public class RollController {
    private static final Logger logger = LoggerFactory.getLogger(RollController.class);

    @WithSpan
    @GetMapping("/rolldice")
    public String index(@RequestParam("player") @SpanAttribute("player") Optional<String> player) {
        int result = this.getRandomNumber(1, 6);
        if (player.isPresent()) {
            logger.info("{} is rolling the dice: {}", player.get(), result);
        } else {
            logger.info("Anonymous player is rolling the dice: {}", result);
        }
        return Integer.toString(result);
    }

    @WithSpan
    @GetMapping("/factorial")
    public long calculateFactorial(@RequestParam("number") @SpanAttribute("number") int number) {
        Span span = Span.current();
        span.setAttribute("Number", String.valueOf(number));
        return factorial(number);
    }

    @WithSpan
    private long factorial(@SpanAttribute("number") int number) {
        if (number < 1) {
            return 1;
        }
        return number * factorial(number - 1);
    }

    @WithSpan
    public int getRandomNumber(@SpanAttribute("min") int min, @SpanAttribute("max") int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}