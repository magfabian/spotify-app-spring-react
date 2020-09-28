package com.codecool.spotify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@MappedSuperclass
public abstract class Card {

   @Id
   @GeneratedValue
   private Long id;

   public String spotifyId;
   public String imageUrl;
   public String onClickUrl;
   public String header;
   public String footer;
   public String footerUrl;

}
