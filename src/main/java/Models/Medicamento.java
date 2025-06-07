package Models;

import java.util.ArrayList;
import java.util.List;

public class Medicamento {
    private int id;
    private String substancia;
    private String classeTerapeutica;
    private String produto;
    private String apresentacao;
    private double precoSugerido;
    List<Laboratorio> laboratorios;

    public Medicamento(int id, String substancia, String classeTerapeutica, String produto, String apresentacao,
                       double precoSugerido) {
        this.id = id;
        this.substancia = substancia;
        this.produto = produto;
        this.classeTerapeutica = classeTerapeutica;
        this.apresentacao = apresentacao;
        this.precoSugerido = precoSugerido;
    }

    public Medicamento(String substancia, String classeTerapeutica, String produto,String apresentacao,
                       double precoSugerido) {
        this.substancia = substancia;
        this.produto = produto;
        this.classeTerapeutica = classeTerapeutica;
        this.apresentacao = apresentacao;
        this.precoSugerido = precoSugerido;
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
    public double getPrecoSugerido() {
        return precoSugerido;
    }

    /**
     * @param precoSugerido the preco to set
     */
    public void setPrecoSugerido(double precoSugerido) {
        this.precoSugerido = precoSugerido;
    }

    public List<Laboratorio> getLaboratorios() {
        return laboratorios;
    }

    public void setLaboratorios(List<Laboratorio> laboratorios) {
        if(this.laboratorios == null)
            this.laboratorios = new ArrayList<Laboratorio>();

        this.laboratorios = laboratorios;
    }

    public void adicionarLaboratorio(Laboratorio lab){
        if(this.laboratorios == null)
            this.laboratorios = new ArrayList<Laboratorio>();

        this.laboratorios.add(lab);
    }
}
