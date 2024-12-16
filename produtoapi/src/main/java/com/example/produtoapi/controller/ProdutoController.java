package com.example.produtoapi.controller;


import com.example.produtoapi.model.Produto;
import com.example.produtoapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    @PostMapping
    public void salvar(@RequestBody Produto produto) {
        System.out.println("Produto Recebido " + produto);
        var id = UUID.randomUUID().toString();
        produto.setId(id);
        produtoRepository.save(produto);
    }

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable String id) {
        // Optional<Produto> produto = produtoRepository.findById(id);
        // return produto.isPresent() ? produto.get() : null;
        return produtoRepository.findById(id).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        id = id.trim();
        System.out.println("produto id: " + id);
        produtoRepository.deleteById(id);
        System.out.println("produto deletado");
    }
    @PutMapping("/{id}")
    public void atualizar(@PathVariable String id, @RequestBody Produto produto) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isPresent()) {
            Produto produtoAtualizado = produtoExistente.get();
            produtoAtualizado.setNome(produto.getNome());
            produtoAtualizado.setDescricao(produto.getDescricao());
            produtoAtualizado.setPreco(produto.getPreco());
            produtoRepository.save(produtoAtualizado);

        } else {
            throw new RuntimeException("não achou o produto: " + id);
        }
    }

    @GetMapping
    public List<Produto> buscarProduto(@RequestParam("nome") String nome){
        return produtoRepository.findByNome(nome);
    }
}
