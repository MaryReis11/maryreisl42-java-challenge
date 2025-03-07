package br.com.Loja.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TabelaDeErros {
	
	VALIDACAO(HttpStatus.BAD_REQUEST, "1001-1000", "Dados de requisição inválidos"),
    PRODUTO_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "1001-2000", "Produto não encontrado"),
    CATEGORIA_NAO_ENCONTRADA(HttpStatus.NOT_FOUND, "1001-2100", "Categoria não encontrada"),
    CARRINHO_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "1001-2200", "Carrinho não encontrado"),
    NOME_CATEGORIA_JA_CADASTRADO(HttpStatus.PRECONDITION_FAILED, "1001-3000", "Nome da categoria já cadastrado"),
    CATEGORIA_ASSOCIADA_PRODUTO(HttpStatus.PRECONDITION_FAILED, "1001-3100", "Categoria associada a um produto"),
    PRODUTO_NO_CARRINHO_NAO_REMOVIVEL(HttpStatus.PRECONDITION_FAILED, "1001-3200", "Produto no carrinho não pode ser removido"),
    APENAS_PRODUTOS_COM_CATEGORIA_NO_CARRINHO(HttpStatus.PRECONDITION_FAILED, "1001-3300", "Apenas produtos associados a uma categoria podem ser adicionados ao carrinho"),
    SISTEMA(HttpStatus.INTERNAL_SERVER_ERROR, "1001-5000", "Erro interno no servidor");

    private final HttpStatus codigoHttp;
    private final String codigoDeErro;
    private final String mensagem;

    TabelaDeErros(HttpStatus codigoHttp, String codigoDeErro, String mensagem) {
        this.codigoHttp = codigoHttp;
        this.codigoDeErro = codigoDeErro;
        this.mensagem = mensagem;
    }

    public HttpStatus getCodigoHttp() {
        return codigoHttp;
    }

    public String getCodigoDeErro() {
        return codigoDeErro;
    }

    public String getMensagem() {
        return mensagem;
    }

}
