package main;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import main.Exceptions.PcPartNotFoundException;
import model.Article;
import model.PcPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController

public class Controller {
    @Autowired
    private Service articleService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all articles"),
            @ApiResponse(code = 404, message = "No articles found")
    })
    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get article by it's Id"),
            @ApiResponse(code = 404, message = "article not found")
    })
    @GetMapping("/articles/{id}")
    public Article getArticle(@PathVariable String id) {
        return articleService.getArticle(id);

    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added article"),
            @ApiResponse(code = 400, message = "article already exists"),
            @ApiResponse(code = 404, message = "NOT FOUND"),
            @ApiResponse(code = 500, message = "Failed to add an article"),
    })
    @PostMapping(value = "/articles")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    @ApiResponses({
            @ApiResponse(code = 204, message = "Successfully updated article"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "article not found"),
    })
    @PutMapping(value = "/articles/{id}")
    public ResponseEntity updateArticle(@RequestBody Article article, @PathVariable String id) throws Exception {
        articleService.updateArticle(article, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "article deleted by Id"),
            @ApiResponse(code = 404, message = "article not found")
    })
    @DeleteMapping(value = "/articles/{id}")
    public ResponseEntity deleteArticle(@PathVariable String id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Part not found")
    })
    @GetMapping("/articles/{id}/parts")
    public List<PcPart> getPart(@PathVariable String id) {
        Article article = getArticle(id);
        if(article.getParts().size() <1){
            throw new PcPartNotFoundException();
        }
        return article.getParts();
    }
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Part not found")
    })
    @GetMapping("/articles/parts")
    public List<PcPart> getAllParts() {
        List<Article> list = getAllArticles();
        List<PcPart> partsList = new ArrayList<>();

        for (Article article : list) {
            if (article.getParts() != null) {
                for (PcPart part : article.getParts()) {
                    partsList.add(part);
                }
            }
        }
        if(partsList.size()<1){
            throw new PcPartNotFoundException();
        }
        return partsList;
    }
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Part not found")
    })
    @GetMapping("/parts")
    public List<PcPart> getAllPartsFromOtherService() {
        List<PcPart> list = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PcPart[]> response =
                restTemplate.getForEntity(
                        "http://localhost:80/api/parts",
                        PcPart[].class);
        PcPart[] parts = response.getBody();
        Collections.addAll(list, parts);
        if(list.size()<1){
            throw new PcPartNotFoundException();
        }
        return list;
    }
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Part not found")
    })
    @GetMapping("/parts/{id}")
    public PcPart getPartFromOtherService(@PathVariable String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PcPart[]> response =
                restTemplate.getForEntity(
                        "http://localhost:80/api/parts/" + id,
                        PcPart[].class);
        PcPart[] parts = response.getBody();
        if(parts.length <1){
            throw new PcPartNotFoundException();
        }
        return parts[0];
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @PostMapping("/articles/{id}/parts")
    public ResponseEntity<PcPart> addPartToArticle(@PathVariable String id, @RequestBody int partId) {
        Article article = getArticle(id);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PcPart[]> response =
                restTemplate.getForEntity(
                        "http://localhost:80/api/parts/" + partId,
                        PcPart[].class);
        PcPart[] parts = response.getBody();
        article.addParts(parts[0]);
        return articleService.addPcPart(parts[0], article);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Part deleted successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Comment not found")
    })
    @DeleteMapping(value = "/articles/parts/{id}")
    public ResponseEntity  deletePart(@PathVariable int id) {
        List<Article> list = articleService.getAllArticles();
        boolean flag = false;
        for (Article article : list) {
            for (int i = 0; i < article.getParts().size(); i++) {
                if (article.getParts().get(i).getId() == id) {
                    article.getParts().remove(i);
                    i--;
                    flag = true;
                }
            }
        }
        if(!flag){
                throw new PcPartNotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Part updated successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Comment not found")
    })
    @PutMapping(value = "/articles/{id}/parts")
    public ResponseEntity updatePart(@PathVariable String id, @RequestBody PcPart part, @RequestParam int partId) throws Exception {
        articleService.updatePcPart(part,id,partId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
