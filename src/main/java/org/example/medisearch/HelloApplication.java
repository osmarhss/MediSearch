package org.example.medisearch;

import DAOs.FarmaciaDao;
import DAOs.LaboratorioDao;
import DAOs.MedicamentoDao;
import DbConnection.DatabaseManager;
import Models.Farmacia;
import Models.Laboratorio;
import Models.Medicamento;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){

        LaboratorioDao laboratorioDao = new LaboratorioDao();
        MedicamentoDao medicamentoDao = new MedicamentoDao();
        FarmaciaDao farmaciaDao = new FarmaciaDao();
        DatabaseManager.getConnection();
//        Laboratorio laboratorio = new Laboratorio("Ems", "2124m242", "jfwejhf","hsafhshd");
//        Laboratorio laboratorio2 = new Laboratorio("Emash", "2124m243", "jfwejhf","hsfhsf");
//
//        laboratorioDao.adicionar(laboratorio);
//        laboratorioDao.adicionar(laboratorio2);
//        System.out.println("Laboratorio adicionado com sucesso!");
//
//        Medicamento medicamentoAtual = new Medicamento("Wnsa", "2141465", "sajdjasdj", "dsfhewhf", 25);
//        Medicamento medicamentoAtual2 = new Medicamento("Wnsa", "2141465", "sajasfdjasdj", "dsfhasfewhf", 40);
//
//        medicamentoDao.adicionar(medicamentoAtual);
//        medicamentoDao.adicionar(medicamentoAtual2);
//        System.out.println("Medicamento adicionado com sucesso!");
//
//        Farmacia farmacia = new Farmacia("aRAUJO", "23152", "EWHFHWEHF", "HFHWEF");
//        Farmacia farmacia2 = new Farmacia("aRAUJOAA", "2315231", "EWHFHWEHFDFASF", "HFHWEASFF");
//        farmaciaDao.adicionar(farmacia);
//        farmaciaDao.adicionar(farmacia2);
//
//        var listaMedicamentos = medicamentoDao.obterTodos();
//        listaMedicamentos.forEach(System.out::println);
//
//        var listaLaboratorios = laboratorioDao.obterTodos();
//        listaLaboratorios.forEach(System.out::println);
//
//        var listaFarmacias = farmaciaDao.obterTodos();
//        listaFarmacias.forEach(System.out::println);
//
//        var med1 = medicamentoDao.obterPorId(1);
//        var med2 = medicamentoDao.obterPorId(2);
//        System.out.println("Medicamento obtido por id:");
//        System.out.println(med1);
//
//        var lab1 = laboratorioDao.obterPorId(1);
//        var lab2 = laboratorioDao.obterPorId(2);
//        System.out.println("Laboratorio obtido por id:");
//        System.out.println(lab1);
//
//        var far1 = farmaciaDao.obterPorId(1);
//        var far2 = farmaciaDao.obterPorId(2);
//        System.out.println("Farmacia obtido por id:");
//        System.out.println(far1);
//
//        med1.setPrecoSugerido(60);
//        med1.setSubstancia("hjdsfjhdsgfjsh");
//        medicamentoDao.atualizar(med1);
//        System.out.println("Medicamento atualizado com sucesso!");
//
//        medicamentoDao.deletar(med2.getId());
//        System.out.println("Medicamento deletado com sucesso!");
//
//        lab1.setNome("AWS");
//        laboratorioDao.atualizar(lab1);
//        System.out.println("Laboratorio atualizado com sucesso!");
//
//        laboratorioDao.deletar(lab2.getId());
//        System.out.println("Laboratorio deletado com sucesso!");
//
//        far1.setNome("Pacheco");
//        farmaciaDao.atualizar(far1);
//        System.out.println("Farmacia atualizado com sucesso!");
//
//        farmaciaDao.deletar(far2.getId());
//        System.out.println("Farmacia deletado com sucesso!");
//
//
//
        launch();
        DatabaseManager.closeConnection();
    }
}