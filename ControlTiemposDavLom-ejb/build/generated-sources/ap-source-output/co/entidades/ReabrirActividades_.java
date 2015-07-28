package co.entidades;

import co.entidades.Actividades;
import co.entidades.Causas;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-07-28T16:58:51")
@StaticMetamodel(ReabrirActividades.class)
public class ReabrirActividades_ { 

    public static volatile SingularAttribute<ReabrirActividades, BigInteger> id;
    public static volatile SingularAttribute<ReabrirActividades, Date> fechaFinal;
    public static volatile SingularAttribute<ReabrirActividades, String> observacion;
    public static volatile SingularAttribute<ReabrirActividades, BigDecimal> porcentajeRetrabajo;
    public static volatile SingularAttribute<ReabrirActividades, BigDecimal> horasEstimadas;
    public static volatile SingularAttribute<ReabrirActividades, String> estado;
    public static volatile SingularAttribute<ReabrirActividades, Date> fechaInicial;
    public static volatile SingularAttribute<ReabrirActividades, Actividades> actividad;
    public static volatile SingularAttribute<ReabrirActividades, Causas> causa;

}