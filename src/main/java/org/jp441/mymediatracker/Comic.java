package org.jp441.mymediatracker;

import java.util.ArrayList;

public class Comic extends Book{
    private int issueNo;

    public Comic(){}

    public Comic(
            String name, ArrayList<String> genre,
            String author, String publisher, int issueNo, double rating){
        super(name, genre, author, publisher, rating);
        this.issueNo = issueNo;
    }

    public int getIssueNo(){
        return issueNo;
    }

    public void setIssueNo(int issueNo){
        this.issueNo = issueNo;
    }

    @Override
    public String toString(){
        return super.toString() + "\nIssueNo: " + issueNo;
    }
}
