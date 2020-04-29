package main;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import main.Exceptions.ArticleNotFoundException;

import main.Exceptions.ExternalApiException;
import main.Exceptions.PcPartNotFoundException;
import model.Article;
import model.PcPart;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class ExternalService {
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;
    private HttpEntity<Object> httpEntity;
    private String basePartResourceURL="http://localhost/api/parts/1";

    public PcPart addArticlePcPart(int id) {
        PcPart part;
        part = restTemplate.getForObject(basePartResourceURL, PcPart.class);

        return part;
    }

    /*
    public ResponseEntity<String> addPart(String id, PcPart part){
        boolean exists = false;
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article.getId().equals(id)) {
                exists = true;
                List<PcPart> list = article.getParts();
                list.add(part);
                article.setParts(list);
                //return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        if(!exists){
            throw new ArticleNotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
*/

 /*   @Value("${parts-service}")
    private String basePartResourceURL="http://external:80/parts";

    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;
    private HttpEntity<Object> httpEntity;

    public ExternalService() {
        this.restTemplate = new RestTemplate();
        this.httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    public PcPart getPcPart(String id) {
        return getArticlePcPart(id);
    }

    public List<PcPart> getPcParts(String ArticleId) {
        return getArticlePcParts(ArticleId);
    }

    public PcPart addPcPart(PcPart PcPart) {
        return addArticlePcPart(PcPart);
    }

    public List<PcPart> addPcParts(List<PcPart> PcPart) {
        return addArticlePcParts(PcPart);
    }

    public PcPart putPcPart(String PcPartId, PcPart PcPart) {
        return updatePcPart(PcPartId, PcPart);
    }

    public void deletePcPart(String PcPartId) {
        removePcPart(PcPartId);
    }


    private PcPart getArticlePcPart(String PcPartId) {
        this.httpEntity = new HttpEntity<>("body", httpHeaders);
        ResponseEntity<PcPart> PcPartResponse = null;
        try {
            PcPartResponse = restTemplate.exchange(basePartResourceURL+PcPartId, HttpMethod.GET, httpEntity, PcPart.class);
        } catch (HttpClientErrorException e) {
            handleError(e, PcPartNotFoundException.createWith(PcPartId));
        }
        return PcPartResponse.getBody();
    }

    private void handleError(HttpClientErrorException e, PcPartNotFoundException with) {
        switch (e.getStatusCode()) {
            case NOT_FOUND:
                throw with;
            case INTERNAL_SERVER_ERROR:
                throw ExternalApiException.create();
        }
    }

    private List<PcPart> getArticlePcParts(String ArticleId) {
        this.httpEntity = new HttpEntity<>("body", httpHeaders);
        ResponseEntity<Response> serverResponse = null;
        try {
            serverResponse = restTemplate.exchange(basePartResourceURL, HttpMethod.GET, httpEntity, Response.class);
        } catch (HttpClientErrorException e) {
            handleError(e, PcPartNotFoundException.createWith(ArticleId));
        }
        List<PcPart> PcParts = Arrays.asList(serverResponse.getBody().data);
        return PcParts.stream().filter(PcPart -> String.valueOf(PcPart.getId()).equals(ArticleId)).collect(Collectors.toList());
    }

    private PcPart addArticlePcPart(PcPart PcPart) {
        PcPart.generateId();
        this.httpEntity = new HttpEntity<>(PcPart, httpHeaders);
        ResponseEntity<ResponseSingle> serverRespones = null;
        try {
            serverRespones = restTemplate.exchange(basePartResourceURL, HttpMethod.POST, httpEntity, ResponseSingle.class);
        } catch (HttpClientErrorException e) {
            handleError(e, PcPartNotFoundException.create());
        }
        return serverRespones.getBody().data;
    }

    private List<PcPart> addArticlePcParts(List<PcPart> PcParts) {
        List<PcPart> postedPcParts = new ArrayList<>();
        postedPcParts.forEach(PcPart -> postedPcParts.add(addArticlePcPart(PcPart)));
        if (postedPcParts.isEmpty())
            throw ExternalApiException.create();
        return postedPcParts;
    }

    private PcPart updatePcPart(String PcPartId, PcPart PcPart) {
        this.httpEntity = new HttpEntity<>(PcPart, httpHeaders);
        ResponseEntity<PcPart> PcPartsResponse = null;
        try {
            PcPartsResponse = restTemplate.exchange(basePartResourceURL+"/"+PcPartId,HttpMethod.PUT, httpEntity, PcPart.class);
        } catch (HttpClientErrorException e) {
            handleError(e, PcPartNotFoundException.createWith(PcPartId));
        }
        return PcPartsResponse.getBody();
    }

    private void removePcPart(String PcPartId) {
        this.httpEntity = new HttpEntity<>("body", httpHeaders);
        try {
            restTemplate.exchange(basePartResourceURL + "/" + PcPartId, HttpMethod.DELETE, httpEntity, PcPart.class);
        }
        catch (HttpClientErrorException e) {
            handleError(e, PcPartNotFoundException.createWith(PcPartId));
        }
    }*/
}
