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

   public String getImageUrl() {
      return imageUrl;
   }
}
