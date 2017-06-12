package com.example.yoon.word;

public class words2 {
    int id;
    String words_name;
    String words_mean;
    String words_memo;
    public boolean select = false;

    public words2(int id, String words_name, String words_mean, String words_memo) {
        super();
        this.id = id;
        this.words_name = words_name;
        this.words_mean = words_mean;
        this.words_memo = words_memo;
    }
}
