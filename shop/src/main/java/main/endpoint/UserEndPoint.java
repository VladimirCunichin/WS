package main.endpoint;
import main.Exceptions.PcPartNotFoundException;
import main.Service;
import io.spring.guides.gs_producing_web_service.PcPart;
import io.spring.guides.gs_producing_web_service.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import io.spring.guides.gs_producing_web_service.GetArticleRequest;
import io.spring.guides.gs_producing_web_service.GetArticleResponse;
import io.spring.guides.gs_producing_web_service.UpdateArticleResponse;
import io.spring.guides.gs_producing_web_service.UpdateArticleRequest;
import io.spring.guides.gs_producing_web_service.*;

import java.util.List;

@Endpoint
public class UserEndPoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private Service articleService;

    @Autowired
    public UserEndPoint(Service articleRepository) {
        this.articleService = articleRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getArticleRequest")
    @ResponsePayload
    public GetArticleResponse getArticle(@RequestPayload GetArticleRequest request) {
        GetArticleResponse response = new GetArticleResponse();
        response.setArticle(articleService.getArticle(request.getId()));

        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateArticleRequest")
    @ResponsePayload
    public UpdateArticleResponse updateArticle(@RequestPayload UpdateArticleRequest request) {
        UpdateArticleResponse response = new UpdateArticleResponse();
        Article article = articleService.getArticle(request.getId());
        PcPart part = new PcPart();
        article.setName(request.getName());
        article.setId(request.getNewid());
        article.setPartId(request.getPartId());
        article.setDescription(request.getDescription());
        part.setId(request.getPcpartid());
        part.setManufacturer(request.getManufacturer());
        part.setPrice(request.getPrice());
        part.setType(request.getType());
        part.setName(request.getPcpartname());
        article.setPart(part);
        articleService.updatePcPart(part,article.getPartId());
        response.setArticle(articleService.getArticle(article.getId()));

        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteArticleRequest")
    @ResponsePayload
    public DeleteArticleResponse deleteArticle(@RequestPayload DeleteArticleRequest request) {
        DeleteArticleResponse response = new DeleteArticleResponse();
        response.setArticle(articleService.getArticle(request.getId()));
        RestTemplate restTemplate = new RestTemplate();
        try{
            restTemplate.delete("http://computer-parts:5000/api/parts/"+ articleService.getArticle(request.getId()).getPart().getId());
        }
        catch(HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw new PcPartNotFoundException();
            }
            throw new PcPartNotFoundException();
        }
        articleService.deleteArticle(request.getId());


        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addArticleRequest")
    @ResponsePayload
    public AddArticleResponse addArticle(@RequestPayload AddArticleRequest request) {
        AddArticleResponse response = new AddArticleResponse();
        Article article = new Article();
        PcPart part = new PcPart();


        article.setName(request.getName());
        article.setId(request.getId());
        article.setPartId(request.getPartId());
        article.setDescription(request.getDescription());
        part.setId(request.getPcpartid());
        part.setManufacturer(request.getManufacturer());
        part.setPrice(request.getPrice());
        part.setType(request.getType());
        part.setName(request.getPcpartname());
        article.setPart(part);
        articleService.addArticle(article);
        /*Article tempa;
        tempa = articleService.getArticle(article.getId());
        article.setPartId(tempa.getPart().getId());*/

        response.setArticle(articleService.getArticle(request.getId()));

        return response;
    }

}
