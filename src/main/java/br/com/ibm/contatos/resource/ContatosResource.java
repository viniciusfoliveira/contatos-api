package br.com.ibm.contatos.resource;


import br.com.ibm.contatos.model.Contato;
import br.com.ibm.contatos.resource.exception.StandardError;
import br.com.ibm.contatos.services.ContatosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contatos")
@Api(value = "API REST Contatos")
@CrossOrigin(origins = "*")
public class ContatosResource {

    @Autowired
    private ContatosService contatosService;

    @PostMapping()
    @ApiOperation(value = "Realiza o cadastro de um novo contato")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Realiza o cadastro de um novo contato"),
            @ApiResponse(code = 422, message = "Erro ao enviar alguma propriedade do body"),
    })
    public ResponseEntity<Contato> save(@RequestBody @Valid Contato c){

          Contato contatos = contatosService.salvar(c);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                 .path("/{id}")
                .buildAndExpand(contatos.getId()).toUri();

        return ResponseEntity.created(uri).body(c);
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Realiza a alteração de um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Realiza a alteração de um produto"),
            @ApiResponse(code = 422, message = "Erro ao enviar alguma propriedade do body"),
            @ApiResponse(code = 404, message = "Id contatos não encontrado"),
            @ApiResponse(code= 400,  message = "Problema na formatação da url ou conversão de tipo")
    })
    public ResponseEntity<Object> update (@PathVariable("id") Long id, @Valid @RequestBody Contato c, HttpServletRequest rs){

         if(contatosService.getPorId(id).isPresent()){
             c.setId(contatosService.getPorId(id).get().getId());
             Contato contatos = contatosService.salvar(c);
             return ResponseEntity.ok(contatos);
         }
         return ResponseEntity.of(Optional.of(new StandardError(HttpStatus.NOT_FOUND.value(),
                 "Id contato não encontrado",
                 rs.getRequestURI())));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Realiza a consulta de um contato especifico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Realiza a consulta de um contato especifico"),
            @ApiResponse(code = 404, message = "Id contatos não encontrado"),
            @ApiResponse(code= 400,  message = "Problema na formatação da url ou conversão de tipo")
    })
    public ResponseEntity<Object> getPorId(@Valid @PathVariable("id") Long id, HttpServletRequest rs){

          if(contatosService.getPorId(id).isPresent())
              return ResponseEntity.ok(contatosService.getPorId(id).get());


        return ResponseEntity.of(Optional.of(new StandardError(HttpStatus.NOT_FOUND.value(),
                "Id contato não encontrado",
                rs.getRequestURI())));
    }

    @GetMapping()
    @ApiOperation(value = "Retorna a consulta de todos os contatos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a consulta de todos os contatos")
    })
    public List<Contato> getAll(){
        return contatosService.getAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Realiza a exclusão de um contato")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Realiza a exclusão de um contato"),
            @ApiResponse(code = 404, message = "Id contatos não encontrado"),
            @ApiResponse(code= 400,  message = "Problema na formatação da url ou conversão de tipo")
    })
    public ResponseEntity delete (@Valid @PathVariable("id") Long id, HttpServletRequest rs){

            if(contatosService.delete(id))
                return ResponseEntity.ok().build();

        return ResponseEntity.of(Optional.of(new StandardError(HttpStatus.NOT_FOUND.value(),
                "Id contato não encontrado",
                rs.getRequestURI())));
    }
}
