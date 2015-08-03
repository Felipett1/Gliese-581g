package co.entidades;

import co.entidades.Actividades;
import co.entidades.Empleados;
import co.entidades.ReabrirActividades;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-08-03T08:41:40")
@StaticMetamodel(Asignaciones.class)
public class Asignaciones_ { 

    public static volatile SingularAttribute<Asignaciones, BigDecimal> id;
    public static volatile SingularAttribute<Asignaciones, String> estado;
    public static volatile SingularAttribute<Asignaciones, Empleados> idEmpleado;
    public static volatile SingularAttribute<Asignaciones, BigDecimal> horasAsignadas;
    public static volatile SingularAttribute<Asignaciones, ReabrirActividades> idReabrirActividades;
    public static volatile SingularAttribute<Asignaciones, Actividades> idActividad;

}