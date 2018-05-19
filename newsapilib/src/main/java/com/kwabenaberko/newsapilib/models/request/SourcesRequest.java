package com.kwabenaberko.newsapilib.models.request;

/**
 * Created by Kwabena Berko on 5/19/2018.
 */

public class SourcesRequest{
    private String category, language, country;

    private SourcesRequest(Builder builder){
        this.category = builder.category;
        this.language = builder.language;
        this.country = builder.country;
    }

    public String getCategory() {
        return category;
    }


    public String getLanguage() {
        return language;
    }


    public String getCountry() {
        return country;
    }


    public static class Builder {
        private String category, country, language;

        public Builder(){

        }

        public Builder category(String category){
            this.category = category;
            return this;
        }

        public Builder language(String language){
            this.language = language;
            return this;
        }

        public Builder country(String country){
            this.country = country;
            return this;
        }

        public SourcesRequest build(){
            return new SourcesRequest(this);
        }
    }
}
