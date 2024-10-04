package fr.eni.projetencheres.bo;

public class Image {

	private String picture;
	private Article article;

	public Image() {
	}

	public Image(String picture, Article article) {
		this.picture = picture;
		this.article = article;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Image [picture=" + picture + ", article=" + article + "]";
	}
}