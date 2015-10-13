package fr.afpa.ecommerce.bean;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class Book extends CommonTable implements Serializable{
    
    private int editorId;
    private int languageId;
    private int taxId;
    private int isbn;
    private Blob image;
    private String title;
    private String subtitle;
    private String summary;
    private int nbPage;
    private Date releaseDate;
    private int edition;
    private float price;

    public Book() {
    }

    public Book(int editorId, int languageId, int taxId, int isbn, Blob image, String title, String subtitle, String summary, int nbPage, Date releaseDate, int edition, float price) {
        this.editorId = editorId;
        this.languageId = languageId;
        this.taxId = taxId;
        this.isbn = isbn;
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.summary = summary;
        this.nbPage = nbPage;
        this.releaseDate = releaseDate;
        this.edition = edition;
        this.price = price;
    }

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getNbPage() {
        return nbPage;
    }

    public void setNbPage(int nbPage) {
        this.nbPage = nbPage;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
    
    
}
