package br.renandev.com.validacoes.validacao.senha

import java.util.regex.Pattern

class Senha(){

    /* Padrão
     * Pelo menos 8 caracteres
     * 1 - Caracter Maiúsculo
     * 1 - Caracter Minúsculo
     * 1 - pelo menos um dígito
     * 1 - Pelo menos um caracter especial
     */

    // Verifica se a senha tem pelo menos um caracter especial
    fun existCharEspecial(Senha: String): Boolean {
        //val regex = "[!@#\$%¨&*()_+,.;~´']*".toRegex()
        return  Pattern.compile(".*[!@#\$%^&*]").matcher(Senha).find()
    }

    // Verifica se a senha tem pelo menos 1 Digito
    fun existCharDigito(Senha: String): Boolean {
        //val regex = "[!@#\$%¨&*()_+,.;~´']*".toRegex()
        return  Senha.chars().anyMatch(Character::isDigit)
    }

    // Verifica se a senha tem mais de 8 Caracteres
    fun tamanhoSenha(Senha: String): Boolean {
        return  Senha.length >= 8
    }

    //verifica se existe pelo menos uma letra Maiúscula
    fun existOneUpper(Senha: String): Boolean {
        //return Senha != Senha.uppercase(Locale.getDefault())
        return Senha.chars().anyMatch(Character::isUpperCase)
    }

    //verifica se existe pelo menos uma letra Minúscula
    fun existOneLower(Senha: String): Boolean {
        //return Senha != Senha.uppercase(Locale.getDefault())
        return Senha.chars().anyMatch(Character::isLowerCase)
    }

}