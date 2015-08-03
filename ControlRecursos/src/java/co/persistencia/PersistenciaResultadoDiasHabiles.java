package co.persistencia;

import co.entidades.ResultadoDiasHabiles;
import co.interfaces.persistencia.IPersistenciaResultadoDiasHabiles;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PersistenciaResultadoDiasHabiles implements IPersistenciaResultadoDiasHabiles {

    @PersistenceContext(unitName = "ControlRecursosPU")
    private EntityManager em;

    @Override
    public ResultadoDiasHabiles obtenerDiasHabilesEmpleado(Date fechaInicial, BigDecimal horasAsignadas, Long idEmpleado) {
        try {
            Query query = em.createNativeQuery("select * from TABLE(utilidades.OBTENER_DIAS_HABILES(?, ?, ?))", "ResultadoDiasHabiles");
            query.setParameter(1, fechaInicial);
            query.setParameter(2, horasAsignadas);
            query.setParameter(3, idEmpleado);
            return (ResultadoDiasHabiles) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error PersistenciaResultadoDiasHabiles.obtenerDiasHabilesEmpleado: " + e);
            return null;
        }
    }
}
