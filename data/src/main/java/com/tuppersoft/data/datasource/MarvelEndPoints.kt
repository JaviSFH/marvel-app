package com.tuppersoft.data.datasource

object MarvelEndPoints {

    const val BASE_URL = "https://gateway.marvel.com/"
    const val PUBLIC_API_KEY = "01561e7185ab0a783e8266367c244c46"
    const val PRIVATE_API_KEY = "95b4555888a6ebec24f0140f13772c07f9e3e140"
    const val characters: String = "/v1/public/characters"

    const val PATH_PARAM_CHARACTER_ID = "characterId"

    const val comicsByCharacter: String = "/v1/public/characters/{$PATH_PARAM_CHARACTER_ID}/comics"
}
