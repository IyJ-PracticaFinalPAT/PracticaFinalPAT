package com.IyJ.PracticaFinal.controller;

import java.util.List;

import com.IyJ.PracticaFinal.model.Cliente;
import com.IyJ.PracticaFinal.service.ClienteService;
import com.IyJ.PracticaFinal.joins.ClienteHistorialJoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public ResponseEntity<Iterable<Cliente>> retreiveClientes(@RequestParam(required=false) String apellidos){
        Iterable<Cliente> response = clienteService.retreiveClientes(apellidos);
        return ResponseEntity.ok().body(response);
    }

    // /api/v1/clientes/?apellidos=de Clemente Fernandez-Picazo

    @GetMapping("/clientes/{dni}/")
    public ResponseEntity<Cliente> retreiveCliente(@PathVariable String dni){
        Cliente response = clienteService.retreiveCliente(dni);
        if(response != null){
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    // /api/v1/clientes/02634832K/

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        cliente.setId(null);
        Boolean response = clienteService.createCliente(cliente);
        Boolean response_validacion = clienteService.validarDniTelefono(cliente);
        if(response && response_validacion){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
            
    }

    @DeleteMapping("/clientes/{dni}/")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable String dni){
        clienteService.deleteCliente(dni);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/clientes/{dni}/")
    public ResponseEntity<Cliente> updateCliente(@PathVariable String dni, @RequestBody Cliente cliente){
        cliente.setId(null);
        Cliente newCliente = clienteService.updateCliente(dni, cliente);
        if (newCliente == null){
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok().body(newCliente);
        }
    }

    @GetMapping("/join/clientes/historiales")
    public ResponseEntity<List<ClienteHistorialJoin>> retrieveClienteHistorial() {
        List<ClienteHistorialJoin> clienteHistorial = clienteService.retreiveClienteHistorial();
        return ResponseEntity.ok().body(clienteHistorial);
    }

}
