package main;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import model.Article;
import model.PcPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/")
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "External api error")
})
public class ExternalController {

 //   @Value("${external.url}")
//    private String baseUrl;


    @Autowired
    private final Service articleService;

    public ExternalController(final Service articleService) {
        this.articleService = articleService;
    }
    /*@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all articles"),
            @ApiResponse(code = 404, message = "No articles found")
    })

    @GetMapping("/articles/parts")
    public List<Article> getAllArticles(){
        return articleService.getAllArticles();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get article by it's Id"),
            @ApiResponse(code = 404, message = "article not found")
    })
    @GetMapping("/articles/{id}/parts")
    public Article getArticle(@PathVariable String id){
        return articleService.getArticle(id);

    }
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Successfully added article"),
            @ApiResponse(code = 400, message = "article already exists"),
            @ApiResponse(code = 404, message = "NOT FOUND"),
            @ApiResponse(code = 500, message = "Failed to add an article"),
    })


    @PostMapping( value = "/articles/parts")
    public ResponseEntity<Article> addArticle(@RequestBody Article article){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PcPart[]> response =
                restTemplate.getForEntity(
                        "http://localhost:80/api/parts/1",
                        PcPart[].class);
        PcPart[] parts = response.getBody();
        article.addParts(parts[0]);
        return articleService.addArticle(article);
    }
    @ApiResponses( {
            @ApiResponse(code = 204, message = "Successfully updated article"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "article not found"),
    })


    @PutMapping(value = "/articles/{id}/parts")
    public ResponseEntity updateArticle(@RequestBody Article article,@PathVariable String id) throws Exception{
        articleService.updateArticle(article, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "article deleted by Id"),
            @ApiResponse(code = 404, message = "article not found")
    })
    @DeleteMapping( value = "/articles/{id}/parts")
    public ResponseEntity deleteArticle(@PathVariable String id){
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

*/


    /*@ApiOperation(code = 200, value = "Add a part for a article by it's Id")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @PostMapping(value = "/articles/{id}")
    public ResponseEntity<Article> postPart(
            @ApiParam(value = "Article Id") @PathVariable("id") String articleId,@RequestBody PcPart part) {
        Article article = articleService.postPcPart(articleId, part);
        return ResponseEntity.ok(article);

    }

    @ApiOperation(code = 200, value = "Get article's parts by it's Id")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Part not found")
    })
    @GetMapping(value ="/articles/{id}")
    public ResponseEntity<PcPart> getPart(
            @ApiParam(value = "Part Id") @PathVariable("id") int articleId) {
        PcPart part = articleService.getPcPart(String.valueOf(articleId));
        return ResponseEntity.ok(part);
    }*/
    /*






    @ApiOperation(code = 200, value = "Get all comments of a article by it's Id")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "article not found")
    })
    @GetMapping("/articles/{id}/api/parts/{id}")
    public ResponseEntity<List<PcPart>> getArticleParts(
            @ApiParam(value = "article Id") @PathVariable("id") String articleId) {
        List<PcPart> parts = articleService.getParts(articleId);
        return ResponseEntity.ok(parts);
    }

    @ApiOperation(code = 200, value = "Add a comment for a article by it's Id")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @PostMapping("articles/{id}/api/parts")
    public ResponseEntity<Article> postComment(
            @ApiParam(value = "article Id") @PathVariable("id") String articleId,
            @ApiParam(value = "Comment body") @Valid @RequestBody PcPart part) {
        Article article = articleService.postPart(articleId, part);
        return ResponseEntity.ok(article);
    }*/

/*
    @ApiOperation(code = 200, value = "Update a comment of a article")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request body"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @PutMapping("/articles/{id}/comments")
    public ResponseEntity<article> putDefect(
            @ApiParam(value = "article Id") @PathVariable("id") Long articleId,
            @ApiParam(value = "Comment Id") @RequestParam String commentId,
            @ApiParam(value = "Comment body") @Valid @RequestBody Comment comment) {
        article article = articleService.putComment(articleId, commentId, comment);
        return ResponseEntity.ok(article);
    }

    @ApiOperation(code = 204, value = "Delete a comment of a article")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Comment deleted successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Comment not found")
    })
    @DeleteMapping("articles/comments/{id}")
    public ResponseEntity deleteDefect(
            @ApiParam(value = "Comment Id") @PathVariable("id") String commentId) {
        articleService.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }*/
}
