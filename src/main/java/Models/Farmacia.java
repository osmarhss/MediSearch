package Models;

import java.util.List;

/**
 *
 * @author Ludmila
 */
public class Farmacia {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private List<Medicamento> medicamentoList;

    public Farmacia( int id, String nome, String endereco, String telefone){
        this.id = id;
        this.endereco = endereco;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Farmacia(String nome, String endereco, String telefone){
        this.endereco = endereco;
        this.nome = nome;
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

    public List<Medicamento> getMedicamentoList() {
        return medicamentoList;
    }

    public void setMedicamentoList(List<Medicamento> medicamentoList) {
        if(medicamentoList == null)
            this.medicamentoList = null;

        this.medicamentoList = medicamentoList;
    }
}
