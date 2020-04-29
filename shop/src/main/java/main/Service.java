package main;

import main.Exceptions.ArticleDuplicateException;
import main.Exceptions.ArticleNotFoundException;
import main.Exceptions.PcPartNotFoundException;
import model.Article;
import model.PcPart;
import model.Response;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service

public class Service {
    RestTemplate restTemplate;
    HttpHeaders httpHeaders;
    HttpEntity<Object> httpEntity;

    private ExternalService externalService;

    Service(){
        this.restTemplate = new RestTemplate();
        this.httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }
    List<PcPart> partsList = new ArrayList<>(Arrays.asList(
       new PcPart(1,"manufacturer1","name1","type1","price1")
    ));
    List<PcPart> partsList2 = new ArrayList<>(Arrays.asList(
       new PcPart(2,"manufacturer2","name2","type2","price2")
    ));
    List<PcPart> partsList3 = new ArrayList<>(Arrays.asList(
       new PcPart(3,"manufacturer3","name3","type3","price3"),
       new PcPart(4,"manufacturer4","name4","type4","price4")
    ));
    List<Article> articleList = new ArrayList<Article>(Arrays.asList(
            new Article("1", "article1", "Desc1",partsList),
            new Article("2", "article3", "Desc2",partsList2),
            new Article("3", "article2", "Desc3",partsList3)
    ));

    public ResponseEntity<Article> addArticle(Article article) {
        for (Article x : articleList) {
            if (x.getId().equals(article.getId())) {
                throw new ArticleDuplicateException();
            }
        }
        if(article.getId() == null){
            article.setId(String.valueOf(articleList.size()+1));
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

    public ResponseEntity<PcPart> addPcPart(PcPart part, Article article) {
        part.setId(article.getParts().size()+1);
        HttpHeaders response = new HttpHeaders();
        response.set("location", "/articles/parts" + part.getId());
        try {
            return ResponseEntity.created(new URI("/articles/parts/" + article.getId())).headers(response).body(part);
        } catch (Exception ex) {
            throw new RuntimeException("Failed creating part");
        }
    }

    public ResponseEntity<Article> updatePcPart(PcPart part, String articleId, int partId){
        Article article = getArticle(articleId);
        List<PcPart> parts = article.getParts();
        boolean flag = false;
        for(int i =0; i < parts.size(); i++){
            if(parts.get(i).getId() == partId){
                parts.set(i,part);
                flag = true;
            }
        }
        if(!flag){
            throw new PcPartNotFoundException();
        }
        return ResponseEntity.ok(article);
    }

}
