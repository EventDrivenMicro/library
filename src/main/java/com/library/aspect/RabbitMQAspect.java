package com.library.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.dto.RabbitDTO;
import com.library.rabbitmq.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class RabbitMQAspect {

    private final RabbitMQProducer rabbitMQProducer;

    @Pointcut("execution(* com.library.service.BookService.saveBook(..)) || " +
            "execution(* com.library.service.BookService.deleteBook(..))")
    public void bookSaveAndDeletePointcut() {
    }

    @AfterReturning(pointcut = "bookSaveAndDeletePointcut()", returning = "result")
    public void afterBookSaveAndDelete(JoinPoint joinPoint, Object result) throws JsonProcessingException {
        String methodName = joinPoint.getSignature().getName();

        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(RabbitDTO.builder().result(result).process(methodName).build());

        rabbitMQProducer.sendMessage(message);
    }
}