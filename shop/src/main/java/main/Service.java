package main;

import main.Exceptions.ArticleDuplicateException;
import main.Exceptions.ArticleNotFoundException;
import model.Article;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service

public class Service {

    List<Article> articleList = new ArrayList<Article>(Arrays.asList(
            new Article("1", "article1", "Desc1"),
            new Article("2", "article3", "Desc2"),
            new Article("3", "article2", "Desc3")
    ));

    public ResponseEntity<Article> addArticle(Article article) {
        for (Article x : articleList) {
            if (x.getId().equals(article.getId())) {
                throw new ArticleDuplicateException();
            }
        }
        articleList.add(article);
        HttpHeaders response = new HttpHeaders();
        response.set("location", "/articles/" + article.getId());
        try {
            return ResponseEntity.created(new URI("/articles/" + article.getId())).headers(response).body(article);
        } catch (Exception ex) {
            throw new RuntimeException("Failed creating article");
        }
    }

    public List<Article> getAllArticles() {
        return articleList;
    }

    public Article getArticle(String id) {
        articleList.stream().filter(t -> t.getId().equals(id)).findFirst().orElseThrow(ArticleNotFoundException::new);
        return articleList.stream().filter(t -> t.getId().equals(id)).findFirst().get();

    }

    public ResponseEntity updateArticle(Article art, String id) {
        boolean exists = false;
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article.getId().equals(id)) {
                exists = true;
                articleList.set(i, art);
                //return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        if(!exists){
            throw new ArticleNotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> deleteArticle(String id) {
        boolean exists = false;
        for (Article x : articleList) {
            if (x.getId().equals(id)) {
                exists = true;
            }
        }
        if (exists) {
            articleList.removeIf(t -> t.getId().equals(id));
        } else {
            throw new ArticleNotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
