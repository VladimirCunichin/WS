package main.Exceptions;

import model.Article;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Article already exists")
public class ArticleDuplicateException extends RuntimeException {
    public ArticleDuplicateException(){

    }
}
