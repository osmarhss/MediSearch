package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ludmila
 */
public class Farmacia {
    private int id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;

    public Farmacia(int id, String nome, String cnpj, String endereco, String telefone){
        this.id = id;
        this.endereco = endereco;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    public Farmacia(String nome, String cnpj, String endereco, String telefone){
        this.endereco = endereco;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "Farmacia{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
