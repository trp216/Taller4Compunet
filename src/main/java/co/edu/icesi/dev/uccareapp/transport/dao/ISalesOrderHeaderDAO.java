package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesorderheader;

public interface ISalesOrderHeaderDAO {
    void save(Salesorderheader entity);
    Salesorderheader update(Salesorderheader entity);
    void delete(Salesorderheader entity);
    Salesorderheader findById(Integer id);
    List<Salesorderheader> findAll();
}
