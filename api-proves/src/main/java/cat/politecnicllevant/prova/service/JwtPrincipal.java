package cat.politecnicllevant.prova.service;

import java.util.List;

/**
 * Dades essencials de l'usuari extretes d'un token JWT vàlid.
 *
 * @param username El nom d'usuari.
 * @param roles La llista de rols de l'usuari.
 */
public record JwtPrincipal(String username, List<String> roles) {}
