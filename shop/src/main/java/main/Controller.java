package main;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class Controller {
    @Autowired
    private Service articleService;
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all articles"),
            @ApiResponse(code = 404, message = "No articles found")
    })
    @GetMapping("/articles")
    public List<Article> getAllArticles(){
        return articleService.getAllArticles();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get article by it's Id"),
            @ApiResponse(code = 404, message = "article not found")
    })
    @GetMapping("/articles/{id}")
    public Article getArticle(@PathVariable String id){
        return articleService.getArticle(id);

    }
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Successfully added article"),
            @ApiResponse(code = 400, message = "article already exists"),
            @ApiResponse(code = 404, message = "NOT FOUND"),
            @ApiResponse(code = 500, message = "Failed to add an article"),
    })
    @PostMapping( value = "/articles")
    public void addArticle(@RequestBody Article article){
        articleService.addArticle(article);
    }
    @ApiResponses( {
            @ApiResponse(code = 200, message = "Successfully updated article"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "article not found"),
    })
    @PutMapping(value = "/articles/{id}")
    public void updateArticle(@RequestBody Article article,@PathVariable String id){
        articleService.updateArticle(article, id);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "article deleted by Id"),
            @ApiResponse(code = 404, message = "article not found")
    })
    @DeleteMapping( value = "/articles/{id}")
    public void deleteArticle(@PathVariable String id){
        articleService.deleteArticle(id);
    }

}
