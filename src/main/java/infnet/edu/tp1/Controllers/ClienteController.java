package infnet.edu.tp1.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import infnet.edu.tp1.Contracts.CreateClienteRequest;
import infnet.edu.tp1.Contracts.UpdateClienteRequest;
import infnet.edu.tp1.Domain.Models.Cliente;
import infnet.edu.tp1.Infrastructure.ClienteRepository;

@RestController
public class ClienteController {

    private static final String Base = "api/cliente";
    private static final String GetEndpoint = Base;
    private static final String GetAllEndpoint = Base + "/all";
    private static final String CreateEndpoint = Base;
    private static final String UpdateEndpoint = Base + "/update";
    private static final String DeleteEndpoint = Base;
    private static final String IdParam = "id";

    @Autowired
    private ClienteRepository _clienteRepository;

    @GetMapping(GetAllEndpoint)
    public ResponseEntity<?> GetAll()
    {
        var result = _clienteRepository.findAll();
        return new ResponseEntity<List<Cliente>>(result, HttpStatus.OK);
    }

    @PostMapping(CreateEndpoint)
    public ResponseEntity<?> CreateCliente(@RequestBody CreateClienteRequest request)
    {
        Cliente cliente = new Cliente();
        cliente = cliente.MapToCliente(request);

        if(cliente.invalid)
        {
            return new ResponseEntity<>("Invalid Object", HttpStatus.BAD_REQUEST);
        }

        _clienteRepository.saveAndFlush(cliente);

        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);

    }

    @PutMapping(UpdateEndpoint)
    public ResponseEntity<?> UpdateCliente(@RequestBody UpdateClienteRequest request)
    {
        Cliente cliente = new Cliente();
        cliente = cliente.MapToCliente(request);

        if(cliente.invalid)
            return new ResponseEntity<>("Invalid Object", HttpStatus.BAD_REQUEST);
        
        if(_clienteRepository.findById(cliente.id).get() == null)
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        

        _clienteRepository.saveAndFlush(cliente);

        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);

    }

    @DeleteMapping(DeleteEndpoint)
    public ResponseEntity<?> DeleteCliente(@RequestParam long id)
    {
        if(_clienteRepository.findById(id).get() == null)
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
            
        _clienteRepository.deleteById(id);

        return new ResponseEntity<>("Deleted", HttpStatus.OK);

    }





}
