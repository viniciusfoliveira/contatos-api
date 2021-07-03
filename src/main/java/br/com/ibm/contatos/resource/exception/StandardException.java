package br.com.ibm.contatos.resource.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Controller
@RestControllerAdvice
public class StandardException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> tratativaDeCamposNulosOuVazios(MethodArgumentNotValidException e, HttpServletRequest sr){

        StandardError se = new StandardError(HttpStatus.UNPROCESSABLE_ENTITY.value(),e.getBindingResult().getFieldError().getDefaultMessage(), sr.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(se);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<StandardError> tratativaNumerosInteiros(HttpServletRequest rs){

          StandardError s = new StandardError(HttpStatus.BAD_REQUEST.value(), "Permitido somente valores inteiros", rs.getRequestURI());

          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(s);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> tratarMetodoNaoSuportado(HttpServletRequest rs){

           StandardError se = new StandardError(HttpStatus.BAD_REQUEST.value(),"Verifique a rota que está sendo chamada", rs.getRequestURI());

           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(se);
    }
}
