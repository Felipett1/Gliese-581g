package co.entidades;

import co.entidades.Actividades;
import co.entidades.Empleados;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-07-28T11:32:09")
@StaticMetamodel(HistoricoBloqueos.class)
public class HistoricoBloqueos_ { 

    public static volatile SingularAttribute<HistoricoBloqueos, BigDecimal> id;
    public static volatile SingularAttribute<HistoricoBloqueos, String> observacion;
    public static volatile SingularAttribute<HistoricoBloqueos, Date> fecha;
    public static volatile SingularAttribute<HistoricoBloqueos, Empleados> idEmpleado;
    public static volatile SingularAttribute<HistoricoBloqueos, Actividades> idActividad;

}