package co.persistencia;

import co.entidades.RegistroHorasDetallado;
import co.interfaces.persistencia.IPersistenciaRegistroHorasDetallado;
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
public class PersistenciaRegistroHorasDetallado implements IPersistenciaRegistroHorasDetallado {

    @PersistenceContext(unitName = "ControlTiemposDavLom")
    private EntityManager em;

    @Override
    public List<RegistroHorasDetallado> consultarDetalleRegistroHoras(String condiciones) {
        String consulta = "SELECT RH.ID, to_char(RH.FECHA, 'dd/mm/yyyy') FECHA, EM.EMPLOYEE_NAME NOMBRE_EMPLEADO, "
                + "AC.ACTIVIDAD, RH.HORA_INICIAL, RH.HORA_FINAL, "
                + "RH.TOTAL_HORAS, RH.PORCENTAJE_REGISTRADO, "
                + "RH.OBSERVACIONES "
                + "FROM REGISTRO_HORAS RH, EMPLEADOS EM, ACTIVIDADES AC, ASIGNACIONES ASI "
                + "WHERE RH.ASIGNACION = ASI.ID "
                + "AND ASI.ID_EMPLEADO = EM.EMPLOYEE "
                + "AND ASI.ID_ACTIVIDAD = AC.ID "
                + "AND ASI.ID_EMPLEADO != 0 " + condiciones
                + "ORDER BY RH.FECHA ASC";
        Query query = em.createNativeQuery(consulta, "RegistroHorasDetallado");
        return query.getResultList();
    }
}
