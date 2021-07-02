package br.com.ibm.contatos.resource.exception;

public class StandardError {

    private Integer status;
    private String mensagem;
    private String path;

    public StandardError(Integer status, String mensagem, String path) {
        this.status = status;
        this.mensagem = mensagem;
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
