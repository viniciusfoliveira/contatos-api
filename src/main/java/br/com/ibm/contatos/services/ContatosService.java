package br.com.ibm.contatos.services;

import br.com.ibm.contatos.model.Contato;
import br.com.ibm.contatos.repository.ContatosRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatosService {

    @Autowired
    private ContatosRespository contatosRespository;

    public Contato salvar(Contato c){
         return contatosRespository.save(c);
    }

    public Optional<Contato> getPorId (Long id){
        Optional<Contato> contatos = contatosRespository.findById(id);
        return contatos;
    }

    public List<Contato> getAll(){
        return contatosRespository.findAll();
    }

    public boolean delete(Long id){
        if(getPorId(id).isPresent()){
            contatosRespository.deleteById(id);
            return true;
        }
        return false;
    }
}
