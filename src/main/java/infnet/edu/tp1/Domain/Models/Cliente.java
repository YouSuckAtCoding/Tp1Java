package infnet.edu.tp1.Domain.Models;

import infnet.edu.tp1.Contracts.CreateClienteRequest;
import infnet.edu.tp1.Contracts.UpdateClienteRequest;
import infnet.edu.tp1.Domain.Primitives.EntityRoot;
import jakarta.persistence.Entity;

@Entity
public class Cliente extends EntityRoot {

    public String name;
    public String email;
    public boolean invalid= false;

    public Cliente(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Cliente()
    {
    }

    public Cliente MapToCliente(CreateClienteRequest request)
    {

        var result = new Cliente();
        if(request.getEmail() == null || request.getEmail() == null)
        {
            result.invalid = true;
            return result;
        }
            
        result.name = request.getName();
        result.email = request.getEmail();

        return result;
    }
    
    public Cliente MapToCliente(UpdateClienteRequest request)
    {

        var result = new Cliente();
        if(request.getId() == 0 || request.getEmail() == null || request.getEmail() == null)
        {
            result.invalid = true;
            return result;
        }
        
        result.id = request.getId();
        result.name = request.getName();
        result.email = request.getEmail();

        return result;
    }
    
    


}
