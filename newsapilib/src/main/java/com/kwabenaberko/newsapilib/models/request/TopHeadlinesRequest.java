package com.kwabenaberko.newsapilib.models.request;

/**
 * Created by Kwabena Berko on 5/19/2018.
 */

public class TopHeadlinesRequest{
    private String category, sources, q, pageSize, page, country, language;

    private TopHeadlinesRequest(Builder builder){
        this.category = builder.category;
        this.sources = builder.sources;
        this.q = builder.q;
        this.pageSize = builder.pageSize;
        this.page = builder.page;
        this.country = builder.country;
        this.language = builder.language;
    }

    public String getCategory() {
        return category;
    }



    public String getSources() {
        return sources;
    }


    public String getQ() {
        return q;
    }


    public String getPageSize() {
        return pageSize;
    }


    public String getPage() {
        return page;
    }


    public String getCountry() {
        return country;
    }



    public String getLanguage() {
        return language;
    }


    public static class Builder{
        private String q, category, sources, country, language, pageSize, page;


        public Builder q(String q){
            this.q = q;
            return this;
        }

        public Builder category(String category){
            this.category = category;
            return this;
        }

        public Builder sources(String sources){
            this.sources = sources;
            return this;
        }

        public Builder country(String country){
            this.country = country;
            return this;
        }

        public Builder language(String language){
            this.language = language;
            return this;
        }

        public Builder pageSize(int pageSize){
            this.pageSize = String.valueOf(pageSize);
            return this;
        }

        public Builder page(int page){
            this.page = String.valueOf(page);
            return this;
        }

        public TopHeadlinesRequest build(){
            return new TopHeadlinesRequest(this);
        }
    }
}
