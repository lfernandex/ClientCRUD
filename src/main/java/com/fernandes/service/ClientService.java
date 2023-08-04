package com.fernandes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernandes.dto.ClientDTO;
import com.fernandes.entities.Client;
import com.fernandes.repository.ClientRepository;
import com.fernandes.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
        public ClientDTO findById(Long id){
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client cli = new Client();

        copyDtoToEntity(dto, cli);

        cli = repository.save(cli);

        return new ClientDTO(cli);
    }
    
    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try{
            Client cli = repository.getReferenceById(id);

            copyDtoToEntity(dto, cli);

            cli = repository.save(cli);

            return new ClientDTO(cli);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id){
        try{
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client cli) {
        
        cli.setName(dto.getName());
        cli.setCpf(dto.getCpf());
        cli.setIncome(dto.getIncome());
        cli.setBirthDate(dto.getBirthDate());
        cli.setChildren(dto.getChildren());

    }
    
}
