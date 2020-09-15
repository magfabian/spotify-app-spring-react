package com.codecool.spotify.model;

public class Card {

   public String id;
   public String imageUrl;
   public String onClickUrl;
   public String header;
   public String footer;
   public String footerUrl;

   public Card(String id, String imageUrl, String onClickUrl, String header, String footer, String footerUrl) {
      this.id = id;
      this.imageUrl = imageUrl;
      this.onClickUrl = onClickUrl;
      this.header = header;
      this.footer = footer;
      this.footerUrl = footerUrl;
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

   public void setId(String id) {
      this.id = id;
   }
}
