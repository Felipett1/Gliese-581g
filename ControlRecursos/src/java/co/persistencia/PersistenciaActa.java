package co.persistencia;

import co.entidades.Acta;
import co.interfaces.persistencia.IPersistenciaActa;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PersistenciaActa implements IPersistenciaActa {

    @PersistenceContext(unitName = "ControlRecursosPU")
    private EntityManager em;

    @Override
    public boolean registrarActa(Acta acta) {
        try {
            if (em.merge(acta) != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error PersistenciaActa.registrarActa: " + e);
            return false;
        }
    }

    @Override
    public boolean validarNumeroActa(BigDecimal numeroActa) {
        try {
            Query query = em.createQuery("SELECT COUNT(a) FROM ACTA a WHERE a.numeroActa = :numeroActa");
            query.setParameter("numeroActa", numeroActa);
            return (Long) query.getSingleResult() > 0;
        } catch (Exception e) {
            System.out.println("Error PersistenciaActa.validarNumeroActa: " + e);
            return false;
        }
    }
}
