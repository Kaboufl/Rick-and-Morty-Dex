package fr.epsi.laurence.paquereau.rickmortydex.model

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class CharactersResponse(
    val info: Info,
    val results: List<Character>
)
