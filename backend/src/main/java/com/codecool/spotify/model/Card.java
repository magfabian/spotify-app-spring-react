package com.codecool.spotify.model;

public class Card {

   private String imageUrl;
   private String onClickUrl;
   private String header;
   private String footerUrl;
   private String footer;

   public Card(String imageUrl, String onClickUrl, String header, String footerUrl, String footer) {
      this.imageUrl = imageUrl;
      this.onClickUrl = onClickUrl;
      this.header = header;
      this.footerUrl = footerUrl;
      this.footer = footer;
   }

   public Card() {
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

   public void setOnClickUrl(String onClickUrl) {
      this.onClickUrl = onClickUrl;
   }

   public void setHeader(String header) {
      this.header = header;
   }

   public void setFooterUrl(String footerUrl) {
      this.footerUrl = footerUrl;
   }

   public void setFooter(String footer) {
      this.footer = footer;
   }
}
