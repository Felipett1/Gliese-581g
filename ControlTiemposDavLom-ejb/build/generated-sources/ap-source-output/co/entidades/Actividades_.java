package co.entidades;

import co.entidades.Actividades;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-07-29T14:34:29")
@StaticMetamodel(Actividades.class)
public class Actividades_ { 

    public static volatile SingularAttribute<Actividades, BigInteger> id;
    public static volatile SingularAttribute<Actividades, Date> fechaFinal;
    public static volatile SingularAttribute<Actividades, String> wbs;
    public static volatile SingularAttribute<Actividades, String> actividad;
    public static volatile SingularAttribute<Actividades, String> bloqueante;
    public static volatile SingularAttribute<Actividades, BigDecimal> porcentajeReal;
    public static volatile SingularAttribute<Actividades, BigDecimal> horasBaseline;
    public static volatile SingularAttribute<Actividades, Actividades> actividadPadre;
    public static volatile SingularAttribute<Actividades, BigDecimal> sprint;
    public static volatile SingularAttribute<Actividades, Date> fechaInicio;
    public static volatile SingularAttribute<Actividades, BigInteger> idUnico;

}