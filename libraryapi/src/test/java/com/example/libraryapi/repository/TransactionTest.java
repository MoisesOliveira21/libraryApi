package com.example.libraryapi.repository;

import com.example.libraryapi.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    TransactionService transactionService;

    /**
     * Commit -> confirmar alterações
     * RollBack -> desfazer as alterações
     */
    @Test
    void simpleTransaction(){

        transactionService.execute();
    }


    @Test
    void transactionManaged(){
        transactionService.atualizacaoSemAtualizar();
    }
}
