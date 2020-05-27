package main;

import main.Exceptions.ArticleDuplicateException;
import main.Exceptions.ArticleNotFoundException;
import main.Exceptions.PcPartDuplicateException;
import main.Exceptions.PcPartNotFoundException;
import io.spring.guides.gs_producing_web_service.PcPart;
import io.spring.guides.gs_producing_web_service.Article;

import model.Response;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.util.*;


@org.springframework.stereotype.Service

public class Service {
    RestTemplate restTemplate;
    HttpHeaders httpHeaders;
    int index = 4;
    HttpEntity<Object> httpEntity;

    private ExternalService externalService;

    Service(){
        this.restTemplate = new RestTemplate();
        Article test = new Article();
        test.setId("1");
        test.setDescription("testdesc");
        test.setName("name1");
        test.setPartId(1);
        articleList.add(test);
        this.httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    List<Article> articleList = new ArrayList<>();
            /*(Arrays.asList(
            new Article("1", "article1", "Desc1",1),
            new Article("2", "article3", "Desc2",2),
            new Article("3", "article2", "Desc3",3)
    ));*/

    public ResponseEntity<Article> addArticle(Article article) {
        for (Article x : articleList) {
            if (x.getId().equals(article.getId())) {
                throw new ArticleDuplicateException();
            }
        }
        if(article.getId() == null){
            article.setId(String.valueOf(articleList.size()+1));
        }
        article.setPartId(index);
        articleList.add(article);
        addPart(article.getPart());
        HttpHeaders response = new HttpHeaders();
        response.set("location", "/articles/" + article.getId());
        try {
            return ResponseEntity.created(new URI("/articles/" + article.getId())).headers(response).body(article);
        } catch (Exception ex) {
            throw new RuntimeException("Failed creating article");
        }
    }

    public List<Article> getAllArticles() {
        for(Article article: articleList){
            article.setPart(getPartByIdFromOtherS(article.getPartId()));
        }
        return articleList;

    }

    public Article getArticle(String id) {
        articleList.stream().filter(t -> t.getId().equals(id)).findFirst().orElseThrow(ArticleNotFoundException::new);
        Article result = articleList.stream().filter(t -> t.getId().equals(id)).findFirst().get();
        result.setPart(getPartByIdFromOtherS(result.getPartId()));
        return result;
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

    public PcPart getPartByIdFromOtherS(long id){
        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<PcPart[]> response =
                    restTemplate.getForEntity(
                            "http://computer-parts:5000/api/parts/" + id,
                            PcPart[].class);
            PcPart[] parts = response.getBody();
            assert parts != null;
            return parts[0];
        }

        catch (HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw new PcPartNotFoundException();
            }
        }
        return new PcPart();
    }

    public List<PcPart> getAllPartsFromOtherS(){
        List<PcPart> list = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PcPart[]> response =
                restTemplate.getForEntity(
                        "http://computer-parts:5000/api/parts",
                        PcPart[].class);
        PcPart[] parts = response.getBody();
        Collections.addAll(list, parts);
        if (list.size() < 1) {
            throw new PcPartNotFoundException();
        }
        return list;
    }

    public ResponseEntity<PcPart> addPart(PcPart part){
        //List<PcPart> list = getAllPartsFromOtherS();
        /*for(PcPart temp: list){
            if(part.getId() == temp.getId()){
                throw new PcPartDuplicateException();
            }
        }*/
        index++;
        RestTemplate restTemplate = new RestTemplate();
        try{
            PcPart result = restTemplate.postForObject("http://computer-parts:5000/api/parts",part,PcPart.class);
        }
        catch(Exception e){
            throw new RuntimeException("Failed to add part");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    public ResponseEntity<PcPart> updatePcPart(PcPart part, long partId){
        try{
            restTemplate.put("http://computer-parts:5000/api/parts/" +partId,part, PcPart.class);
        }
        catch(Exception e){
            throw new RuntimeException("Failed to update part");
        }
        return ResponseEntity.ok(part);
    }

}
