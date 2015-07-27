package co.persistencia;

import co.entidades.Cronograma;
import co.interfaces.persistencia.IPersistenciaCronograma;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 908036
 */
@Singleton
public class PersistenciaCronograma implements IPersistenciaCronograma{
    
    @PersistenceContext(unitName = "ControlTiemposDavLom")
    private EntityManager em;
    
    //OBSOLETO
    @Override
    public List<Cronograma> estadoCronograma(BigInteger id){
        String consulta = "SELECT T.* FROM TABLE (estructuraCronograma(?)) T ORDER BY T.ID_UNICO ASC";
        Query query = em.createNativeQuery(consulta, "CronogramaDetallado");
        query.setParameter(1, id);
        List<Cronograma> listaCronograma = query.getResultList();
        return listaCronograma;
    }
    
    @Override
    public List<Cronograma> cronogramaGeneral(){
        String consulta = "SELECT * FROM ESTADO_CRONOGRAMA";
        Query query = em.createNativeQuery(consulta, "CronogramaDetallado");
        List<Cronograma> listaCronograma = query.getResultList();
        return listaCronograma;
    }
    
    @Override
    public Cronograma estadisticasCronogramaGeneral(){
        try {
            String consulta = "SELECT * FROM ESTADO_CRONOGRAMA WHERE ACTIVIDAD_PADRE IS NULL";
            Query query = em.createNativeQuery(consulta, "CronogramaDetallado");
            Cronograma estadoCronograma = (Cronograma) query.getSingleResult();
            return estadoCronograma;   
        } catch (Exception e) {
            System.out.println("Error:  PersistenciaCronograma.estadisticasCronogramaGeneral: " + e);
            return null;
        }
    }
    
}
