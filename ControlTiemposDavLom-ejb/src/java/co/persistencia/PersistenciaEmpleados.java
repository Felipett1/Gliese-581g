/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.persistencia;

import co.entidades.Empleados;
import co.interfaces.persistencia.IPersistenciaEmpleados;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 908034
 */
@Stateless
public class PersistenciaEmpleados implements IPersistenciaEmpleados {

    @PersistenceContext(unitName = "ControlTiemposDavLom")
    private EntityManager entityManager;

    
    @Override
    public List<Empleados> buscarEmpleados() {
        Query query = entityManager.createQuery("SELECT a FROM Empleados a WHERE a.employee != 0 AND a.assignmentStatus = 'Activo'");
        return query.getResultList();
    }
    
    @Override
    public Empleados obtenerEmpleado(BigInteger idEmpleado){
        Query query = entityManager.createQuery("SELECT e FROM Empleados e WHERE e.employee = :idEmpleado");
        query.setParameter("idEmpleado", idEmpleado);
        return (Empleados) query.getSingleResult();
    }
}
