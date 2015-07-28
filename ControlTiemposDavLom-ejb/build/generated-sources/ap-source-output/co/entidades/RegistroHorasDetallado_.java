package co.entidades;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-07-28T10:57:35")
@StaticMetamodel(RegistroHorasDetallado.class)
public class RegistroHorasDetallado_ { 

    public static volatile SingularAttribute<RegistroHorasDetallado, BigInteger> id;
    public static volatile SingularAttribute<RegistroHorasDetallado, BigDecimal> horaInicial;
    public static volatile SingularAttribute<RegistroHorasDetallado, BigDecimal> horaFinal;
    public static volatile SingularAttribute<RegistroHorasDetallado, String> fecha;
    public static volatile SingularAttribute<RegistroHorasDetallado, String> nombreEmpleado;
    public static volatile SingularAttribute<RegistroHorasDetallado, String> actividad;
    public static volatile SingularAttribute<RegistroHorasDetallado, BigDecimal> porcentajeRegistrado;
    public static volatile SingularAttribute<RegistroHorasDetallado, String> observaciones;
    public static volatile SingularAttribute<RegistroHorasDetallado, BigDecimal> totalHoras;

}