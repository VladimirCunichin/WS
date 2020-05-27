package main;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import main.Exceptions.ExternalApiException;
import main.Exceptions.PcPartDuplicateException;
import main.Exceptions.PcPartNotFoundException;
import io.spring.guides.gs_producing_web_service.PcPart;
import io.spring.guides.gs_producing_web_service.Article;

import model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
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
    public PcPart getPart(@PathVariable String id) {
        Article article = getArticle(id);
        long partId = article.getPartId();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PcPart[]> response =
                restTemplate.getForEntity(
                        "http://computer-parts:5000/api/parts/" + partId,
                        PcPart[].class);
        PcPart[] parts = response.getBody();
        if (parts[0].getId() == 0) {
            throw new PcPartNotFoundException();
        }
        return parts[0];
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Part not found")
    })
    @GetMapping("/articles/parts")
    public List<PcPart> getAllParts() {
        List<Article> list = getAllArticles();
        List<Long> partsId = new ArrayList<>();
        List<PcPart> partsList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        for (Article article : list) {
            partsId.add(article.getPartId());
        }
        for (long partId : partsId) {
            if (partId == 0) {
                throw new PcPartNotFoundException();
            }
            ResponseEntity<PcPart[]> response =
                    restTemplate.getForEntity(
                            "http://computer-parts:5000/api/parts/" + partId,
                            PcPart[].class);
            PcPart[] parts = response.getBody();

            partsList.add(parts[0]);

        }
        List myUniqueList = partsList.stream().distinct().collect(Collectors.toList());
        return myUniqueList;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Part not found")
    })
    @GetMapping("/parts")
    public List<PcPart> getAllPartsFromOtherService() {
        return articleService.getAllPartsFromOtherS();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Part not found")
    })
    @GetMapping("/parts/{id}")
    public PcPart getPartFromOtherServiceByPartId(@PathVariable long id) {
        return articleService.getPartByIdFromOtherS(id);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added part"),
            @ApiResponse(code = 400, message = "partId already exists"),
            @ApiResponse(code = 404, message = "NOT FOUND"),
            @ApiResponse(code = 500, message = "Failed to add an part"),
    })
    @PostMapping("/api/parts")
    public ResponseEntity<PcPart> addPartToOtherService(@RequestBody PcPart part) {
        return articleService.addPart(part);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @PostMapping("/articles/{id}/parts")
    public ResponseEntity addPartToArticle(@PathVariable String id, @RequestBody long partId) {
        Article article = getArticle(id);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<PcPart[]> response =
                    restTemplate.getForEntity(
                            "http://computer-parts:5000/api/parts/" + partId,
                            PcPart[].class);
            PcPart[] parts = response.getBody();
            article.setPartId(parts[0].getId());
            return ResponseEntity.ok(parts[0]);//articleService.addPcPart(parts[0], article);

        } catch (final HttpClientErrorException e) {
            if (e.getResponseBodyAsString().contains("404")) {
                throw new PcPartNotFoundException();
            }
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Part deleted successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "part not found")
    })
    @DeleteMapping(value = "/api/parts/{id}")
    public ResponseEntity  deletePart(@PathVariable long id) {
        RestTemplate restTemplate = new RestTemplate();
        try{
            restTemplate.delete("http://computer-parts:5000/api/parts/"+id);
        }
        catch(HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw new PcPartNotFoundException();
            }
            throw new PcPartNotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Part updated successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Part not found")
    })
    @PutMapping(value = "/api/parts/{id}")
    public ResponseEntity updatePart(@PathVariable long id, @RequestBody PcPart part) {
        PcPart temp = getPartFromOtherServiceByPartId(id);
        part.setId(temp.getId());
        articleService.updatePcPart(part,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
