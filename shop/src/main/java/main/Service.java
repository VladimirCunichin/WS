package main;

import model.Article;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@org.springframework.stereotype.Service

public class Service {

    List<Article> articleList = new ArrayList<Article>(Arrays.asList(
            new Article("1","article1","Desc1"),
            new Article("2","article3","Desc2"),
            new Article("3","article2","Desc3")
            ));

    public void addArticle(Article article) {
        articleList.add(article);
    }

    public List<Article> getAllArticles(){
        return articleList;
    }
    public Article getArticle(String id){
        return  articleList.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }
    public void updateArticle(Article art,String id){
        for(int i =0; i < articleList.size();i++){
            Article article = articleList.get(i);
            if(article.getId().equals(id)){
                articleList.set(i,art);
            }
        }
    }

    public void deleteArticle(String id) {
        articleList.removeIf(t-> t.getId().equals(id));
    }
}
