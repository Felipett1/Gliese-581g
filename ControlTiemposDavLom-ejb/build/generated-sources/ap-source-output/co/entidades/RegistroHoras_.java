package co.entidades;

import co.entidades.Asignaciones;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-07-29T15:37:13")
@StaticMetamodel(RegistroHoras.class)
public class RegistroHoras_ { 

    public static volatile SingularAttribute<RegistroHoras, Long> id;
    public static volatile SingularAttribute<RegistroHoras, BigDecimal> horaInicial;
    public static volatile SingularAttribute<RegistroHoras, BigDecimal> horaFinal;
    public static volatile SingularAttribute<RegistroHoras, Date> fecha;
    public static volatile SingularAttribute<RegistroHoras, String> observaciones;
    public static volatile SingularAttribute<RegistroHoras, BigDecimal> porcentajeRegistrado;
    public static volatile SingularAttribute<RegistroHoras, Asignaciones> asignacion;
    public static volatile SingularAttribute<RegistroHoras, BigDecimal> totalHoras;

}