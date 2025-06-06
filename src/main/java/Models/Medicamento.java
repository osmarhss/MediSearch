package Models;

import java.util.ArrayList;
import java.util.List;

public class Medicamento {
    private int id;
    private String substancia;
    private String classeTerapeutica;
    private String produto;
    private String apresentacao;
    private double preco;
    private int quantidade;
    List<Laboratorio> laboratorios;

    public Medicamento(int id, String substancia, String classeTerapeutica, String produto,String apresentacao,
                       double preco,int quantidade) {
        this.id = id;
        this.substancia = substancia;
        this.produto = produto;
        this.classeTerapeutica = classeTerapeutica;
        this.apresentacao = apresentacao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Medicamento(String substancia, String classeTerapeutica, String produto,String apresentacao,
                       double preco,int quantidade, String laboratorio) {
        this.substancia = substancia;
        this.produto = produto;
        this.classeTerapeutica = classeTerapeutica;
        this.apresentacao = apresentacao;
        this.preco = preco;
        this.quantidade = quantidade;
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
     * @return the substancia
     */
    public String getSubstancia() {
        return substancia;
    }

    /**
     * @param substancia the substancia to set
     */
    public void setSubstancia(String substancia) {
        this.substancia = substancia;
    }

    /**
     * @return the classeTerapeutica
     */
    public String getClasseTerapeutica() {
        return classeTerapeutica;
    }

    /**
     * @param classeTerapeutica the classeTerapeutica to set
     */
    public void setClasseTerapeutica(String classeTerapeutica) {
        this.classeTerapeutica = classeTerapeutica;
    }

    /**
     * @return the produto
     */
    public String getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

    /**
     * @return the apresentacao
     */
    public String getApresentacao() {
        return apresentacao;
    }

    /**
     * @param apresentacao the apresentacao to set
     */
    public void setApresentacao(String apresentacao) {
        this.apresentacao = apresentacao;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public List<Laboratorio> getLaboratorios() {
        return laboratorios;
    }

    public void setLaboratorioId(List<Laboratorio> laboratorios) {
        if(laboratorios == null)
            this.laboratorios = new ArrayList<Laboratorio>();

        this.laboratorios = laboratorios;
    }
}
