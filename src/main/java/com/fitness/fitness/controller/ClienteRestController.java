package com.fitness.fitness.controller;

import com.fitness.fitness.entity.Cliente;
import com.fitness.fitness.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**REST API Controller para gestionar Clientes*/

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    /**
     * GET - Listar todos los clientes */
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = clienteService.obtenerTodos();
        
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    /**
     * GET - Buscar cliente por ID
     * URL: GET http://localhost:8080/api/clientes/{id}*/
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.obtenerPorId(id);
        
        if (cliente.isPresent()) {
            return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * POST - Insertar nuevo cliente */
    @PostMapping
    public ResponseEntity<Cliente> insertar(@RequestBody Cliente cliente) {
        try {
            Cliente nuevoCliente = clienteService.guardar(cliente);
            return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * PUT - Editar cliente existente */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteService.obtenerPorId(id);
        
        if (clienteExistente.isPresent()) {
            Cliente clienteActualizado = clienteExistente.get();
            
            // Actualizar campos
            clienteActualizado.setNombre(cliente.getNombre());
            clienteActualizado.setApellido(cliente.getApellido());
            clienteActualizado.setEmail(cliente.getEmail());
            clienteActualizado.setTelefono(cliente.getTelefono());
            clienteActualizado.setDni(cliente.getDni());
            clienteActualizado.setEstado(cliente.getEstado());
            clienteActualizado.setFaltas(cliente.getFaltas());
            
            Cliente clienteGuardado = clienteService.guardar(clienteActualizado);
            return new ResponseEntity<>(clienteGuardado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE - Eliminar cliente por ID */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            Optional<Cliente> cliente = clienteService.obtenerPorId(id);
            
            if (cliente.isPresent()) {
                clienteService.eliminar(id);
                return new ResponseEntity<>("Cliente Eliminado Correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cliente no Encontrado",HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
